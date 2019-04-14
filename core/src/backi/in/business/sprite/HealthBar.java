package backi.in.business.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;

public class HealthBar extends Sprite {

    private Rectang worldBounds;
    private static final float hbSize = 0.2f;
    private float currentSize;

    public HealthBar(TextureAtlas atlas) {
        super(atlas.findRegion("Boss_HP_Center"));
        setWidth(hbSize);
        setHeightProportion(0.02f);
    }

    @Override
    public void update(float hpLeft) {
        currentSize = hbSize*hpLeft;
        setWidth(currentSize);
        setRight(worldBounds.getRight());
    }

    @Override
    public void resize(Rectang worldBounds) {
        this.worldBounds = worldBounds;
        setTop(worldBounds.getTop());
        setRight(worldBounds.getRight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        if ((currentSize/hbSize) < 0.5f) {
//            System.out.println(currentSize/hbSize);
            batch.setColor(0.5f, 0.2f, 0f, 0.2f + currentSize/hbSize);
        }
        super.draw(batch);
        batch.setColor(Color.WHITE);
    }

    public void resetSize() {
        currentSize = hbSize;
        setWidth(currentSize);
    }
}
