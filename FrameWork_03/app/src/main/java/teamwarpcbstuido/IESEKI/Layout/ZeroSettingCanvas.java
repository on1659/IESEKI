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

    public Rect m_ZeroSetButton;
    public Rect m_GameSpeedButtonUp;
    public Rect m_GameSpeedButtonDown;

    public Rect m_GameSpeed_1;
    public Rect m_GameSpeed_2;
    public Rect m_GameSpeed_3;
    public Rect m_GameSpeed_4;
    public Rect m_GameSpeed_5;

    public Rect m_SpeedText;

    static public Rect m_GameSpeed_pos;

    Bitmap background;
    Bitmap bnt_zeroin;
    Rect back;

    Bitmap speed_down;
    Bitmap speed_up;
    Bitmap speed_step;
    Bitmap speed_cur_pos;

    Bitmap game_speed_text;

    private Player m_player;

    public ZeroSettingCanvas(Context context) {
        super(context);


        m_player = new Player("Player");

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();


        //RenderManager is not use
        //this image is only use this activity
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background2);
        bnt_zeroin = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_zeroin);

        speed_down = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_speeddown);
        speed_up = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_speedup);
        speed_step = BitmapFactory.decodeResource(context.getResources(), R.drawable.speed_step);
        speed_cur_pos = BitmapFactory.decodeResource(context.getResources(), R.drawable.speed_pos);

        game_speed_text = BitmapFactory.decodeResource(context.getResources(), R.drawable.speed_text);

        //���
        back  = new Rect();
        back.set(0, 0, width, height);

        //�������� ��ư
        int l = DPI[0] * 8;
        int t = DPI[1] * 50;
        int w = DPI[0] * 20;
        int h = DPI[1] * 10;

        m_ZeroSetButton = new Rect();
        m_ZeroSetButton.set(l, t, l + w, t + h);

        l = DPI[0] * 13;
        t = DPI[1] * 5;
        w = DPI[0] * 10;
        h = DPI[1] * 5;

        m_SpeedText = new Rect();
        m_SpeedText.set(l, t, l + w, t + h);


        l = DPI[0] * 3;
        t = DPI[1] * 9;
        w = DPI[0] * 5;
        h = DPI[1] * 5;

        m_GameSpeedButtonDown = new Rect();
        m_GameSpeedButtonDown.set(l, t, l + w, t + h);


        l = DPI[0] * 9;
        t = DPI[1] * 10;
        w = DPI[0] * 3;
        h = DPI[1] * 3;

        m_GameSpeed_1 = new Rect();
        m_GameSpeed_1.set(l, t, l + w, t + h);

        l = DPI[0] * 13;

        m_GameSpeed_2 = new Rect();
        m_GameSpeed_2.set(l, t, l + w, t + h);

        l = DPI[0] * 17;

        m_GameSpeed_3 = new Rect();
        m_GameSpeed_3.set(l, t, l + w, t + h);

        l = DPI[0] * 21;

        m_GameSpeed_4 = new Rect();
        m_GameSpeed_4.set(l, t, l + w, t + h);

        l = DPI[0] * 25;

        m_GameSpeed_5 = new Rect();
        m_GameSpeed_5.set(l, t, l + w, t + h);

        l = DPI[0] * 29;
        t = DPI[1] * 9;
        w = DPI[0] * 5;
        h = DPI[1] * 5;

        m_GameSpeedButtonUp = new Rect();
        m_GameSpeedButtonUp.set(l, t, l + w, t + h);

        m_GameSpeed_pos = new Rect();
        m_GameSpeed_pos.set(this.SpeedPosCheck());
    }

    public void SetSpeed(Rect r)
    {
        m_GameSpeed_pos.set(r);
    }


    static public int SpeedPosCheck(int x)
    {
        if(AppManager.getInstance().getGameSpeed() == 0.7f)
            return x * 9;
        else if(AppManager.getInstance().getGameSpeed() == 1.2f)
            return x * 13;
        else if(AppManager.getInstance().getGameSpeed() == 1.7f)
            return x * 17;
        else if(AppManager.getInstance().getGameSpeed() == 2.2f)
            return x * 21;
        else if(AppManager.getInstance().getGameSpeed() == 2.7f)
            return x * 25;

        return 0;
    }

    public Rect SpeedPosCheck()
    {
        if(AppManager.getInstance().getGameSpeed() == 0.7f)
            return m_GameSpeed_1;
        else if(AppManager.getInstance().getGameSpeed() == 1.2f)
            return m_GameSpeed_2;
        else if(AppManager.getInstance().getGameSpeed() == 1.7f)
            return m_GameSpeed_3;
        else if(AppManager.getInstance().getGameSpeed() == 2.2f)
            return m_GameSpeed_4;
        else if(AppManager.getInstance().getGameSpeed() == 2.7f)
            return m_GameSpeed_5;

        return null;
    }

    public void Invalidate()
    {
        this.invalidate();
    }


    public void onDraw(Canvas canvas) {


        canvas.drawBitmap(background, null, back, null);
        m_player.onDrawPlayer(canvas);
        m_player.onUpdate(System.currentTimeMillis());
        canvas.drawBitmap(bnt_zeroin, null, m_ZeroSetButton, null);
        canvas.drawBitmap(game_speed_text,null, m_SpeedText, null);

        canvas.drawBitmap(speed_down, null, m_GameSpeedButtonDown, null);

        canvas.drawBitmap(speed_step,null, m_GameSpeed_1, null);
        canvas.drawBitmap(speed_step,null, m_GameSpeed_2, null);
        canvas.drawBitmap(speed_step,null, m_GameSpeed_3, null);
        canvas.drawBitmap(speed_step,null, m_GameSpeed_4, null);
        canvas.drawBitmap(speed_step,null, m_GameSpeed_5, null);

        canvas.drawBitmap(speed_cur_pos,null, m_GameSpeed_pos, null);

        canvas.drawBitmap(speed_up,null, m_GameSpeedButtonUp, null);

        invalidate();
    }

    public void setSensorRevise() {
        m_player.setSensorRevise();
    }
}