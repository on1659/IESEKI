package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.GraphicObject;

/**
 * Created by JYJ on 2015-06-04.
 */
public class UI extends GraphicObject {

    private int X = 0;
    private int Y = 1;
    int DPI[] = new int[2];
    int k;

    Bitmap m_healthbar;
    Rect m_src_healthbar;
    Rect m_dst_healthbar;

    int width, height;

    public UI() {
        super(null);

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();

        DPI = AppManager.getInstance().getDPI();

        m_healthbar = AppManager.getInstance().getBitmap(R.drawable.health_bar);
        m_healthbar = Bitmap.createScaledBitmap(m_healthbar, m_healthbar.getWidth() / DPI[X] * 8, m_healthbar.getHeight() / DPI[Y] * 8, true);

        m_src_healthbar = new Rect();
        m_dst_healthbar = new Rect();

        m_src_healthbar.set(0, 0, m_healthbar.getWidth() / 5 * 5, m_healthbar.getHeight());
        m_dst_healthbar.set(0, DPI[Y], m_healthbar.getWidth() / 5 * 5, DPI[Y] + m_healthbar.getHeight());
    }

    public void onUpdate(int hp) {
        if (hp >= 0) {
            m_src_healthbar.set(0, 0, m_healthbar.getWidth() / 5 * hp, m_healthbar.getHeight());
            m_dst_healthbar.set(0, DPI[Y], m_src_healthbar.right, DPI[Y] + m_healthbar.getHeight());
        }
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_healthbar, m_src_healthbar, m_dst_healthbar, null);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_healthbar, m_src_healthbar, m_dst_healthbar, null);
    }

}