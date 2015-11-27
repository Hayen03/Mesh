package hayen.util.math;

/**
 * Created by Hayen on 15-11-27.
 */
public interface  IVector <T extends IVector>{

	public T add(T b);
	public T multiply(double k);

}
