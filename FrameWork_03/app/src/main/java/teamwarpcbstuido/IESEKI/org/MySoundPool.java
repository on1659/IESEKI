package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

import teamwarpcbstuido.IESEKI.R;

/**
 * Created by JYJ on 2015-06-08.
 */
public class MySoundPool {

    private SoundPool m_SoundPool;
    private HashMap m_SoundPoolMap;
    private AudioManager m_AudioManager;


    int monsterdie_sound;

    public MySoundPool(Context context) {

        /* SoundPool(
        한번에 실행가능한 사운드수 , 스트림 타입 , 음질);*/
        m_SoundPool = new SoundPool(200, AudioManager.STREAM_MUSIC, 0);
        m_SoundPoolMap = new HashMap();

        //SoundPool용 사운드 로드 (context, 리소스id, 우선순위)
        AppManager.EFFECT_MONSTER_DIE = m_SoundPool.load(context, R.raw.effectmusic_monsterdie, 0);
    }

    public void play(int choice) {

        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            //실행할 사운드 ID, 왼쪽볼륨, 오른쪽볼륨, 재생우선순위, 반복여부, 재생속도
            m_SoundPool.play(choice, 5, 5, 0, 0, 1);
        }
    }

    public void stop(int choice) {
        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            m_SoundPool.stop(choice);
        }
    }

    public void pause(int choice) {
        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            m_SoundPool.pause(choice);
        }
    }

}
