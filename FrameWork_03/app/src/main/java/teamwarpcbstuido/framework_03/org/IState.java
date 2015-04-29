package teamwarpcbstuido.framework_03.org;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015-04-08.
 */
public interface IState{

    public void Init();
    public void Destroy();
    public void Update();

    public void Render(Canvas canvas) ;
    public boolean onTouchEvent(MotionEvent event);

}
