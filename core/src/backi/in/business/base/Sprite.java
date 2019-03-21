package backi.in.business.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.math.Rectang;
import backi.in.business.utils.RegionsUtils;

public class Sprite extends Rectang {
    private float angle;
    private float scale = 1f;
    protected TextureRegion[] regions;

    protected int frame=0;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        this.regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = RegionsUtils.split(region, rows, cols, frames);
    }

    public void setHeightProportion (float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void update (float delta) {

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

    public void resize(Rectang worldBounds) {

    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
