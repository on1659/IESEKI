package teamwarpcbstuido.IESEKI.game;

import android.graphics.Canvas;

import teamwarpcbstuido.IESEKI.Layout.Loading;
import teamwarpcbstuido.IESEKI.Layout.SelectMenu;
import teamwarpcbstuido.IESEKI.org.Collision;
import teamwarpcbstuido.IESEKI.org.Debug;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.IState;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Administrator on 2015-04-09.
 */
public class GameState implements IState {

    private static int X = 0;
    private static int Y = 1;
    private static int width;
    private static int height;

    final public static int Monster_Type_01 = 85;
    final public static int Monster_Type_02 = 95;
    final public static int Monster_Type_03 = 105;

    final public static int ITEM_PIWheel = 0;
    final public static int ITEM_BloodyShield = 1;
    final public static int ITEM_Meruss = 2;

    final public static int ITEM_SigmaReflect = 3;
    final public static int ITEM_Adrenaline = 4;

    private float FPS;


    private int DPI[] = new int[2];

    private BackGround m_background;
    private UI m_ui;
    private Player m_player;
    private Debug debug = new Debug();
    private Pause m_pause;
    private GameOver m_gameover;

    TimerTask eff_timer;

    private Vector<Monster> m_monster = new Vector<Monster>();
    private Vector<Item> m_item = new Vector<Item>();
    private Vector<Effect> m_effect = new Vector<Effect>();


    private long LastRegenEnemy = System.currentTimeMillis();
    private long LastRegenItem= System.currentTimeMillis();

    long fpsStartTime = 0L;             // Frame 시작 시간
    int frameCnt = 0;                      // 돌아간 Frame 갯수
    double timeElapsed = 0.0f;

    float current_time;

    long tmpTime;

    boolean debugCheck;
    Rect debugBtn = new Rect();

    Rect button = new Rect();

    boolean GameOver_Check;
    private long mGameTimer;
    private int mCreatMonsetNum;
    @Override
    public void Init()
    {
        DPI =  AppManager.getInstance().getDPI();
        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

        m_background = new BackGround();
        m_ui = new UI();
        m_gameover = new GameOver();

        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player));

        FPS = 0;

        current_time = System.currentTimeMillis();

        Make_Monster(4, Monster_Type_01);
        Make_Item(2, ITEM_BloodyShield);

        button.set(DPI[X] * 25, DPI[Y] * 40, DPI[X] * 30, DPI[Y] * 45);
        debugBtn.set(DPI[X] * 25, DPI[Y] * 20, DPI[X] * 30, DPI[Y] * 25);

        debugCheck= false;

        tmpTime = 0;

        GameOver_Check = false;

        mCreatMonsetNum = 4;
        mGameTimer =0;
        MainGame_TimerManager();
    }
    @Override
    public void Render(Canvas canvas)
    {

        m_background.onDraw(canvas);

        if (m_monster.size() != 0) {
            for (int i = 0; i < m_monster.size() - 1; i++)
                m_monster.get(i).onDraw(canvas);
        }

        if (m_item.size() != 0) {
            for (int i = 0; i < m_item.size() - 1; i++)
                m_item.get(i).onDraw(canvas);
        }

        if (m_effect.size() > 0) {
            for (int i = 0; i < m_effect.size(); i++)
                m_effect.get(i).Eff_Draw(canvas);
        }


        if (debugCheck) Debug.debugLine(canvas);

        /*
        {
            debug.drawText(canvas, "FPS : " + FPS, DPI, 10, 13, 55, Color.BLUE);

            debug.drawText(canvas, "Player_left : " + m_player.getPos().left, DPI, 10, 15, 35, Color.RED);
            debug.drawText(canvas, "Player_Top : " + m_player.getPos().top, DPI, 10, 17, 35, Color.RED);
            debug.drawText(canvas, "Player_degree : " + m_player.getDegree(), DPI, 10, 19, 35, Color.RED);


            debug.drawText(canvas, "Roll : " + AppManager.getInstance().getSensorX(), DPI, 10, 21, 35, Color.BLUE);
            debug.drawText(canvas, "Pitch : " + AppManager.getInstance().getSensorY(), DPI, 10, 23, 35, Color.BLUE);

            debug.drawText(canvas, "Monster Num : " + m_monster.size(), DPI, 10, 25, 35, Color.GREEN);


            debug.drawText(canvas, "Item Num : " + m_item.size(), DPI, 10, 27, 35, Color.BLACK);


            debug.drawText(canvas, "Effect Num : " + m_effect.size(), DPI, 10, 29, 35, Color.CYAN);


            if (m_effect.size() > 1) {
                debug.drawText(canvas, "GameTime  : " + m_effect.get(0).m_activeTime, DPI, 10, 31, 35, Color.YELLOW);
                debug.drawText(canvas, "GameTime  : " + m_effect.get(1).m_activeTime, DPI, 10, 35, 35, Color.YELLOW);
            }

             debug.drawText(canvas, FPS, 250, 550, 55, Color.BLUE);

              Paint paint = new Paint();
              paint.setStyle(Paint.Style.FILL);
              paint.setColor(Color.RED);
              canvas.drawRect(button, paint);
              paint.setColor(Color.BLUE);
              canvas.drawRect(debugBtn, paint);

         }
        */

        m_player.onDrawPlayer(canvas);
        m_ui.onDraw(canvas);

        if(GameOver_Check == true)
            m_gameover.onDraw(canvas);

        if (debugCheck) Debug.debugLine(canvas);

    }
    public float FramePerSecond()
    {
        long fpsEndTime = System.currentTimeMillis();
        float timeDelta = (fpsEndTime - fpsStartTime) * 0.001f;
        float fps = 0;

        // Frame 증가 셋팅
        frameCnt++;
        timeElapsed += timeDelta;

        // FPS를 구해서 로그로 표시

        fps = (float)(frameCnt/timeElapsed);
        frameCnt = 0;
        timeElapsed = 0.0f;


        // Frame 시작 시간 다시 셋팅
        fpsStartTime = System.currentTimeMillis();

        float frame_time = System.currentTimeMillis() - current_time;
        float frame_rate = 1.0f / frame_time;
        current_time += frame_time;
        return 0.04f;
    }

    @Override
    public void Update() {

        if (m_pause.m_return == false && GameOver_Check == false) {
            long GameTime = System.currentTimeMillis();
            FPS = this.FramePerSecond();

            m_player.onUpdate(GameTime);
            m_background.onUpdate(GameTime);

            if (m_effect.size() > 0) {
                for (int i = 0; i < m_effect.size(); i++) {
                    m_effect.get(i).onUpdate(m_player, FPS);

                    if (m_effect.get(i).Die()) m_effect.remove(i);
                }
            }
            //Move
            if (m_monster.size() > 0) {
                for (int i = 0; i < m_monster.size() - 1; i++) {
                    m_monster.get(i).onUpdate(FPS);
                    if (m_monster.get(i).Die()) m_monster.remove(i);

                }
            }

            if (m_item.size() > 0) {
                for (int i = 0; i < m_item.size() - 1; i++) {
                    m_item.get(i).Move(FPS);
                    if (m_item.get(i).DIe()) m_item.remove(i);
                }
            }


            this.Collision();
        }
    }

    @Override
    public void Destroy() {

    }

    public void Collision() {
        if (m_monster.size() != 0) {
            for (int i = 0; i < m_monster.size() - 1; i++) {

                //Circle Collision
                if (Collision.collisionCircle(m_monster.get(i).getX(), m_monster.get(i).getY(), m_monster.get(i).getRadius(), m_player.getX(), m_player.getY(), m_player.getRadius()))
                {
                    m_monster.remove(i);

                    if(m_ui.getScore()> AppManager.getInstance().getPreference().BestScoreLoad())
                        AppManager.getInstance().getPreference().BestScoreSave(m_ui.getScore());

                    //GameOver_Check = true;
                }

                    for (int effNum = 0; effNum < m_effect.size(); effNum++)
                    {
                        //Circle Collision
                        if (m_effect.get(effNum).getType() == ITEM_PIWheel || m_effect.get(effNum).getType() == ITEM_BloodyShield || m_effect.get(effNum).getType() == ITEM_Meruss)
                        {
                            if (Collision.collisionCircle(m_monster.get(i).getX(), m_monster.get(i).getY(), m_monster.get(i).getRadius(), m_effect.get(effNum).getX(), m_effect.get(effNum).getY(), m_effect.get(effNum).getRadius()))
                            {
                                m_monster.remove(i);
                                AppManager.getInstance().get_mySoundPool().play(AppManager.EFFECT_MONSTER_DIE);
                            }
                        }
                    }


            }
        }

        if (m_item.size() > 0) {
            for (int i = 0; i < m_item.size() - 1; i++) {

                //Circle Collision
                if (Collision.collisionCircle(m_item.get(i).getX(), m_item.get(i).getY(), m_item.get(i).getRadius(), m_player.getX(), m_player.getY(), m_player.getRadius())) {
                    Make_Effect(m_item.get(i).getType(), i);
                    m_item.remove(i);
                }
            }
        }
    }

    //AddItem
    public void AddItem() {

        if (m_item.size() == 3) {
            for (int i = 0; i < m_item.size(); i++) {
                if (m_item.get(i).getWallCount() > 2) {
                    Random rnd = new Random();
                    Make_Item(1, rnd.nextInt(5)); // 추후 랜덤
                    break;
                }

            }
        }

        if (m_item.size() < 3) {
            Random rnd = new Random();
            Make_Item(1, rnd.nextInt(5)); // 추후 랜덤
        }
    }

    //AddMonster
    public void AddMonster() {


        if(mGameTimer % 20 == 0)
            Make_Monster(mCreatMonsetNum, Monster_Type_01);
        //mCreatMonsetNum++;



        if(mGameTimer == 60)
            mCreatMonsetNum = 5;

        else if(mGameTimer == 120)
            mCreatMonsetNum = 6;

        else if(mGameTimer == 220)
            mCreatMonsetNum = 7;

        else if(mGameTimer == 320)
            mCreatMonsetNum = 8;

        else if(mGameTimer == 420)
            mCreatMonsetNum = 9;

        else if(mGameTimer == 520)
            mCreatMonsetNum = 10;

        else if(mGameTimer == 660)
            mCreatMonsetNum = 11;

        else if(mGameTimer == 780)
            mCreatMonsetNum = 12;

        else if(mGameTimer == 860)
            mCreatMonsetNum = 13;

        else if(mGameTimer == 940)
            mCreatMonsetNum = 14;




        //Random rnd =new Random();
        //long GameTime = System.currentTimeMillis();

        //if (System.currentTimeMillis() - LastRegenEnemy >= 1000)
        //{
        //    LastRegenEnemy = System.currentTimeMillis();
        //    Make_Monster(4, Monster_Type_01);
        //}
    }

    //Item Effect
    public void Make_Item(int make_num, int make_type) {
        for (int i = 0; i < make_num; i++) {
            Item itm = null;
            Random rnd = new Random();

            switch (make_type) {
                case ITEM_PIWheel:
                    itm = new Item(AppManager.getInstance().getBitmap(R.drawable.itm_piwheel), DPI, ITEM_PIWheel);
                    break;

                case ITEM_BloodyShield:
                    itm = new Item(AppManager.getInstance().getBitmap(R.drawable.itm_bloodyshield), DPI, ITEM_BloodyShield);
                    break;

                case ITEM_Meruss:
                    itm = new Item(AppManager.getInstance().getBitmap(R.drawable.itm_meruss), DPI, ITEM_Meruss);
                    break;

                case ITEM_SigmaReflect:
                    itm = new Item(AppManager.getInstance().getBitmap(R.drawable.itm_reflect), DPI, ITEM_SigmaReflect);
                    break;

                case ITEM_Adrenaline:
                    itm = new Item(AppManager.getInstance().getBitmap(R.drawable.itm_adrenaline), DPI, ITEM_Adrenaline);
                    break;
            }

            itm.SetPosition(DPI, rnd.nextInt(25) + 2, -rnd.nextInt(5), 5, 5); //Max DPI[X] is 36
            itm.setDir(m_player.getX(), m_player.getY());
            m_item.add(itm);
        }
    }

    //Item Effect
    public void Make_Effect(int item_type, int item_num) {
        Effect effect = null;
        TimerTask timer = null;

        switch (item_type) {
            case ITEM_PIWheel:
                effect = new Effect(AppManager.getInstance().getBitmap(R.drawable.eff_piwheel), ITEM_PIWheel);
                effect.Eff_PIWheel(m_player);
                break;

            case ITEM_BloodyShield:
                effect = new Effect(AppManager.getInstance().getBitmap(R.drawable.eff_bloodyshield), ITEM_BloodyShield);
                effect.Eff_BloodyShield(m_item.get(item_num));
                break;

            case ITEM_Meruss:
                effect = new Effect(AppManager.getInstance().getBitmap(R.drawable.eff_meruss), ITEM_Meruss);
                effect.Eff_Meruss(m_item.get(item_num));
                break;

            case ITEM_SigmaReflect:
                effect = new Effect(null, ITEM_SigmaReflect);
                effect.Eff_Reflect();
                for (int i = 0; i < m_monster.size(); i++)
                    m_monster.get(i).ChageDir(-1, -1);
                break;

            case ITEM_Adrenaline:
                effect = new Effect(null, ITEM_Adrenaline);
                effect.Eff_Adrenaline();

                for (int i = 0; i < m_monster.size(); i++)
                    m_monster.remove(i);
                m_monster.clear();
                break;
        }

        m_effect.add(effect);
        effect.Effect_TimerManager(eff_timer);
    }

    //Make
    public void Make_Monster(int make_num, int make_type) {
        for(int i = 0; i < make_num; i++)
        {
            Monster mon = null;
            Random rnd = new Random();


            switch(make_type)
            {
                case Monster_Type_01://Monster_Type_01
                    mon = new Monster_Type_01(DPI);
                    break;

                case Monster_Type_02://Monster_Type_02:
                    break;

                case Monster_Type_03://Monster_Type_03:
                    break;

            }//switch

            mon.SetPosition(DPI, this.random(-2, 38), -rnd.nextInt(2), 5, 5); //Max DPI[X] is 36
            mon.setDir(m_player.getPos());
            m_monster.add(mon);
        }//for

    }


    //////////////////////////////////Callback

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float tx,ty;
        tx =event.getX();
        ty =event.getY();

        int x = (int)tx;
        int y = (int)ty;

        if(button.contains(x,y))
        {
            m_player.setSensorRevise();

        }
        if(debugBtn.contains(x,y))
        {
            if (debugCheck) debugCheck = false;
            else debugCheck= true;
        }
        if(GameOver_Check == true && m_gameover.r_gomain.contains(x,y))
        {
            AppManager.getInstance().get_myMediaPlayer().stop(AppManager.MUSIC_MAINGAME_BGM);
            System.exit(0);
        }
        return false;
    }

    private int random(int start, int end) {
        Random rnd = new Random();
        if (end <= start) {
            return (rnd.nextInt(start - end) + start);
        }
        return (rnd.nextInt(end - start) + start);
    }

    //-------------------------------------------------------타이머 매니저
    public void MainGame_TimerManager() {
        //------------------------------------------------타이머1
        TimerTask maingame_timer = new TimerTask() {
            public void run() {
                try {
                    if(m_pause.m_return == false && GameOver_Check == false)
                    {
                        m_ui.updateScore();
                        mGameTimer++;

                        AddMonster();
                        AddItem();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer Timer1 = new Timer();
        Timer1.schedule(maingame_timer, 0, 100);
    }
}