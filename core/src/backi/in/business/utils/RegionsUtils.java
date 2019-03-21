package backi.in.business.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionsUtils {

    public static TextureRegion[] split(TextureRegion region, int rows, int cols, int frames) {
        if (region == null) throw new RuntimeException("Split region error");
        TextureRegion[] regionsArray = new TextureRegion[frames];
        int tileWidth = region.getRegionWidth()/cols;
        int tileHeight = region.getRegionHeight()/rows;

        int frame = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regionsArray[frame] = new TextureRegion(region, tileWidth*j, tileHeight*i, tileWidth, tileHeight);
                if (frame == frames -1) return regionsArray;
                frame++;
            }
        }
        return regionsArray;
    }
}
