package teamwarpcbstuido.framework_03.game;

import android.graphics.Canvas;
import teamwarpcbstuido.framework_03.org.Debug;

import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayDeque;
import java.util.ArrayList;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.IState;
import teamwarpcbstuido.framework_03.org.AppManager;


/**
 * Created by Administrator on 2015-04-09.
 */
public class GameState implements IState {



    private BackGround m_background;
    private Player m_player;
    private Debug debug = new Debug();

    private ArrayList<Monster> m_monster = new ArrayList<Monster>();
    @Override
    public void Init()
    {
        m_background = new BackGround();
        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.character_ray));


        Monster mon = null;
        mon = new Monster_Type_01();
        //monster =  new Monster();


    }

    @Override
    public void Render(Canvas canvas)
    {
        m_background.Draw(canvas);
        m_player.Draw(canvas);
        debug.drawText(canvas, AppManager.getInstance().getSensorX(), 150,150, 45, Color.RED);
        debug.drawText(canvas, AppManager.getInstance().getSensorY(), 150,250, 45, Color.RED);

    }

    @Override
    public void Update()
    {
         long GameTime = System.currentTimeMillis();
          m_player.onUpdate(GameTime);
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
