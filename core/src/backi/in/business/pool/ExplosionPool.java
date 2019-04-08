package backi.in.business.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import backi.in.business.base.SpritesPool;
import backi.in.business.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureAtlas atlas;
    private Sound sound;

    public ExplosionPool(TextureAtlas atlas, Sound sound) {
        this.atlas = atlas;
        this.sound = sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(atlas, sound);
    }
}
