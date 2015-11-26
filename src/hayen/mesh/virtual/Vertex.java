package hayen.mesh.virtual;

import hayen.util.IDrawable;

import java.awt.*;

/**
 * Created by Hayen on 15-11-24.
 */
public abstract class Vertex implements IDrawable{

	private Color _color;
	private double _radius = 0;

	public Vertex(Color c){ _color = c; }

	public double distance(){ return Math.sqrt(distanceSqr()); }
	public double distance(Vertex v){ return Math.sqrt(distanceSqr(v)); }
	public double distanceSqr(){
		double[] val = getValue();
		double distSqr = 0;
		for (double d : val)
			distSqr += d*d;
		return distSqr;
	}
	public double distanceSqr(Vertex v){
		double[] val1 = getValue(), val2 = v.getValue();
		double distSqr = 0;
		int length = val1.length > val2.length ? val1.length : val2.length;
		double a, b;
		for (int i = 0; i < length; i++){
			try { a = val1[i]; }
			catch (IndexOutOfBoundsException e) { a = 0; }
			try { b = val1[i]; }
			catch (IndexOutOfBoundsException e) { b = 0; }
			distSqr += (a-b)*(a-b);
		}
		return distSqr;
	}

	public Color getColor(){ return _color; }
	public double getRadius(){ return _radius; }

	public Vertex setColor(Color color){
		_color = color;
		return this;
	}
	public Vertex setRadius(double r){
		_radius = r;
		return this;
	}

	public static int getDimension(){ return 0; }

	protected abstract double[] getValue();

}
