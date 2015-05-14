package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;

import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-04-09.
 */
public class Player extends SpriteAnimation {

    long m_gameTime;
    public int width, height;
    protected  int reviseX, reviseY;
    int hp;

    public Player(Bitmap bitmap){
        super(bitmap);
        int DPI[] = new int[2];

        width =  AppManager.getInstance().getWidth();
        height=  AppManager.getInstance().getHeight();
        DPI = AppManager.getInstance().getDPI();

        float revise[] = new float[2];
        revise = AppManager.getInstance().getPreference().SensorLoad();
        reviseX = (int)revise[0];
        reviseY = (int)revise[1];

        //this.InitSpriteData(10, 1 ,10, 6);
        this.InitSpriteData(1, 1 ,1, 1);

        this.SetPosition(DPI,5,6,5,5);
    }
    public void  onUpdate(long GameTime)
    {
        m_gameTime = GameTime;
        this.SpriteUpdate(GameTime);

        m_cx -= (int)AppManager.getInstance().getSensorX() - reviseX;
        m_cy -= (int)AppManager.getInstance().getSensorY() - reviseY;

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

        m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
    }

    void setSensorRevise()
    {
        reviseX =(int) AppManager.getInstance().getSensorX();
        reviseY =(int) AppManager.getInstance().getSensorY();
        AppManager.getInstance().getPreference().SensorSave(reviseX, reviseY);
    }

    public int getReviseX(){return reviseX;}
    public int getReviseY(){return reviseY;}


}