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
        �ѹ��� ���డ���� ����� , ��Ʈ�� Ÿ�� , ����);*/
        m_SoundPool = new SoundPool(200, AudioManager.STREAM_MUSIC, 0);
        m_SoundPoolMap = new HashMap();

        //SoundPool�� ���� �ε� (context, ���ҽ�id, �켱����)
        AppManager.EFFECT_MONSTER_DIE = m_SoundPool.load(context, R.raw.effectmusic_monsterdie, 0);
    }

    public void play(int choice) {

        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            //������ ���� ID, ���ʺ���, �����ʺ���, ����켱����, �ݺ�����, ����ӵ�
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
