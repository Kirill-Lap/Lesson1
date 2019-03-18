package backi.in.business.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;
import backi.in.business.screen.GameScreen;

public class BtnPlay extends ScalableButton {

    private Game game;

    public BtnPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btn_start"));
        this.game = game;
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        setBottom(worldBounds.getBottom() + 0.03f);
        setRight(worldBounds.getRight() - 0.03f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
