package backi.in.business.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import backi.in.business.base.ScalableButton;
import backi.in.business.math.Rectang;
import backi.in.business.screen.GameScreen;

public class BtnNewGame extends ScalableButton {

    private Game game;

    public BtnNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
        setHeightProportion(0.05f);
    }

    @Override
    public void resize(Rectang worldBounds) {
        pos.set(0f, -0.25f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
