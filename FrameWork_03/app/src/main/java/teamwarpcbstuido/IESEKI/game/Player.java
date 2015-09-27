package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.MathCalu;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-04-09.
 */
public class Player extends SpriteAnimation {

    private float m_gameTime;
    public int width, height;
    protected  int reviseX, reviseY;
    private double m_deree;
    private float m_speed;
    private boolean isTablet;
    int DPI[] = new int[2];

    public boolean Equip_BloodyShield; //플레이어가 블러디쉴드를 가진 상태인가?(BloodyShield Modify)

    public Player(String name)
    {
        super(name);

        width =  AppManager.getInstance().getWidth();
        height=  AppManager.getInstance().getHeight();
        DPI = AppManager.getInstance().getDPI();
        m_deree = 0;
        float revise[] = new float[2];
        revise = AppManager.getInstance().getPreference().SensorLoad();
        reviseX = (int)revise[0];
        reviseY = (int)revise[1];
        isTablet = AppManager.getInstance().IsTablet();
        m_speed = AppManager.getInstance().getGameSpeed();
        //this.InitSpriteData(10, 1 ,10, 6);
        this.InitSpriteData(1, 1, 1, 1);
        this.SetPosition(width / 2, height/ 2, DPI[0] * 5, DPI[1] * 5);

        Equip_BloodyShield = false; //초기 블러디쉴드 false(BloodyShield Modify)
    }

    public void onUpdate(float GameTime)
    {
        m_gameTime = GameTime;
        this.SpriteUpdate(GameTime);


        //position
        if(isTablet)
        {
            m_deree = MathCalu.getCos(0, 1, (-AppManager.getInstance().getSensorY() - reviseY), (AppManager.getInstance().getSensorX()) - reviseX);


            m_cx += ((int)AppManager.getInstance().getSensorY()  * m_speed - reviseY);
            m_cy -=  ((int)AppManager.getInstance().getSensorX() * m_speed - reviseX);

        }
        else
        {
            m_deree = MathCalu.getCos(0, 1, (AppManager.getInstance().getSensorX() - reviseX), (AppManager.getInstance().getSensorY()) - reviseY);

            m_cx -= ((int)AppManager.getInstance().getSensorX()  * m_speed - reviseX);
            m_cy -=  ((int)AppManager.getInstance().getSensorY() * m_speed - reviseY);
        }

        if(m_cx >= width - m_w/2) {
            m_cx = width - m_w/2;
        }

        else if(m_cx <= m_w/2) {
            m_cx = m_w/2;
        }


        if(m_cy >= height - m_h/2) {
            m_cy = height- m_h/2;
        }

        else if(m_cy <= m_h/2) {
            m_cy = m_h/2;
        }

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
    }

    public void setSensorRevise()
    {
        reviseX = (int) AppManager.getInstance().getSensorX();
        reviseY = (int) AppManager.getInstance().getSensorY();
        AppManager.getInstance().getPreference().SensorSave(reviseX, reviseY);
    }

    public void onDrawPlayer(Canvas canvas)
    {
        onDraw(canvas, m_deree);
    }

    public void setSpeed(float speed)
    {
        m_speed = speed;
    }



    public int getReviseX(){return reviseX;}
    public int getReviseY(){return reviseY;}
    public double getDegree(){return m_deree;}


}