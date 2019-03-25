package backi.in.business.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.math.Rectang;
import backi.in.business.math.Rnd;
import backi.in.business.pool.EnemyPool;
import backi.in.business.sprite.EnemyShip;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLETHEIGHT = 0.015f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.25f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOADINTERVAL = 1.5f;
    private static final int ENEMY_SMALL_HP = 1;
    private TextureRegion[] enemySmallRegions;
    private Vector2 enemySmallV = new Vector2(0f, -0.12f);

    private static final float ENEMY_MIDDLE_HEIGHT = 0.12f;
    private static final float ENEMY_MIDDLE_BULLETHEIGHT = 0.02f;
    private static final float ENEMY_MIDDLE_BULLET_VY = -0.3f;
    private static final int ENEMY_MIDDLE_DAMAGE = 1;
    private static final float ENEMY_MIDDLE_RELOADINTERVAL = 1f;
    private static final int ENEMY_MIDDLE_HP = 2;
    private TextureRegion[] enemyMiddleRegions;
    private Vector2 enemyMiddleV = new Vector2(0f, -0.10f);

    private static final float ENEMY_BIG_HEIGHT = 0.16f;
    private static final float ENEMY_BIG_BULLETHEIGHT = 0.022f;
    private static final float ENEMY_BIG_BULLET_VY = -0.35f;
    private static final int ENEMY_BIG_DAMAGE = 2;
    private static final float ENEMY_BIG_RELOADINTERVAL = 0.8f;
    private static final int ENEMY_BIG_HP = 3;
    private TextureRegion[] enemyBigRegions;
    private Vector2 enemyBigV = new Vector2(0f, -0.08f);

    private Rectang worldBounds;

    private float generateInterval = 4f;
    private float generateTimer = 0f;

    private TextureRegion bulletRegion;
    private EnemyPool enemyPool;



    public EnemiesEmitter(TextureAtlas atlas, Rectang worldBounds, EnemyPool enemyPool) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemySmallRegions = RegionsUtils.split(textureRegion0, 1,2,2);
        this.enemyMiddleRegions = RegionsUtils.split(textureRegion1, 1,2,2);
        this.enemyBigRegions = RegionsUtils.split(textureRegion2, 1,2,2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generate(float delta) {
        generateTimer +=delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
            int i = Rnd.randomInt(1,3);
            System.out.println("I = " + i);
            switch (i){
                case 1:
                    enemyShip.set(
                            enemySmallRegions,
                            enemySmallV,
                            new Vector2(0f, ENEMY_SMALL_BULLET_VY),
                            ENEMY_SMALL_BULLETHEIGHT,
                            bulletRegion,
                            ENEMY_SMALL_HEIGHT,
                            ENEMY_SMALL_DAMAGE,
                            ENEMY_SMALL_RELOADINTERVAL,
                            ENEMY_SMALL_HP
                    );
                    break;
                case 2:
                    enemyShip.set(
                            enemyMiddleRegions,
                            enemyMiddleV,
                            new Vector2(0f, ENEMY_MIDDLE_BULLET_VY),
                            ENEMY_MIDDLE_BULLETHEIGHT,
                            bulletRegion,
                            ENEMY_MIDDLE_HEIGHT,
                            ENEMY_MIDDLE_DAMAGE,
                            ENEMY_MIDDLE_RELOADINTERVAL,
                            ENEMY_MIDDLE_HP
                    );
                    break;
                case 3:
                    enemyShip.set(
                            enemyBigRegions,
                            enemyBigV,
                            new Vector2(0f, ENEMY_BIG_BULLET_VY),
                            ENEMY_BIG_BULLETHEIGHT,
                            bulletRegion,
                            ENEMY_BIG_HEIGHT,
                            ENEMY_BIG_DAMAGE,
                            ENEMY_BIG_RELOADINTERVAL,
                            ENEMY_BIG_HP
                    );
                    break;
            }

            enemyShip.pos.x = Rnd.randomFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
