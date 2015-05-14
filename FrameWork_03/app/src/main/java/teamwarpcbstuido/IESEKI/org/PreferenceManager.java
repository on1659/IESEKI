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

    public int DataLoad(String str,int type)
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
    public float[] SensorLoad()
    {
        float revise[] = new float[2];
        SharedPreferences pref = m_context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        revise[0] =  pref.getFloat("reviseX", 0);
        revise[1] =  pref.getFloat("reviseY", 0);
        return revise;
    }


}
