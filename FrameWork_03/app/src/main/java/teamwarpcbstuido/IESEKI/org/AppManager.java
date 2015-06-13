package teamwarpcbstuido.IESEKI.org;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.view.Display;
import android.view.WindowManager;

//All of resource put in this Class
public class AppManager {


    private static AppManager s_instance;
    private Context m_context;
    private GameView m_gameview;
    private Resources m_resource;
    private Vibrator m_vibe;
    private PreferenceManager m_preference;
    private Thread m_thread;
    private moveSensor m_moveSensor;
    private MyMediaPlayer m_myMediaPlayer;
    private MySoundPool   m_mySoundPool;


    final public static int MUSIC_SELECT_BGM = 0;
    final public static int MUSIC_MAINGAME_BGM = 1;

    public static int EFFECT_MONSTER_DIE = 0;

    static final int X = 0;

    static final int Y = 1;

    static final  int DPI[] = new int[2];
    int screen_width, screen_height;

    public static AppManager getInstance(){
        if (s_instance == null)
            s_instance = new AppManager();

        return s_instance;
    }

    public void setGameView(GameView _gameview) {
        m_gameview = _gameview;
    }

    public void setAppManager(Context context)
    {
        m_context = context;
    }

    public void setResuorces(Resources _resource) {
        m_resource = _resource;
    }

    public void setMoveSensor(){ m_moveSensor = new moveSensor(m_context); }

    public void setPreference() {
        m_preference = new PreferenceManager(m_context);
    }

    public void setVibeSensor() { m_vibe = (Vibrator) m_context.getSystemService(Context.VIBRATOR_SERVICE); }

    public void setThread(Thread _thread) { m_thread = _thread; }

    public void setSize()
    {
        Display dispaly = ((WindowManager) m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //((WindowManager)context.getSystemService(Context.WIFI_SERVICE)).getDefaultDisplay();
        screen_width = dispaly.getWidth();
        screen_height = dispaly.getHeight();
        DPI[X] = screen_width / 36;
        DPI[Y] = screen_height / 64;
    }

    public void set_myMediaPlayer() { m_myMediaPlayer = new MyMediaPlayer(m_context); }
    public void set_mySoundPool(){ m_mySoundPool = new MySoundPool(m_context);}



    public float getSensorX(){return m_moveSensor.getX();}
    public float getSensorY(){return m_moveSensor.getY();}
    public int getWidth(){return screen_width;}
    public int getHeight(){return screen_height;}
    public int getDPI(int select){return DPI[select];}
    public int[] getDPI(){return DPI;}
    public void getShake(long _time){m_vibe.vibrate(_time);}




    public GameView getGameView(){return m_gameview;}
    public Resources getresource(){return m_resource;}
    public Bitmap getBitmap(int r){return BitmapFactory.decodeResource(m_resource, r);}
    public PreferenceManager getPreference(){return m_preference;}
    public Thread getThread(){return m_thread;}
    public MyMediaPlayer get_myMediaPlayer(){ return m_myMediaPlayer; }
    public MySoundPool get_mySoundPool(){ return m_mySoundPool; }


}
