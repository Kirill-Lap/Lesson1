package backi.in.business.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;

import static backi.in.business.math.Rnd.randomFloat;

public class Star extends Sprite {

    protected Vector2 v;
    private Rectang worldBounds;
    private float shiningScale = 0.5f;
    private static final float shiningScaleRate = 1f/60f;
    private float currSize;
    private float size;

    public Star(TextureRegion region) {
        super(region);
        float vX = randomFloat(-0.01f, 0.01f);
        float vY = randomFloat(-0.15f, -0.03f);
        v = new Vector2(vX,vY);
        size = randomFloat(0.004f, 0.010f);
        currSize = randomFloat(size, size*(1f+shiningScale));
        setHeightProportion(currSize);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (!((shiningScale < 0  && currSize > size) || (shiningScale > 0 && currSize <size*(1+shiningScale)))) {
            shiningScale*=-1f;
        }

        currSize+=size*shiningScale*shiningScaleRate;
        setHeightProportion(currSize);
        handleBounds();
    }

    @Override
    public void resize(Rectang worldBounds) {
        this.worldBounds = worldBounds;
        float posX = randomFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = randomFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX,posY);
    }

    private void handleBounds(){
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
    }

}
