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
   protected  int width, height;

    public Monster(Bitmap bitmap)
    {
        super(bitmap);
        xDir = 1;
        yDir = 1;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
    }

    public void Move(float fps)
    {
        m_cy += (m_speed * yDir) * fps;
        m_cx += (m_speed * xDir / 3) * fps;
    }


    public boolean Collision(Rect col)
    {
        return m_pos.intersect(col);
    }

    public void setDir(Rect _Destiny) {
        xDestiny = _Destiny.left;
        yDestiny = _Destiny.top;

        if (xDestiny > m_cx) {
            xDir = 1;
        } else {
            xDir = -1;
        }
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

    public boolean Die()
    {
        if (m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //어떤 해상도이든 몬스터 가로세로길이
            return true;

        return  false;
    }

    public void ChageDir(float _xDir, float _yDir) {
        xDir *= _xDir;
        yDir *= _yDir;
    }
}
