package teamwarpcbstuido.IESEKI.Layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.game.Player;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.Debug;

/**
 * Created by Dev.YT on 2015-06-10.
 */
public class ZeroSettingCanvas extends View {

    private int width, height;
    private int DPI[] = new int[2];

    public Rect m_Button;
    public Rect m_GameSpeedButtonUp;
    public Rect m_GameSpeedButtonDown;


    Bitmap background;
    Bitmap bnt_zeroin;
    Rect back;

    private Player m_player;

    public ZeroSettingCanvas(Context context) {
        super(context);


        m_player = new Player("Player");

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();


        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background2);
        bnt_zeroin = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_zeroin);

        int l = DPI[0] * 8;
        int t = DPI[1] * 50;
        int w = DPI[0] * 20;
        int h = DPI[1] * 10;



        m_Button = new Rect();
        m_Button.set(l, t, l + w, t + h);


        l = DPI[0] * 8;
        t = DPI[1] * 10;
        w = DPI[0] * 5;
        h = DPI[1] * 5;

        m_GameSpeedButtonUp = new Rect();
        m_GameSpeedButtonUp.set(l, t, l + w, t + h);

        l = DPI[0] * 8;
        t = DPI[1] * 15;

        m_GameSpeedButtonDown = new Rect();
        m_GameSpeedButtonDown.set(l, t, l + w, t + h);

        back  = new Rect();
        back.set(0, 0, width, height);
    }


    public void onDraw(Canvas canvas) {


        canvas.drawBitmap(background, null, back, null);
        m_player.onDrawPlayer(canvas);
        m_player.onUpdate(System.currentTimeMillis());
        canvas.drawBitmap(bnt_zeroin, null, m_Button, null);

        canvas.drawBitmap(bnt_zeroin,null, m_GameSpeedButtonUp, null);

        canvas.drawBitmap(bnt_zeroin,null, m_GameSpeedButtonDown, null);

        invalidate();
    }

    public void setSensorRevise() {
        m_player.setSensorRevise();
    }
}