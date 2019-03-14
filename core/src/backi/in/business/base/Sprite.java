package backi.in.business.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import backi.in.business.math.Rectang;

public class Sprite extends Rectang {
    private float angle;
    private float scale = 1f;
    private TextureRegion[] regions;

    private int frame=0;

    public Sprite(TextureRegion region) {
        this.regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeigth(),
                scale, scale,
                angle
        );
    }
}
