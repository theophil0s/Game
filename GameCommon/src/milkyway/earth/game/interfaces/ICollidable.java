package milkyway.earth.game.interfaces;

import milkyway.earth.object.GameObject;

public interface ICollidable {

	public void isCollidingWith(GameObject object);

	public void checkContains(GameObject object);

	public void setContains(GameObject object);

}
