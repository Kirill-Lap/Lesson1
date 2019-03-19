package backi.in.business.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.BaseScreen;
import backi.in.business.math.Rectang;
import backi.in.business.sprite.Background;
import backi.in.business.sprite.BtnExit;
import backi.in.business.sprite.BtnPlay;
import backi.in.business.sprite.Star;


// Рабочий экран
public class MenuScreen2 extends BaseScreen {
    private static final int STAR_COUNT = 32;

    private Game game;

    private Background background;
    private Texture backgroundImg;
    private Star starList[];
    TextureAtlas atlas;

    private BtnExit btnExit;
    private BtnPlay btnPlay;
    private Music music;

    public MenuScreen2(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("stairway.mp3"));
        music.setVolume(0.5f);
        music.play();
        backgroundImg = new Texture("tsn.jpg");
        background = new Background(new TextureRegion(backgroundImg));
        starList = new Star[STAR_COUNT];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star();
        }
        atlas = new TextureAtlas("Textures/btns.pack");
        btnExit = new BtnExit(atlas);
        btnPlay = new BtnPlay(atlas, game);
    }

    @Override
    public void resize(Rectang worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:starList) {
            star.resize(worldBounds);
        }
        btnExit.resize(worldBounds);
        btnPlay.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (Star star:starList) {
            star.update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.57f, 0.35f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star:starList) {
            star.draw(batch);
        }
        btnExit.draw(batch);
        btnPlay.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        music.dispose();
        backgroundImg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btnExit.touchDown(touch, pointer);
        btnPlay.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnExit.touchUp(touch,pointer);
        btnPlay.touchUp(touch, pointer);
        return false;
    }
}
