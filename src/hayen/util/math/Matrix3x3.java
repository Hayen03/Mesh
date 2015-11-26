package hayen.util.math;

public class Matrix3x3 extends Matrix {
	
	public static final Matrix3x3 identity = new Matrix3x3();
	
	public Matrix3x3(){
		super(3);
		setToIdentity();
	}
	
	public Matrix3x3 setToIdentity(){
		for (int m = 0; m < 3; m++)
			for (int n = 0; n < 3; m++)
				set(m, n, (m == n) ? 1 : 0);
		return this;
	}
	
}
