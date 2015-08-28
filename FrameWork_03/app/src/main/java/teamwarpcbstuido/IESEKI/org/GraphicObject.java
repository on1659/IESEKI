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

   // protected Bitmap m_bitmap;

    public int m_cx;
    public int m_cy;
    public int m_w;
    public int m_h;
    protected Rect m_pos;
    protected String m_name;


    public GraphicObject(String name)
    {
        //m_bitmap = bitmap;
        m_name = name;
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

    public void SetPosition(int _x, int _y)
    {
        m_cx = _x;
        m_cy = _y;
        m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
    }


    public void SetPosition(int DPI[], int _x, int _y, int _w, int _h)
    {
        m_cx = DPI[0] * _x;
        m_cy = DPI[1] *  _y;
        m_w =  DPI[0] * _w;
        m_h =  DPI[1] * _h;

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
    }
    public void Draw(Canvas canvas){
         m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
        canvas.drawBitmap(AppManager.getInstance().GetImage(m_name), null, m_pos, null);

    }




    public int getX(){return m_cx - m_w/2;}
    public int getY(){return m_cy- m_h/2;}
    public int GetWidth(){return m_w;}
    public int GetHeight(){return m_h;}
    public int getRadius(){return m_w/2;}
    public Rect getPos(){return m_pos;}
   // public void resetBitmap(Bitmap _bitmap){ AppManager.getInstance().GetImage(m_name) = _bitmap; }
    public int getWidth() {
        return m_w;
    }

    public int getHight() {
        return m_h;
    }

    public int GetBitmapWidth(){return AppManager.getInstance().GetImage(m_name).getWidth();}
    public int GetBitmapHeight(){return AppManager.getInstance().GetImage(m_name).getHeight();}
}
