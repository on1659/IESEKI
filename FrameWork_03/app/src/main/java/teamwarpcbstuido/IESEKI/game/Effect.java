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

        //z 추후 업데이트 사항
        m_activeTime = 0; // 여기에서 Preference에서 데이터 받아온다
    }

    public void Eff_PIWheel(int _x, int _y, int _w, int _h) //타이머로 시간재고 일정시간동안 돌리기 (효과발동 ~ 효과끝)
    {

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
        DPI = AppManager.getInstance().getDPI();

        m_cx = DPI[0] * _x;
        m_cy = DPI[1] * _y;
        m_w = DPI[0] * _w;
        m_h = DPI[1] * _h;
        /*
        1. 빙글빙글 돌아가게 하기
        2. 몬스터와 충돌처리
        */
    }


    public void Eff_PIWheel(Player _player) //타이머로 시간재고 일정시간동안 돌리기 (효과발동 ~ 효과끝)
    {
        m_cx = _player.m_cx;
        m_cy = _player.m_cy;
        m_w = _player.getWidth() / 2 * 3;
        m_h = _player.getHight() / 2 * 3;

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));

        /*
        1. 빙글빙글 돌아가게 하기
        2. 몬스터와 충돌처리
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
        if ((m_GameTime - m_CreateTime) / 1000 > m_activeTime + 3) //아이템 기본 지속시간이 3초
        {
            return true;    //제거하자
        }
        return false;  //제거안해
    }

    public Rect getPos() {
        return m_pos;
    }

}
