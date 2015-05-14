package teamwarpcbstuido.framework_03.game;

import android.app.AppOpsManager;
import android.appwidget.AppWidgetProvider;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.org.AppManager;
import teamwarpcbstuido.framework_03.org.GraphicObject;

/**
 * Created by Administrator on 2015-04-15.
 */
public class BackGround extends GraphicObject{

    Bitmap m_layer2 ;
    int width, height;
    Rect pos;
    public BackGround()
    {
        super(AppManager.getInstance().getBitmap(R.drawable.tree));
        m_layer2  = AppManager.getInstance().getBitmap(R.drawable.tree_02);
        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

      pos = new Rect( 0,0, width, height );

    }

    @Override
    public void Draw(Canvas canvas) {
       canvas.drawBitmap(m_bitmap, null, pos, null);
       canvas.drawBitmap(m_layer2, null, pos, null);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, null, pos, null);
        canvas.drawBitmap(m_layer2, null, pos, null);
    }


}
