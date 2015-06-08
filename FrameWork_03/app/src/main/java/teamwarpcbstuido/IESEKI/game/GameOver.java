package teamwarpcbstuido.IESEKI.game;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.GraphicObject;

/**
 * Created by JYJ on 2015-06-07.
 */
public class GameOver extends GraphicObject {

    private int X = 0;
    private int Y = 1;
    int DPI[] = new int[2];

    Bitmap m_gomain;
    Rect r_gomain;

    int width, height;

    public GameOver() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_gomain = AppManager.getInstance().getBitmap(R.drawable.btn_option);

        r_gomain = new Rect();

        r_gomain.set(width / 2 + DPI[X] - DPI[X] * 5,
                height / 2 + DPI[Y] - DPI[Y] * 2,
                width / 2 + DPI[X] + DPI[X] * 5,
                height / 2 + DPI[Y] + DPI[Y] * 2);
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_gomain, null, r_gomain, null);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_gomain, null, r_gomain, null);
    }

}
