package backi.in.business.pool;

import backi.in.business.base.SpritesPool;
import backi.in.business.math.Rectang;
import backi.in.business.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private Rectang worldBounds;

    public EnemyPool(BulletPool bulletPool, Rectang worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds);
    }
}
