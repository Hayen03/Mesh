package hayen.mesh;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Edge2 implements IDrawable{
	
	private Vertex2 _p1, _p2;
//	private double _size;
	
	public Edge2(Vertex2 p1, Vertex2 p2){
		_p1 = p1;
		_p2 = p2;
	}
	
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
	public void draw(Graphics g, AffineTransform transform) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(new GradientPaint(_p1.getPoint(), _p1.getColor(), _p2.getPoint(), _p2.getColor()));
		g2.draw(transform.createTransformedShape(new Line2D.Double(_p1.getX(), _p1.getY(), _p2.getX(), _p2.getY())));
	}
	
}