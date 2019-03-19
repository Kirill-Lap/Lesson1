package backi.in.business;

import com.badlogic.gdx.Game;

import backi.in.business.screen.MenuScreen;
import backi.in.business.screen.MenuScreen2;

public class StarGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen2(this));
    }
}
