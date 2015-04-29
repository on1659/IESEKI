package teamwarpcbstuido.framework_03.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import teamwarpcbstuido.framework_03.R;

public class IntroState implements IState {
    Bitmap icon;
    int x, y;

    @Override
    public void Destroy(){
    }

    @Override
    public void Init() {
        icon = AppManager.getInstance().getBitmap(R.drawable.character);
    }

    @Override
    public void Update() {
    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(icon, x, y, null);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }



}
