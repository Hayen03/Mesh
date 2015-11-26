package hayen.util.math;

/**
 * Created by Hayen on 15-11-25.
 */
public class Matrix {

	private int _m, _n;
	private double[][] _mat;

	public Matrix(int m, int n){
		_m = m;
		_n = n;
		_mat = new double[m][n];
	}
	public Matrix(int m){ this(m, m); }
	public Matrix(Matrix mat){
		this(mat._m, mat._n);
		for (int m = 0; m < _m; m++)
			for (int n = 0; n < _n; n++)
				_mat[m][n] = mat._mat[m][n];
	}

	public double get(int m, int n){
		return _mat[m][n];
	}
	public Matrix set(int m, int n, double val){
		_mat[m][n] = val;
		return this;
	}

	public Matrix concatenate(Matrix b) throws MatrixNonCompatibleException {
		if (b._m != this._n)
			throw new MatrixNonCompatibleException();
		Matrix r = new Matrix(_m, b._n);
		double val;
		for (int m = 0; m < r._m; m++){
			for (int n = 0; n < r._n; n++){

				val = 0;
				for (int x = 0; x < _n; x++)
					val += this.get(m, x) * b.get(x, n);

				r.set(m, n, val);
			}
		}
		return r;
	}
	public Matrix add(Matrix b) throws MatrixNonCompatibleException {
		if (this._m != b._m || this._n != b._n)
			throw new MatrixNonCompatibleException();

		Matrix r = new Matrix(this);
		for (int m = 0; m < r._m; m++)
			for (int n = 0; n < r._n; n++)
				r._mat[m][n] += b._mat[m][n];
		return r;
	}

	public boolean isSquare(){ return _m == _n; }

	public Matrix transpose(){
		Matrix r = new Matrix(_n, _m);
		for (int m = 0; m < _n; m++)
			for (int n = 0; n < _m; n++)
				r._mat[n][m] = _mat[m][n];
		return r;
	}
	public Matrix inverse() throws MatrixNonCompatibleException {
		if (!isSquare())
			throw new MatrixNonCompatibleException();
		return null;
	}
	public double det() throws MatrixNonCompatibleException {
		if (!isSquare())
			throw new MatrixNonCompatibleException();
		
		// TODO
		double d = 0;
		return d;
	}
	
	public static Matrix Identity(int m){
		Matrix r = new Matrix(m);
		for (int i = 0; i < m; i++)
			r._mat[i][i] = 1;
		return r;
	}

	@Override
	public String toString(){
		String s = "";
		
		for (int m = 0; m < _m; m++){
			for (int n = 0; n < _n; n++)
				s += _mat[m][n] + "\t";
			s += "\n";
		}
		
		return s;
	}
	
	public int getWidth(){ return _m; }
	public int getHeight(){ return _n; }
	
	public class MatrixNonCompatibleException extends Exception { public MatrixNonCompatibleException() { super("The matrix are not compatible for this operation"); } }

}
