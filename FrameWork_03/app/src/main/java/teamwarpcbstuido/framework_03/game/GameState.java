package teamwarpcbstuido.framework_03.game;

import android.graphics.Canvas;
import teamwarpcbstuido.framework_03.org.Debug;

import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.IState;
import teamwarpcbstuido.framework_03.org.AppManager;


/**
 * Created by Administrator on 2015-04-09.
 */
public class GameState implements IState {



    private static int X = 0;
    private static int Y = 1;
    private static int width;
    private static int height;
    private  int Monster_Type_01 = 85;
    private  int Monster_Type_02 = 95;
    private  int Monster_Type_03 = 105;


    private int DPI[] = new int[2];

    private BackGround m_background;
    private Player m_player;
    private Debug debug = new Debug();

    private ArrayList<Monster> m_monster = new ArrayList<Monster>();

    @Override
    public void Init()
    {
        DPI =  AppManager.getInstance().getDPI();
        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

        m_background = new BackGround();
        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.character_ray));

        Make_Monster(15, Monster_Type_01);
    }
    @Override
    public void Render(Canvas canvas)
    {
        m_background.onDraw(canvas);

        for(Monster mon : m_monster)
             mon.onDraw(canvas);



        {
            debug.drawText(canvas, m_monster.get(0).GetX(), 150, 350, 45, Color.RED);
            debug.drawText(canvas, m_monster.get(0).GetY(), 250, 350, 45, Color.RED);

            debug.drawText(canvas, m_monster.get(0).GetWidth(), 150, 450, 45, Color.BLUE);
            debug.drawText(canvas, m_monster.get(0).GetHeight(), 250, 450, 45, Color.BLUE);


            debug.drawText(canvas, m_monster.get(1).GetX(), 350, 350, 45, Color.RED);
            debug.drawText(canvas, m_monster.get(1).GetY(), 450, 350, 45, Color.RED);

            debug.drawText(canvas, m_monster.get(1).GetWidth(), 350, 450, 45, Color.BLUE);
            debug.drawText(canvas, m_monster.get(1).GetHeight(), 450, 450, 45, Color.BLUE);

            debug.drawText(canvas, AppManager.getInstance().getSensorX(), 150, 150, 45, Color.RED);
            debug.drawText(canvas, AppManager.getInstance().getSensorY(), 150, 250, 45, Color.RED);

        }
        m_player.onDraw(canvas);
    }

    @Override
    public void Update()
    {
         long GameTime = System.currentTimeMillis();
          m_player.onUpdate(GameTime);

         this.Collision();
    }

    @Override
    public void Destroy()
    {
    }

    public void Collision()
    {

        for(int i = 0; i< m_monster.size(); i++)
        {
            if(m_monster.get(i).Collision(m_player.getPos()))
            {
                m_monster.remove(i);
            }
        }
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


    public void Make_Monster(int make_num,int make_type)
    {

        for(int i = 0; i < make_num; i++)
        {
            Monster mon = null;
            Random rnd = new Random();


            switch(make_type)
            {
                case 85://Monster_Type_01
                    mon = new Monster_Type_01();
                    break;
                case 95://Monster_Type_02:
                    break;
                case 105://Monster_Type_03:
                    break;
            }//switch


            mon.SetPosition(DPI, rnd.nextInt(25) + 2, rnd.nextInt(10) + 4, 2, 2); //Max DPI[X] is 36
            m_monster.add(mon);
        }//for

    }

}
