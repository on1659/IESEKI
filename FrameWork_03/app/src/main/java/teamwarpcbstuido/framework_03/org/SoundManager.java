package teamwarpcbstuido.framework_03.org;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;


public class SoundManager {

    private  static SoundManager s_instance;
    private SoundPool           m_SoundPool;
    private HashMap             m_SoundPoolMap;
    private AudioManager        m_AudioManager;
    private Context             m_context;


    public  static SoundManager getInstance(){
        if(s_instance == null){
            s_instance = new SoundManager();
        }
        return s_instance;
    }

    public void Init(Context context){
        m_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        m_context = context;
    }
    public void addSound(int _index, int _soundId){
        int id = m_SoundPool.load(m_context, _soundId, 1);
        m_SoundPoolMap.put(_index, id);
    }

    public void play(int _index){
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume((AudioManager.STREAM_MUSIC));
        m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, 0, 1f);

    }

    public void playLooped(int _index) {
        float streamVolume = m_AudioManager.getStreamVolume((AudioManager.STREAM_MUSIC));
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume((AudioManager.STREAM_MUSIC));
        m_SoundPool.play((Integer) m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, -1, 1f);
    }

}
