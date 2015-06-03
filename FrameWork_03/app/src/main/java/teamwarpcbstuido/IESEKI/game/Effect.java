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
    private int m_speed;

    public long m_CreateTime;
    private long m_activeTime;

    public Effect(Bitmap bitmap, int type) {
        super(bitmap);
        m_type = type;
        rota = 0;
        m_CreateTime = System.currentTimeMillis();

        //z ���� ������Ʈ ����
        m_activeTime = 0; // ���⿡�� Preference���� ������ �޾ƿ´�

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
    }

    public void Eff_BloodyShdiled(Item item) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {
        m_cx = item.m_cx;
        m_cy = item.m_cy;
        m_w = item.getWidth() / 2 * 3;
        m_h = item.getHight() / 2 * 3;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
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

    public void Eff_Meruss(Item item) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {
        m_cx = item.m_cx;
        m_cy = item.m_cy;
        m_w = item.getWidth() / 2 * 3;
        m_h = item.getHight() / 2 * 3;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        DPI = AppManager.getInstance().getDPI();
        m_speed = DPI[0] * 5;
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

            case GameState.ITEM_BloodyShield:
                canvas.rotate(rota, m_cx, m_cy);
                canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.eff_bloodyshield), null, m_pos, null);
                canvas.rotate(-rota, m_cx, m_cy);
                break;

            case GameState.ITEM_Meruss:
                canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.dragon_mou), null, m_pos, null);
                break;
        }
    }

    public void onUpdate(long _GameTime, Player _player, float fps) {
        m_GameTime = _GameTime;

        switch (m_type) {
            case GameState.ITEM_PIWheel:
                rota += 5;
                break;

            case GameState.ITEM_BloodyShield:
                m_cx = _player.m_cx;
                m_cy = _player.m_cy;
                m_w = _player.getWidth() / 2 * 3;
                m_h = _player.getHight() / 2 * 3;
                m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
                rota += 5;
                break;

            case GameState.ITEM_Meruss:
                m_activeTime += 15;
                m_pos.top -= m_speed * fps;
                m_pos.bottom -= m_speed * fps;
                break;

        }
    }

    public boolean Die() {
        if ((m_GameTime - m_CreateTime) / 1000 > m_activeTime + 3) //������ �⺻ ���ӽð��� 3��
        {
            return true;    //��������
        }
        if (m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //� �ػ��̵� ���� ���μ��α���
            return true;

        return false;
    }

    public Rect getPos() {
        return m_pos;
    }


}
