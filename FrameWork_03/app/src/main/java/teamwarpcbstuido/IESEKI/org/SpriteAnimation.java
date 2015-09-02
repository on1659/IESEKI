package teamwarpcbstuido.IESEKI.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

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
