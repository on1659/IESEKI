package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

    ImageButton custom_swt_music_onoff;
    ImageButton custom_swt_vibe_onoff;

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

        custom_swt_music_onoff = (ImageButton) findViewById(R.id.option_music_custom_switch);
        custom_swt_music_onoff.setOnClickListener(this);

        if(AppManager.getInstance().getPreference().MusicOptionLoad() == true)
            custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_on);
        else
            custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_off);

        custom_swt_vibe_onoff = (ImageButton) findViewById(R.id.option_vibe_custom_switch);
        custom_swt_vibe_onoff.setOnClickListener(this);

        if(AppManager.getInstance().getPreference().VibeOptionLoad() == true)
            custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_on);
        else
            custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_off);

    }

    @Override
    public void onClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.option_btn_zeroin:

                AppManager.getInstance().RenderManager();
                intent = new Intent(this, ZeroSetting.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;

            case R.id.option_music_custom_switch:

                if(AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
                    custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_off);

                    AppManager.getInstance().getPreference().MusicOptionSave(false);
                }
                else {
                    custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_on);

                    AppManager.getInstance().getPreference().MusicOptionSave(true);
                }

                break;

            case R.id.option_vibe_custom_switch:

                if(AppManager.getInstance().getPreference().VibeOptionLoad() == true) {
                    custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_off);

                    AppManager.getInstance().getPreference().VibeOptionSave(false);
                }
                else {
                    custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_on);

                    AppManager.getInstance().getPreference().VibeOptionSave(true);
                }

                break;

            default:
                break;
        }
    }//onClick
}
