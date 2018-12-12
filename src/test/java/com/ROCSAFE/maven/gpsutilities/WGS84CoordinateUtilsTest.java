/**
 * 
 */
package com.ROCSAFE.maven.gpsutilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 13383861
 *
 */
public class WGS84CoordinateUtilsTest  {
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@BeforeEach
	protected void setUp() throws Exception {
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@AfterEach
	protected void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#degToRadian(double)}.
	 */
	@Test
	public void testDegToRadian() {
		assertEquals(Math.PI, Math.toRadians(180));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#radianToDeg(double)}.
	 */
	@Test
	public void testRadianToDeg() {
		assertEquals(180.0, Math.toDegrees(Math.PI));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#getDistanceMetresBetweenWGS84(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate, com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetDistanceMetresBetweenWGS84() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(58.64389, 3.07);
		assertEquals(969932.984, WGS84CoordinateUtils.getDistanceMetresBetweenWGS84(p, q),50);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#getDistanceMetresLatToOther(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate, com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetDistanceMetresLatToOther() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(58.64389, 3.07);
		assertEquals(954769.686, WGS84CoordinateUtils.getDistanceMetresLatToOther(p, q),50);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#getDistanceMetresLngToOther(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate, com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetDistanceMetresLngToOther() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(58.64389, 3.07);
		assertEquals(189344.209, WGS84CoordinateUtils.getDistanceMetresLngToOther(p, q),50);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84CoordinateUtils#getGPSCoordGivenDistanceInitialBearing(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate, double, double)}.
	 * @throws Exception 
	 
	public void testGetGPSCoordGivenDistanceInitialBearing() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(-37.95103, 144.9582);
		double initialBearing = 306.8682;
		double distance = 54972.271;
		assertEquals(new WGS84Coordinate(-37.65282, 143.9265), WGS84CoordinateUtils.getGPSCoordGivenDistanceInitialBearing(p, distance, initialBearing));
	}
	*/

}
