package hayen.mesh;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public interface IDrawable {
	public void draw(Graphics g, AffineTransform transform);
}
