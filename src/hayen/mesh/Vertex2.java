package hayen.mesh;

import hayen.mesh.virtual.Vertex;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Vertex2 extends Vertex {

	private double _x, _y;
//	private Ellipse2D.Double _ellipse = null;
	
	public Vertex2(double x, double y, Color color){
		super(color);
		_x = x;
		_y = y;
	}
	public Vertex2(double x, double y){ this(x, y, Color.black); }
	public Vertex2(Color color){ this(0, 0, color); }
	public Vertex2(){ this(0, 0, Color.black); }
	public Vertex2(Vertex2 orig){ this(orig._x, orig._y, orig.getColor()); }
	
	public double getX(){ return _x; }
	public double getY(){ return _y; }
	public Point2D.Double getPoint(){ return new Point2D.Double(_x, _y); }
	
	public Vertex2 setX(double x){
		_x = x;
//		_ellipse = null;
		return this;
	}
	public Vertex2 setY(double y){
		_y = y;
//		_ellipse = null;
		return this;
	}
	public Vertex2 setPoint(Point point){
		_x = point.getX();
		_y = point.getY();
//		_ellipse = null;
		return this;
	}

/*
	public void draw(Graphics g, Transform transform) {
		Transform2 t2 = (Transform2)transform;
		Color colorBuffer = g.getColor();
		g.setColor(getColor());
		if (_ellipse == null)
			_ellipse = new Ellipse2D.Double(_x - getRadius(), _y - getRadius(), getRadius()/2, getRadius()/2);
		((Graphics2D)g).fill(t2.createTransformedShape(_ellipse));
		g.setColor(colorBuffer);
	}
	*/
	
	public double distanceSqr(double x, double y){ return (_x+x)*(_x+x) + (_y+y)*(_y+y); }
	public double distanceSqr(Vertex v){
		return 0;
	};
	public double distance(Vertex v2){ return Math.sqrt(distanceSqr(v2)); }

	public static int getDimension(){ return 2; }

	@Override
	protected double[] getValue() {
		return new double[0];
	}

}
