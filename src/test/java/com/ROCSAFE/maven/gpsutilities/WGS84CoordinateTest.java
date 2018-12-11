/**
 * 
 */
package com.ROCSAFE.maven.gpsutilities;

import java.math.BigDecimal;

import junit.framework.TestCase;

/**
 * @author David Smyth
 *
 */
public class WGS84CoordinateTest extends TestCase {

	WGS84Coordinate p1;
	WGS84Coordinate p2;
	WGS84Coordinate p3;
	WGS84Coordinate p4;
	WGS84Coordinate p5;
	WGS84Coordinate p6;
	WGS84Coordinate p7;
	BigDecimal p1Lat;
	BigDecimal p1Lng;
	BigDecimal p1Alt;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		p1Lat = new BigDecimal(53.2);
		p1Lng = new BigDecimal(-9.6);
		p1Alt = new BigDecimal(10);
		p1 = new WGS84Coordinate(p1Lat, p1Lng, p1Alt);
		p2 = new WGS84Coordinate(p1Lat, p1Lng);
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLat()}.
	 */
	public void testGetLat() {
		assertEquals(p1.getLat(), p1Lat);
		assertEquals(p2.getLat(), p1Lat);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#setLat(java.math.BigDecimal)}.
	 * @throws Exception 
	 */
	public void testSetLat() throws Exception {
		p1.setLat(new BigDecimal(0.0));
		assertEquals(p1.getLat(), new BigDecimal(0.0));
		p1.setLat(new BigDecimal(-8.1234));
		assertEquals(p1.getLat(), new BigDecimal(-8.1234));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLng()}.
	 * @throws Exception 
	 */
	public void testGetLng() {
		assertEquals(p1.getLng(), p1Lng);
		assertEquals(p2.getLng(), p1Lng);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#setLng(java.math.BigDecimal)}.
	 * @throws Exception 
	 */
	public void testSetLng() throws Exception {
		p1.setLng(new BigDecimal(0.0));
		assertEquals(p1.getLng(), new BigDecimal(0.0));
		p1.setLng(new BigDecimal(-8.1234));
		assertEquals(p1.getLng(), new BigDecimal(-8.1234));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getAlt()}.
	 */
	public void testGetAlt() {
		assertEquals(p1Alt, p1.getAlt());
		assertEquals(new BigDecimal(0.0), p2.getAlt());
		//assertEquals(p2.getAlt(),  );
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#setAlt(java.math.BigDecimal)}.
	 * @throws Exception 
	 */
	public void testSetAltBigDecimal() throws Exception {
		p1.setAlt(new BigDecimal(5.1));
		assertEquals(p1.getAlt(), new BigDecimal(5.1));
		p1.setAlt(new BigDecimal(100.02365));
		assertEquals(p1.getAlt(), new BigDecimal(100.02365));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#toString()}.
	 */
	public void testToString() {
		assertEquals("" + p1.getLat() + ", " + p1.getLng() + ", " + p1.getAlt() + "", p1.toString());
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#WGS84Coordinate()}.
	 */
	/*
	public void testWGS84Coordinate() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#WGS84Coordinate(double, double)}.
	 */
	/*
	public void testWGS84CoordinateDoubleDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#WGS84Coordinate(java.math.BigDecimal, java.math.BigDecimal)}.
	 */
	/*
	public void testWGS84CoordinateBigDecimalBigDecimal() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#WGS84Coordinate(java.lang.Double, java.lang.Double, java.lang.Double)}.
	 */
	/*
	public void testWGS84CoordinateDoubleDoubleDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#WGS84Coordinate(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)}.
	 */
	/*
	public void testWGS84CoordinateBigDecimalBigDecimalBigDecimal() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#equals(java.lang.Object)}.
	 * @throws Exception 
	 */
	public void testEqualsObject() throws Exception {
		assertTrue(p1.equals(new WGS84Coordinate(p1.getLat(), p1.getLng(), p1.getAlt())));
		WGS84Coordinate p1Copy = new WGS84Coordinate();
		p1Copy.setLat(p1.getLat());
		p1Copy.setLng(p1.getLng());
		p1Copy.setAlt(p1.getAlt());
		assertTrue(p1Copy.equals(p1));
		
		WGS84Coordinate p1CopyNoAlt = new WGS84Coordinate(p1.getLat(), p1.getLng());
		assertFalse(p1.equals(p1CopyNoAlt));
		//test not equal to random non-WGSCoordinate object
		assertFalse(p1.equals(""));
		//test does not equal null
		assertFalse(p1Copy.equals(null));
		
		assertFalse(p1.equals(p2));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#clone()}.
	 * @throws Exception 
	 */
	public void testClone() throws Exception {
		WGS84Coordinate p1Clone = p1.clone();
		assertEquals(p1Clone, p1);
		//check that shallow copy not created
		p1Clone.setLat(new BigDecimal(20.0));
		assertFalse(p1Clone.equals(p1));
		p1Clone.setLat(p1.getLat());
		assertTrue(p1Clone.equals(p1));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLatMetresToOther(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	public void testGetLatMetresToOther() throws Exception {
		assertEquals("Two points should be equal in distance", 0, p2.getLatMetresToOther(p1), 0.5);
		//confirm test as described here: https://www.movable-type.co.uk/scripts/latlong.html
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(58.64389, 5.714722);
		assertEquals(954769.686, p.getMetresToOther(q), 50);
				
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLngMetresToOther(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	public void testGetLngMetresToOther() throws Exception {
		//confirm test as described here: https://www.movable-type.co.uk/scripts/latlong.html
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(50.06639, 3.07);
		assertEquals(189344.209, p.getMetresToOther(q), 50);
		
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#throwRangeException(java.lang.String, double, double)}.
	 
	public void testThrowRangeException() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getMetresToOther(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	public void testGetMetresToOtherWGS84Coordinate() throws Exception {
		//check tests as described here: https://www.movable-type.co.uk/scripts/latlong-vincenty.html
		WGS84Coordinate p = new WGS84Coordinate(50.06639, 5.714722);
		WGS84Coordinate q = new WGS84Coordinate(58.64389, 3.07);
		assertEquals(969932.984, p.getMetresToOther(q), 50);
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyLat(java.math.BigDecimal)}.
	 
	public void testVerifyLatBigDecimal() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyLat(java.lang.Double)}.
	 
	public void testVerifyLatDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyLng(java.math.BigDecimal)}.
	 
	public void testVerifyLngBigDecimal() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyLng(double)}.
	 
	public void testVerifyLngDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyAlt(java.math.BigDecimal)}.
	 
	public void testVerifyAltBigDecimal() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyAlt(java.lang.Double)}.
	 
	public void testVerifyAltDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#verifyAlt(java.lang.Integer)}.
	 
	public void testVerifyAltInteger() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#setAlt(java.lang.Double)}.
	 
	public void testSetAltDouble() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#setAlt(java.lang.Integer)}.
	 
	public void testSetAltInteger() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#add(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	public void testAddWGS84Coordinate() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(10, 2);
		WGS84Coordinate q = new WGS84Coordinate(10.5, 1.3);
		assertEquals(p.add(q), new WGS84Coordinate(p.getLat().add(q.getLat()), p.getLng().add(q.getLng())));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#multiply(java.math.BigDecimal)}.
	 * @throws Exception 
	 */
	public void testMultiplyBigDecimal() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(10, 2);
		assertEquals(p.multiply(2), new WGS84Coordinate(p.getLat().multiply(new BigDecimal(2.0)), p.getLng().multiply(new BigDecimal(2.0))));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#multiply(int)}.
	 
	public void testMultiplyInt() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#divide(int)}.
	 * @throws Exception 
	 */
	public void testDivide() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(10, 2);
		assertEquals(p.divide(2), new WGS84Coordinate(p.getLat().divide(new BigDecimal(2.0)), p.getLng().divide(new BigDecimal(2.0))));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#subtract(com.ROCSAFE.maven.gpsutilities.WGS84Coordinate)}.
	 * @throws Exception 
	 */
	public void testSubtractWGS84Coordinate() throws Exception {
		WGS84Coordinate p = new WGS84Coordinate(10, 2);
		WGS84Coordinate q = new WGS84Coordinate(10.5, 1.3);
		assertEquals(p.subtract(q), new WGS84Coordinate(p.getLat().subtract(q.getLat()), p.getLng().subtract(q.getLng())));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLowerLatBound()}.
	 */
	public void testGetLowerLatBound() {
		assertEquals(p1.getLowerLatBound(), new BigDecimal(-90));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getUpperLatBound()}.
	 */
	public void testGetUpperLatBound() {
		assertEquals(p1.getUpperLatBound(), new BigDecimal(90));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLowerLngBound()}.
	 */
	public void testGetLowerLngBound() {
		assertEquals(p1.getLowerLngBound(), new BigDecimal(-180));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getUpperLngBound()}.
	 */
	public void testGetUpperLngBound() {
		assertEquals(p1.getUpperLngBound(), new BigDecimal(180));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getLowerAltBound()}.
	 */
	public void testGetLowerAltBound() {
		assertEquals(p1.getLowerAltBound(), new BigDecimal(0));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getUpperAltBound()}.
	 */
	public void testGetUpperAltBound() {
		assertEquals(p1.getUpperAltBound(), new BigDecimal(20000));
	}

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getX()}.
	 
	public void testGetX() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getY()}.
	 
	public void testGetY() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getZ()}.
	 
	public void testGetZ() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#getMetresToOther(com.ROCSAFE.maven.gpsutilities.CoordinateInterface)}.
	 
	public void testGetMetresToOtherCoordinateInterface() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#add(com.ROCSAFE.maven.gpsutilities.CoordinateInterface)}.
	 
	public void testAddCoordinateInterface() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#addX(java.math.BigDecimal)}.
	 
	public void testAddX() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#subtractY(java.math.BigDecimal)}.
	 
	public void testSubtractY() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#subtractX(java.math.BigDecimal)}.
	 
	public void testSubtractX() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#subtract(com.ROCSAFE.maven.gpsutilities.CoordinateInterface)}.
	 
	public void testSubtractCoordinateInterface() {
		fail("Not yet implemented");
	}
	*/

	/**
	 * Test method for {@link com.ROCSAFE.maven.gpsutilities.WGS84Coordinate#addY(java.math.BigDecimal)}.
	 
	public void testAddY() {
		fail("Not yet implemented");
	}
	*/

}
