package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TrackingStar extends Star {

    private Vector2 vHorizontal;
    private Vector2 vBuf;
    private final float scale = 0.2f;

    public TrackingStar(TextureRegion region) {
        super(region);
        vHorizontal = new Vector2();
        vBuf = new Vector2();
        v.y *= 0.7f;
    }

    public void update(float delta, Vector2 vShip) {
        if (Math.abs(vShip.x) > 0f) {
            vBuf.set(v);
            vHorizontal.set(vShip.x, 0f).rotate(180);
            v.mulAdd(vHorizontal, scale);
        }
        super.update(delta);
        if (!vBuf.isZero()) {
            v.set(vBuf);
            vBuf.setZero();
            vHorizontal.setZero();
        }
    }
}
