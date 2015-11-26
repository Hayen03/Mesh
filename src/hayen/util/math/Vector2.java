package hayen.util.math;

/**
 * Created by Hayen on 15-11-25.
 */
public class Vector2 extends Vector<Vector2> {
	
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
	
}
