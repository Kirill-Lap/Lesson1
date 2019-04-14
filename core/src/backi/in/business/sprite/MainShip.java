package backi.in.business.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.Sprite;
import backi.in.business.math.Rectang;
import backi.in.business.pool.BulletPool;
import backi.in.business.pool.ExplosionPool;

public class MainShip extends Ship {

    private static final int NULL_POINTER = -1;
    private Rectang sensibleRadius;

//    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0.7f,0);

    private boolean pressedLeft = false;
    private boolean pressedRight = false;
    private boolean pressedUp = false;
    private boolean pressedDown = false;

    private int leftPointer = NULL_POINTER;
    private int rightPointer = NULL_POINTER;

    private static final float hitRadiusV = 0.55f;
    private static final float hitRadiusH = 0.9f;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletHeight = 0.02f;
        this.vBullet.set(0,0.5f);
        this.damage = 1;
        this.reloadInterval = 0.2f;
        this.explosionPool = explosionPool;
        sensibleRadius = new Rectang();
        sensibleRadius.pos.set(pos);
        sensibleRadius.pos.set(pos);
        sensibleRadius.setWidth(hitRadiusH*getWidth());
        sensibleRadius.setHeight(hitRadiusV*getHeigth());
        this.shootSound = shootSound;

    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void startNewGame(Rectang worldBounds){
        pos.x  = worldBounds.pos.x;
        setBottom(worldBounds.getBottom()+0.05f);
        revive();
    }

    @Override
    public void resize(Rectang worldBounds) {
        super.resize(worldBounds);
//        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom()+0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }

        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }

        if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
            stop();
        }

        if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop());
            stop();

        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != NULL_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != NULL_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            leftPointer = NULL_POINTER;
            checkPressed();
        } else {
            rightPointer = NULL_POINTER;
            checkPressed();
        }

//        stop();
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode){
            case 19:
            case 51:
                pressedUp = true;
                moveUp();
                break;
            case 20:
            case 47:
                pressedDown = true;
                moveDown();
                break;
            case 21:
            case 29:
                pressedLeft = true;
                moveLeft();
                break;
            case 22:
            case 32:
                pressedRight = true;
                moveRight();
                break;
            case 66:
                stop();
                break;
        }
        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode){
            case 19:
            case 51:
                pressedUp = false;
                checkPressed();
                break;
            case 20:
            case 47:
                pressedDown = false;
                checkPressed();
                break;
            case 21:
            case 29:
                pressedLeft = false;
                checkPressed();
                break;
            case 22:
            case 32:
                pressedRight = false;
                checkPressed();
                break;
            case 66:
                stop();
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).scl(-1);
    }

    private void moveUp(){
        v.set(v0).rotate(90);
    }

    private void moveDown(){
        v.set(v0).rotate(-90);
    }

    private void checkPressed() {
        if (rightPointer != NULL_POINTER) {
            moveRight();
            return;
        }

        if (leftPointer != NULL_POINTER) {
            moveLeft();
            return;
        }


        if (pressedRight) {
            moveRight();
            return;
        }
        if (pressedLeft) {
            moveLeft();
            return;
        }
        if (pressedUp) {
            moveUp();
            return;
        }
        if (pressedDown) {
            moveDown();
            return;
        }
        stop();
    }

    public Vector2 getV(){
        return v;
    }

    public boolean gotHit(Rectang bullet) {
        sensibleRadius.pos.set(pos);
        if (sensibleRadius.isInside(bullet.pos)) {
            return true;
        } else {
            return false;
        }
//        }
//        if ()
//
//        return !(
//                        bullet.getRight() < getLeft()
//                        || bullet.getLeft() > getRight()
//                        || bullet.getTop() > getBottom()
//                        || bullet.getBottom() > pos.y
//        );
    }

    private void stop() {
        v.setZero();
    }

}
