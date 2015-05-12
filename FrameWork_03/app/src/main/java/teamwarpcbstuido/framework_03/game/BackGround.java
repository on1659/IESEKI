package teamwarpcbstuido.framework_03.game;

import android.appwidget.AppWidgetProvider;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.AppManager;
import teamwarpcbstuido.framework_03.org.GraphicObject;

/**
 * Created by Administrator on 2015-04-15.
 */
public class BackGround extends GraphicObject{


    public BackGround()
    {
        super(AppManager.getInstance().getBitmap(R.drawable.tree));

    }
       void Update(long GameTime)
       {

       }

}
