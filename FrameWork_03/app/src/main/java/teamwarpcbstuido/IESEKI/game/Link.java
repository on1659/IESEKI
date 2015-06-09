package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Script;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.GameView;
import teamwarpcbstuido.IESEKI.org.IState;

/**
 * Created by JYJ on 2015-06-03.
 */
public class Link extends Activity {

    static public AlertDialog.Builder builder;
    static boolean pause_check = false;

    //public static Activity a;

    Pause m_pause;

    SelectMenu m_selectMenu;

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

        m_selectMenu.m_myMediaPlayer.play(2);

        //a = Link.this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                m_pause.m_return = true;
                m_pause.show();

                m_selectMenu.m_myMediaPlayer.pause(2);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
