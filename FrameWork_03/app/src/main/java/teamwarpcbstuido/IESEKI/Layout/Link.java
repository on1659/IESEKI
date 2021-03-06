package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

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

    public final int GAME_PAUSE = 0;
    public final int GAME_PLAYING = 1;


    Pause m_pause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        AppManager.getInstance().RenderManager();
        AppManager.getInstance().setLink(Link.this);

        setContentView(R.layout.custom_view);

        m_pause = new Pause(Link.this);
        m_pause.setCancelable(false);

        AppManager.getInstance().get_myMediaPlayer().reStartplay(AppManager.MUSIC_MAINGAME_BGM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().get_myMediaPlayer().reStartplay(AppManager.MUSIC_SELECT_BGM);
        AppManager.getInstance().getGameView().Destory();
        AppManager.getInstance().setGameView(null);
        AppManager.getInstance().setThread(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_MAINGAME_BGM);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppManager.getInstance().getGameView().StartThread();
        AppManager.getInstance().getGameView().onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppManager.getInstance().getGameView().onPause();
        AppManager.getInstance().getGameView().Destory();
        AppManager.getInstance().setThread(null);
    }

   public void Finish() {
        AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_MAINGAME_BGM);
        AppManager.getInstance().getGameView().Destory();
        this.finish();
    }

    public void PopupPause()
    {
        m_pause.m_return = true;//SetReturn(true);
	    AppManager.getInstance().getGameView().ShowPause(true);
        m_pause.show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                this.PopupPause();
                AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_MAINGAME_BGM);
                 break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
