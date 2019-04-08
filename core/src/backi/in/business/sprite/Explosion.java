package backi.in.business.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;

public class Explosion extends Sprite {
    private Sound sound;

    private float animateInterval = 0.0017f;
    private float animateTimer;

    public Explosion(TextureAtlas atlas, Sound sound) {
        super(atlas.findRegion("explosion"), 9, 9, 74);
        this.sound = sound;
    }

    public void set(float height, Vector2 pos) {
        setHeightProportion(height);
        this.pos.set(pos);
        sound.play();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        animateTimer+=delta;
        if (animateTimer>=animateInterval) {
            animateTimer=0;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
