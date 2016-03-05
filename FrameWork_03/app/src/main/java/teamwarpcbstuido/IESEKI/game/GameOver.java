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
    //------------------16'.03.05 JJY
    Bitmap m_share;
    Bitmap m_scorewindow;
    Bitmap m_newrecord;
    //------------------16'.03.05 JJY

    static public Rect r_gomain;
    static public Rect r_background;
    static public Rect r_restart;
    //------------------16'.03.05 JJY
    static public Rect r_share;
    static public Rect r_scorewindow;
    static public Rect r_newrecord;
    //------------------16'.03.05 JJY


    int width, height;

    UI m_ui;

    //--------------------------------------------------
    static public boolean gomain_btn_push = false;
    static public boolean restart_btn_push = false;
    //------------------16'.03.05 JJY
    static public boolean share_btn_push = false;
    static public boolean is_new_record = false;
    //------------------16'.03.05 JJY

    Bitmap m_gomain_push;
    Bitmap m_restart_push;
    //------------------16'.03.05 JJY
    Bitmap m_share_push;
    //------------------16'.03.05 JJY

    public GameOver() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_gomain = AppManager.getInstance().getBitmap(R.drawable.btn_gomain);
        //m_background = AppManager.getInstance().getBitmap(R.drawable.gameover_background);
        m_background = AppManager.getInstance().getBitmap(R.drawable.gameover_background2);
        m_restart = AppManager.getInstance().getBitmap(R.drawable.btn_restart);
        //------------------16'.03.05 JJY
        m_share = AppManager.getInstance().getBitmap(R.drawable.btn_share);
        //------------------16'.03.05 JJY

        m_gomain_push = AppManager.getInstance().getBitmap(R.drawable.btn_push_gomain);
        m_restart_push = AppManager.getInstance().getBitmap(R.drawable.btn_push_restart);
        //------------------16'.03.05 JJY
        m_share_push = AppManager.getInstance().getBitmap(R.drawable.btn_push_share);
        m_scorewindow = AppManager.getInstance().getBitmap(R.drawable.gamescore_window);
        m_newrecord = AppManager.getInstance().getBitmap(R.drawable.new_record);
        //------------------16'.03.05 JJY

        r_gomain = new Rect();
        r_background = new Rect();
        r_restart = new Rect();
        //------------------16'.03.05 JJY
        r_share = new Rect();
        r_scorewindow = new Rect();
        r_newrecord = new Rect();
        //------------------16'.03.05 JJY

        r_gomain.set(width / 2 + DPI[X] - DPI[X] * 6 - DPI[X] * 8,
                ((height / 4 * 2) + 130) + DPI[Y] - DPI[Y] * 3,
                     width / 2 + DPI[X] + DPI[X] * 6 - DPI[X] * 8,
                ((height / 4 * 2) + 130) + DPI[Y] + DPI[Y] * 3);

        r_restart.set(width / 2 + DPI[X] - DPI[X] * 6 + DPI[X] * 6,
                ((height / 4 * 2) + 130) + DPI[Y] - DPI[Y] * 3,
                width / 2 + DPI[X] + DPI[X] * 6 + DPI[X] * 6,
                ((height / 4 * 2) + 130) + DPI[Y] + DPI[Y] * 3);
        //------------------16'.03.05 JJY
        r_share.set(width / 2 + DPI[X] - DPI[X] * 2 - DPI[X] * 8,
                ((height / 4 * 2) + 20) + DPI[Y] - DPI[Y] * 3,
                width / 2 + DPI[X] + DPI[X] * 2 + DPI[X] * 6,
                ((height / 4 * 2) + 20) + DPI[Y] + DPI[Y] * 3);

        r_scorewindow.set(width / 2 + DPI[X] - DPI[X] * 7 - DPI[X] * 8,
                ((height / 4 * 2) - 20) + DPI[Y] - DPI[Y] * 10,
                width / 2 + DPI[X] + DPI[X] * 7 + DPI[X] * 6,
                ((height / 4 * 2) - 20) + DPI[Y] + DPI[Y] * 10);

        r_newrecord.set(width / 2 + DPI[X] - DPI[X] * 5 - DPI[X] * 8,
                ((height / 4 * 2) - 180) + DPI[Y] - DPI[Y] * 6,
                width / 2 + DPI[X] + DPI[X] * 5 + DPI[X] * 6,
                ((height / 4 * 2) - 180) + DPI[Y] + DPI[Y] * 6);
        //------------------16'.03.05 JJY

        r_background.set(0, 0, width, height);
    }

    @Override
    public void Draw(Canvas canvas) {

    }

    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(m_background, null, r_background, null);

        //------------------16'.03.05 JJY
        canvas.drawBitmap(m_scorewindow, null, r_scorewindow, null);

        if(is_new_record == true)
            canvas.drawBitmap(m_newrecord, null, r_newrecord, null);
        //------------------16'.03.05 JJY

        if(gomain_btn_push == false)
            canvas.drawBitmap(m_gomain, null, r_gomain, null);
        else
            canvas.drawBitmap(m_gomain_push, null, r_gomain, null);

        if(restart_btn_push == false)
            canvas.drawBitmap(m_restart, null, r_restart, null);
        else
            canvas.drawBitmap(m_restart_push, null, r_restart, null);

        //------------------16'.03.05 JJY
        if(share_btn_push == false)
            canvas.drawBitmap(m_share, null, r_share, null);
        else
            canvas.drawBitmap(m_share_push, null, r_share, null);
        //------------------16'.03.05 JJY

        //------------------16'.03.05 JJY
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(DPI[X] * 4);

        String s_score = "" + m_ui.getScore();
        canvas.drawText("" + m_ui.getScore(), width / 2 - (DPI[X] * s_score.length()), (height / 4 * 2) - DPI[Y] * 3, paint);
        //------------------16'.03.05 JJY
    }

    //------------------16'.03.05 JJY
    public void ScoreCheck()
    {
        is_new_record = false;

        //New Record
        if (m_ui.getScore() > AppManager.getInstance().getPreference().BestScoreLoad())
        {
            is_new_record = true;
            AppManager.getInstance().getPreference().BestScoreSave(m_ui.getScore());
        }
    }
    //------------------16'.03.05 JJY
}
