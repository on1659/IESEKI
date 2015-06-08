package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by JYJ on 2015-06-08.
 */
public class Pause extends Dialog implements View.OnClickListener {

    ImageButton btn_return;
    ImageButton btn_gomain;

    public static boolean m_return;

    public Pause(Context context) {

        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialog);

        //------------------------------------------------액티비티 또는 커스텀 다이얼로그 크기 설정
        double d_width = AppManager.getInstance().getWidth() * 0.7; //Display 사이즈의 70%
        double d_height = AppManager.getInstance().getHeight() * 0.7;  //Display 사이즈의 70%
        getWindow().getAttributes().width = (int) d_width;
        getWindow().getAttributes().height = (int) d_height;


        btn_return = (ImageButton) findViewById(R.id.pause_btn_return);
        btn_gomain = (ImageButton) findViewById(R.id.pause_btn_gomain);

        btn_return.setOnClickListener(this);
        btn_gomain.setOnClickListener(this);

        m_return = false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.pause_btn_gomain:
                System.exit(0);
                break;

            case R.id.pause_btn_return:
                m_return = false;
                dismiss();
                break;

            default:
                break;
        }
    }//onClick

}
