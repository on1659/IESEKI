package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.MyMediaPlayer;
import teamwarpcbstuido.IESEKI.org.MySoundPool;
import teamwarpcbstuido.IESEKI.org.PreferenceManager;
import teamwarpcbstuido.IESEKI.org.moveSensor;

/**
 * Created by JYJ on 2015-06-03.
 */
public class SelectMenu extends Activity  {

    ImageButton btn_start;
    ImageButton btn_quit;
    ImageButton btn_help;
    ImageButton btn_option;

    boolean btn_start_push = false;
    boolean btn_help_push = false;
    boolean btn_option_push = false;
    boolean btn_quit_push = false;


    /**back 버튼 두번 터치 간(Default 1초)*/
    private final static int BACK_TIMMER = 1 * 1000;
    /**back 버튼 두번 터치 체크용 flag*/
    private boolean touchflag = false;

    boolean m_flagPause;
    boolean m_flagPausebtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.select_menu);
        m_flagPause = false;
        m_flagPausebtn  = false;

       btn_start = (ImageButton) findViewById(R.id.select_btn_start);
       //btn_start.setOnClickListener(this);

       btn_quit = (ImageButton) findViewById(R.id.select_btn_quit);
       //btn_quit.setOnClickListener(this);

       btn_help = (ImageButton) findViewById(R.id.select_btn_help);
       //btn_help.setOnClickListener(this);

       btn_option = (ImageButton) findViewById(R.id.select_btn_option);
       //btn_option.setOnClickListener(this);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_SELECT_BGM);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
        AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_SELECT_BGM);
    }


    public void OnClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.select_btn_start:
                m_flagPausebtn = true;
                AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_SELECT_BGM);
                intent = new Intent(this, Link.class);
                startActivity(intent);

                if(btn_start_push == false) {
                    btn_start.setSelected(true);
                    btn_start_push = true;
                }
                else if(btn_start_push == true) {
                    btn_start.setSelected(false);
                    btn_start_push = false;
                }
                break;

            case R.id.select_btn_help:
                intent = new Intent(this, Help.class);
                startActivity(intent);

                if(btn_help_push == false) {
                    btn_help.setSelected(true);
                    btn_help_push = true;
                }
                else if(btn_help_push == true) {
                    btn_help.setSelected(false);
                    btn_help_push = false;
                }
                break;

            case R.id.select_btn_option:

                intent = new Intent(this, Option.class);
                startActivity(intent);

                if(btn_option_push == false) {
                    btn_option.setSelected(true);
                    btn_option_push = true;
                }
                else if(btn_option_push == true) {
                    btn_option.setSelected(false);
                    btn_option_push = false;
                }
                break;

            case R.id.select_btn_quit:
                AppManager.getInstance().Destroy();
                finish();
                System.exit(0);

                if(btn_quit_push == false) {
                    btn_quit.setSelected(true);
                    btn_quit_push = true;
                }
                else if(btn_quit_push == true) {
                    btn_quit.setSelected(false);
                    btn_quit_push = false;
                }
                break;

            default:
                break;
        }

    }//onClick

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(touchflag)
                {
                    //2번째 클릭시 종료한다.
                    finish();
                }
                else{
                    //1번째 클릭시 flag를 true로 변경, 타이머 동작
                    startTimer();
                }
                return true;
            case KeyEvent.KEYCODE_MENU:

            default :
                return false;
        }
    }

    private void startTimer(){

        touchflag = true;
        AppManager.getInstance().ShowToast(getString(R.string.double_touch_exit));

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                touchflag = false;
            }
        }, BACK_TIMMER);

    }
}
