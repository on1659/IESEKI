package teamwarpcbstuido.framework_03.org;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import teamwarpcbstuido.framework_03.R;
import teamwarpcbstuido.framework_03.game.GameState;


/**
 * Created by Administrator on 2015-04-01.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    Bitmap bit;
    private Paint pnt;
    int width, height;
    int DPI[] = new int[2];

    private IState m_state;
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameview;
    private SoundManager soundManger;
    private GameViewThread m_thread;
    private moveSensor m_moveSensor;

    private  GameState gameState;

    public GameView(Context context){
        super(context);
        setFocusable(true);

        Display dispaly = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
        //m_thread = new GameViewThread((getHolder(), this);
        width = dispaly.getWidth();
        height = dispaly.getHeight();

        DPI[AppManager.X] = width / 18;
        DPI[AppManager.Y] = height / 32;

        m_moveSensor = new moveSensor(context);

        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResuorces(getResources());
        AppManager.getInstance().setSize(width, height);
        AppManager.getInstance().setMoveSensor(m_moveSensor);
        AppManager.getInstance().setVibeSensor(context);



        getHolder().addCallback(this);
        gameState = new GameState();
        ChangeGameState(gameState);

       bit = BitmapFactory.decodeResource(context.getResources(), R.drawable.character);
       pnt = new Paint();
       pnt.setAlpha(50);

        // getHolder().addCallback(this);
       m_thread = new GameViewThread(getHolder(), this);

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
       // AppManager.getInstance().getGameView().ChangeGameState(new CreditState());
        m_state.onTouchEvent(event);
        return true;
    }


      public void ChangeGameState(IState _state){
          if(m_state != null)
              m_state.Destroy();
          _state.Init();
          m_state = _state;
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

    public void TimerManager(TimerTask timertask1, TimerTask timertask2)
    {
        //------------------------------------------------타이머1
        timertask1 = new TimerTask(){
            public void run()
            {
                try
                {
                }

                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Timer Timer1 = new Timer();
        Timer1.schedule(timertask1, 0, 1000); //실제시계 1초 마다 실행

        //------------------------------------------------타이머2
        timertask2 = new TimerTask(){
            public void run()
            {
                try{


                }

                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Timer Timer2 = new Timer();
        Timer2.schedule(timertask2, 0, 30); //실제시계 0.03초 마다 실행
    }
}
