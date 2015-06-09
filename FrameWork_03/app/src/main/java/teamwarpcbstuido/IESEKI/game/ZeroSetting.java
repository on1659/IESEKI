package teamwarpcbstuido.IESEKI.game;

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
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.Debug;

/**
 * Created by Dev.YT on 2015-06-10.
 */
public class ZeroSetting extends View {

    // 센서에따라 움직일 아이콘의 좌표
    private int width, height;
    private int DPI[] = new int[2];

    public Rect m_Button;

    Bitmap background;
    Bitmap bnt_zeroin;


    private Player m_player;

    public ZeroSetting(Context context) {
        super(context);

        m_player = new Player(BitmapFactory.decodeResource(context.getResources(), R.drawable.player));

        Display dispaly = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
        width = dispaly.getWidth();
        height = dispaly.getHeight();

        DPI[0] = width / 36;
        DPI[1] = height / 64;
        ;

        // 아이콘을 그려준다.
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background);
        bnt_zeroin = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_zeroin);

        int l = DPI[0] * 15;
        int t = DPI[1] * 15;
        int w = DPI[0] * 15;
        int h = DPI[1] * 13;
        m_Button = new Rect();
        m_Button.set(l, t, l + w, t + h);
    }


    public void onDraw(Canvas canvas) {


        canvas.drawBitmap(background, 0, 0, null);
        m_player.onDrawPalyer(canvas);
        m_player.onUpdate(System.currentTimeMillis());
        canvas.drawBitmap(bnt_zeroin, null, m_Button, null);

        invalidate();
    }

    public void setSensorRevise() {
        m_player.setSensorRevise();
    }
}