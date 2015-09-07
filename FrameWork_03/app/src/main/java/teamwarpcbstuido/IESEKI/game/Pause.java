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

    ImageButton custom_swt_music_onoff;
    ImageButton custom_swt_vibe_onoff;

    boolean btn_gomain_push = false;
    boolean btn_return_push = false;

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

        custom_swt_music_onoff = (ImageButton) findViewById(R.id.pause_music_custom_switch);
        custom_swt_music_onoff.setOnClickListener(this);

        if(AppManager.getInstance().getPreference().MusicOptionLoad() == true)
            custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_on);
        else
            custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_off);

        custom_swt_vibe_onoff = (ImageButton) findViewById(R.id.pause_vibe_custom_switch);
        custom_swt_vibe_onoff.setOnClickListener(this);

        if(AppManager.getInstance().getPreference().VibeOptionLoad() == true)
            custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_on);
        else
            custom_swt_vibe_onoff.setBackgroundResource(R.drawable.swt_off);

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
                AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_MAINGAME_BGM);
                //AppManager.getInstance().get_myMediaPlayer().release(AppManager.MUSIC_MAINGAME_BGM);
                AppManager.getInstance().getGameView().ShowPause(false);

                AppManager.getInstance().GetTimer().cancel(); //타이머1 종료

                //System.exit(0);
                this.Exit();

                if(btn_gomain_push == false) {
                    btn_gomain.setSelected(true);
                    btn_gomain_push = true;
                }
                else if(btn_gomain_push == true) {
                    btn_gomain.setSelected(false);
                    btn_gomain_push = false;
                }
                break;

            case R.id.pause_btn_return:
                AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_MAINGAME_BGM);
                //AppManager.getInstance().get_myMediaPlayer().release(AppManager.MUSIC_MAINGAME_BGM);
                AppManager.getInstance().getGameView().ShowPause(false);

                m_return = false;
                dismiss();

                if(btn_return_push == false) {
                    btn_return.setSelected(true);
                    btn_return_push = true;
                }
                else if(btn_return_push == true) {
                    btn_return.setSelected(false);
                    btn_return_push = false;
                }
                break;

            case R.id.pause_music_custom_switch:

                if(AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
                    custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_off);

                    AppManager.getInstance().getPreference().MusicOptionSave(false);
                }
                else {
                    custom_swt_music_onoff.setBackgroundResource(R.drawable.swt_on);

                    AppManager.getInstance().getPreference().MusicOptionSave(true);
                }

                break;

            case R.id.pause_vibe_custom_switch:

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
