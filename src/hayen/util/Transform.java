package hayen.util;

import java.awt.*;

/**
 * Created by Hayen on 15-11-24.
 */
public abstract class Transform<T extends Transform> {

	private int _dimension = 0;
	
	public abstract T transform(T t);
	public abstract T pretransform(T t);
	
	public boolean isAffine(){ return false; }
}
