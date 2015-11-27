package hayen.util;

import java.awt.*;

/**
 * Abstract construction of a Transform object. Serve as the base class for every Transform, be it in 2 dimension, or 3, or even 4, that I make.
 * @author Hayen
 */
public abstract class Transform<T extends Transform> {

	/**
	 * This state means that the type of the Transform is unknown
	 */
	public static final int TYPE_UNKNOWN = -1;
	/**
	 * This state means that NO transformation is applied, AKA. Identity
	 */
	public static final int TYPE_NONE = 0;
	/**
	 * If this flag is active, it means this transform apply a translation
	 * ( translation != 0 )
	 */
	public static final int TYPE_TRANSLATION = 2;
	/**
	 * If this flag is active, it means the transform apply a scaling effect
	 * ( scale != 1 )
	 */
	public static final int TYPE_SCALE = 3;
	/**
	 * If this flag is active, it means the transform apply a shearing effect
	 * ( shear != 0 )
	 */
	public static final int TYPE_SHEAR = 8;
	/**
	 * If this flag is active, it means the scale of this transform is null
	 * ( scale == 0 )
	 */
	public static final int TYPE_NULL_SCALE = 16;
	/**
	 * This state means that the transform apply every type transformation
	 */
	public static final int TYPE_GENERAL = TYPE_TRANSLATION | TYPE_SCALE | TYPE_SHEAR;

	private int _dimension = 0;

	/**
	 * Type of this transform.
	 * @see #TYPE_NONE
	 * @see #TYPE_SCALE
	 * @see #TYPE_TRANSLATION
	 * @see #TYPE_SHEAR
	 * @see #TYPE_NULL_SCALE
	 * @see #TYPE_GENERAL
	 * @see #TYPE_UNKNOWN
	 */
	private int _type;

	public Transform(){
		_type = TYPE_UNKNOWN;
	}
	public abstract T transform(T t);
	public abstract T pretransform(T t);

	protected void setType(int t){ _type = t; }
	public int getType(){ return _type; }
	
	public boolean isAffine(){ return false; }
}
