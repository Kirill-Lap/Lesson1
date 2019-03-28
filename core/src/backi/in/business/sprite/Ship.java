package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;

public class Ship extends Sprite {

    protected Vector2 v = new Vector2();
    protected Vector2 vBullet = new Vector2();
    protected float bulletHeight;
    protected int damage;
    protected float hp;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;

    protected Rectang worldBounds;

    protected float reloadInterval;
    protected float reloadTimer;


    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rectang worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        reloadTimer += delta;

        if (reloadTimer > reloadInterval) {
            shoot();
            reloadTimer =0f;
        }
        pos.mulAdd(v, delta);

    }

    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, vBullet, bulletHeight, worldBounds, damage);
    }
}
