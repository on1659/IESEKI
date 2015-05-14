package teamwarpcbstuido.framework_03.game;

import android.app.AppOpsManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.WindowManager;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.AppManager;
import teamwarpcbstuido.framework_03.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-05-13.
 */
public class Monster_Type_01 extends  Monster {


    public Monster_Type_01(int DPI[])
    {
        super(AppManager.getInstance().getBitmap(R.drawable.monster_goldpig));

        m_speed =5;
        this.InitSpriteData(5, 1 ,10, 6);//w,h, speed, coutn

    }


    public void onUpdate(long GameTime) {
        this.Move(GameTime);
    }
}
