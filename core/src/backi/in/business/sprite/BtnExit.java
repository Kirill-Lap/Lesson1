package backi.in.business.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;

public class BtnExit extends ScalableButton {
    public BtnExit(TextureAtlas atlas) {
        super(atlas.findRegion("exit"));
        setHeightProportion(0.1f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        pos.set(worldBounds.pos.x, worldBounds.pos.y- 0.25f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
