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
    Bitmap m_background;
    static public Rect r_gomain;
    static public Rect r_background;

    int width, height;

    public GameOver() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_gomain = AppManager.getInstance().getBitmap(R.drawable.btn_gomain);
        m_background = AppManager.getInstance().getBitmap(R.drawable.gameover_background);

        r_gomain = new Rect();
        r_background = new Rect();

        r_gomain.set(width / 2 + DPI[X] - DPI[X] * 6,
                height / 2 + DPI[Y] - DPI[X] * 3,
                width / 2 + DPI[X] + DPI[X] * 6,
                height / 2 + DPI[Y] + DPI[Y] * 3);

        r_background.set(0, 0, width, height);
    }

    @Override
    public void Draw(Canvas canvas) {

        canvas.drawBitmap(m_background, null, r_background, null);
        canvas.drawBitmap(m_gomain, null, r_gomain, null);

    }

    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(m_background, null, r_background, null);
        canvas.drawBitmap(m_gomain, null, r_gomain, null);

    }

}
