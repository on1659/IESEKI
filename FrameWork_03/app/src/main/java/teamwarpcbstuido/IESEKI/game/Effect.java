package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by JYJ on 2015-05-20.
 */
public class Effect {

    final private int ITEM_PIWheel = 0;
    final private int ITEM_BloodyShield = 1;
    final private int ITEM_AlphaMissile = 3;
    final private int ITEM_SigmaReflect = 4;
    final private int ITEM_Adrenaline = 5;

    Bitmap bmp_effect;

    Rect rect_effect;

    int cx;
    int cy;
    int w;
    int h;

    public Effect(int type) {
        switch (type) {
            case ITEM_PIWheel:
                bmp_effect = AppManager.getInstance().getBitmap(R.drawable.eff_piwheel);
                break;
        }
    }

    public void Eff_PIWheel(int DPI[], int _x, int _y, int _w, int _h) //타이머로 시간재고 일정시간동안 돌리기 (효과발동 ~ 효과끝)
    {
        cx = DPI[0] * _x;
        cy = DPI[1] * _y;
        w = DPI[0] * _w;
        h = DPI[1] * _h;

        /*
        1. 빙글빙글 돌아가게 하기
        2. 몬스터와 충돌처리
        */
    }

    public void onDraw(Canvas canvas) {
        rect_effect.set((cx - w / 2), (cy - h / 2), (cx + w / 2), (cy + h / 2));
        canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.eff_piwheel), null, rect_effect, null);

        //Paint paint = new Paint();
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setColor(Color.RED);
        //canvas.drawCircle( m_cx, m_cy,m_w/2,paint);
    }

}
