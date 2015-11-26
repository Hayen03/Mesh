package hayen.mesh;

import hayen.mesh.hayen.mesh.virtual.Mesh;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public abstract class Mesh2 extends Mesh {
	
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

		@Override
		public void draw(Graphics g, AffineTransform transform) {
			AffineTransform t = new AffineTransform(transform);
			t.preConcatenate(_transform);
			if (showFaces())
				for (Face2 f : _faces)
					f.draw(g, t);
			if (showEdges())
				for (Edge2 e : _edges)
					e.draw(g, t);
			if (showVertices())
				for (Vertex2 v : _vertices)
					v.draw(g, t);
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

	}
}