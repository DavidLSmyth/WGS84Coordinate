package com.ROCSAFE.maven.gpsutilities;

import java.math.BigDecimal;

/**
 * A specification of the familiar 3D Cartesian Coordinate system.
 * Assumes that unit length is 1m.
 * 
 * @author David Smyth
 *
 */
public interface CoordinateInterface {
	
	public BigDecimal getX();
	public BigDecimal getY();
	public BigDecimal getZ();
	public BigDecimal getMetresToOther(CoordinateInterface other);
	
	//must be able to add and subtract coordinates to each other to form a grid
	public CoordinateInterface add(CoordinateInterface other);
	public CoordinateInterface addX(BigDecimal x)  throws Exception;	
	public CoordinateInterface addY(BigDecimal y)  throws Exception;	
		
	public CoordinateInterface subtract(CoordinateInterface other);
	public CoordinateInterface subtractY(BigDecimal y)  throws Exception;
	public CoordinateInterface subtractX(BigDecimal x)  throws Exception;
}