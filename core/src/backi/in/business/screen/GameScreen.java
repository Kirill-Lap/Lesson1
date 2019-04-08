package backi.in.business.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import backi.in.business.base.BaseScreen;
import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.EnemyPool;
import backi.in.business.pool.ExplosionPool;
import backi.in.business.sprite.Background;
import backi.in.business.sprite.BtnGameOver;
import backi.in.business.sprite.BtnNewGame;
import backi.in.business.sprite.Bullet;
import backi.in.business.sprite.EnemyShip;
import backi.in.business.sprite.MainShip;
import backi.in.business.sprite.Star;
import backi.in.business.utils.EnemiesEmitter;

public class GameScreen extends BaseScreen {
    private static final int STAR_COUNT = 64;


    private Background background;
    private Texture backgroundImg;
    private Star starList[];
    TextureAtlas atlas;
    private MainShip mainShip;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private EnemiesEmitter enemiesEmitter;
    private ExplosionPool explosionPool;

    private BtnGameOver btnGameOver;
    private BtnNewGame btnNewGame;

    private Sound explosionSound;

    private Game game;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        backgroundImg = new Texture("Textures/bg.png");
        background = new Background(new TextureRegion(backgroundImg));

        starList = new Star[STAR_COUNT];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(new TextureRegion(new Texture("star2.png")));
        }
        atlas = new TextureAtlas("Textures/mainAtlas.tpack");
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        enemiesEmitter = new EnemiesEmitter(atlas, worldBounds, enemyPool);
        mainShip = new MainShip(atlas, bulletPool, explosionPool);
        btnGameOver = new BtnGameOver(atlas);
        btnNewGame = new BtnNewGame(atlas,game);
    }

    @Override
    public void resize(Rectang worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:starList) {
            star.resize(worldBounds);
        }
        if (!mainShip.isDestroyed()) {
            mainShip.resize(worldBounds);
        } else {
            btnGameOver.resize(worldBounds);
            btnNewGame.resize(worldBounds);
        }
        btnGameOver.resize(worldBounds);
        btnNewGame.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void update(float delta) {
        background.update(delta);
        for (Star star:starList) {
            star.update(delta);
        }
        explosionPool.updateActiveSprites(delta);

        if (!mainShip.isDestroyed()) {
            mainShip.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            enemiesEmitter.generate(delta);

        }
    }

    private void checkCollisions(){
        if (mainShip.isDestroyed()) {
            return;
        }
        List<EnemyShip> enemyList = enemyPool.getActiveObjects();
        List<Bullet> bulletList = bulletPool.getActiveObjects();


        for (EnemyShip enemyShip:enemyList) {
            if(enemyShip.isDestroyed()) {
                continue;
            }
            float minDist = enemyShip.getHalfWidth()+mainShip.getHalfWidth();
            if (enemyShip.pos.dst(mainShip.pos) < minDist) {
                enemyShip.destroy();
                mainShip.damage(mainShip.getHp());
            }
        }
        for (EnemyShip enemyShip: enemyList) {
            if (enemyShip.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemyShip.gotHit(bullet)) {
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }

        for (Bullet bullet : bulletList) {
                if (bullet.getOwner() == mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (mainShip.gotHit(bullet)) {
                    mainShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
        }
    }

    private void deleteAllDestroyed(){

        bulletPool.freeDestroyedActiveSprites();
        explosionPool.freeDestroyedActiveSprites();
        enemyPool.freeDestroyedActiveSprites();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.57f, 0.35f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star:starList) {
            star.draw(batch);
        }
        if (!mainShip.isDestroyed()) {
            mainShip.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
        } else {
            btnGameOver.draw(batch);
            btnNewGame.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        batch.end();

    }


    @Override
    public void dispose() {
        backgroundImg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (!mainShip.isDestroyed()) {
            mainShip.touchDown(touch, pointer);
        } else {
            btnGameOver.touchDown(touch,pointer);
            btnNewGame.touchDown(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (!mainShip.isDestroyed()) {
            mainShip.touchUp(touch, pointer);
        } else {
            btnGameOver.touchUp(touch,pointer);
            btnNewGame.touchUp(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!mainShip.isDestroyed()) {
            mainShip.keyDown(keycode);
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!mainShip.isDestroyed()) {
            mainShip.keyUp(keycode);
        }
        return super.keyUp(keycode);
    }


}
