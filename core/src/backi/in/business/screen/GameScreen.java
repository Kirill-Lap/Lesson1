package backi.in.business.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import backi.in.business.base.BaseScreen;
import backi.in.business.base.Font;
import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.EnemyPool;
import backi.in.business.pool.ExplosionPool;
import backi.in.business.sprite.Background;
import backi.in.business.sprite.BtnGameOver;
import backi.in.business.sprite.BtnNewGame;
import backi.in.business.sprite.Bullet;
import backi.in.business.sprite.EnemyShip;
import backi.in.business.sprite.HealthBar;
import backi.in.business.sprite.MainShip;
import backi.in.business.sprite.Star;
import backi.in.business.sprite.TrackingStar;
import backi.in.business.utils.EnemiesEmitter;

public class GameScreen extends BaseScreen {
    private static final int STAR_COUNT = 64;
    private static final float FONT_SIZE = 0.02f;
    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final int HP_MAINSHIP = 20;
    private static final String LEVEL = "Level: ";

    private enum State {PLAYING, GAME_OVER};

    private Background background;
    private Texture backgroundImg;
    private TrackingStar starList[];
    private TextureAtlas atlas;
    private TextureAtlas btnAtlas;
    private MainShip mainShip;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private EnemiesEmitter enemiesEmitter;
    private ExplosionPool explosionPool;

    private BtnGameOver btnGameOver;
    private BtnNewGame btnNewGame;

    private Sound explosionSound;

    private Game game;

    private int frags;
    private int level;
    private State state;

    private Font font;
    private StringBuilder sbFrags;
    private StringBuilder sbHP;
    private StringBuilder sbLevel;

    private Sound mainShipShootSound;
    private Sound enemyShootSound;

    private HealthBar healthBar;


    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        mainShipShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        enemyShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        backgroundImg = new Texture("Textures/bg_2.png");
        background = new Background(new TextureRegion(backgroundImg));

        starList = new TrackingStar[STAR_COUNT];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new TrackingStar(new TextureRegion(new Texture("star2.png")));
        }
        atlas = new TextureAtlas("Textures/mainAtlas.tpack");
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, enemyShootSound);
        enemiesEmitter = new EnemiesEmitter(atlas, worldBounds, enemyPool);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, mainShipShootSound);
        mainShip.setHP(HP_MAINSHIP);
        btnAtlas = new TextureAtlas("Textures/btns.pack");
        btnGameOver = new BtnGameOver(btnAtlas);
        btnNewGame = new BtnNewGame(btnAtlas,this);
        font = new Font("font/StarGame.fnt", "font/StarGame.png");
        font.setSize(FONT_SIZE);
        sbFrags = new StringBuilder();
        sbHP = new StringBuilder();
        sbLevel = new StringBuilder();
        healthBar = new HealthBar(new TextureAtlas("Textures/HealthBar.pack"));
        startNewGame();


    }

    @Override
    public void resize(Rectang worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:starList) {
            star.resize(worldBounds);
        }
        if (state == State.PLAYING) {
            mainShip.resize(worldBounds);
        }
        //        btnGameOver.resize(worldBounds);
        btnNewGame.resize(worldBounds);
        healthBar.resize(worldBounds);

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
        for (TrackingStar star:starList) {
            star.update(delta,mainShip.getV());
        }
        explosionPool.updateActiveSprites(delta);
        healthBar.update(mainShip.getHp()/(float)HP_MAINSHIP);
        if (state == State.PLAYING) {
            mainShip.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            enemiesEmitter.generate(delta, level);

        }
    }

    private void checkCollisions(){
        if (state == State.GAME_OVER) {
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
                System.out.println("Main ship destroyed by " + enemyShip);
                state = State.GAME_OVER;
                return;
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
                    if (enemyShip.isDestroyed()) {
                        System.out.println(enemyShip + " is destroyed");
                        frags++;
                        level = 1+ frags/10;
                    }
                }
            }
        }

        for (Bullet bullet : bulletList) {
                if (bullet.getOwner() == mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (mainShip.gotHit(bullet)) {
                    mainShip.damage(bullet.getDamage());
//                    System.out.println("Main ship got damage by " + bullet + " bullets' owner: " + bullet.getOwner());
                    bullet.destroy();
                    if (mainShip.isDestroyed()) {
                        state = State.GAME_OVER;
                    }
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
        batch.setColor(0.9f, 0.9f, 0.05f, 0.5f);
        for (Star star:starList) {
            star.draw(batch);
        }
        batch.setColor(Color.WHITE);
        if (state == State.PLAYING) {
            mainShip.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
        } else if (state == State.GAME_OVER){
            btnGameOver.draw(batch);
            btnNewGame.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        healthBar.draw(batch);
        printInfo();
        batch.end();

    }

    private void printInfo(){
        sbFrags.setLength(0);
        sbLevel.setLength(0);
        sbHP.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags), worldBounds.getLeft() + 0.01f, worldBounds.getTop());
        font.draw(batch, sbLevel.append(LEVEL).append(level), worldBounds.pos.x, worldBounds.getTop(), Align.center);
//        font.draw(batch, sbHP.append(HP).append(mainShip.getHp()), worldBounds.getRight()-0.01f, worldBounds.getTop(), Align.right);
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        atlas.dispose();
        btnAtlas.dispose();
        explosionSound.dispose();
        mainShipShootSound.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        font.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchDown(touch, pointer);
        } else if (state == State.GAME_OVER){
            btnGameOver.touchDown(touch,pointer);
            btnNewGame.touchDown(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchUp(touch, pointer);
        } else if (state == State.GAME_OVER){
            btnGameOver.touchUp(touch,pointer);
            btnNewGame.touchUp(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return super.keyUp(keycode);
    }

    public void startNewGame(){
        state = State.PLAYING;
        frags=0;
        level=1;

        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
        healthBar.resetSize();
        mainShip.startNewGame(worldBounds);
        mainShip.setHP(HP_MAINSHIP);
    }
}
