package backi.in.business.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ScalableButton extends Sprite {

    private static final float PRESS_SCALE = 0.9f;

    private int pointer;
    private boolean isPressed;

    public ScalableButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isInside(touch)) {
            return false;
        }
        this.pointer = pointer;
        setScale(PRESS_SCALE);
        isPressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer!= pointer || !isPressed) {
            return false;
        }
        if (isInside(touch)) {
            action();
//            return false;
        }
        isPressed = false;
        setScale(1f);
        return false;
    }

    public abstract void action();
}
