package teamwarpcbstuido.IESEKI.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class SpriteAnimation extends GraphicObject {

    private Rect mSRectangle;
    private int mFPS;
    private int mNoOfFrames;
    private int mCurrentFrame;

    private float mFrameTimer;

    private int mSpriteHeight;
    private int mSpriteWidth;

    protected boolean mbReply = true;
    protected boolean mbEnd = false;

   private float                  m_frameFPS;
   private int                     m_frameCount;

   private int                  m_frameWidth;
   private float                  m_frameHeight;

   private float                  m_frameLeft;
   private float                  m_frameTop;

    private float                  m_width;
    private float                   m_height;

    float               m_imgWidth;
    float               m_imgHeight;

    float               m_frameFPSInit;


    public SpriteAnimation(String name) {
        super(name);
        mSRectangle = new Rect(0, 0, 0, 0);
        mFrameTimer = 0;
        mCurrentFrame = 0;
    }

    public void InitSpriteData(int _w, int _h, int theFPS, int theFrameCount)
    {
        mSpriteWidth = AppManager.getInstance().GetImage(m_name).getWidth() / _w;
        mSpriteHeight = AppManager.getInstance().GetImage(m_name).getHeight() / _h;
        mSRectangle.top = 0;
        mSRectangle.bottom = mSpriteHeight;
        mSRectangle.left = 0;
        mSRectangle.right = mSpriteWidth;
        mFPS = 1000 / theFPS;
        mNoOfFrames = theFrameCount;
    }

    public void SetSprite(float frameFPS, int frameCount, int frameCountHeight) {

        m_width = AppManager.getInstance().GetImage(m_name).getWidth();//(float)CRenderManager::GetInstance()->GetImage(m_name)->GetWidth() / frameCount;
        m_height = AppManager.getInstance().GetImage(m_name).getHeight();//(float)CRenderManager::GetInstance()->GetImage(m_name)->GetHeight() / frameCountHeight;


        m_frameFPS = 1 / frameFPS;
        m_frameFPSInit = m_frameFPS;
        m_frameCount = frameCount;
        m_imgWidth = m_width / frameCount;
        m_imgHeight = m_height / frameCountHeight;


        mSRectangle.top = 0;
        mSRectangle.bottom = (int)m_imgHeight;
        mSRectangle.left = 0;
        mSRectangle.right = (int)m_imgWidth;

    }

    @Override
    public void Draw(Canvas canvas) {
        Rect dest = new Rect((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        canvas.drawBitmap(AppManager.getInstance().GetImage(m_name), mSRectangle, dest, null);
    }

    public void onDraw(Canvas canvas) {

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        canvas.drawBitmap(AppManager.getInstance().GetImage(m_name), mSRectangle, m_pos, null);
    }


    public void onDraw(Canvas canvas, Paint _pnt) {

        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        canvas.drawBitmap(AppManager.getInstance().GetImage(m_name), mSRectangle, m_pos, _pnt);
    }

    public void onDraw(Canvas canvas, double angle) {
        canvas.rotate((float)angle, m_cx, m_cy);
        m_pos.set((m_cx - m_w / 2), (m_cy - m_h / 2), (m_cx + m_w / 2), (m_cy + m_h / 2));
        canvas.drawBitmap(AppManager.getInstance().GetImage(m_name), mSRectangle, m_pos, null);
        canvas.rotate((float) angle * -1.0f, m_cx, m_cy);
    }

   public  void OnSpriteUpdate()
    {
        m_frameLeft = m_imgWidth  * m_frameWidth;
        m_frameTop = m_imgHeight * m_frameHeight;

        m_frameFPS += m_frameFPS;
        m_frameWidth += m_frameFPS;

        if (m_frameWidth > m_frameCount)
        {
            m_frameWidth = 0;
            m_frameFPS = m_frameFPSInit;
        }

        mSRectangle.left = (int)m_frameLeft;
        mSRectangle.right = mSRectangle.left + (int)m_imgWidth;
    }


    public void SpriteUpdate(float GameTime) {
        if (!mbEnd) {
            if (GameTime > mFrameTimer + mFPS) {
                mFrameTimer = GameTime;
                mCurrentFrame += 1;

                if (mCurrentFrame >= mNoOfFrames) {
                    if (mbReply)
                        mCurrentFrame = 0;
                    else
                        mbEnd = true;
                }
            }
        }

        mSRectangle.left = mCurrentFrame * mSpriteWidth;
        mSRectangle.right = mSRectangle.left + mSpriteWidth;
    }


    public boolean getAnimationEnd() {
        return mbEnd;
    }

}
