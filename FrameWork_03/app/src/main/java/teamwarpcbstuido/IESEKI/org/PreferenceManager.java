package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015-05-15.
 */
public class PreferenceManager {

    Context m_context;

    public  PreferenceManager(Context _context)
    {
        m_context = _context;
    }

    public void DataSave(String str, int data)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putInt(str, data);
        ed.commit();
    }
    public void DataSave(String str, float data)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putFloat(str, data);
        ed.commit();
    }

    public int DataLoad(String str, boolean type)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt(str, 0);
    }

    public int DataLoad(String str)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt(str, 0);
    }

    public float DataLoad(String str,float type)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getFloat(str, 0);
    }

    public void SensorSave(float _reviseX, float _reviseY){

        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putFloat("reviseX", _reviseX);
        ed.putFloat("reviseY", _reviseY);
        ed.commit();

    }

    public void GameSpeedSave(float speed)
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putFloat("GameSpeed", speed);
        ed.commit();
    }
    public float GameSpeedLoad()
    {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getFloat("GameSpeed", 1.5f);
    }

    public float[] SensorLoad()
    {
        float revise[] = new float[2];
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        revise[0] =  pref.getFloat("reviseX", 0);
        revise[1] =  pref.getFloat("reviseY", 0);
        return revise;
    }

    public void MusicOptionSave(boolean _onoff) {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean("Music_OnOff", _onoff);
        ed.commit();

    }

    public boolean MusicOptionLoad() {
        boolean music_onoff;
        music_onoff = false;
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        music_onoff = pref.getBoolean("Music_OnOff", true);
        return music_onoff;
    }

    public void BestScoreSave(int _score) {
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putInt("BestScore", _score);
        ed.commit();

    }

    public int BestScoreLoad() {
        int bestscore;
        bestscore = 0;
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        bestscore = pref.getInt("BestScore", 0);
        return bestscore;
    }


}
