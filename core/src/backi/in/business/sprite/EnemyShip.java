package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.ExplosionPool;

public class EnemyShip extends Ship {


    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rectang worldBounds) {
        this.worldBounds = worldBounds;
        this.regions = new TextureRegion[2];
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;

    }

    public void set(
        TextureRegion[] region,
        Vector2 v,
        Vector2 vBullet,
        float bulletHeight,
        TextureRegion bulletRegion,
        float height,
        int damage,
        float reloadInterval,
        int hp
    ) {


        this.regions = region;
        this.v.set(v);
        this.vBullet.set(vBullet);
        this.bulletHeight = bulletHeight;
        this.bulletRegion = bulletRegion;
        setHeightProportion(height);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;

    }

    public boolean gotHit(Rectang bullet) {
        return !(
                bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
                );
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rectang worldBounds) {
        this.worldBounds = worldBounds;
    }

}
