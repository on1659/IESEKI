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
        m_size = 40;
        this.InitSpriteData(104,62,3,6);
        // this.InitSpriteData(h,w/num,3,num);
        this.SetPosition(140, 380);
    }

    public void  onUpdate(long GameTime)
    {
        m_gameTime = GameTime;




        if(m_x > width - m_size)
             m_x = width - m_size;
        else if(m_x < 0)
            m_x = 0;
        else
            m_x -= (int)AppManager.getInstance().getSensorX();

        if(m_y > height - m_size)
            m_y = height- m_size;
        else if(m_y < 0)
            m_y = 0;
        else
          m_y -= (int)AppManager.getInstance().getSensorY();



    }
}
