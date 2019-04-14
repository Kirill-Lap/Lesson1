package backi.in.business.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import backi.in.business.base.Sprite;

public class MovingHealthBar extends Sprite {

    private EnemyShip masterShip;

    public MovingHealthBar(TextureAtlas atlas, EnemyShip ship) {
        super(atlas.findRegion("moving_bar"), 2,2, 4);
        masterShip = ship;
        setWidth(0.015f);
        setLeft(masterShip.getRight());
        setTop(masterShip.getTop());
        setHeightProportion( 0.05f);//masterShip.getHeigth()/3f);
    }

    @Override
    public void update(float delta) {
        float hpPercentage = (float)masterShip.getHp()/(float)masterShip.getFullHP();
        if (hpPercentage>= 0.75f) {
            frame = 0;
        } else if (hpPercentage<0.75 && hpPercentage>= 0.5f) {
            frame = 1;
        } else if (hpPercentage<0.5 && hpPercentage>= 0.25f) {
            frame = 2;
        } else if (hpPercentage<0.25 ) {
            frame = 3;
        }
        setLeft(masterShip.getRight());
        setTop(masterShip.getTop());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
