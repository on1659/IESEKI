package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import teamwarpcbstuido.IESEKI.Layout.Link;
import teamwarpcbstuido.IESEKI.Layout.Loading;
import teamwarpcbstuido.IESEKI.Layout.SelectMenu;
import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by JYJ on 2015-06-08.
 */
public class Pause extends Dialog implements View.OnClickListener {

    ImageButton btn_return;
    ImageButton btn_gomain;
    Context m_context;

    //public 일부로 public로 선언함
    public static boolean m_return;

    Switch swt_music_onoff;

    public Pause(Context context) {

        super(context);
        m_context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.custom_dialog);

        //------------------------------------------------다이얼로그/액티비티 비율 설정하기
        double d_width = AppManager.getInstance().getWidth() * 0.7; //Display의 가로의 70%
        double d_height = AppManager.getInstance().getHeight() * 0.7;  //Display의 세로의 70%
        getWindow().getAttributes().width = (int) d_width;
        getWindow().getAttributes().height = (int) d_height;


        btn_return = (ImageButton) findViewById(R.id.pause_btn_return);
        btn_gomain = (ImageButton) findViewById(R.id.pause_btn_gomain);

        btn_return.setOnClickListener(this);
        btn_gomain.setOnClickListener(this);

        swt_music_onoff = (Switch) findViewById(R.id.custom_switch_music);

        swt_music_onoff.setChecked(AppManager.getInstance().getPreference().MusicOptionLoad());
        swt_music_onoff.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton cb, boolean isChecking) {
                AppManager.getInstance().getPreference().MusicOptionSave(isChecking);
            }
        });

        m_return = false;
    }

    public void Exit()
    {
       // AppManager.getInstance().get_myMediaPlayer().stop(AppManager.MUSIC_MAINGAME_BGM);
        //System.exit(0);

        AppManager.getInstance().getLink().Finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.pause_btn_gomain:
                AppManager.getInstance().get_myMediaPlayer().stop(AppManager.MUSIC_MAINGAME_BGM);
               AppManager.getInstance().getGameView().ShowPause(false);
               // System.exit(0);

                AppManager.getInstance().Timer1.cancel(); //타이머1 종료

                this.Exit();
                break;

            case R.id.pause_btn_return:
                AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_MAINGAME_BGM);
                AppManager.getInstance().getGameView().ShowPause(false);

                m_return = false;
                dismiss();
                break;

            default:
                break;
        }
    }//onClick



}
