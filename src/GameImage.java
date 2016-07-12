import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameImage extends Image{

	public Point location;
	
	public GameImage(String path) throws SlickException {
		super(path);
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
}
