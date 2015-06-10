package teamwarpcbstuido.IESEKI.Layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

public class LoadingCanvas extends View {

    // 센서에따라 움직일 아이콘의 좌표
    private int width, height;
    private int DPI[] = new int[2];
    Rect pos;
    Bitmap background;
    Paint pnt;
    int alpha = 200;

    public LoadingCanvas(Context context) {
        super(context);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
        background =  AppManager.getInstance().getBitmap(R.drawable.warplogo);

        pnt = new Paint();
        pnt.setAlpha(alpha);
        pnt.setColor(Color.WHITE);
        pos  = new Rect();
        pos.set(0,0,width,height);
    }


    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, null, pos, null);
        canvas.drawRect(0, 0, width, height, pnt);
        Debug.drawText(canvas, " alpha : " + alpha, 100,100, 50, Color.RED);
        alpha -= 2;
        if(alpha < 100)alpha +=1;
        if(alpha < 0)alpha=0;
        pnt.setAlpha(alpha);
        invalidate();
    }


}