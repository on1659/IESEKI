package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by JYJ on 2015-06-07.
 */
public class Option extends Activity implements View.OnClickListener {

    ImageButton btn_zeroin;

    Switch swt_music_onoff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//가로세로전환

        setContentView(R.layout.option);

        btn_zeroin = (ImageButton) findViewById(R.id.option_btn_zeroin);
        btn_zeroin.setOnClickListener(this);

        swt_music_onoff = (Switch) findViewById(R.id.option_switch_music);
        swt_music_onoff.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton cb, boolean isChecking) {
                AppManager.getInstance().getPreference().MusicOptionSave(isChecking);
            }
        });
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.option_btn_zeroin:
                intent = new Intent(this, ZeroInTest.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                break;
        }
    }//onClick
}
