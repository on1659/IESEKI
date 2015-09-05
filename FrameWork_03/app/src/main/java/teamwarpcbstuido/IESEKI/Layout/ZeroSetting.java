package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by JYJ on 2015-06-08.
 */
public class ZeroSetting extends Activity {

    ZeroSettingCanvas SettingVIew;

    int l;
    int t;
    int w;
    int h;

    private int DPI[] = new int[2];

    static public boolean zeroset_btn_push = false;
    static public boolean speeddown_btn_push = false;
    static public boolean speedup_btn_push = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DPI = AppManager.getInstance().getDPI();

        l = ZeroSettingCanvas.SpeedPosCheck(DPI[0]);
        t = DPI[1] * 10;
        w = DPI[0] * 3;
        h = DPI[1] * 3;

        SettingVIew = new ZeroSettingCanvas(this);
        setContentView(SettingVIew);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tx, ty;
        tx = (int) event.getX();
        ty = (int) event.getY();

        zeroset_btn_push = false;
        speedup_btn_push = false;
        speeddown_btn_push = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (SettingVIew.m_ZeroSetButton.contains(tx, ty)) {
                    Toast.makeText(this.getApplicationContext(), getString(R.string.zero_set), Toast.LENGTH_LONG).show();
                    SettingVIew.setSensorRevise();

                    zeroset_btn_push = true;
                }

                else if(SettingVIew.m_GameSpeedButtonUp.contains(tx, ty)) {
                    AppManager.getInstance().GameSpeedUp();
                    SettingVIew.SetSpeed(SettingVIew.SpeedPosCheck());
                    SettingVIew.Invalidate();

                    speedup_btn_push = true;
                }
                else if(SettingVIew.m_GameSpeedButtonDown.contains(tx,ty)) {
                    AppManager.getInstance().GameSpeedDown();
                    SettingVIew.SetSpeed(SettingVIew.SpeedPosCheck());
                    SettingVIew.Invalidate();

                    speeddown_btn_push = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

}