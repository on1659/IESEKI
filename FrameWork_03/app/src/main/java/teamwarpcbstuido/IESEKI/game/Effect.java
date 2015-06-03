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

        //z 추후 업데이트 사항
        m_activeTime = 0; // 여기에서 Preference에서 데이터 받아온다

        width = AppManager.getInstance().getWidth();
        height = AppManager.getInstance().getHeight();
    }

    public void Eff_BloodyShdiled(Item item) //타이머로 시간재고 일정시간동안 돌리기 (효과발동 ~ 효과끝)
    {
        m_cx = item.m_cx;
        m_cy = item.m_cy;
        m_w = item.getWidth() / 2 * 3;
        m_h = item.getHight() / 2 * 3;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
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

    public void Eff_Meruss(Item item) //타이머로 시간재고 일정시간동안 돌리기 (효과발동 ~ 효과끝)
    {
        m_cx = item.m_cx;
        m_cy = item.m_cy;
        m_w = item.getWidth() / 2 * 3;
        m_h = item.getHight() / 2 * 3;
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        DPI = AppManager.getInstance().getDPI();
        m_speed = DPI[0] * 5;
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
        if ((m_GameTime - m_CreateTime) / 1000 > m_activeTime + 3) //아이템 기본 지속시간이 3초
        {
            return true;    //제거하자
        }
        if (m_cy > height + m_h || m_cx < -m_w || m_cx > width + m_w) //어떤 해상도이든 몬스터 가로세로길이
            return true;

        return false;
    }

    public Rect getPos() {
        return m_pos;
    }


}
