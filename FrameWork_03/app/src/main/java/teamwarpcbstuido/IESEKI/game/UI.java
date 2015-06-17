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

    Bitmap m_gameui;
    Rect r_gameui;

    int width, height;

    static public int score;

    public UI() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_gameui = AppManager.getInstance().getBitmap(R.drawable.maingame_ui);

        r_gameui = new Rect();
        r_gameui.set(0, 0, width, height);

        score = 0;
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_gameui, null, r_gameui, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Best Score : " + score, DPI[X] * 5, DPI[Y] * 4, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Score : " +score, DPI[X] * 5, DPI[Y] * 7, paint);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_gameui, null, r_gameui, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Best Score : " + score, DPI[X] * 5, DPI[Y] * 4, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DPI[X] * 2);
        canvas.drawText("Score : " +score, DPI[X] * 5, DPI[Y] * 7, paint);
    }

}