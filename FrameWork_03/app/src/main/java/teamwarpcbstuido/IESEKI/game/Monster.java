package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.MathCalu;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;
/**
 * Created by Administrator on 2015-05-13.
 */
public class Monster extends SpriteAnimation
{

   protected int m_speed;
   protected  int m_speedX;
   protected  int m_speedY;
   protected int xDestiny, yDestiny;
   protected float xDir, yDir;
   protected  int width, height;
    private boolean m_hardMode;

    public Monster(String name)
    {
        super(name);
        xDir = 1;
        yDir = 1;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        m_hardMode = false;
    }

    public void onUpdate(float fps) {
        // m_speed = DPI[1] * 5;

        if(m_hardMode)
        {
            m_cy += (m_speedX * yDir) * fps;
            m_cx += (m_speedY * xDir / 3) * fps;
        }
       else
        {
            m_cy += (m_speedX * yDir) * fps;
            m_cx += (m_speedY * xDir / 3) * fps;
        }
    }


    public boolean Collision(Rect col)
    {
        return m_pos.intersect(col);
    }

    public void setDir(Rect _Destiny) {

        float revise[] = new float[2];
        float m_deree;
        Bitmap bitmap;
        Matrix m;
        m = new Matrix();

        xDestiny = _Destiny.left;
        yDestiny = _Destiny.top;

        if (xDestiny > m_cx)
        {
            xDir = 1;
        } else {
            xDir = -1;
        }
    }

    public void setDir(Rect _Destiny, boolean hardMode) {

        m_hardMode = hardMode;
        double dir[] = new double[2];
        dir = MathCalu.getDirectionVector(m_cx, m_cy,_Destiny.left, _Destiny.top);

        xDir = (float)dir[0];
        yDir = (float)dir[1];

       //float mGap = yDestiny - m_cy;
       //if (mGap > 0)
       //{
       //    yDir = 1;
       //}
       //else {
       //    yDir = -1;
       //}
           // m_speed *= AppManager.getInstance().getDPI()[1] * AppManager.getInstance().Random(5,5);
        if(m_hardMode == true)
        {
            m_speedY *= 3;
            m_speedX *= 3;
        }
        else
        {
            m_speedX *= 2;
            m_speedY *= 2;
        }
       // xDestiny = _Destiny.left;
       // yDestiny = _Destiny.top;
//
       // if (xDestiny > m_cx)
       // {
       //     xDir = 1;
       // } else {
       //     xDir = -1;
       // }
    }

    public void SetHardModeDir(float y)
    {
        yDir = y;
    }

    public void setDir(int _x, int _y) {
        xDestiny = _x;
        yDestiny = _y;

        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            xDir = 0;
            return;

        }
        if(xDestiny > m_cx)
        {
            xDir = 1;
        }
        else
        {
            xDir = -1;
        }
    }

    public boolean Die() {
        if (m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //어떤 해상도이든 몬스터 가로세로길이
            return true;

        return  false;
    }

    public void ChageDir(float _xDir, float _yDir) {
        xDir *= _xDir;
        yDir *= _yDir;
    }

    /*
     public void SetSpeed(int _offsetSpeed, int _changeSpeed) {
         m_speed = _changeSpeed;
        m_speed += _offsetSpeed;
    }
    */
}
