package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import backi.in.business.base.ScalableButton;
import backi.in.business.screen.GameScreen;

public class BtnNewGame extends ScalableButton {

    private GameScreen gameScreen;

    public BtnNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("new_game"));
        setHeightProportion(0.07f);
        pos.set(0f, -0.25f);
        this.gameScreen = gameScreen;
    }

//    @Override
//    public void resize(Rectang worldBounds) {
//        pos.set(0f, -0.25f);
//    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
