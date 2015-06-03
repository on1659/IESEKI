package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.GameView;

/**
 * Created by JYJ on 2015-06-03.
 */
public class SelectMenu extends Activity implements View.OnClickListener {

    Button btn_start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.select_menu);

        btn_start = (Button) findViewById(R.id.select_btn_start);
        btn_start.setOnClickListener(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {

        Intent intent;
        intent = null;

        switch (v.getId()) {
            case R.id.select_btn_start:

                intent = new Intent(this, Link.class);
                startActivity(intent);
                // finish();
                break;

            default:
                break;
        }

    }//onClick

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                System.exit(0);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
