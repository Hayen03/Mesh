package hayen.util;

import java.awt.Shape;

import hayen.util.math.Matrix;
import hayen.util.math.Matrix3x3;
import hayen.util.math.Vector2;

/**
 * Created by Hayen on 15-11-25.
 */
public class AffineTransform2 extends Transform2<AffineTransform2>{

	private Matrix3x3 _mat;
	
	public AffineTransform2(){
		_mat = new Matrix3x3();
	}
	
	@Override
	public AffineTransform2 shear(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 shear(Vector2 v) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 shear(double v) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 rotate(double theta) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 scale(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 scale(Vector2 v) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 scale(double v) {
		// TODO Auto-generated method stub
		return null;
	}
	public AffineTransform2 translate(double x, double y){
		return this;
	}
	public AffineTransform2 translate(Vector2 v){
		return translate(v.getX(), v.getY());
	}
	
	@Override
	public Vector2 getScale() {
		return new Vector2(_mat.get(0, 0), _mat.get(1,  1));
	}
	@Override
	public double getScaleX() {
		return _mat.get(0, 0);
	}
	@Override
	public double getScaleY() {
		return _mat.get(1, 1);
	}
	@Override
	public Vector2 getShear() {
		return new Vector2(_mat.get(0, 1), _mat.get(1, 0));
	}
	@Override
	public double getShearX() {
		return _mat.get(0, 1);
	}
	@Override
	public double getShearY() {
		// TODO Auto-generated method stub
		return _mat.get(1, 0);
	}
	@Override
	public double getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Vector2 getTranslation(){
		// TODO: implementation
		return null;
	}
	public double getTranslationX(){
		// TODO: implementation
		return 0;
	}
	public double getTranslationY(){
		// TODO: implementation
		return 0;
	}
	@Override
	public Matrix getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AffineTransform2 transform(AffineTransform2 t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AffineTransform2 pretransform(AffineTransform2 t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Shape createTransformedShape(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AffineTransform2 createScaleTransform(double x, double y){ return new AffineTransform2().scale(x, y); }
	public static AffineTransform2 createScaleTransform(double x){ return new AffineTransform2().scale(x); }
	public static AffineTransform2 createScaleTransform(Vector2 v){ return new AffineTransform2().scale(v); }
	public static AffineTransform2 createShearTransform(double x, double y){ return new AffineTransform2().shear(x, y); }
	public static AffineTransform2 createShearTransform(double x){ return new AffineTransform2().shear(x); }
	public static AffineTransform2 createShearTransform(Vector2 v){ return new AffineTransform2().shear(v); }
	public static AffineTransform2 createTranslateTransform(double x, double y){ return new AffineTransform2().translate(x, y); }
	public static AffineTransform2 createTranslateTransform(Vector2 v){ return new AffineTransform2().translate(v); }
	public static AffineTransform2 createRotationTransform(double theta){ return new AffineTransform2().rotate(theta); }
	
}
