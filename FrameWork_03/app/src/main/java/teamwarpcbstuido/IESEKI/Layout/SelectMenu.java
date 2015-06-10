package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
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
public class SelectMenu extends Activity implements View.OnClickListener {

   // static public MyMediaPlayer m_myMediaPlayer;
   // static public MySoundPool m_mySoundPool;


    ImageButton btn_start;
    ImageButton btn_quit;
    ImageButton btn_help;
    ImageButton btn_option;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.select_menu);


        btn_start = (ImageButton) findViewById(R.id.select_btn_start);
        btn_start.setOnClickListener(this);

        btn_quit = (ImageButton) findViewById(R.id.select_btn_quit);
        btn_quit.setOnClickListener(this);

        btn_help = (ImageButton) findViewById(R.id.select_btn_help);
        btn_help.setOnClickListener(this);

        btn_option = (ImageButton) findViewById(R.id.select_btn_option);
        btn_option.setOnClickListener(this);

        AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_SELECT_BGM);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.select_btn_start:
                AppManager.getInstance().get_myMediaPlayer().stop(AppManager.MUSIC_SELECT_BGM);
               intent = new Intent(this, Link.class);
                startActivity(intent);
                //finish(); //이거 finish 해주는게 확실히 맞는지 확인하고, 생애주기와 더불어 추후에 수정여부 결정
                break;

            case R.id.select_btn_help:

                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;

            case R.id.select_btn_option:

                intent = new Intent(this, Option.class);
                startActivity(intent);
                break;

            case R.id.select_btn_quit:

                finish();
                System.exit(0);
                break;

            default:
                break;
        }

    }//onClick

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                //this.Destory()
                System.exit(0);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
