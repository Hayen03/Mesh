package hayen.mesh.virtual;

/**
 * Created by Hayen on 15-11-24.
 */
public abstract class Face {
	public abstract double area();
	public abstract boolean hasEdge(Edge e);
}
