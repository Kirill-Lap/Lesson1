package backi.in.business.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;
import backi.in.business.screen.GameScreen;

public class BtnPlay extends ScalableButton {

    private Game game;

    public BtnPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("play"));
        this.game = game;
        setHeightProportion(0.1f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        pos.set(worldBounds.pos);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
