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

    // 센서에따라 움직일 아이콘의 좌표
    private int width, height;
    private int DPI[] = new int[2];

    public Rect m_Button;


    Bitmap background;
    Bitmap bnt_zeroin;
    Rect back;

    private Player m_player;

    public ZeroSettingCanvas(Context context) {
        super(context);

        m_player = new Player("플레이어");

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        // 아이콘을 그려준다.
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background2);
        bnt_zeroin = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_zeroin);

        int l = DPI[0] * 8;
        int t = DPI[1] * 50;
        int w = DPI[0] * 20;
        int h = DPI[1] * 10;

        m_Button = new Rect();
        m_Button.set(l, t, l + w, t + h);

        back  = new Rect();
        back.set(0, 0, width, height);
    }


    public void onDraw(Canvas canvas) {


        canvas.drawBitmap(background,null,back,null);
        m_player.onDrawPlayer(canvas);
        m_player.onUpdate(System.currentTimeMillis());
        canvas.drawBitmap(bnt_zeroin, null, m_Button, null);

        invalidate();
    }

    public void setSensorRevise() {
        m_player.setSensorRevise();
    }
}