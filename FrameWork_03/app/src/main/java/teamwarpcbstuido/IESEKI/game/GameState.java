package teamwarpcbstuido.IESEKI.game;

import android.graphics.Canvas;

import teamwarpcbstuido.IESEKI.org.Collision;
import teamwarpcbstuido.IESEKI.org.Debug;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

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
    final private  int Monster_Type_01 = 85;
    final private  int Monster_Type_02 = 95;
    final private  int Monster_Type_03 = 105;

    final private int ITEM_PIWheel = 0;
    final private int ITEM_BloodyShield = 1;
    final private int ITEM_AlphaMissile = 3;
    final private int ITEM_SigmaReflect = 4;
    final private int ITEM_Adrenaline = 5;

    private float FPS;


    private int DPI[] = new int[2];

    private BackGround m_background;
    private Player m_player;
    private Debug debug = new Debug();

    private ArrayList<Monster> m_monster = new ArrayList<Monster>();
    private ArrayList<Item> m_item = new ArrayList<Item>();
    private ArrayList<Effect> m_effect = new ArrayList<Effect>();

    private long LastRegenEnemy = System.currentTimeMillis();
    private long LastRegenItem= System.currentTimeMillis();

    long fpsStartTime = 0L;             // Frame 시작 시간
    int frameCnt = 0;                      // 돌아간 Frame 갯수
    double timeElapsed = 0.0f;

    float current_time;


    boolean debugCheck;
    Rect debugBtn = new Rect();

    Rect button = new Rect();

    @Override
    public void Init()
    {
        DPI =  AppManager.getInstance().getDPI();
        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

        m_background = new BackGround();
        //m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.character_ray));
        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player));
        FPS = 0;

        current_time = System.currentTimeMillis();

        Make_Monster(4, Monster_Type_01);
        Make_Item(5, ITEM_PIWheel);

        button.set(DPI[X] * 25, DPI[Y] * 40, DPI[X] * 30, DPI[Y] * 45);
        debugBtn.set(DPI[X] * 25, DPI[Y] * 20, DPI[X] * 30, DPI[Y] * 25);

        debugCheck= false;
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

        if (m_effect.size() != 0) {
            for (int i = 0; i < m_effect.size() - 1; i++)
                m_effect.get(i).onDraw(canvas);
        }


        {

            debug.drawText(canvas, m_player.getPos().left, 150, 450, 45, Color.RED);
            debug.drawText(canvas, m_player.getPos().top, 250, 450, 45, Color.RED);

            debug.drawText(canvas, AppManager.getInstance().getSensorX(), 150, 150, 45, Color.RED);
            debug.drawText(canvas, AppManager.getInstance().getSensorY(), 150, 250, 45, Color.RED);

            debug.drawText(canvas, m_effect.size(), 150, 350, 45, Color.BLUE);

        }

        debug.drawText(canvas, FPS, 250, 550, 55, Color.BLUE);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(button, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(debugBtn, paint);

        m_player.onDraw(canvas);

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
        return 1/fps;
    }


    @Override
    public void Update()
    {
        long GameTime = System.currentTimeMillis();
        FPS = this.FramePerSecond();

        m_player.onUpdate(GameTime);
        m_background.onUpdate(GameTime);

        if (m_monster.size() != 0) {
            for (int i = 0; i < m_monster.size() - 1; i++)
            {
                m_monster.get(i).Move(FPS);
                if(m_monster.get(i).Die()) m_monster.remove(i);

            }
        }

        if (m_item.size() != 0) {
            for (int i = 0; i < m_item.size() - 1; i++) {
                m_item.get(i).Move(FPS);
            }
        }

        this.AddMonster();
        this.AddItem();
        this.Collision();
    }

    @Override
    public void Destroy()
    {
    }
    public void Collision()
    {
        if (m_monster.size() != 0) {
            for (int i = 0; i < m_monster.size() - 1; i++) {

                //Circle Collision
                if (Collision.collisionCircle(m_monster.get(i).getX(), m_monster.get(i).getY(), m_monster.get(i).getRadius(), m_player.getX(), m_player.getY(), m_player.getRadius()))
                {
                    m_monster.remove(i);
                }
            }
        }

        if (m_item.size() != 0) {
            for (int i = 0; i < m_item.size() - 1; i++) {

                //Circle Collision
                if (Collision.collisionCircle(m_item.get(i).getX(), m_item.get(i).getY(), m_item.get(i).getRadius(), m_player.getX(), m_player.getY(), m_player.getRadius())) {
                    Make_Effect(m_item.get(i).getType());
                    m_item.remove(i);
                }
            }
        }
    }

    public void AddItem() {
        Random rnd = new Random();
        long GameTime = System.currentTimeMillis();
        if (System.currentTimeMillis() - LastRegenItem >= 5500) {
            LastRegenItem = System.currentTimeMillis();
            Make_Item(rnd.nextInt(8), ITEM_PIWheel); // 추후 랜덤
        }
    }
    public void AddMonster()
    {
        Random rnd =new Random();
        long GameTime = System.currentTimeMillis();
        if (System.currentTimeMillis() - LastRegenEnemy >= 5500)
        {
            LastRegenEnemy = System.currentTimeMillis();
            Make_Monster(rnd.nextInt(8), Monster_Type_01);
        }
    }

    public void Make_Item(int make_num, int make_type) {
        for (int i = 0; i < make_num; i++) {
            Item itm = null;
            Random rnd = new Random();

            switch (make_type) {
                case ITEM_PIWheel:
                    itm = new Item_PIWheel(DPI);
                    itm.setType(ITEM_PIWheel);
                    break;
                case ITEM_BloodyShield:
                    break;
                case ITEM_AlphaMissile:
                    break;
                case ITEM_SigmaReflect:
                    break;
                case ITEM_Adrenaline:
                    break;
            }

            itm.SetPosition(DPI, rnd.nextInt(25) + 2, -rnd.nextInt(2), 5, 5); //Max DPI[X] is 36

            itm.setDir(m_player.getX(), m_player.getY());
            m_item.add(itm);
        }
    }

    public void Make_Effect(int itm_type) {
        Effect eff = null;

        switch (itm_type) {
            case ITEM_PIWheel:
                eff = new Effect(ITEM_PIWheel);
                eff.Eff_PIWheel(DPI, 5, 5, 10, 10);
                break;
            case ITEM_BloodyShield:
                break;
            case ITEM_AlphaMissile:
                break;
            case ITEM_SigmaReflect:
                break;
            case ITEM_Adrenaline:
                break;
        }

        m_effect.add(eff);
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
                    mon = new Monster_Type_01(DPI);
                    break;
                case 95://Monster_Type_02:
                    break;
                case 105://Monster_Type_03:
                    break;
            }//switch

            mon.SetPosition(DPI, rnd.nextInt(25) + 2, -rnd.nextInt(2), 5, 5); //Max DPI[X] is 36
            mon.setDir(m_player.getX(), m_player.getY());
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
        return false;
    }

    private int random(int start, int end) {
        Random rnd = new Random();
        if (end <= start) {
            return (rnd.nextInt(start - end) + start);
        }
        return (rnd.nextInt(end - start) + start);
    }
}