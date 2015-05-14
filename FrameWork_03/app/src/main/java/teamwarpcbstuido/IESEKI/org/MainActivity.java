package teamwarpcbstuido.IESEKI.org;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {



    // Display dispaly = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getdefaultdisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
    //   width = dispaly.getWidth();

    //   height = dispaly.getHeight();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//가로세로전환

        setContentView(new GameView(this));
    }
}
