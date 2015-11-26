package hayen.util;

import java.awt.Shape;

import hayen.util.math.Matrix;
import hayen.util.math.Vector2;

public abstract class Transform2 <T extends Transform2> extends Transform<T> {

	public abstract T shear(double x, double y);
	public abstract T shear(Vector2 v);
	public abstract T shear(double v);
	public abstract T rotate(double theta);
	public abstract T scale(double x, double y);
	public abstract T scale(Vector2 v);
	public abstract T scale(double v);
	
	public abstract Vector2 getScale();
	public abstract double getScaleX();
	public abstract double getScaleY();
	public abstract Vector2 getShear();
	public abstract double getShearX();
	public abstract double getShearY();
	public abstract double getRotation();

	public abstract Matrix getMatrix();
	public abstract Shape createTransformedShape(Shape shape);
	
}
