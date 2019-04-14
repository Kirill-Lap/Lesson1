package backi.in.business.math;

import com.badlogic.gdx.math.Vector2;

public class Rectang {

    public final Vector2 pos = new Vector2(); // позиция центра
    protected float halfWidth;
    protected float halfHeight;

    public Rectang() {
    }

    public Rectang(Rectang from){
        this(from.pos.x, from.pos.y, from.getHalfWidth(), from.getHalfHeight());
    }

    public Rectang(float x, float y, float halfWidth, float halfHeight) {
        pos.set(x,y);
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public float getLeft() {
        return pos.x - halfWidth;
    }

    public float getRight() {
        return pos.x + halfWidth;
    }

    public float getBottom() {
        return pos.y - halfHeight;
    }

    public float getTop() {
        return pos.y + halfHeight;
    }

    public float getWidth() {
        return halfWidth*2f;
    }

    public float getHeigth() {
        return halfHeight*2f;
    }

    public void setLeft(float left){
        pos.x = left + halfWidth;
    }

    public void setRight(float right){
        pos.x = right - halfWidth;
    }

    public void setTop(float top){
        pos.y = top - halfHeight;
    }

    public void setBottom(float bottom){
        pos.y = bottom + halfHeight;
    }

    public void setWidth (float width) {
        this.halfWidth = width/2f;
    }

    public void setHeight (float height) {
        this.halfHeight = height/2f;
    }


    public void setSize(float width, float height) {
        this.halfWidth = width/2f;
        this.halfHeight = height/2f;
    }

    public boolean isInside(Vector2 touch) {
        return touch.x >= getLeft() && touch.x <= getRight() && touch.y >= getBottom() && touch.y <= getTop();
    }

    public boolean isOutside(Rectang bounds) {
//        return getLeft() < bounds.getLeft() || getRight() > bounds.getRight() || getBottom() < bounds.getBottom() || getTop() > bounds.getTop();
        return getRight() < bounds.getLeft() || getLeft() > bounds.getRight() || getTop() < bounds.getBottom() || getBottom() > bounds.getTop();
    }

}
