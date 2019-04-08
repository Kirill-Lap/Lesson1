package backi.in.business.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;

public class BtnGameOver extends ScalableButton {

    public BtnGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.05f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        pos.set (0f, 0.25f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

}
