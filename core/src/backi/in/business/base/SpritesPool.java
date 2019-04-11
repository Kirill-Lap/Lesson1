package backi.in.business.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool <T extends Sprite>  {

    protected final List<T> activeObjects = new ArrayList<T>();

    protected final List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size()-1);
        }
        activeObjects.add(object);
        System.out.println(this.getClass().getName() + " FreeObjects = " + freeObjects.size() + " ActiveObjects = " + activeObjects.size());
        return object;

    }

    public void updateActiveSprites(float delta){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (!sprite.isDestroyed()) {
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (!sprite.isDestroyed()) {
                sprite.draw(batch);
            }
        }
    }

    public void freeDestroyedActiveSprites(){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                //TOD
                free(sprite);
                i--;
                sprite.revive();
            }
        }
    }

    public void free(T object) {
        if (activeObjects.remove(object)) {
            freeObjects.add(object);
        }
        System.out.println(this.getClass().getName() + " FreeObjects = " + freeObjects.size() + " ActiveObjects = " + activeObjects.size());
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    public void dispose(){
        activeObjects.clear();
        freeObjects.clear();
    }

    public void freeAllActiveObjects(){
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }
}
