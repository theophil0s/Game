package milkyway.earth.network.message;

public class AddPlayer extends PlayerIDMessage {

	private String name;
	private float x;
	private float y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
