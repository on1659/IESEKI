package teamwarpcbstuido.IESEKI.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Administrator on 2015-04-08.
 */
public class GraphicObject {

    protected Bitmap m_bitmap;

    protected int m_cx;
    protected int m_cy;
    protected int m_w;
    protected int m_h;
    protected Rect m_pos;


    public GraphicObject(Bitmap bitmap)
    {
        m_bitmap = bitmap;
        m_cx = 0;
        m_cy = 0;
        m_w = 0;
        m_h  =0;
        m_pos = new Rect();

    }

    public void SetPosition(int _x, int _y, int _w, int _h)
    {
        m_cx = _x;
        m_cy = _y;
        m_w = _w;
        m_h = _h;

        m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
    }

    public void SetPosition(int DPI[], int _x, int _y, int _w, int _h)
    {
        m_cx = DPI[0] * _x;
        m_cy = DPI[1] *  _y;
        m_w =  DPI[0] * _w;
        m_h =  DPI[1] * _h;

        m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
    }
    public void Draw(Canvas canvas){
         m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
        canvas.drawBitmap(m_bitmap, null, m_pos, null);

    }



    public int getX(){return m_cx - m_w/2;}
    public int getY(){return m_cy- m_h/2;}
    public int GetWidth(){return m_w;}
    public int GetHeight(){return m_h;}
    public int getRadius(){return m_w/2;}
    public Rect getPos(){return m_pos;}


    public int GetBitmapWidth(){return m_bitmap.getWidth();}
    public int GetBitmapHeight(){return m_bitmap.getHeight();}
}
