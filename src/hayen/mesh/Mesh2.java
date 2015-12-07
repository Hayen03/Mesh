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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Mesh2 extends Mesh implements Shape {
	
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
			
			/* trouve les arretes exterieurs
			 * Une arrete est exterieur si elle est utilise que par une seule face
			*/
			List<Edge2> outsideEdge = new LinkedList<Edge2>();
			int nbFace = 0;
			for (Edge2 e : _edges){
				for (Face2 f : _faces){
					if (f.hasEdge(e))
						nbFace++;
				}
				if (nbFace == 1)
					outsideEdge.add(e);
				nbFace = 0;
			}
			return new MeshPathIterator(outsideEdge, at != null ? new AffineTransform(at) : null);
		}

		@Override
		public PathIterator getPathIterator(AffineTransform at, double flatness) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class MeshPathIterator implements PathIterator {

		static final int SEG_END = -1;
		
		private List<Edge2> edges;
		private Edge2 courant;
		private int type = SEG_END;
		private AffineTransform transform;
		
		private MeshPathIterator(List<Edge2> e, AffineTransform at){
			edges = e;
			transform = at;
		}
		
		@Override
		public int getWindingRule() {
			return PathIterator.WIND_EVEN_ODD;
		}

		@Override
		public boolean isDone() {
			return edges.size() != 0;
		}

		@Override
		public void next() {
			if (courant == null || type == SEG_END){
				courant = edges.get(0);
				type = PathIterator.SEG_MOVETO;
			}
			else if (type == SEG_LINETO || type == SEG_MOVETO){
					
				Edge2 n = null;
				Iterator<Edge2> it = edges.iterator();
				boucle:
					while (it.hasNext()){ // trouver un segment qui se connecte au precedent
						if ((n = it.next().connected(courant)) != null){
							it.remove();
							break boucle;
						}
					}

				if (n == null) // s'il n'y avait aucun segment qui se connectait
					type = SEG_END;
				else { // s'il y en avait un
					courant = n;
					type = SEG_LINETO;
				}
					
			}
			
		}

		@Override
		public int currentSegment(float[] coords) {
			switch (type){
			case SEG_LINETO:
			case SEG_MOVETO:
				coords[0] = (float)courant.getVertex1().getX();
				coords[1] = (float)courant.getVertex1().getY();
				if (transform != null)
					transform.transform(coords, 0, coords, 0, 2);
				break;
			case SEG_END:	
				coords[0] = (float)courant.getVertex2().getX();
				coords[1] = (float)courant.getVertex2().getY();
				if (transform != null)
					transform.transform(coords, 0, coords, 0, 2);
				return SEG_LINETO;
			default:
				// TODO error or something
				break;
			}
			return type;
		}

		@Override
		public int currentSegment(double[] coords) {
			switch (type){
			case SEG_LINETO:
			case SEG_MOVETO:
				coords[0] = courant.getVertex1().getX();
				coords[1] = courant.getVertex1().getY();
				if (transform != null)
					transform.transform(coords, 0, coords, 0, 2);
				break;
			case SEG_END:	
				coords[0] = courant.getVertex2().getX();
				coords[1] = courant.getVertex2().getY();
				if (transform != null)
					transform.transform(coords, 0, coords, 0, 2);
				return SEG_LINETO;
			default:
				// TODO error or something
				break;
			}
			return type;
		}
		
	}
	
}