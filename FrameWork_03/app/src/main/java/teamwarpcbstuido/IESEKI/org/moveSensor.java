package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

/**
 * Created by Administrator on 2015-05-06.
 */
public class moveSensor extends View implements SensorEventListener {

    SensorManager m_sensorManager; // 센서를 사용하려면 필요한 센서매니져
    // 센서에따라 움직일 아이콘의 좌표
    int m_x = 0;
    int m_y= 0;

    float Heading;
    float Pitch;
    float Roll;

    int Width, Height;

    Debug deb =  new Debug();

    public moveSensor(Context context)
    {
        super(context);
        m_sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        m_sensorManager.registerListener(this, m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
      synchronized (this)
      {
          switch(event.sensor.getType())
          {
              case Sensor.TYPE_ORIENTATION:
                  Heading = event.values[0];
                    Pitch = event.values[1];
                     Roll = event.values[2];
                  break;
          }
      }
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
    public float getX(){return Roll;}
    public float getY(){return Pitch;}

}
