package teamwarpcbstuido.IESEKI.game;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import teamwarpcbstuido.IESEKI.Layout.ZeroSetting;
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

    Bitmap m_background;
    Bitmap m_gomain;
    Bitmap m_restart;

    static public Rect r_gomain;
    static public Rect r_background;
    static public Rect r_restart;

    int width, height;

    UI m_ui;

    //--------------------------------------------------
    static public boolean gomain_btn_push = false;
    static public boolean restart_btn_push = false;

    Bitmap m_gomain_push;
    Bitmap m_restart_push;

    public GameOver() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_gomain = AppManager.getInstance().getBitmap(R.drawable.btn_gomain);
        m_background = AppManager.getInstance().getBitmap(R.drawable.gameover_background);
        m_restart = AppManager.getInstance().getBitmap(R.drawable.btn_restart);

        m_gomain_push = AppManager.getInstance().getBitmap(R.drawable.btn_push_gomain);
        m_restart_push = AppManager.getInstance().getBitmap(R.drawable.btn_push_restart);

        r_gomain = new Rect();
        r_background = new Rect();
        r_restart = new Rect();

        r_gomain.set(width / 2 + DPI[X] - DPI[X] * 6 - DPI[X] * 8,
                    (height / 4 * 2) + DPI[Y] - DPI[X] * 3,
                     width / 2 + DPI[X] + DPI[X] * 6 - DPI[X] * 8,
                   (height / 4 * 2) + DPI[Y] + DPI[Y] * 3);

        r_restart.set(width / 2 + DPI[X] - DPI[X] * 6 + DPI[X] * 6,
                (height / 4 * 2) + DPI[Y] - DPI[X] * 3,
                width / 2 + DPI[X] + DPI[X] * 6 + DPI[X] * 6,
                (height / 4 * 2) + DPI[Y] + DPI[Y] * 3);


        r_background.set(0, 0, width, height);
    }

    @Override
    public void Draw(Canvas canvas) {

    }

    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(m_background, null, r_background, null);

        if(gomain_btn_push == false)
            canvas.drawBitmap(m_gomain, null, r_gomain, null);
        else
            canvas.drawBitmap(m_gomain_push, null, r_gomain, null);

        if(restart_btn_push == false)
            canvas.drawBitmap(m_restart, null, r_restart, null);
        else
            canvas.drawBitmap(m_restart_push, null, r_restart, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 4);

        String s_score = "Score : " + m_ui.getScore();
        canvas.drawText("Score : " + m_ui.getScore(), width / 2 - (DPI[X] * s_score.length()), (height / 4 * 2) - DPI[X] * 5, paint);

    }
}
