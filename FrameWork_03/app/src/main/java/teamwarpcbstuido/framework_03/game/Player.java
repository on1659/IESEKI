package teamwarpcbstuido.framework_03.game;

import android.app.AppOpsManager;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.AppManager;
import teamwarpcbstuido.framework_03.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-04-09.
 */
public class Player extends SpriteAnimation {

    long m_gameTime;
    public int width, height;
    protected  int limitX, limitY;
    int hp;

    public Player(Bitmap bitmap){
        super(bitmap);
        int dpiX, dpiY;

        width =  AppManager.getInstance().getWidth();
        height=  AppManager.getInstance().getHeight();

        dpiX=  AppManager.getInstance().getDPI(0);
        dpiY=  AppManager.getInstance().getDPI(1);

        this.InitSpriteData(10, 1 ,10, 6);
        this.SetPosition(dpiX * 5, dpiY * 6,dpiX * 9,dpiY * 9);



    }
    public void  onUpdate(long GameTime)
    {
        m_gameTime = GameTime;
        this.SpriteUpdate(GameTime);

        m_cx -= (int)AppManager.getInstance().getSensorX();
        m_cy -= (int)AppManager.getInstance().getSensorY();

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
    }
}
