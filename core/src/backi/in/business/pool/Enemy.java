package backi.in.business.pool;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Enemy {
    SMALL (0.1f, 0.010f, -0.35f, 1, 0.3f, 1, -0.15f),
    MIDDLE (0.12f, 0.02f, -0.25f, 2,   0.4f, 5, -0.10f),
    BIG (0.16f, 0.03f, -0.16f, 10, 0.8f, 10, -0.08f);

    private final float ENEMY_HEIGHT;
    private final float ENEMY_BULLETHEIGHT ;
    private final float ENEMY_BULLET_VY;
    private final int ENEMY_DAMAGE;
    private final float ENEMY_RELOADINTERVAL;
    private final int ENEMY_HP;
    private final float ENEMY_V;

//    private Vector2 enemySmallV = new Vector2(0f, -0.12f);

    private static final List<Enemy> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random random = new Random();

    public static Enemy randomEnemy() {
        return VALUES.get(random.nextInt(SIZE));
    }



    Enemy(float ENEMY_HEIGHT, float ENEMY_BULLETHEIGHT, float ENEMY_BULLET_VY, int ENEMY_DAMAGE, float ENEMY_RELOADINTERVAL, int ENEMY_HP, float ENEMY_V) {
        this.ENEMY_HEIGHT = ENEMY_HEIGHT;
        this.ENEMY_BULLETHEIGHT = ENEMY_BULLETHEIGHT;
        this.ENEMY_BULLET_VY = ENEMY_BULLET_VY;
        this.ENEMY_DAMAGE = ENEMY_DAMAGE;
        this.ENEMY_RELOADINTERVAL = ENEMY_RELOADINTERVAL;
        this.ENEMY_HP = ENEMY_HP;
        this.ENEMY_V = ENEMY_V;
    }

    public float ENEMY_HEIGHT() {
        return ENEMY_HEIGHT;
    }

    public float ENEMY_BULLETHEIGHT() {
        return ENEMY_BULLETHEIGHT;
    }

    public float ENEMY_BULLET_VY() {
        return ENEMY_BULLET_VY;
    }

    public int ENEMY_DAMAGE() {
        return ENEMY_DAMAGE;
    }

    public float ENEMY_RELOADINTERVAL() {
        return ENEMY_RELOADINTERVAL;
    }

    public int ENEMY_HP() {
        return ENEMY_HP;
    }

    public float ENEMY_V() {
        return ENEMY_V;
    }
}
