package backi.in.business.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.ExplosionPool;

public class EnemyShip extends Ship {

    private MovingHealthBar movingHealthBar;
    private int fullHP;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rectang worldBounds, Sound shootSound) {
        this.worldBounds = worldBounds;
        this.regions = new TextureRegion[2];
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        TextureAtlas atlas = new TextureAtlas("Textures/mhb.pack");
        movingHealthBar = new MovingHealthBar(atlas, this);

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
        fullHP = hp;

    }

    public int getFullHP() {
        return fullHP;
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
        movingHealthBar.update(delta);
        if (getTop()<worldBounds.getBottom()) {
            this.silentDestroy();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        movingHealthBar.draw(batch);
    }

    @Override
    public void resize(Rectang worldBounds) {
        this.worldBounds = worldBounds;
    }

}
