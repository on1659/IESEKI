package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.GameView;
import teamwarpcbstuido.IESEKI.org.MyMediaPlayer;
import teamwarpcbstuido.IESEKI.org.MySoundPool;
import teamwarpcbstuido.IESEKI.org.PreferenceManager;
import teamwarpcbstuido.IESEKI.org.SoundManager;
import teamwarpcbstuido.IESEKI.org.moveSensor;

/**
 * Created by JYJ on 2015-06-03.
 */
public class SelectMenu extends Activity implements View.OnClickListener {

    static public MyMediaPlayer m_myMediaPlayer;
    static public MySoundPool m_mySoundPool;
    private PreferenceManager m_preferenceManager;
    private moveSensor m_moveSensor;

    ImageButton btn_start;
    ImageButton btn_quit;
    ImageButton btn_help;
    ImageButton btn_option;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.select_menu);

        btn_start = (ImageButton) findViewById(R.id.select_btn_start);
        btn_start.setOnClickListener(this);

        btn_quit = (ImageButton) findViewById(R.id.select_btn_quit);
        btn_quit.setOnClickListener(this);

        btn_help = (ImageButton) findViewById(R.id.select_btn_help);
        btn_help.setOnClickListener(this);

        btn_option = (ImageButton) findViewById(R.id.select_btn_option);
        btn_option.setOnClickListener(this);

        m_myMediaPlayer = new MyMediaPlayer(this);
        m_mySoundPool = new MySoundPool(this);

        Display dispaly = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
        //m_thread = new GameViewThread((getHolder(), this);
        int width = dispaly.getWidth();
        int height = dispaly.getHeight();

        m_preferenceManager = new PreferenceManager(this);
        m_moveSensor = new moveSensor(this);

        AppManager.getInstance().setPreference(m_preferenceManager);
        AppManager.getInstance().setMoveSensor(m_moveSensor);
        AppManager.getInstance().setSize(width, height);


        m_myMediaPlayer.play(AppManager.MUSIC_SELECT_BGM);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.select_btn_start:
                m_myMediaPlayer.stop(AppManager.MUSIC_SELECT_BGM);
                intent = new Intent(this, Link.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                //this.onDestroy();
                System.exit(0);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
