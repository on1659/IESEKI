package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-05-15.
 */
public class Item extends SpriteAnimation {

    protected int m_speed;
    protected int xDestiny, yDestiny;
    protected float xDir, yDir;
    protected int width, height;

    protected  int wallCount;
    protected boolean isReflect;
    protected int m_type;


    public Item(Bitmap bitmap, int DPI[], int _type) {
        super(bitmap);
        xDir = 1;
        yDir = 1;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        m_type = _type;

        this.InitSpriteData(1, 1, 1, 1);
        m_speed = DPI[1] * 5;
        wallCount = 0;
        isReflect = false;
    }

    public void Move(float fps) {


        m_cy += (m_speed * yDir) * fps;
        m_cx += (m_speed * xDir / 3) * fps;

        if(wallCount >= 3)return;

        //초기에 생성했을때 화면안에 들어왔는지 아닌지 체크해야된다
        // if(!isView)
        // {
        //  if(m_w/2 <= m_cx && m_cx <= width && m_cy/2  <= m_cy && m_cy + m_h <= height)
        //      isView = true;
        //  }
        //else
        //{

        if(m_cx >= width - m_w/2 || m_cx <= m_w/2)
        {
            xDir *= -1;
            wallCount ++;
        }

        if(m_cy >= height - m_h/2)
        {
            yDir *= -1;
            isReflect= true;
            wallCount++;
        }
        if(isReflect && m_cy <= m_h/2)
        {
            yDir *= -1;
            wallCount++;
        }
        //}
    }

    public void setDir(int px, int py) {
        int range = width / 10;

        if (px - range < m_cx && m_cx < px + range)
            xDir = 0;
        else if (px - range > m_cx)
            xDir = -1;
        else if (px + range < m_cx)
            xDir = 1;
    }

    public int getType() {
        return m_type;
    }

    public void Wall_collision() {

    }

    public boolean DIe() {
        if (m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //어떤 해상도이든 몬스터 가로세로길이
            return true;

        return false;
    }

    public int getWallCount()
    {
        return wallCount;
    }
}
