package backi.in.business.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;

public class BtnExit extends ScalableButton {
    public BtnExit(TextureAtlas atlas) {
        super(atlas.findRegion("btn_exit"));
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        setBottom(worldBounds.getBottom() + 0.03f);
        setLeft(worldBounds.getLeft() + 0.03f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
