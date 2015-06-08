package teamwarpcbstuido.IESEKI.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.Debug;

/**
 * Created by JYJ on 2015-06-08.
 */
public class ZeroInTestView extends View implements SensorEventListener {

    SensorManager m_sensorManager; // 센서를 사용하려면 필요한 센서매니져

    // 센서에따라 움직일 아이콘의 좌표
    int m_x = 0;
    int m_y = 0;

    float Heading;
    float Pitch;
    float Roll;

    int Width, Height;

    Debug deb = new Debug();

    Bitmap bmp;
    Bitmap background;

    public ZeroInTestView(Context context) {
        super(context);

        Display display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        // 센서매니져를 받아온뒤 리스너로 SensorEventListner 를 상속받은 자신을 등록한다.
        m_sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        m_sensorManager.registerListener(this, m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);

        // 아이콘을 그려준다.
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(background, 0, 0, null);

        // 아이콘을 그려준다.
        canvas.drawBitmap(bmp, m_x, m_y, null);

        deb.drawText(canvas, "Orientation :", 0, 20, 20, Color.BLACK);
        deb.drawText(canvas, "Heading :" + Float.toString(Heading), 0, 40, 20, Color.BLACK);
        deb.drawText(canvas, "Pitch :" + Float.toString(Pitch), 0, 60, 20, Color.BLACK);
        deb.drawText(canvas, "Roll :" + Float.toString(Roll), 0, 80, 20, Color.BLACK);

        deb.drawText(canvas, "Device Width : " + Width, 0, 110, 25, Color.BLUE);
        deb.drawText(canvas, "Device Height : " + Height, 0, 130, 25, Color.BLUE);


        deb.drawText(canvas, "m_x : " + m_x, 0, 160, 25, Color.RED);
        deb.drawText(canvas, "m_y : " + m_y, 0, 180, 25, Color.RED);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()) {
                // 방향센서값의 경우
                case Sensor.TYPE_ORIENTATION:
                    Heading = event.values[0];
                    Pitch = event.values[1];
                    Roll = event.values[2];


                    // 아이콘움직이기

                    //if(m_x >= 0 && m_x <= Width)
                    m_x -= Roll;
                    // if(m_y >= 0 && m_y <= Height)
                    m_y -= Pitch;

                    // 아이콘이 화면밖못빠져나가게 고정한다
                    if (m_x <= 0)
                        m_x = 0;
                    if (m_y <= 0)
                        m_y = 0;

                    if (m_x > Width - bmp.getWidth())
                        m_x = Width - bmp.getWidth();
                    if (m_y > Height - bmp.getHeight())
                        m_y = Height - bmp.getHeight();
                    break;
            }
        }
        // 화면을 갱신한다.
        invalidate();

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // 센서 정확도
    }
}
