package backi.in.business.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.BaseScreen;
import backi.in.business.sprite.Background;
import backi.in.business.sprite.BadLogic;


// Рабочий экран
public class MenuScreen2 extends BaseScreen {
    private BadLogic badLogic;
    private Background background;
    private Texture img;
    private Texture backgroundImg;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        backgroundImg = new Texture("tsn.jpg");
        badLogic = new BadLogic(new TextureRegion(img));
        background = new Background(new TextureRegion(backgroundImg));

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.57f, 0.35f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        badLogic.draw(batch);
        batch.end();
        badLogic.update();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        badLogic.touchDown(touch, pointer);
        return false;
    }
}
