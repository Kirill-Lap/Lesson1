package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
//        setSize(1.6f,1f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        setHeightProportion(worldBounds.getHeigth());
        pos.set(worldBounds.pos);
    }
}
