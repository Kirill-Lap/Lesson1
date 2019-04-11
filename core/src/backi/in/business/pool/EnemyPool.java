package backi.in.business.pool;

import com.badlogic.gdx.audio.Sound;

import backi.in.business.base.SpritesPool;
import backi.in.business.math.Rectang;
import backi.in.business.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rectang worldBounds;

    private Sound shootSound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rectang worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds, shootSound);
    }
}
