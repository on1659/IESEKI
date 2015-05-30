package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.security.PrivateKey;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by JYJ on 2015-05-20.
 */
public class Effect extends SpriteAnimation {


    private int rota;

    Bitmap bmp_effect;
    private int m_type;
    public long m_GameTime;
    private int width;
    private int height;
    private int DPI[] = new int[2];

    public long m_CreateTime;
    private long m_activeTime;

    public Effect(Bitmap bitmap, int type) {
        super(bitmap);
        m_type = type;
        rota = 0;
        m_CreateTime = System.currentTimeMillis();

        //z ���� ������Ʈ ����
        m_activeTime = 0; // ���⿡�� Preference���� ������ �޾ƿ´�
    }

    public void Eff_PIWheel(int _x, int _y, int _w, int _h) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
        DPI = AppManager.getInstance().getDPI();

        m_cx = DPI[0] * _x;
        m_cy = DPI[1] * _y;
        m_w = DPI[0] * _w;
        m_h = DPI[1] * _h;
        /*
        1. ���ۺ��� ���ư��� �ϱ�
        2. ���Ϳ� �浹ó��
        */
    }


    public void Eff_PIWheel(Player _player) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {
        m_cx = _player.m_cx;
        m_cy = _player.m_cy;
        m_w = _player.getWidth() / 2 * 3;
        m_h = _player.getHight() / 2 * 3;

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));

        /*
        1. ���ۺ��� ���ư��� �ϱ�
        2. ���Ϳ� �浹ó��
        */
    }
    public void onDraw(Canvas canvas) {
        switch (m_type)

        {
            case GameState.ITEM_PIWheel:
                canvas.rotate(rota, m_cx, m_cy);
                canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.eff_piwheel), null, m_pos, null);
                canvas.rotate(-rota, m_cx, m_cy);
                break;
        }
    }

    public void onUpdate(long _GameTime, Player _player) {
        m_GameTime = _GameTime;
        m_cx = _player.m_cx;
        m_cy = _player.m_cy;
        m_w = _player.getWidth() / 2 * 3;
        m_h = _player.getHight() / 2 * 3;

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));

        rota += 5;
    }

    public boolean Die() {
        if ((m_GameTime - m_CreateTime) / 1000 > m_activeTime + 3) //������ �⺻ ���ӽð��� 3��
        {
            return true;    //��������
        }
        return false;  //���ž���
    }

    public Rect getPos() {
        return m_pos;
    }

}
