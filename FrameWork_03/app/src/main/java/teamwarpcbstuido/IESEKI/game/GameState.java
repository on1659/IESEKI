package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.system.OsConstants;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import teamwarpcbstuido.IESEKI.Layout.Link;
import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.Collision;
import teamwarpcbstuido.IESEKI.org.Debug;
import teamwarpcbstuido.IESEKI.org.IState;

import static android.graphics.Color.*;

/**
 * Created by Administrator on 2015-04-09.
 */
public class GameState implements IState {
   // Context context;
   // public GameState(Context context){
   //     //self.context = context;
 //   }
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

    final public static int NORMAL_MODE = 0;
    final public static int HARD_MODE   = 1;

    private float FPS;


    private int DPI[] = new int[2];

    private BackGround m_background;
    private UI m_ui;
    private Player m_player;
    private Debug debug = new Debug();
    private Pause m_pause;
    private GameOver m_gameover;


    TimerTask eff_timer;

    private ArrayList<Monster> m_monster = new ArrayList<Monster>();
      private ArrayList<Item> m_item = new ArrayList<Item>();
    private ArrayList<Effect> m_effect = new ArrayList<Effect>();


    private long LastRegenEnemy = System.currentTimeMillis();
    private long LastRegenItem= System.currentTimeMillis();

    long fpsStartTime = 0L;             // Frame 시작 시간
    int frameCnt = 0;                      // 돌아간 Frame 갯수
    double timeElapsed = 0.0f;

    float current_time;

    long tmpTime;


    boolean GameOver_Check;
    private long mGameTimer;
    private long mGameLevelDesign;
    private int mCreatMonsetNum;

    private int itemCreateTime;

    private int m_mode;

    Rect tempbutton;

    private boolean m_showPause;

    //private TimerTask maingame_timer;
    //private Timer Timer1;

    @Override
    public void Init()
    {

        DPI =  AppManager.getInstance().getDPI();
        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

        m_background = new BackGround();
        m_ui = new UI();
        m_gameover = new GameOver();

        m_player = new Player("Player");

        MainGame_TimerManager();

        this.RestartGame();

        tempbutton = new Rect();
        tempbutton.set(width / 2, height / 2, width / 2 + 50, height / 2 + 50);
    }


    @Override
    public void RestartGame()
    {

        m_monster.clear();
        m_effect.clear();
        m_item.clear();
        m_player.SetPosition(width / 2, height / 2);
        m_ui.ScoreInit();

        FPS = 0;

        current_time = System.currentTimeMillis();

        Make_Monster(4, Monster_Type_01, NORMAL_MODE);
        Make_Item(2, ITEM_BloodyShield);

        tmpTime = 0;

        GameOver_Check = false;

        mCreatMonsetNum = 4;
        mGameTimer =0;
        m_mode = NORMAL_MODE;

        itemCreateTime = 0;

        mGameLevelDesign = 60;

        m_showPause = false;
    }

    @Override
    public void onPause()
    {
        AppManager.getInstance().setPasueFlag(true);
        AppManager.getInstance().SetPauseSaveData("character", m_player);
        AppManager.getInstance().SetPauseSaveData("item", m_item);
        AppManager.getInstance().SetPauseSaveData("effect", m_effect);
        AppManager.getInstance().SetPauseSaveData("background", m_background);
        AppManager.getInstance().SetPauseSaveData("monster", m_monster);
    }

    @Override
    public void onResume()
    {
        m_player = (Player) AppManager.getInstance().GetPauseSaveData("character");
        m_item = (ArrayList<Item>) AppManager.getInstance().GetPauseSaveData("item");
        m_effect = (ArrayList<Effect>) AppManager.getInstance().GetPauseSaveData("effect");
        m_background= (BackGround) AppManager.getInstance().GetPauseSaveData("background");
        m_monster = (ArrayList<Monster>) AppManager.getInstance().GetPauseSaveData("monster");

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



        {
           //if (GameOver_Check)
           //    debug.drawText(canvas, "GameOver_Check True", DPI, 5, 13, 55, BLUE);

           //else
           //    debug.drawText(canvas, "GameOver_Check False", DPI, 5, 13, 55, BLUE);

           //if (m_showPause)
           //    debug.drawText(canvas, "m_showPause True", DPI, 5, 18, 55, BLUE);

           //else
           //    debug.drawText(canvas, "m_showPause False", DPI, 5, 18, 55, BLUE);

            //debug.drawText(canvas, "FPS : " + FPS, DPI, 10, 13, 55, BLUE);

           //debug.drawText(canvas, "Player_left : " + m_player.getPos().left, DPI, 10, 15, 35, RED);
           //debug.drawText(canvas, "Player_Top : " + m_player.getPos().top, DPI, 10, 17, 35, RED);
           //debug.drawText(canvas, "Player_degree : " + m_player.getDegree(), DPI, 10, 19, 35, RED);


          debug.drawText(canvas, "Roll : " + AppManager.getInstance().getSensorX(), DPI, 4, 21, 35, BLUE);
          debug.drawText(canvas, "Pitch : " + AppManager.getInstance().getSensorY(), DPI, 4, 23, 35, BLUE);

           //debug.drawText(canvas, "Monster Num : " + m_monster.size(), DPI, 10, 25, 35, GREEN);


          //  debug.drawText(canvas, "Item Num : " + m_item.size(), DPI, 10, 27, 35, BLACK);


           // debug.drawText(canvas, "Effect Num : " + m_effect.size(), DPI, 10, 29, 35, CYAN);


           /// if (m_effect.size() > 1) {
           ///     debug.drawText(canvas, "GameTime  : " + m_effect.get(0).m_activeTime, DPI, 10, 31, 35, YELLOW);
           ///     debug.drawText(canvas, "GameTime  : " + m_effect.get(1).m_activeTime, DPI, 10, 35, 35, YELLOW);
           /// }

            // debug.drawText(canvas, FPS, 250, 550, 55, BLUE);


         }


        m_player.onDrawPlayer(canvas);
        m_ui.onDraw(canvas);

        if(GameOver_Check == true)
             m_gameover.onDraw(canvas);


        canvas.drawBitmap(AppManager.getInstance().GetImage("Player"), null, tempbutton, null);

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

    public void onUpdate()
    {
        if (m_pause.m_return == false && GameOver_Check == false) {
            long GameTime = System.currentTimeMillis();
            FPS = this.FramePerSecond();
            this.Collision();

            m_player.onUpdate(FPS);
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


        }

    }


    @Override
    public void Update() {
        if (m_pause.m_return == false && GameOver_Check == false) {
            long GameTime = System.currentTimeMillis();
            FPS = this.FramePerSecond();
            this.Collision();

            m_player.onUpdate(FPS);
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


        }

    }

    @Override
    public void Destroy()
    {
        AppManager.getInstance().SetTimerTask(null);
        AppManager.getInstance().SetTiemr(null);
        m_monster = null;
        m_item = null;
        m_effect = null;
        m_background = null;
        m_ui = null;
        m_gameover = null;
        m_player = null;
    }

    public void Collision() {

        boolean isCollision = false;

        if (m_monster.size() != 0)
        {
            for (int i = 0; i < m_monster.size() - 1; i++) {


                //Circle Collision
                if (Collision.collisionCircle(m_monster.get(i).getX(), m_monster.get(i).getY(), m_monster.get(i).getRadius(), m_player.getX(), m_player.getY(), m_player.getRadius()))
                {
                    for(int num = 0; num < m_effect.size(); num++)
                    {
                        if(m_effect.get(num).getType() == ITEM_BloodyShield)
                            isCollision = true;
                    }
                   // m_monster.remove(i);

                    if(isCollision)
                        break;

                    if(m_ui.getScore()> AppManager.getInstance().getPreference().BestScoreLoad())
                        AppManager.getInstance().getPreference().BestScoreSave(m_ui.getScore());

                    GameOver_Check = true;
                }

                for (int effNum = 0; effNum < m_effect.size(); effNum++)
                {
                    //Circle Collision
                    if (m_effect.get(effNum).getType() == ITEM_PIWheel || m_effect.get(effNum).getType() == ITEM_BloodyShield || m_effect.get(effNum).getType() == ITEM_Meruss)
                    {
                        if (Collision.collisionCircle(m_monster.get(i).getX(), m_monster.get(i).getY(), m_monster.get(i).getRadius(), m_effect.get(effNum).getX(), m_effect.get(effNum).getY(), m_effect.get(effNum).getRadius()))
                        {
                            m_ui.killMonster(50);
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

    public void MakeItem()
    {
        if(mGameTimer % 10 == 0)
        {
            Random rnd = new Random();
            if(rnd.nextBoolean())
             Make_Item(1, rnd.nextInt(5)); // 추후 랜덤
            else
                return;
        }
    }

    //AddItem
    public void AddItem() {

        if(itemCreateTime < 0) return;
        if (m_item.size() == 3) {
            for (int i = 0; i < m_item.size(); i++)
            {
                if (m_item.get(i).getWallCount() > 2)
                {
                  MakeItem();
                    break;
                }

            }
        }

        if (m_item.size() < 3)
        {
           MakeItem();
        }
    }

    //AddMonster
    public void AddMonster() {

        if(mGameTimer % 20 == 0)
            Make_Monster(mCreatMonsetNum, Monster_Type_01, m_mode);

        if(mGameTimer == mGameLevelDesign)
        {
            mCreatMonsetNum++;
            mGameLevelDesign += 60;
            if(mGameLevelDesign > 2000)
            {
                mGameLevelDesign = 2000;
                mCreatMonsetNum--;
                m_mode = HARD_MODE;
            }
        }

        //else if(mGameTimer == 120)
        //    mCreatMonsetNum = 6;
//
        //else if(mGameTimer == 220)
        //    mCreatMonsetNum = 7;
//
        //else if(mGameTimer == 320)
        //    mCreatMonsetNum = 8;
//
        //else if(mGameTimer == 420)
        //    mCreatMonsetNum = 9;
//
        //else if(mGameTimer == 520)
        //    mCreatMonsetNum = 10;
//
        //else if(mGameTimer == 660)
        //    mCreatMonsetNum = 11;
//
        //else if(mGameTimer == 780)
        //    mCreatMonsetNum = 12;
//
        //else if(mGameTimer == 860)
        //    mCreatMonsetNum = 13;
//
        //else if(mGameTimer == 940)
        //    mCreatMonsetNum = 14;
//
    }

    //Item Effect
    public void Make_Item(int make_num, int make_type) {
        for (int i = 0; i < make_num; i++) {
            Item itm = null;
            Random rnd = new Random();

            switch (make_type) {
                case ITEM_PIWheel:
                    itm = new Item("itm_piwheel", DPI, ITEM_PIWheel);
                    break;

                case ITEM_BloodyShield:
                    itm = new Item("itm_bloodyshield", DPI, ITEM_BloodyShield);
                    break;

                case ITEM_Meruss:
                    itm = new Item("itm_meruss", DPI, ITEM_Meruss);
                    break;

                case ITEM_SigmaReflect:
                    itm = new Item("itm_reflect", DPI, ITEM_SigmaReflect);
                    break;

                case ITEM_Adrenaline:
                    itm = new Item("itm_adrenaline", DPI, ITEM_Adrenaline);
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
                effect = new Effect("eff_piwheel", ITEM_PIWheel);
                effect.Eff_PIWheel(m_player);
                break;

            case ITEM_BloodyShield:
                effect = new Effect("eff_bloodyshield", ITEM_BloodyShield);
                effect.Eff_BloodyShield(m_item.get(item_num));
                break;

            case ITEM_Meruss:
                effect = new Effect("eff_meruss", ITEM_Meruss);
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

                m_ui.killMonster(m_monster.size() * 30);

                for (int i = 0; i < m_monster.size(); i++)
                    m_monster.remove(i);
                m_monster.clear();
                break;
        }

        m_effect.add(effect);
        effect.Effect_TimerManager(eff_timer);
    }

    //Make
    public void Make_Monster(int make_num, int make_type, int hardMode) {
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


            if(hardMode == HARD_MODE)
            {
                if(this.random())
                {
                    mon.SetPosition( -1 * this.random(0 , 15 * DPI[X]), this.random(- 5 * DPI[Y], height), DPI[X] * 5,DPI[Y] *  5);

                }
                else
                {

                    mon.SetPosition(this.random(width ,width + 15 * DPI[X]), this.random(- 5 * DPI[Y], height), DPI[X] * 5,DPI[Y] *  5);
                }
                mon.setDir(m_player.getPos(), true);
            }

            //mon.SetPosition(this.random(-2 * DPI[X], 38 * DPI[X]), -this.random(0, 5 * DPI[Y]), DPI[X] * 5,DPI[Y] * 5); //Max DPI[X] is 36

            if(hardMode == NORMAL_MODE )
            {
               // mon.SetPosition(this.random(-2 * DPI[X], 38 * DPI[X]), -this.random(0, 5 * DPI[Y]), DPI[X] * 5,DPI[Y] * 5); //Max DPI[X] is 36
                mon.SetPosition(this.random(-2 * DPI[X], 38 * DPI[X]), - 1 * this.random(0, 15 * DPI[Y]), DPI[X] * 5,DPI[Y] *  5); //Max DPI[X] is 36
                mon.setDir(m_player.getPos(), false);
            }

            m_monster.add(mon);
        }//for

    }



    @Override
    public void ShowPause(boolean flag)
    {
        m_showPause = flag;
    }


    //////////////////////////////////Callback

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float tx,ty;

        tx = event.getX();
        ty = event.getY();

        int x = (int)tx;
        int y = (int)ty;

        //죽어쓸때
        if(GameOver_Check == true && m_gameover.r_gomain.contains(x,y))
        {
           AppManager.getInstance().get_myMediaPlayer().stop(AppManager.MUSIC_MAINGAME_BGM);
          // System.exit(0);
           // Link.link.Finish();

            AppManager.getInstance().GetTimer().cancel(); //타이머1 종료
            AppManager.getInstance().getLink().Finish();

            return false;
           //this.RestartGame();
        }

        if(GameOver_Check == true && m_gameover.r_restart.contains(x,y))
        {
            this.RestartGame();
        }

        if(tempbutton.contains(x,y))
        {
            AppManager.getInstance().getLink().Share();
        }

        return false;
    }



    private int random(int dpi, int start, int end) {
        Random rnd = new Random();
        if (end <= start) {
            return (rnd.nextInt(start * dpi - end * dpi) + start * dpi);
        }
        return (rnd.nextInt(end * dpi - start * dpi) + start * dpi);
    }


    private int random(int start, int end) {
        Random rnd = new Random();
        if (end <= start) {
            return (rnd.nextInt(start - end) + start);
        }
        return (rnd.nextInt(end - start) + start);
    }

    private boolean random() {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }


    //-------------------------------------------------------타이머 매니저
    public void MainGame_TimerManager() {
        //------------------------------------------------타이머1
        AppManager.getInstance().maingame_timer = new TimerTask()
        {
            public void run() {
                try {
                    if(m_showPause == false && GameOver_Check == false)
                    {
                        m_ui.updateScore();
                        mGameTimer++;
                        itemCreateTime++;
                        AddMonster();
                        AddItem();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        AppManager.getInstance().Timer1 = new Timer();
        AppManager.getInstance().Timer1.schedule(AppManager.getInstance().maingame_timer, 0, 100);
    }
}