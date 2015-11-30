package hayen.util;

import hayen.util.math.Matrix;
import hayen.util.math.Matrix3x3;
import hayen.util.math.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Hayen on 15-11-25.
 */
public class AffineTransform2 extends Transform2<AffineTransform2>{

	private final double[][] _mat;
	
	public AffineTransform2(){
		_mat = new double[2][3];
		_mat[0][0] = _mat[1][1] = 1;
		setType(TYPE_UNKNOWN);
	}
	public AffineTransform2(AffineTransform2 b){
		_mat = new double[2][3];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++)
				this._mat[i][j] = b._mat[i][j];
		setType(TYPE_UNKNOWN);
	}
	
	@Override
	public AffineTransform2 shear(double x, double y) {
		double M00 = _mat[0][0], M01 = _mat[0][1], M10 = _mat[1][0], M11 = _mat[1][1], M02 = _mat[0][2], M12 = _mat[1][2];

		if (isShear()){
			_mat[0][0] += x*M10;
			_mat[1][1] += y*M01;
		}
		if (isScaleNull()){
			_mat[1][0] += y*M00;
			_mat[0][1] += x*M11;
		}
		if (isTranslation()) {
			_mat[0][2] += x*M12;
			_mat[1][2] += y*M02;
		}

		updateType();
		return this;
	}
	@Override
	public AffineTransform2 shear(Vector2 v) { return shear(v.getX(), v.getY()); }
	@Override
	public AffineTransform2 shear(double v) { return shear(v, v); }

	/**
	 * Apply a rotation.
	 * <p>
	 *     The rotation C is applied after all the previous transformation T, such as M = C*T	 *
	 * </p>
	 * @param theta : the angle of the rotation, in radians
	 * @return
	 */
	@Override
	public AffineTransform2 rotate(double theta) {

		double M00 = _mat[0][0], M01 = _mat[0][1], M02 = _mat[0][2], M10 = _mat[1][0], M11 = _mat[1][1], M12 = _mat[1][2];
		double cos = Math.cos(theta), sin = Math.sin(theta);

		/* Special handling for quadrant rotation */
		if (sin == 1)
			rotate90();
		else if (sin == -1)
			rotate270();
		else if (cos == 1)
			return this;
		else if (cos == -1)
			rotate180();

		if (isTranslation()){
			_mat[0][2] = cos*M02 + sin*M12;
			_mat[1][2] = cos*M12 - sin*M02;
		}
		if (isShear() || isScale()){
			_mat[0][0] = cos*M00 + sin*M10;
			_mat[0][1] = cos*M01 + sin*M11;
			_mat[1][0] = cos*M10 - sin*M00;
			_mat[1][1] = cos*M11 - sin*M01;
		}
		updateType();
		return this;
	}
	public AffineTransform2 rotate(double theta, Vector2 anchor){
		translate(anchor.multiply(-1));
		rotate(theta);
		translate(anchor);
		return this;
	}
	@Override
	public AffineTransform2 scale(double x, double y) {
		int type = getType();

		if ((type & TYPE_NULL_SCALE) != TYPE_NULL_SCALE){
			_mat[0][0] *= x;
			_mat[1][1] *= y;
		}
		if ((type & TYPE_SHEAR) == TYPE_SHEAR){
			_mat[1][0] *= x;
			_mat[0][1] *= y;
		}
		if ((type & TYPE_TRANSLATION) == TYPE_TRANSLATION){
			_mat[0][2] *= x;
			_mat[1][2] *= y;
		}

		updateType();
		return this;
	}
	@Override
	public AffineTransform2 scale(Vector2 v) { return scale(v.getX(), v.getY()); }
	@Override
	public AffineTransform2 scale(double v) { return scale(v, v); }
	public AffineTransform2 translate(double x, double y){
		_mat[0][2] += x; _mat[1][2] += y;
		updateType();
		return this;
	}
	public AffineTransform2 translate(Vector2 v){
		return translate(v.getX(), v.getY());
	}
	
	@Override
	public Vector2 getScale() { return new Vector2(_mat[0][0], _mat[1][1]); }
	@Override
	public double getScaleX() { return _mat[0][0]; }
	@Override
	public double getScaleY() { return _mat[1][1]; }
	@Override
	public Vector2 getShear() { return new Vector2(_mat[0][1], _mat[1][0]); }
	@Override
	public double getShearX() { return _mat[0][1]; }
	@Override
	public double getShearY() { return _mat[1][0]; }
	public Vector2 getTranslation(){ return new Vector2(_mat[0][2], _mat[1][2]); }
	public double getTranslationX(){ return _mat[0][2]; }
	public double getTranslationY(){ return _mat[1][2]; }
	@Override
	public Matrix getMatrix() { return new Matrix3x3(	_mat[0][0], 	_mat[0][1], 	_mat[0][2],
														_mat[1][0], 	_mat[1][1], 	_mat[1][2],
														0, 				0, 				1); }
	
	/**
	 * T*M
	 */
	@Override
	public AffineTransform2 transform(AffineTransform2 t) {
		AffineTransform2 r = new AffineTransform2();
		r._mat[0][0] = t._mat[0][0]*this._mat[0][0] + t._mat[0][1]*this._mat[1][0];
		r._mat[0][1] = t._mat[0][0]*this._mat[0][1] + t._mat[0][1]*this._mat[1][1];
		r._mat[0][2] = t._mat[0][0]*this._mat[0][2] + t._mat[0][1]*this._mat[1][2] + t._mat[0][2];
		r._mat[1][0] = t._mat[1][0]*this._mat[0][0] + t._mat[1][1]*this._mat[1][0];
		r._mat[1][1] = t._mat[1][0]*this._mat[0][1] + t._mat[1][1]*this._mat[1][1];
		r._mat[1][2] = t._mat[1][0]*this._mat[0][2] + t._mat[1][1]*this._mat[1][2] + t._mat[1][2];
		return r;
	}
	@Override
	public AffineTransform2 pretransform(AffineTransform2 t) { return t.transform(this); }
	@Override
	public Shape createTransformedShape(Shape shape) { return toJavaAffineTransform().createTransformedShape(shape); } // for now...

	public AffineTransform toJavaAffineTransform(){ return new AffineTransform(_mat[0][0], _mat[0][1], _mat[0][2], _mat[1][0], _mat[1][1], _mat[1][2]); }

	public static AffineTransform2 createScaleTransform(double x, double y){ return new AffineTransform2().scale(x, y); }
	public static AffineTransform2 createScaleTransform(double x){ return new AffineTransform2().scale(x); }
	public static AffineTransform2 createScaleTransform(Vector2 v){ return new AffineTransform2().scale(v); }
	public static AffineTransform2 createShearTransform(double x, double y){ return new AffineTransform2().shear(x, y); }
	public static AffineTransform2 createShearTransform(double x){ return new AffineTransform2().shear(x); }
	public static AffineTransform2 createShearTransform(Vector2 v){ return new AffineTransform2().shear(v); }
	public static AffineTransform2 createTranslateTransform(double x, double y){ return new AffineTransform2().translate(x, y); }
	public static AffineTransform2 createTranslateTransform(Vector2 v){ return new AffineTransform2().translate(v); }
	public static AffineTransform2 createRotationTransform(double theta){ return new AffineTransform2().rotate(theta); }

	private void updateType(){
		int type = TYPE_NONE;
		if (_mat[0][0] != 1 || _mat[1][1] != 1)
			type = type | TYPE_SCALE;
		if (type == TYPE_SCALE && _mat[0][0] == 0 && _mat[1][1] == 0)
			type = type | TYPE_NULL_SCALE;
		if (_mat[0][1] != 0 || _mat[1][0] != 0)
			type = type | TYPE_SHEAR;
		if (_mat[0][2] != 0 || _mat[1][2] != 0)
			type = type | TYPE_TRANSLATION;
		setType(type);
	}

	public boolean isTranslation(){ return (getType() & TYPE_TRANSLATION) == TYPE_TRANSLATION; }
	public boolean isScale(){ return (getType() & TYPE_SCALE) == TYPE_SCALE; }
	public boolean isScaleNull(){ return (getType() & TYPE_NULL_SCALE) == TYPE_NULL_SCALE; }
	public boolean isShear(){ return (getType() & TYPE_SHEAR) == TYPE_SHEAR; }
	public boolean isIdentity(){ return getType() == TYPE_NONE; }

	public AffineTransform2 rotate90(){
		double M00 = _mat[0][0], M01 = _mat[0][1], M10 = _mat[1][0], M11 = _mat[1][1], M02 = _mat[0][2], M12 = _mat[1][2];
		_mat[0][0] = M10; _mat[0][1] = M11; _mat[0][2] = M12;
		_mat[1][0] = -M00; _mat[1][1] = -M01; _mat[1][2] = -M02;
		return this;
	}
	public AffineTransform2 rotate180(){
		double M00 = _mat[0][0], M01 = _mat[0][1], M10 = _mat[1][0], M11 = _mat[1][1], M02 = _mat[0][2], M12 = _mat[1][2];
		_mat[0][0] = -M10; _mat[0][1] = -M11; _mat[0][2] = -M12;
		_mat[1][0] = -M00; _mat[1][1] = -M01; _mat[1][2] = -M02;
		return this;
	}
	public AffineTransform2 rotate270(){
		double M00 = _mat[0][0], M01 = _mat[0][1], M10 = _mat[1][0], M11 = _mat[1][1], M02 = _mat[0][2], M12 = _mat[1][2];
		_mat[0][0] = -M10; _mat[0][1] = -M11; _mat[0][2] = -M12;
		_mat[1][0] = M00; _mat[1][1] = M01; _mat[1][2] = M02;
		return this;
	}
	
	public AffineTransform2 flipX(){
		_mat[0][0] *= -1; _mat[0][1] *= -1; _mat[0][2] *= -1;
		return this;
	}
	public AffineTransform2 flipY(){
		_mat[1][0] *= -1; _mat[1][1] *= -1; _mat[1][2] *= -1;
		return this;
	}
	
}
