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
public class Item extends SpriteAnimation {

    protected int m_speed;
    protected int xDestiny, yDestiny;
    protected float xDir, yDir;
    protected int width, height;

    protected int m_type;

    public Item(Bitmap bitmap) {
        super(bitmap);
        xDir = 1;
        yDir = 1;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
    }


    public void Move(float fps) {
        m_cy += (m_speed * yDir) * fps;
        m_cx += (m_speed * xDir / 3) * fps;
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

    public void setType(int _type) {
        m_type = _type;
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
}
