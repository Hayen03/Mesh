package hayen.mesh;

import hayen.util.IDrawable;
import hayen.util.Transform;
import hayen.util.Transform2;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Edge2 implements IDrawable {
	
	private Vertex2 _p1, _p2;
//	private double _size;
	
	public Edge2(Vertex2 p1, Vertex2 p2){
		_p1 = p1;
		_p2 = p2;
	}
	public Edge2(Edge2 orig){ this(new Vertex2(orig._p1), new Vertex2(orig._p2)); }
	
	public Vertex2 getVertex1(){ return _p1; }
	public Vertex2 getVertex2(){ return _p2; }
	
	public Edge2 setVertex1(Vertex2 p1){
		_p1 = p1;
		return this;
	}
	public Edge2 setVertex2(Vertex2 p2){
		_p2 = p2;
		return this;
	}

	@Override
	public void draw(Graphics g, Transform transform) {
		Graphics2D g2 = (Graphics2D)g;
		Transform2 t2 = (Transform2)transform;
		g2.setPaint(new GradientPaint(_p1.getPoint(), _p1.getColor(), _p2.getPoint(), _p2.getColor()));
		g2.draw(t2.createTransformedShape(new Line2D.Double(_p1.getX(), _p1.getY(), _p2.getX(), _p2.getY())));
	}

	public double length(){ return 0; }
	
}
