package com.ROCSAFE.maven.gpsutilities;

public class WGS84CoordinateUtils {
	
	//For a posssible future Haversine implementation
	protected static double m1 = 111132.92;
	protected static double m2 = -559.82;
	protected static double m3 = 1.175;
	protected static double m4 = -0.0023;
	
	protected static double p1 = 111412.84;
	protected static double p2 = -93.5;
	protected static double p3 = 0.118;
	
	//WGS defining parameters found here: https://en.wikipedia.org/wiki/Geodetic_datum#World_Geodetic_System_1984_(WGS_84)
	
	//semi-major axis
	protected static final double WGSa = 6378137.0;
	//semi-minor axis
	protected static final double WGSb = 6356752.314245;
	//reciprocal of flattening
	protected static final double WGSf = 1 / 298.257223563; 
	
	private final static double TwoPi = 2.0 * Math.PI;
	
	public static double degToRadian(double degrees) {
		return Math.toRadians(degrees);
	}
	
	public static double radianToDeg(double radians) {
		return Math.toDegrees(radians);
	}
	
	/**
	 * @see <a href="https://www.movable-type.co.uk/scripts/latlong-vincenty.html">vincenty reference formula</a>
	 * @param coord - The coordinate from which to extend a distance given in metres
	 * @param distance - The distance in metres to extend from coord
	 * @param initialBearing - The initial azimuth (In land navigation, azimuth is usually denoted alpha and defined as a 
	 * horizontal angle measured clockwise from a north base line or meridian). i.e. in this case azimuth is the angle 
	 * between the start point and the desired latitude
	 * @return A WGS84 coordinate which is distance m away from coord, in the specified bearing
	 * @throws Exception 
	 */
	private static WGS84Coordinate vincentyGeodesicDirect(WGS84Coordinate coord, double distance, double initialBearing) throws Exception {
	    double Phi1 = Math.toRadians(coord.getLat().doubleValue()), Lambda1 = Math.toRadians(coord.getLng().doubleValue());
	    double Alpha1 = Math.toRadians(initialBearing);
	    double s = distance;

		double a = WGSa;
		double b = WGSb;
		double f = WGSf;

		double sinAlpha1 = Math.sin(Alpha1);
		double cosAlpha1 = Math.cos(Alpha1);

		double tanU1 = (1-f) * Math.tan(Phi1), cosU1 = 1 / Math.sqrt((1 + tanU1*tanU1)), sinU1 = tanU1 * cosU1;
		double Sigma1 = Math.atan2(tanU1, cosAlpha1);
		double sinAlpha = cosU1 * sinAlpha1;
		double cosSqAlpha = 1 - sinAlpha*sinAlpha;
		double uSq = cosSqAlpha * (a*a - b*b) / (b*b);
		double A = 1 + uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
		double B = uSq/1024 * (256+uSq*(-128+uSq*(74-47*uSq)));

	    double  cos2SigmaM, sinSigma, cosSigma, DeltaSigma;

	    double Sigma = s / (b*A), SigmaPrime, iterations = 0;
	    do {
	        cos2SigmaM = Math.cos(2*Sigma1 + Sigma);
	        sinSigma = Math.sin(Sigma);
	        cosSigma = Math.cos(Sigma);
	        DeltaSigma = B*sinSigma*(cos2SigmaM+B/4*(cosSigma*(-1+2*cos2SigmaM*cos2SigmaM)-
	            B/6*cos2SigmaM*(-3+4*sinSigma*sinSigma)*(-3+4*cos2SigmaM*cos2SigmaM)));
	        SigmaPrime = Sigma;
	        Sigma = s / (b*A) + DeltaSigma;
	    } while (Math.abs(Sigma-SigmaPrime) > 1e-12 && ++iterations<100);
	    if (iterations >= 100) throw new Exception("Formula failed to converge"); // not possible!

	    double x = sinU1*sinSigma - cosU1*cosSigma*cosAlpha1;
	    double Phi2 = Math.atan2(sinU1*cosSigma + cosU1*sinSigma*cosAlpha1, (1-f)*Math.sqrt(sinAlpha*sinAlpha + x*x));
	    double Lambda = Math.atan2(sinSigma*sinAlpha1, cosU1*cosSigma - sinU1*sinSigma*cosAlpha1);
	    double C = f/16*cosSqAlpha*(4+f*(4-3*cosSqAlpha));
	    double L = Lambda - (1-C) * f * sinAlpha *
	        (Sigma + C*sinSigma*(cos2SigmaM+C*cosSigma*(-1+2*cos2SigmaM*cos2SigmaM)));
	    double Lambda2 = (Lambda1+L+3*Math.PI)%(2*Math.PI) - Math.PI;  // normalise to -180..+180

	    double Alpha2 = Math.atan2(sinAlpha, -x);
	    Alpha2 = (Alpha2 + 2*Math.PI) % (2*Math.PI); // normalise to 0..360

	    return new WGS84Coordinate(Math.toDegrees(Phi2), Math.toDegrees(Lambda2));
//	    return {
//	        point:        new LatLon(Phi2.toDegrees(), Lambda2.toDegrees(), this.datum),
//	        finalBearing: Alpha2.toDegrees(),
//	        iterations:   iterations,
//	    }
	}
	
	//private 
	private static double vincentyGeodesicInverse(WGS84Coordinate from, WGS84Coordinate to) throws Exception {
		
//		double a = WGSa;
//		double b = WGSb;
//		double f = WGSf;
		
		double a = 	WGSa;
		double b = WGSb;
		double f = WGSf;
		
		double phiOne = Math.toRadians(from.getLat().doubleValue());
		double phiTwo = Math.toRadians(to.getLat().doubleValue());

		double lambdaOne = Math.toRadians(from.getLng().doubleValue());
		double lambdaTwo = Math.toRadians(to.getLng().doubleValue());
		
		double L = lambdaTwo - lambdaOne;
		
		double tanU1 = (1-f) * Math.tan(phiOne);
		double cosU1 = 1 / Math.sqrt((1 + tanU1*tanU1));
		double sinU1 = tanU1 * cosU1;
		
		double tanU2 = (1-f) * Math.tan(phiTwo);
		double cosU2 = 1 / Math.sqrt((1 + tanU2*tanU2));
		double sinU2 = tanU2 * cosU2;
		
		double sinLambda;
		double cosLambda;
		double sinSqSigma;
		double sinSigma=0;
		double cosSigma=0;
		double Sigma=0;
		double sinAlpha;
		double cosSqAlpha=0;
		double cos2SigmaM=0;
		double C;
		

		double Lambda = L, LambdaPrime, iterations = 0;
		//System.out.println("Lambda: " + L);
		boolean antimeridian = Math.abs(L) > Math.PI;
		//System.out.println("anitmeridian: " + antimeridian);
	    do {
	        sinLambda = Math.sin(Lambda);
	        cosLambda = Math.cos(Lambda);
	        sinSqSigma = (cosU2*sinLambda) * (cosU2*sinLambda) + (cosU1*sinU2-sinU1*cosU2*cosLambda) * (cosU1*sinU2-sinU1*cosU2*cosLambda);
	        if (sinSqSigma == 0) break; // co-incident points
	        sinSigma = Math.sqrt(sinSqSigma);
	        cosSigma = sinU1*sinU2 + cosU1*cosU2*cosLambda;
	        Sigma = Math.atan2(sinSigma, cosSigma);
	        sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
	        cosSqAlpha = 1 - sinAlpha*sinAlpha;
	        cos2SigmaM = (cosSqAlpha != 0) ? (cosSigma - 2*sinU1*sinU2/cosSqAlpha) : 0; // equatorial line: cosSqAlpha=0 (§6)
	        C = f/16*cosSqAlpha*(4+f*(4-3*cosSqAlpha));
	        LambdaPrime = Lambda;
	        Lambda = L + (1-C) * f * sinAlpha * (Sigma + C*sinSigma*(cos2SigmaM+C*cosSigma*(-1+2*cos2SigmaM*cos2SigmaM)));
	        double iterationCheck = antimeridian ? Math.abs(Lambda)-Math.PI : Math.abs(Lambda);
	        //System.out.println("iterationCheck: " + iterationCheck);
	        if (iterationCheck > Math.PI) throw new Exception("Lambda > pi after " + iterations + " iterations.");
	    } while (Math.abs(Lambda-LambdaPrime) > 1e-12 && ++iterations<1000);
	    
	    
	    if (iterations >= 1000) throw new Exception("Formula failed to converge");

		double uSq = cosSqAlpha * (a*a - b*b) / (b*b);
		double A = 1 + uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
		double B = uSq/1024 * (256+uSq*(-128+uSq*(74-47*uSq)));
		double DeltaSigma = B*sinSigma*(cos2SigmaM+B/4*(cosSigma*(-1+2*cos2SigmaM*cos2SigmaM)-
		        B/6*cos2SigmaM*(-3+4*sinSigma*sinSigma)*(-3+4*cos2SigmaM*cos2SigmaM)));

		double s = b*A*(Sigma-DeltaSigma);

//		double Alpha1 = Math.atan2(cosU2*sinLambda,  cosU1*sinU2-sinU1*cosU2*cosLambda);
//		double Alpha2 = Math.atan2(cosU1*sinLambda, -sinU1*cosU2+cosU1*sinU2*cosLambda);
//
//		Alpha1 = (Alpha1 + 2*Math.PI) % (2*Math.PI); // normalise to 0..360
//		    Alpha2 = (Alpha2 + 2*Math.PI) % (2*Math.PI); // normalise to 0..360
		return s;
	}	

	/**
	 * 
	 * @param from
	 * @param to
	 * @return The distance in metres from WGS84 coordinate 'from' to WGS84 coordinate 'to' 
	 * @throws Exception
	 */
	public static double getDistanceMetresBetweenWGS84(WGS84Coordinate from, WGS84Coordinate to) throws Exception {
		//returns the length of the geodesic from coordinate from to coordinate to
		return vincentyGeodesicInverse(from, to);
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return The latitudinal distance in metres from WGS84 coordinate 'from' to WGS84 coordinate 'to' 
	 * @throws Exception
	 */
	public static double getDistanceMetresLatToOther(WGS84Coordinate from, WGS84Coordinate to) throws Exception {
		//returns the length of the geodesic along a line of latitude of the projection of coordinate from to coordinate to (project to latitude space)
		return vincentyGeodesicInverse(from, new WGS84Coordinate(to.getLat(), from.getLng()));
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return The longitudinal distance in metres from WGS84 coordinate 'from' to WGS84 coordinate 'to' 
	 * @throws Exception
	 */
	public static double getDistanceMetresLngToOther(WGS84Coordinate from, WGS84Coordinate to) throws Exception {
		//returns the length of the geodesic along a line of longitude of the projection of coordinate from to coordinate to (project to longitude space)
		return vincentyGeodesicInverse(from, new WGS84Coordinate(from.getLat(), to.getLng()));
	}
	
	/**
	 * @param from - The point from which to calculate the final GPS coordinate the given distance away
	 * @param distance - The distance at which the return point with be from the provided 'from' coordinate
	 * @param initialBearing - The latitude +- from the 'from' coordinate at which the destination coordiante will lie
	 * @return A GPSCoordinate that lies a distance 'distance' from the provided 'from' coordinate 'initialBearing' degrees lat above or below
	 * @throws Exception
	 */
	public static WGS84Coordinate getGPSCoordGivenDistanceInitialBearing(WGS84Coordinate from, double distance, double initialBearing) throws Exception {
		return vincentyGeodesicDirect(from, distance, initialBearing);
	}
	
	


}

