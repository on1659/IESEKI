package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
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

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//���μ�����ȯ


        setContentView(R.layout.custom_view);

        m_pause = new Pause(Link.this);
        m_pause.setCancelable(false);


       // m_selectMenu.m_myMediaPlayer.play(AppManager.MUSIC_MAINGAME_BGM);


        AppManager.getInstance().get_myMediaPlayer().play(AppManager.MUSIC_MAINGAME_BGM);

       //if(AppManager.getInstance().getPreference().DataLoad("gameMode", 0) == GAME_PAUSE)
       //    AppManager.getInstance().getGameView().onResume();

       //AppManager.getInstance().getPreference().DataSave("gameMode",GAME_PLAYING);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().getGameView().Destory();
        AppManager.getInstance().setGameView(null);
        AppManager.getInstance().setThread(null);
    }



    @Override
    protected void onResume() {
        super.onResume();
      //  AppManager.getInstance().getGameView().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // AppManager.getInstance().getGameView().onPause();
      //  AppManager.getInstance().getPreference().DataSave("gameMode", GAME_PAUSE);
        m_pause.Exit();
    }


    public void Share()
    {
        //View rootView = findViewById(android.R.id.content).getRootView();
        LinearLayout relativeLayout = (LinearLayout)findViewById(R.id.customview_linearlayout);
        AppManager.getInstance().implement_Capture_Share(this, relativeLayout);// Take_Capture.getInstance().takeScreenshot(rootView);
    }


    public void Finish() {
        AppManager.getInstance().getGameView().Destory();
        this.finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                m_pause.m_return = true;//SetReturn(true);
                m_pause.show();
                AppManager.getInstance().get_myMediaPlayer().pause(AppManager.MUSIC_MAINGAME_BGM);
                //m_selectMenu.m_myMediaPlayer.pause(AppManager.MUSIC_MAINGAME_BGM);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }



}
