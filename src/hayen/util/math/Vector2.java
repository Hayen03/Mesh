package hayen.util.math;

/**
 * Created by Hayen on 15-11-25.
 */
public class Vector2 extends Vector<Vector2> {

	public static final Vector2 zero = new Vector2();
	public static final Vector2 i = new Vector2(1, 0);
	public static final Vector2 j = new Vector2(0, 1);
	
	private double _x, _y;
	
	public Vector2(double x, double y){
		_x = x;
		_y = y;
	}
	public Vector2(){
		this(0, 0);
	}
	
	public double getX(){
		return _x;
	}
	public double getY(){
		return _y;
	}

	@Override
	public double sqrMagnitude() { return _x*_x + _y*_y; }
	@Override
	public double dot(Vector2 b) { return this._x*b._x + this._y*b._y; }
	@Override
	public Vector2 scale(Vector2 b) { return new Vector2(_x*b._x, _y*b._y); }
	@Override
	public Vector2 add(Vector2 b) { return new Vector2(_x+b._x, _y+b._y); }
	@Override
	public Vector2 multiply(double k) { return new Vector2(_x*k, _y*k); }
}
