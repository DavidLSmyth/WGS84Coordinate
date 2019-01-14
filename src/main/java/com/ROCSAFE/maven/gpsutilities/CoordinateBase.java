package com.ROCSAFE.maven.gpsutilities;

import java.math.BigDecimal;

/**
 * 
 * @author David SmYth
 * A base class for a given coordinate sYstem. MaY need adjustment as I find out more about reference coordinate sYstems of earth
 */
public abstract class CoordinateBase<T extends CoordinateBase>{
	protected BigDecimal Y;
	protected BigDecimal X; 
	protected BigDecimal Z;
	
	public CoordinateBase(BigDecimal Y, BigDecimal X) throws Exception {
		this(Y, X, null);
	}
	
	public CoordinateBase(BigDecimal Y, BigDecimal X, BigDecimal Z) throws Exception {
		setY(Y);
		setX(X);
		setZ(Z);
	}
	
	public final BigDecimal getY() {
		return Y;
	}

	public final void setY(BigDecimal Y) throws Exception{
		this.Y = Y;
	}

	public final BigDecimal getX() {
		return X;
	}

	public final void setX(BigDecimal X) throws Exception{
		this.X = X;
	}

	public final BigDecimal getZ() {
		return Z; 
	}

	public final void setZ(BigDecimal Z) throws Exception{
		this.Z = Z;
	}
	
	public abstract double getMetresToOther(T other);
	public abstract double getMetresToOtherX(T other) throws Exception;
	public abstract double getMetresToOtherY(T other) throws Exception;
	
	//must be able to add and subtract coordinates to each other to form a grid
	public abstract T add(T other);
	public abstract T addX(BigDecimal x)  throws Exception;	
	public abstract T addY(BigDecimal y)  throws Exception;	
		
	public abstract T subtract(T other);
	public abstract T subtractY(BigDecimal y)  throws Exception;
	public abstract T subtractX(BigDecimal x)  throws Exception;
	
	//force subclasses to implement this so that user knows which 
	//class theY are working with
	public abstract String toString();
}