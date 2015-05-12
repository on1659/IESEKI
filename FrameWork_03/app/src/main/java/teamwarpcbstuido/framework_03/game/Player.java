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
    int m_size;
    public Player(Bitmap bitmap){
        super(bitmap);
        int w, h;
        int num;
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        num = 6;
        width =  AppManager.getInstance().getWidth();
        height=  AppManager.getInstance().getHeight();
        this.InitSpriteData(10, 1 ,10, 6);
        this.SetPosition(140, 380,70,80);

    }

    public void  onUpdate(long GameTime)
    {
        m_gameTime = GameTime;
        this.SpriteUpdate(GameTime);

        m_cx -= (int)AppManager.getInstance().getSensorX();
        m_cy -= (int)AppManager.getInstance().getSensorY();

        if(m_cx >= width - m_w) {
            m_cx = width - m_w;
        }

        else if(m_cx <= m_w) {
            m_cx = m_w;
        }


        if(m_cy >= height - m_h) {
            m_cy = height- m_h;
        }

        else if(m_cy <= m_h) {
            m_cy = m_h;
        }


    }
}
