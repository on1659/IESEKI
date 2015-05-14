package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;
/**
 * Created by Administrator on 2015-05-13.
 */
public class Monster extends SpriteAnimation
{

   protected int m_speed;
   protected int xDestiny, yDestiny;
   protected float xDir, yDir;
   protected  int height;

    public Monster(Bitmap bitmap) {
        super(bitmap);
        xDir = 1;
        yDir = 1;
        height = AppManager.getInstance().getHeight();
    }


    public void Move(float fps)
    {
        m_cy += (m_speed * yDir);
        m_cx += (m_speed * xDir) / 3;
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
        if(m_cy > height + 30) //어떤 해상도이든 30이상 넘어가면 된다.
            return true;
        return  false;
    }


}
