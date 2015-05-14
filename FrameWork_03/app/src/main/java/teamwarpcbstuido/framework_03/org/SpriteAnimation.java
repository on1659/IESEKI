package teamwarpcbstuido.framework_03.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import teamwarpcbstuido.framework_03.org.GraphicObject;

public class SpriteAnimation extends GraphicObject {

    private Rect mSRectangle;
    private int mFPS;
    private int mNoOfFrames;
    private int mCurrentFrame;

    private long mFrameTimer;

    private int mSpriteHeight;
    private int mSpriteWidth;

    protected boolean mbReply =true;
    protected boolean mbEnd = false;

    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);
        mSRectangle = new Rect(0,0,0,0);
        mFrameTimer =0;
        mCurrentFrame =0;
    }

    public void InitSpriteData(int _w, int _h, int theFPS, int theFrameCount) {
        mSpriteHeight = m_bitmap.getHeight() / _h;
        mSpriteWidth = m_bitmap.getWidth() / _w;
        mSRectangle.top = 0;
        mSRectangle.bottom = mSpriteHeight;
        mSRectangle.left = 0;
        mSRectangle.right = mSpriteWidth;
        mFPS = 1000 /theFPS;
        mNoOfFrames = theFrameCount;
    }
    @Override
     public void Draw(Canvas canvas)
    {
        Rect dest =new Rect( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
        canvas.drawBitmap(m_bitmap, mSRectangle, dest, null);
    }

    public void onDraw(Canvas canvas)
    {
      m_pos.set( (m_cx - m_w/2) , (m_cy - m_h/2), (m_cx + m_w/2), (m_cy + m_h/2) );
        canvas.drawBitmap(m_bitmap, mSRectangle, m_pos, null);
    }

    public void SpriteUpdate(long GameTime) {
        if(!mbEnd){
            if(GameTime > mFrameTimer + mFPS ) {
                mFrameTimer = GameTime;
                mCurrentFrame +=1;

                if(mCurrentFrame >= mNoOfFrames) {
                    if(mbReply)
                        mCurrentFrame = 0;
                    else
                        mbEnd = true;
                }
            }
        }

        mSRectangle.left = mCurrentFrame * mSpriteWidth;
        mSRectangle.right = mSRectangle.left + mSpriteWidth;
    }
    public boolean getAnimationEnd(){
        return mbEnd;
    }


}