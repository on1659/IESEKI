package teamwarpcbstuido.framework_03.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;

import teamwarpcbstuido.framework_03.R;

public class CreditState implements IState {
    Bitmap icon;
    int x, y;

    @Override
    public void Destroy()
    {
    }

    @Override
    public void Init() {
        icon = AppManager.getInstance().getBitmap(R.drawable.monster_dolphin);
    }

    @Override
    public void Update()
    {
    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawColor((Color.BLACK));
        canvas.drawBitmap(icon, x , y , null);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }



}
