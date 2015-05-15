package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-05-15.
 */
public class Item extends SpriteAnimation{

    protected int m_speed;
    protected int xDestiny, yDestiny;
    protected float xDir, yDir;
    protected  int width, height;

    public Item(Bitmap bitmap) {
        super(bitmap);
        xDir = 1;
        yDir = 1;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
    }


    public void Move(long GameTime, float fps)
    {
        m_cy += (m_speed * yDir) * fps;
        m_cx += (m_speed * xDir / 3) * fps;
    }

    public boolean Collision(Rect col)
    {
        return m_pos.intersect(col);
    }

    public void setDir(int _x, int _y)
    {
        xDestiny = _x;
        yDestiny = _y;

        if(xDestiny > m_cx)
        {
            xDir = 1;
        }
        else
        {
            xDir = -1;
        }
    }

    public boolean Die(){
      //  if(m_cy > height + 15 || m_cx < - 15 || m_cx > width + 15) //어떤 해상도이든 5이상 넘어가면 된다.
      //      return true;

        return  false;
    }


}
