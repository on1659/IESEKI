package teamwarpcbstuido.IESEKI.org;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.game.GameState;


/**
 * Created by Administrator on 2015-04-01.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    int width, height;
    int DPI[] = new int[2];

    private IState m_state;
    private SurfaceHolder m_surfaceHolder;
    private GameViewThread m_thread;
    private  GameState gameState;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);

        AppManager.getInstance().setGameView(this);

        getHolder().addCallback(this);

        gameState = new GameState();
        ChangeGameState(gameState);

        m_thread = new GameViewThread(getHolder(), this);
        AppManager.getInstance().setThread(m_thread);
    }



    public void onDrawImage(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        gameState.Render(canvas);

  //      canvas.drawBitmap(bit, 10, 10, pnt);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void onUpdate(){

        m_state.Update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;
    }


      public void ChangeGameState(IState _state){
          if(m_state != null)
              m_state.Destroy();
          _state.Init();
          m_state = _state;
      }

    public void Destory()
    {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry){
            try{
                m_thread.join();
                retry = false;
            }catch (InterruptedException e){

            }
        }


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry){
            try{
                m_thread.join();
                retry = false;
            }catch (InterruptedException e){

            }
        }
    }

}
