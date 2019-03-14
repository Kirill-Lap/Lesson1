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

    public float getBottom() {
        return pos.y - halfWidth;
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
}
