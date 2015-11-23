package hayen.mesh;

/**
 * Base Mesh class for all Mesh class. Regroup all common properties and methods of a mesh
 * @author Hayen
 */
public abstract class Mesh implements IDrawable{
	
	private boolean _showFaces, _showEdges, _showVertices;
	
	public Mesh(){
		_showFaces = _showEdges = _showVertices = true;
	}
	
	/**
	 * Create the mask with all three parameter in one shot
	 * @param faces : show the faces?
	 * @param edges : show the edges?
	 * @param vertices : show the vertices?
	 */
	protected void setMask(boolean faces, boolean edges, boolean vertices){
		_showFaces = faces;
		_showEdges = edges;
		_showVertices = vertices;
	}
	
	/**
	 * return true if the mesh draw the faces on rendering
	 * @return is the mesh drawing faces
	 */
	public boolean showFaces(){ return _showFaces; }
	/**
	 * return true if the mesh draw the edges on rendering
	 * @return is the mesh drawing edges
	 */
	public boolean showEdges(){ return _showEdges; }
	/**
	 * return true if the mesh draw the vertices on rendering
	 * @return is the mesh drawing vertices
	 */
	public boolean showVertices(){ return _showVertices; }
	
	/**
	 * Set if this mesh should render the faces on rendering
	 * @param showFace : should the faces be drawn?
	 * @return this
	 */
	public Mesh setShowFaces(boolean showFace){
		_showFaces = showFace;
		return this;
	}
	/**
	 * Set if this mesh should render the edges on rendering
	 * @param showFace : should the edges be drawn?
	 * @return this
	 */
	public Mesh setShowEdges(boolean showEdge){
		_showEdges = showEdge;
		return this;
	}
	/**
	 * Set if this mesh should render the vertices on rendering
	 * @param showFace : should the vertices be drawn?
	 * @return this
	 */
	public Mesh setShowVertices(boolean showVertices){
		_showVertices = showVertices;
		return this;
	}
	
	/**
	 * return the number of vertices in the mesh
	 * @return : amount of vertices
	 */
	public abstract int nbVertices();
	/**
	 * return the number of edges in the mesh
	 * @return : amount of edges
	 */
	public abstract int nbEdges();
	/**
	 * return the number of faces in the mesh
	 * @return : amount of faces
	 */
	public abstract int nbFaces();

}
