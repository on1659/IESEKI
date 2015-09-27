package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.Debug;
import teamwarpcbstuido.IESEKI.org.SpriteAnimation;

/**
 * Created by JYJ on 2015-05-20.
 */
public class Effect extends SpriteAnimation {

    Bitmap bmp_effect;
    private int m_type;

    private int width;
    private int height;
    private int DPI[] = new int[2];
    private int m_speed;

    public int m_activeTime = 0;

    Paint pnt = new Paint();
    int m_alpha;

    Paint pnt2 = new Paint();
    int m_alpha2;

    public Effect(String name, int type) {
        super(name);
        m_type = type;

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
        DPI = AppManager.getInstance().getDPI();

        m_alpha = 255;
        pnt.setAlpha(m_alpha);

        m_alpha2 = 200;
        pnt2.setAlpha(m_alpha2);

        m_activeTime = 5;
    }

    public void Eff_BloodyShield(Item item) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {
        m_cx = item.m_cx;
        m_cy = item.m_cy;
        m_w = item.getWidth() / 2 * 3;
        m_h = item.getHight() / 2 * 3;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        //this.InitSpriteData(1, 1, 1, 1);
        this.SetSprite(40, 12, 1);
        /*
        1. ���ۺ��� ���ư��� �ϱ�
        2. ���Ϳ� �浹ó��
        */
    }
    public void Eff_PIWheel(Player _player) //Ÿ�̸ӷ� �ð���� �����ð����� ������ (ȿ���ߵ� ~ ȿ����)
    {
        m_cx = _player.m_cx;
        m_cy = _player.m_cy;
        m_w = _player.getWidth() / 2 * 5;
        m_h = _player.getHight() / 2 * 5;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        //this.InitSpriteData(12, 1, 1, 12);
        this.SetSprite(40, 12, 1);
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
        this.InitSpriteData(1, 1, 1, 1);
        m_speed = DPI[0] * 3;
        /*
        1. ���ۺ��� ���ư��� �ϱ�
        2. ���Ϳ� �浹ó��
        */
    }

    public void Eff_Reflect() {
        //�ٷε����� �̰���
        m_activeTime = 0;
        return;
    }

    public void Eff_Adrenaline() {
        //�ٷε����� �̰���
        m_activeTime = 3;
        pnt2.setColor(Color.WHITE);
        return;
    }

    public void Eff_Draw(Canvas canvas) {

        switch (m_type) {
            case GameState.ITEM_PIWheel:
                onDraw(canvas, pnt);
                break;

            case GameState.ITEM_BloodyShield:
                onDraw(canvas, pnt);
                break;

            case GameState.ITEM_Meruss:
                onDraw(canvas);
                break;

            case GameState.ITEM_Adrenaline:
                canvas.drawRect(0, 0, width, height, pnt2);
                break;
        }
    }

    public void onUpdate(Player _player, float fps) {

        switch (m_type) {

            case GameState.ITEM_PIWheel:
                //this.SpriteUpdate(fps);
                this.OnSpriteUpdate();
                break;

            case GameState.ITEM_BloodyShield:
                this.OnSpriteUpdate();
                m_cx = _player.m_cx;
                m_cy = _player.m_cy;
                m_w = _player.getWidth() / 2 * 3;
                m_h = _player.getHight() / 2 * 3;
                m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));

                //블러디 쉴드 이펙트가 생존해 있는 기간동안에는 블러디쉴드 상태를 계속 true해줌(BloodyShield Modify)
                _player.Equip_BloodyShield = true;

                break;

            case GameState.ITEM_Meruss:
                m_cy -= m_speed * fps;
                m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
                break;

        }

        if(m_alpha > 5) {
            m_alpha -= 2;
            pnt.setAlpha(m_alpha);
        }

        if(m_alpha2 > 5) {
            m_alpha2 -= 5;
            pnt2.setAlpha(m_alpha2);
        }
    }

    //-------------------------------------------------------Ÿ�̸� �Ŵ���
    public void Effect_TimerManager(TimerTask timertask1) {
        //------------------------------------------------Ÿ�̸�1
        timertask1 = new TimerTask() {
            public void run() {
                try {
                    if (m_type != GameState.ITEM_Meruss)
                        m_activeTime--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer Timer1 = new Timer();
        Timer1.schedule(timertask1, 0, 1000); //�����ð� 1�� ���� ����
    }

    public boolean Die() {
        if (m_activeTime < 1) //������ �⺻ ���ӽð��� 3��
        {
            return true;    //��������
        }
        if (m_cy < 0 || m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //� �ػ��̵� ���� ���μ��α���
            return true;

        return false;
    }

    public int getType() {
        return m_type;
    }

}
