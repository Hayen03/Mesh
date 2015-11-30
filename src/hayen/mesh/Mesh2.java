package hayen.mesh;

import hayen.mesh.virtual.Mesh;
import hayen.util.AffineTransform2;
import hayen.util.Transform;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Mesh2 extends Mesh implements Shape {
	
	private AffineTransform _transform;
	
	public Mesh2(){
		_transform = new AffineTransform();
	}
	
	/**
	 * Scale the mesh by x on the x-axis and y on the y-axis without deforming the base mesh
	 * @param x : the scaling factor on the x-axis
	 * @param y : the scaling factor on the y-axis
	 * @return this
	 */
	public Mesh2 scale(double x, double y){
		_transform.scale(x, y);
		return this;
	}
	/**
	 * Translate the mesh by x on the x-axis and y on the y-axis
	 * @param x : the amount to translate on the x-axis
	 * @param y : the amount to translate on the y-axis
	 * @return this
	 */
	public Mesh2 translate(double x, double y){
		_transform.translate(x, y);
		return this;
	}
	/**
	 * Rotate the mesh by a radian without deforming the base mesh
	 * @param a : the angle, in radian
	 * @return this
	 */
	public Mesh2 rotate(double a){
		_transform.rotate(a);
		return this;
	}

	/**
	 * Return a copy of the transform modifying the mesh
	 * @return the affine transformation
	 */
	public AffineTransform getTransform(){ return new AffineTransform(_transform); }
	
	/**
	 * BakedMesh in 2 dimension implementation. The vertices, edges and faces cannot be modified
	 * @author Hayen
	 */
	public class BakedMesh2 extends Mesh2{
		
		private Vertex2[] _vertices;
		private Edge2[] _edges;
		private Face2[] _faces;
		private Rectangle2D _bounds = null;
		
		/**
		 * Create a new BakedMesh2. The parameter cannot be null, but they can be empty or of size 0
		 * @param vertices
		 * @param edges
		 * @param faces
		 */
		private BakedMesh2(Vertex2[] vertices, Edge2[] edges, Face2[] faces){
			super();
			_vertices = vertices;
			_edges = edges;
			_faces = faces;
			setMask(faces!=null, edges!=null, vertices!=null);
		}

		public void draw(Graphics g, AffineTransform transform) {
			if (showFaces())
				drawFaces(g, transform);
			if (showEdges())
				drawEdges(g, transform);
			if (showVertices())
				drawVertices(g, transform);
		}
		@Override
		public void draw(Graphics g, @SuppressWarnings("rawtypes") Transform transform){
			draw(g, ((AffineTransform2)transform).toJavaAffineTransform());
		}

		@Override
		public int nbVertices() { return _vertices.length; }
		@Override
		public int nbEdges() { return _edges.length; }
		@Override
		public int nbFaces() { return _faces.length; }

		@Override
		public double area(){
			double a = 0;
			for (Face2 f : _faces)
				a += f.area();
			return a;
		}
		@Override
		public double volume(){ return 0; }
		
		public void drawFaces(Graphics g, AffineTransform transform){
			for (Face2 f : _faces){
				// TODO
			}
		}
		public void drawEdges(Graphics g, AffineTransform transform){
			for (Edge2 e : _edges){
				// TODO
			}
		}
		public void drawVertices(Graphics g, AffineTransform transform){
			for (Vertex2 v : _vertices){
				// TODO
			}
		}
		private void generateBounds(){
			double minX = _vertices[0].getX(), maxX = _vertices[0].getX(), minY = _vertices[0].getY(), maxY = _vertices[0].getY();
			for (int i = 1; i < _vertices.length; i++){
				Vertex2 v = _vertices[i];
				
				if (minX > v.getX())
					minX = v.getX();
				else if (maxX < v.getX())
					maxX = v.getX();
				
				if (minY > v.getY())
					minY = v.getY();
				else if (maxY < v.getY())
					maxY = v.getY();
			}
		}

		@Override
		public Rectangle getBounds() {
			if (_bounds == null)
				generateBounds();
			return (Rectangle) _bounds;
		}

		@Override
		public Rectangle2D getBounds2D() {
			if (_bounds == null)
				generateBounds();
			return _bounds;
		}

		@Override
		public boolean contains(double x, double y) {
			if (_bounds == null)
				generateBounds();
			if (_bounds.contains(x, y)){
				int i = 0;
				while (i < _faces.length){
					if (_faces[i].contains(x, y))
						return true;
					i++;
				}
			}
			return false;
		}

		@Override
		public boolean contains(Point2D p) { return contains(p.getX(), p.getY()); }

		@Override
		public boolean intersects(double x, double y, double w, double h) {
			if (_bounds == null)
				generateBounds();
			if (_bounds.intersects(x, y, w, h)){
				int i = 0;
				while (i < _faces.length)
					if (_faces[i].intersects(x, y, w, h))
						return true;
			}
			return false;
		}

		@Override
		public boolean intersects(Rectangle2D r) { return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight()); }

		@Override
		public boolean contains(double x, double y, double w, double h) {
			if (_bounds == null)
				generateBounds();
			if (_bounds.contains(x, y, w, h)){
				int i = 0;
				while (i < _faces.length)
					if (_faces[i].contains(x, y, w, h))
						return true;
			}
			return false;
		}

		@Override
		public boolean contains(Rectangle2D r) { return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight()); }

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