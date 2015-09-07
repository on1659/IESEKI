package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.GraphicObject;

/**
 * Created by JYJ on 2015-06-04.
 */
public class UI extends GraphicObject {

    private int X = 0;
    private int Y = 1;
    int DPI[] = new int[2];
    int k;

    Rect r_gameui;
    Rect r_game_count;
    Rect r_game_start;

    int width, height;


    static public int m_score;

    public UI() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();


        r_gameui = new Rect();
        r_gameui.set(0, 0, width, height);

        int l = DPI[0] * 15;
        int t = DPI[1] * 26;
        int w = DPI[0] * 6;
        int h = DPI[1] * 6;

        r_game_count = new Rect();
        r_game_count.set(l, t, l + w, t + h);

        l = DPI[0] * 8;
        t = DPI[1] * 24;
        w = DPI[0] * 20;
        h = DPI[1] * 10;

        r_game_start = new Rect();
        r_game_start.set(l, t, l + w, t + h);

        m_score = 0;
    }

    @Override
    public void Draw(Canvas canvas) {
        int a = 50;
        int b = 50;
        canvas.drawText("a + b = " + (a + b), 50, 50, null);

    }

    public void onDraw(Canvas canvas, long game_time) {

        if(game_time >= 10 && game_time < 20)
            canvas.drawBitmap(AppManager.getInstance().GetImage("Count3"), null, r_game_count, null);
        else if(game_time >= 20 && game_time < 30)
            canvas.drawBitmap(AppManager.getInstance().GetImage("Count2"), null, r_game_count, null);
        else if(game_time >= 30 && game_time < 40)
            canvas.drawBitmap(AppManager.getInstance().GetImage("Count1"), null, r_game_count, null);
        else if(game_time >= 40 && game_time < 45)
            canvas.drawBitmap(AppManager.getInstance().GetImage("Count_Start"), null, r_game_start, null);

        canvas.drawBitmap(AppManager.getInstance().GetImage("Ui"), null, r_gameui, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Best Score : " +AppManager.getInstance().getPreference().BestScoreLoad(), DPI[X] * 5, DPI[Y] * 4, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Score : " + m_score, DPI[X] * 5, DPI[Y] * 7, paint);
    }

    public void killMonster(int score)
    {
        m_score += score;
    }

    public void updateScore()
    {
        m_score ++;
    }

    public void ScoreInit(){ m_score = 0; }

    static public int getScore()
    {
        return  m_score;
    }


}