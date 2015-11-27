package hayen.util.math;

public class Matrix3x3 extends Matrix {
	
	public static final Matrix3x3 identity = new Matrix3x3();
	
	public Matrix3x3(){
		super(3);
		setToIdentity();
	}
	public Matrix3x3(	double m00, double m01, double m02,
						double m10, double m11, double m12,
						double m20, double m21, double m22)
	{
		super(3);
		set(0, 0, m00); set(0, 1, m01); set(0, 2, m02);
		set(1, 0, m10); set(1, 1, m11); set(1, 2, m12);
		set(2, 0, m20); set(2, 1, m21); set(2, 2, m22);
	}
	
	public Matrix3x3 setToIdentity(){
		for (int m = 0; m < 3; m++)
			for (int n = 0; n < 3; m++)
				set(m, n, (m == n) ? 1 : 0);
		return this;
	}
	
}
