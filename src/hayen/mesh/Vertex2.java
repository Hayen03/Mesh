package hayen.mesh;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Vertex2 implements IDrawable{
	public static final double DIAMETER = 2;
	
	private double _diam;
	private double _x, _y;
	private Color _color;
	private Ellipse2D.Double _ellipse = null;
	
	public Vertex2(double x, double y, Color color){
		_x = x;
		_y = y;
		_color = color;
		_diam = DIAMETER;
	}
	public Vertex2(double x, double y){ this(x, y, Color.black); }
	public Vertex2(Color color){ this(0, 0, color); }
	public Vertex2(){ this(0, 0, Color.black); }
	
	public double getX(){ return _x; }
	public double getY(){ return _y; }
	public Point2D.Double getPoint(){ return new Point2D.Double(_x, _y); }
	public Color getColor(){ return _color; }
	public double getDiameter(){ return _diam; }
	
	public Vertex2 setX(double x){
		_x = x;
		_ellipse = null;
		return this;
	}
	public Vertex2 setY(double y){
		_y = y;
		_ellipse = null;
		return this;
	}
	public Vertex2 setPoint(Point point){
		_x = point.getX();
		_y = point.getY();
		_ellipse = null;
		return this;
	}
	public Vertex2 setColor(Color color){
		_color = color;
		return this;
	}
	public Vertex2 setDiameter(double d){
		_diam = d;
		_ellipse = null;
		return this;
	}
	
	@Override
	public void draw(Graphics g, AffineTransform transform) {
		Color colorBuffer = g.getColor();
		g.setColor(_color);
		if (_ellipse == null)
			_ellipse = new Ellipse2D.Double(_x - _diam/2, _y - _diam/2, _diam, _diam);
		((Graphics2D)g).fill(transform.createTransformedShape(_ellipse));
		g.setColor(colorBuffer);
	}
	
	public double distanceSqr(Vertex2 v2){ return (_x+v2._x)*(_x+v2._x) + (_y+v2._y)*(_y+v2._y); }
	public double distance(Vertex2 v2){ return Math.sqrt(distanceSqr(v2)); }
	
}
