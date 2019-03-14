package backi.in.business.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import backi.in.business.base.BaseScreen;

// Нерабочий экран
public class MenuScreen extends BaseScreen {
    private SpriteBatch batch;
    private Texture img;
    Vector2 touch;
    Vector2 v;
    Vector2 pos;
    Vector2 dest;
    boolean toDest = false;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        pos = new Vector2();
        v = new Vector2(2,1);
        dest = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.57f, 0.35f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        pos.add(v);
        if ( img.getWidth() + pos.x  >= Gdx.graphics.getWidth() || pos.x <= 0){
            v.set(-v.x, v.y);
        }

        if (img.getHeight() + pos.y >= Gdx.graphics.getHeight() || pos.y <= 0 ) {
            v.set(v.x, -v.y);
        }
        if (toDest) {
            //без этой корректировки направления, объект почему-то промахивался (возможно из-за погрешности вычислений после нормализации
            dest.set(touch.x - pos.x, touch.y - pos.y);
            dest.nor();
            v.set(dest);
            if (Math.abs (pos.x- touch.x) < 1 && Math.abs(pos.y -touch.y) < 1) {
                v.set(0, 0);
                toDest = false;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch x = " + (int)touch.x + " y = " + (int)touch.y);
        toDest = true;

        dest.set(touch.x - pos.x, touch.y - pos.y); // промежуточный вектор направления

//        System.out.println("Vx " + dest.x + " Vy " + dest.y);
        dest.nor();
//        System.out.println("NVx " + dest.x + " NVy " + dest.y);
        v.set(dest);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case 19:
                v.set(0,1);
                break;
            case 20:
                v.set(0,-1);
                break;
            case 21:
                v.set(-1,0);
                break;
            case 22:
                v.set(1,0);
                break;
            case 66:
                v.set(2,1);
                break;
        }
        return super.keyDown(keycode);
    }
}
