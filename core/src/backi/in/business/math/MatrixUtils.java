package backi.in.business.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class MatrixUtils {
    public MatrixUtils() {
    }


    //4x4
    public static void calcTransitionMatrix(Matrix4 mat, Rectang src, Rectang dst){
        float scaleX = dst.getHalfWidth() / src.getHalfWidth();
        float scaleY = dst.getHalfHeight() / src.getHalfHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y, 0f).scale(scaleX, scaleY,1f).translate(-src.pos.x, -src.pos.y, 0f);
    }

    //3x3
    public static void calcTransitionMatrix(Matrix3 mat, Rectang src, Rectang dst){
        float scaleX = dst.getHalfWidth() / src.getHalfWidth();
        float scaleY = dst.getHalfHeight() / src.getHalfHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y).scale(scaleX, scaleY).translate(-src.pos.x, -src.pos.y);
    }

}
