package hayen.util.math;

/**
 * Created by Hayen on 15-11-25.
 */
public abstract class Vector<T extends Vector> implements IVector<T>{

	public double magnitude(){ return Math.sqrt(sqrMagnitude()); }
	public abstract double sqrMagnitude();

	public T normalized(){ return this.multiply(1 / magnitude()); }
	public abstract double dot(T b);
	public T projectOn(T b) { return (T)b.multiply(dot(b) / b.sqrMagnitude()); }
	public abstract T scale(T b);
}
