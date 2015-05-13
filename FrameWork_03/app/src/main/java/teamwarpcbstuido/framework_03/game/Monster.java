package teamwarpcbstuido.framework_03.game;

import android.app.AppOpsManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.WindowManager;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.AppManager;
import teamwarpcbstuido.framework_03.org.SpriteAnimation;
/**
 * Created by Administrator on 2015-05-13.
 */
public class Monster extends SpriteAnimation
{

   public static Monster m_monster;
   protected int m_hp;
   protected int m_speed;
    protected int xDestiny, yDestiny;

    public Monster(Bitmap bitmap) {
        super(bitmap);
    }
    public void Move()
    {

        if(yDestiny > m_cy)
        {
            m_cy--;
        }
        else if(yDestiny < m_cy)
        {
           m_cy++;
        }

        if(xDestiny > m_cx)
        {
            m_cx++;
        }
        else if(xDestiny < m_cx)
        {
            m_cx--;
        }

    }
}
