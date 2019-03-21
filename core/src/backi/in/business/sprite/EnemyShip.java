package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;
import backi.in.business.utils.RegionsUtils;

public class EnemyShip extends Sprite {
    private Rectang worldBounds;
    private Vector2 v;
    private Vector2 vBullet;
    private int damage;
    private int life;

    public EnemyShip() {
        v = new Vector2();
        vBullet = new Vector2();
        this.regions = new TextureRegion[2];

    }

    public void set(
        TextureRegion region,
        Vector2 pos0,
        Vector2 v0,
        Vector2 vBullet,
        float height,
        Rectang worldBounds,
        int damage,
        int life
    ) {
        this.regions = RegionsUtils.split(region, 1,2, 2);
        pos.set(pos0);
        v.set(v0);
        this.vBullet.set(vBullet);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
        this.life = life;
    }

    @Override
    public void update(float delta) {
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
