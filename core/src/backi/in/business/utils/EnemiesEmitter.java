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

    public void generate(float delta, int level) {
        generateTimer +=delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
//            int i = Rnd.randomInt(1,3);
            float allocator = Rnd.randomFloat(0f, 1f);
//            System.out.println("I = " + i);
            Enemy enemyType = Enemy.SMALL;
            TextureRegion[] currentRegions = enemySmallRegions;

            if (allocator >= 0f && allocator <= 0.6f) {
                currentRegions = enemySmallRegions;
                enemyType = Enemy.SMALL;
            } else if (allocator > 0.6f && allocator <= 0.9f) {
                currentRegions = enemyMiddleRegions;
                enemyType = Enemy.MIDDLE;
            } else if (allocator > 0.9f && allocator <=1f) {
                    currentRegions = enemyBigRegions;
                    enemyType = Enemy.BIG;
            }
            float levelScale = (1f + (float)(level-1)/10f);
            enemyShip.set(
                    currentRegions,
                    new Vector2(0, enemyType.ENEMY_V()*levelScale),
                    new Vector2(0f, enemyType.ENEMY_BULLET_VY()*levelScale),
                    enemyType.ENEMY_BULLETHEIGHT(),
                    bulletRegion,
                    enemyType.ENEMY_HEIGHT(),
                    enemyType.ENEMY_DAMAGE()*(int)levelScale,
                    enemyType.ENEMY_RELOADINTERVAL(),
                    enemyType.ENEMY_HP()*level
            );

            enemyShip.pos.x = Rnd.randomFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop() - 0.01f);
        }
    }
}
