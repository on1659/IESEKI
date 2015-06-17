package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.game.Pause;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.MyMediaPlayer;
import teamwarpcbstuido.IESEKI.org.MySoundPool;

/**
 * Created by JYJ on 2015-06-03.
 */
public class Link extends Activity {

    static public AlertDialog.Builder builder;
    static boolean pause_check = false;

     static public MySoundPool m_mySoundPool;
     static public MyMediaPlayer m_myMediaPlayer;

    Pause m_pause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//가로세로전환

        setContentView(R.layout.custom_view);

        m_pause = new Pause(Link.this);
        m_pause.setCancelable(false);

       // m_selectMenu.m_myMediaPlayer.play(AppManager.MUSIC_MAINGAME_BGM);
        //a = Link.this;

        AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_MAINGAME_BGM);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                m_pause.m_return = true;
                m_pause.show();

                AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_MAINGAME_BGM);
                // m_selectMenu.m_myMediaPlayer.pause(AppManager.MUSIC_MAINGAME_BGM);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }



}
