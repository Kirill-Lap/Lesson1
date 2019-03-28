package backi.in.business.pool;

import backi.in.business.base.SpritesPool;
import backi.in.business.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
