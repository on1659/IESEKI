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

        float revise[] = new float[2];
        float m_deree;
        Bitmap bitmap;
        Matrix m;
        m = new Matrix();

       //revise = AppManager.getInstance().getPreference().SensorLoad();
       //m_deree = (float)MathCalu.getCos(0, 1, (AppManager.getInstance().getSensorX() - (int) revise[0]), (AppManager.getInstance().getSensorY()) - (int) revise[1]);
       //m.postRotate(m_deree, m_bitmap.getWidth() / 2,  m_bitmap.getHeight() / 2 );
       //m_bitmap = Bitmap.createBitmap(m_bitmap, 0, 0, m_bitmap.getWidth(), m_bitmap.getHeight(), m, false);

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

    public void SetSpeed(int _offsetSpeed, int _changeSpeed) {
        m_speed = _changeSpeed;
        m_speed += _offsetSpeed;
    }

}
