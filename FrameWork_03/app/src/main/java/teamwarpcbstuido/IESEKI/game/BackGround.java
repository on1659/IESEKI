package teamwarpcbstuido.IESEKI.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
import teamwarpcbstuido.IESEKI.org.GraphicObject;

/**
 * Created by Administrator on 2015-04-15.
 */


public class BackGround extends GraphicObject{

    private int X = 0;
    private int Y = 1;
    int DPI[] = new int[2];

    Bitmap m_layer1 ;
    Bitmap m_layer2 ;
    Rect m_background_01;
    Rect m_background_02;

    int width, height;

    public BackGround()
    {
        super(null);

        width =  AppManager.getInstance().getWidth();
        height =  AppManager.getInstance().getHeight();

        DPI =  AppManager.getInstance().getDPI();


         m_layer1  = AppManager.getInstance().getBitmap(R.drawable.background);
         m_layer2 = m_layer1;

        m_background_01 = new Rect();
        m_background_02 = new Rect();
        m_background_01.set(0,0,width,height);
        m_background_02.set(0, height, width, height*2 + DPI[Y]/4);


    }

    public void onUpdate(long GameTime)
    {

        m_background_01.offset(0, -DPI[Y]/4);
        m_background_02.offset(0, -DPI[Y]/4);

        if(m_background_01.top <= -height)
            m_background_01.set(0, height, width, height*2 + DPI[Y]/4);

        if(m_background_02.top <= -height)
            m_background_02.set(0, height, width, height*2 + DPI[Y]/4);


    }

    @Override
    public void Draw(Canvas canvas){
    canvas.drawBitmap(m_layer1, null, m_background_01, null);
    canvas.drawBitmap(m_layer2, null, m_background_02, null);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(m_layer1, null, m_background_01, null);
        canvas.drawBitmap(m_layer2, null, m_background_02, null);
    }

}