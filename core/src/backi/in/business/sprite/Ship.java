package backi.in.business.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.ExplosionPool;

public class Ship extends Sprite {

    protected Vector2 v = new Vector2();
    protected Vector2 vBullet = new Vector2();
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;

    protected Rectang worldBounds;

    protected float reloadInterval;
    protected float reloadTimer;

    protected  float damageAnimateInterval = 0.1f;
    protected float damageAnimateTimer;

    protected Sound shootSound;

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

        if (reloadTimer >= reloadInterval) {
            shoot();
            reloadTimer =0f;
        }


        pos.mulAdd(v, delta);
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) {
            frame = 0;
        }
    }

    public void damage(int damage){
        frame = 1;
        damageAnimateTimer = 0;
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void silentDestroy() {
        super.destroy();
    }

    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, vBullet, bulletHeight, worldBounds, damage);
        if (shootSound!= null) shootSound.play();
    }

    public void boom(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.getHeigth(), this.pos);
    }

    public int getHp() {
        return hp;
    }
}
