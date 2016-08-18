package milkyway.earth.game.effects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class RenderEffect {

	public static void renderAsGhost(Image image, Animation animation, float renderX, float renderY, float renderW,
			float renderH, float r, float g, float b, float a) {

		GL11.glBlendFunc(GL11.GL_CONSTANT_COLOR, GL11.GL_ONE);
		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
		GL14.glBlendColor(r, g, b, a);

		if (animation != null) {

			animation.draw(renderX, renderY, renderW, renderH, null);

		} else if (image != null) {

			image.draw(renderX, renderY, renderW, renderH, null);

		}
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

	}
}
