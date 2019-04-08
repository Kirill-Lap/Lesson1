package backi.in.business.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.math.Rectang;
import backi.in.business.math.Rnd;
import backi.in.business.pool.Enemy;
import backi.in.business.pool.EnemyPool;
import backi.in.business.sprite.EnemyShip;

public class EnemiesEmitter {

    private TextureRegion[] enemySmallRegions;

    private TextureRegion[] enemyMiddleRegions;

    private TextureRegion[] enemyBigRegions;

    private Rectang worldBounds;

    private float generateInterval = 2f;
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
            Enemy enemyType = Enemy.randomEnemy();
            TextureRegion[] currentRegions = enemySmallRegions;

            switch (enemyType) {
                case SMALL:
                    currentRegions = enemySmallRegions;
                    break;
                case MIDDLE:
                    currentRegions = enemyMiddleRegions;
                    break;
                case BIG:
                    currentRegions = enemyBigRegions;
                    break;
            }

            enemyShip.set(
                    currentRegions,
                    new Vector2(0, enemyType.ENEMY_V()),
                    new Vector2(0f, enemyType.ENEMY_BULLET_VY()),
                    enemyType.ENEMY_BULLETHEIGHT(),
                    bulletRegion,
                    enemyType.ENEMY_HEIGHT(),
                    enemyType.ENEMY_DAMAGE(),
                    enemyType.ENEMY_RELOADINTERVAL(),
                    enemyType.ENEMY_HP()
            );

            enemyShip.pos.x = Rnd.randomFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
