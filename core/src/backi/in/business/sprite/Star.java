package backi.in.business.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;

import static backi.in.business.math.Rnd.randomFloat;

public class Star extends Sprite {

    private Vector2 v;
    private Rectang worldBounds;

    public Star(TextureRegion region) {
        super(region);
        float vX = randomFloat(-0.01f, 0.01f);
        float vY = randomFloat(-0.4f, -0.1f);
        v = new Vector2(vX,vY);
        float size = randomFloat(0.01f, 0.011f);
        setHeightProportion(size);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
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
