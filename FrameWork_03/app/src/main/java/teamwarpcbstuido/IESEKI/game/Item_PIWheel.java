package teamwarpcbstuido.IESEKI.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Administrator on 2015-05-15.
 */
public class Item_PIWheel extends Item {

    public Item_PIWheel(int DPI[]) {
        super(AppManager.getInstance().getBitmap(R.drawable.itm_piwheel));
        this.InitSpriteData(1, 1, 1, 1);
        m_speed = DPI[1] * 5;
    }
}
