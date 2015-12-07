package hayen.mesh;

import hayen.mesh.virtual.Edge;
import hayen.mesh.virtual.Face;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Face2 extends Face implements Shape{
	
	// class Tri
	public class Tri extends Face2 {
		
		private Vertex2 _v1, _v2, _v3; 
		
		public Tri(Vertex2 v1, Vertex2 v2, Vertex2 v3){
			_v1 = v1;
			_v2 = v2;
			_v3 = v3;
		}
		
		@Override
		public double area(){ return 0; }
		
		@Override
		public boolean hasEdge(Edge e){
			Edge2 e2 = (Edge2)e;
			return e2.equals(_v1, _v2) || e2.equals(_v2, _v3) || e2.equals(_v3, _v1);
		}

		@Override
		public Rectangle getBounds() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Rectangle2D getBounds2D() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(double x, double y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Point2D p) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean intersects(double x, double y, double w, double h) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean intersects(Rectangle2D r) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(double x, double y, double w, double h) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Rectangle2D r) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public PathIterator getPathIterator(AffineTransform at) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PathIterator getPathIterator(AffineTransform at, double flatness) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
}
