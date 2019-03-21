package backi.in.business.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.BaseScreen;
import backi.in.business.math.Rectang;
import backi.in.business.sprite.Background;
import backi.in.business.sprite.MainShip;
import backi.in.business.sprite.Star;

public class GameScreen extends BaseScreen {
    private static final int STAR_COUNT = 64;


    private Background background;
    private Texture backgroundImg;
    private Star starList[];
    TextureAtlas atlas;
    private MainShip mainShip;


    @Override
    public void show() {
        super.show();
        backgroundImg = new Texture("Textures/bg.png");
        background = new Background(new TextureRegion(backgroundImg));

        starList = new Star[STAR_COUNT];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(new TextureRegion(new Texture("star2.png")));
        }
        atlas = new TextureAtlas("Textures/mainAtlas.tpack");
        mainShip = new MainShip(atlas);
    }

    @Override
    public void resize(Rectang worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:starList) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        background.update(delta);
        for (Star star:starList) {
            star.update(delta);
        }
        mainShip.update(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0.57f, 0.35f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star:starList) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        batch.end();

    }


    @Override
    public void dispose() {
        backgroundImg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }


}
