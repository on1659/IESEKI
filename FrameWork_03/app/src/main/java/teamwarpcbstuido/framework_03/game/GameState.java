package teamwarpcbstuido.framework_03.game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import teamwarpcbstuido.framework_03.org.IState;


/**
 * Created by Administrator on 2015-04-09.
 */
public class GameState implements IState {



    private BackGround m_background;

    @Override
    public void Init()
    {
        m_background = new BackGround();
    }

    @Override
    public void Render(Canvas canvas)
    {
        m_background.Draw(canvas);
    }

    @Override
    public void Update()
    {


    }

    @Override
    public void Destroy()
    {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float tx,ty;
        tx =event.getX();
        ty =event.getY();

        int x = (int)tx;
        int y = (int)ty;

        return false;
    }

}
