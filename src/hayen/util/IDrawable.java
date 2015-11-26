package hayen.util;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public interface IDrawable {
	public void draw(Graphics g, Transform transform);
}
