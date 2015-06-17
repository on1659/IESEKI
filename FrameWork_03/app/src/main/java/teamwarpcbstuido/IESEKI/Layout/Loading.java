package teamwarpcbstuido.IESEKI.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.MyMediaPlayer;
import teamwarpcbstuido.IESEKI.org.MySoundPool;
import teamwarpcbstuido.IESEKI.org.PreferenceManager;
import teamwarpcbstuido.IESEKI.org.SoundManager;
import teamwarpcbstuido.IESEKI.org.moveSensor;

public class Loading extends Activity {

    private PreferenceManager m_preferenceManager;
    private moveSensor m_moveSensor;
    private SoundManager soundManger;

    static public MyMediaPlayer m_mediaplayer;
    static public MySoundPool m_soundpool;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //捞吧 力老 刚历 秦具壩
        AppManager.getInstance().setAppManager(this);

        AppManager.getInstance().setSize();
        AppManager.getInstance().setVibeSensor();
        AppManager.getInstance().setPreference();
        AppManager.getInstance().setMoveSensor();
        AppManager.getInstance().setResuorces(getResources());
        AppManager.getInstance().set_myMediaPlayer();
        AppManager.getInstance().set_mySoundPool();

        setContentView(new LoadingCanvas(this));

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        public void run() {
            Intent intent = new Intent(Loading.this, SelectMenu.class);
            startActivity(intent);
            finish();
        }
    }, 3100);
    }

}
