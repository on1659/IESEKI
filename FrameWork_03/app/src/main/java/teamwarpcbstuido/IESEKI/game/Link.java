package teamwarpcbstuido.IESEKI.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Script;
import android.view.Window;
import android.view.WindowManager;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.GameView;

/**
 * Created by JYJ on 2015-06-03.
 */
public class Link extends Activity {

    static public AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//가로세로전환

        setContentView(R.layout.custom_view);
    }

    static public AlertDialog GameOver_Dialog(final Context _context) {

        builder = new AlertDialog.Builder(_context);
        builder.setTitle("GAME OVER");
        builder.setMessage("점수" + 2000);
        builder.setIcon(R.drawable.player);

        //pop.setMultiChoiceItems(itemsId, checkedItems, listener)
        //pop.setMultiChoiceItems(items, checkedItems, listener)
        //pop.setSingleChoiceItems(itemsId, checkedItem, listener)
        //pop.setSingleChoiceItems(R.array.menu, mSelect, new DialogInterface.OnClickListener()

        builder.setPositiveButton("메인으로", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int selection) {

            }
        });

        builder.setCancelable(false); //뒤로가기 버튼 비활성화 하기
        AlertDialog dialog = builder.create();

        return dialog;
    }
}
