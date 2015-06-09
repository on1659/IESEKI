package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by JYJ on 2015-06-08.
 */
public class ZeroInTest extends Activity {

    ZeroSetting SettingVIew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SettingVIew = new ZeroSetting(this);
        setContentView(SettingVIew);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tx, ty;
        tx = (int) event.getX();
        ty = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (SettingVIew.m_Button.contains(tx, ty)) {
                    Toast.makeText(this.getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                    SettingVIew.setSensorRevise();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

}
