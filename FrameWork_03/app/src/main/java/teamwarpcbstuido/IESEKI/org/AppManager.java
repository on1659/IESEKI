package teamwarpcbstuido.IESEKI.org;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;
import android.os.Environment;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import teamwarpcbstuido.IESEKI.Layout.Link;
import teamwarpcbstuido.IESEKI.R;

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
    private Link          m_link;

    private TreeMap<String, Object> m_PauseData = new TreeMap<String, Object>();

    private TreeMap<String, Bitmap> bitmapList = new TreeMap<String, Bitmap>();


    final public static int MUSIC_SELECT_BGM = 0;
    final public static int MUSIC_MAINGAME_BGM = 1;

    public static int EFFECT_MONSTER_DIE = 0;

    static final int X = 0;
    static final int Y = 1;

    static final  int DPI[] = new int[2];
    static final int dp[] = new int[2];
    private int screen_width, screen_height;
    private int m_gameSpeed;

    //타이머 태스크와 타이머1
    public TimerTask maingame_timer;
    public Timer Timer1;


    //Pause값
    boolean pauseflag = false;

    public void setPasueFlag(boolean flag){ pauseflag = flag; }

    public boolean GetPauseFlag(){return pauseflag;}


    public void SetTimerTask(TimerTask timeTask)
    {
        maingame_timer = timeTask;
    }
    public void SetTimer(Timer timer)
    {
        Timer1 = timer;
    }

    public TimerTask GetTimerTask()
    {
        return maingame_timer;
    }

    public Timer GetTimer()
    {
        Timer temp = Timer1;
        return Timer1;
    }



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

    public void setLink(Link link)
    {
       // if(m_link == null)
            m_link = link;
    }

    public void set_myMediaPlayer() { m_myMediaPlayer = new MyMediaPlayer(m_context); }
    public void set_mySoundPool(){ m_mySoundPool = new MySoundPool(m_context);}

    public void ReStartGame() { m_gameview.RestartGame();}

    public float getSensorX(){return m_moveSensor.getX() * this.getGameSpeed();}
    public float getSensorY(){return m_moveSensor.getY() * this.getGameSpeed();}
    public int getWidth(){return screen_width;}
    public int getHeight(){return screen_height;}
    public int[] getDPI(){return DPI;}
    public void getShake(long _time){m_vibe.vibrate(_time);}

    public Link getLink(){return m_link;}


    public GameView getGameView(){return m_gameview;}
    public Resources getresource(){return m_resource;}
    public Bitmap getBitmap(int r){return BitmapFactory.decodeResource(m_resource, r);}
    public PreferenceManager getPreference(){return m_preference;}
    public Thread getThread(){return m_thread;}
    public MyMediaPlayer get_myMediaPlayer(){ return m_myMediaPlayer; }
    public MySoundPool get_mySoundPool(){ return m_mySoundPool; }

    public int Random(int start, int end) {
        Random rnd = new Random();
        if (end <= start) {
            return (rnd.nextInt(start - end) + start);
        }
        return (rnd.nextInt(end - start) + start);
    }


    public Bitmap GetImage(String name)
    {
       Bitmap bitmap =  bitmapList.get(name);
        return bitmap;
    }

    private void Addimage(String name, int r)
    {
        Bitmap bitmap[] = new Bitmap[2];
        bitmap[0] = BitmapFactory.decodeResource(m_resource, r);
        bitmapList.put(name, bitmap[0]);
    }

    public void SetPauseSaveData(String name, Object obj)
    {
        m_PauseData.put(name, obj);
    }

    public Object GetPauseSaveData(String name)
    {
        Object obj =  m_PauseData.get(name);
        return obj;
    }

    public void RenderManager()
    {
        this.Addimage("itm_piwheel", R.drawable.itm_piwheel);
        this.Addimage("itm_reflect", R.drawable.itm_reflect);
        this.Addimage("itm_meruss", R.drawable.itm_meruss);
        this.Addimage("itm_bloodyshield", R.drawable.itm_bloodyshield);
        this.Addimage("itm_adrenaline", R.drawable.itm_adrenaline);

        this.Addimage("imgMonster", R.drawable.enemy);

        this.Addimage("imgBackground",R.drawable.game_background2);

        this.Addimage("eff_piwheel",R.drawable.eff_piwheel);
        this.Addimage("eff_bloodyshield",R.drawable.eff_bloodyshield);
        this.Addimage("eff_meruss",R.drawable.eff_meruss);

        this.Addimage("Player", R.drawable.player);

        this.Addimage("Ui", R.drawable.maingame_ui);

    }

    //캡쳐 후 공유 함수
    public void implement_Capture_Share(Context context,View view){
        this.CreateFolder();
        Take_Capture screen = Take_Capture.getInstance();
        String path = screen.takeScreenshot(view);
        Picture_Share pShare = Picture_Share.getInstance();
        pShare.share(context, path);
    }

    /**폴더 생성*/
    private void CreateFolder(){

        try{
            //check sdcard mount state
            String str = Environment.getExternalStorageState();
            if ( str.equals(Environment.MEDIA_MOUNTED)) {

                Log.d("Sd_card_path", "sdcard mounted");
                String mTargetDirPath = this.getScreenShootFilePath();

                File file = new File(mTargetDirPath);
                if(!file.exists()){
                    file.mkdirs();
                    Log.e("Sd_card_path", mTargetDirPath + " folder created");
                }else{
                    //  Log.d(TAG, mTargetDirPath + " is exist");
                }
            }else{
                Log.d("Sd_card_path", "sdcard unmount, use default image.");
            }
        }catch(Exception e){
            Log.d("Sd_card_path", "fail");
        }
    }

    /**스크린샷 파일 경로를 가져온다.*/
    private String getScreenShootFilePath(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/IESEKI";
    }

    public void PauseDataDestory()
    {
        m_PauseData = null;
    }

    public void Destroy()
    {
        m_PauseData = null;
        s_instance = null;
        m_context = null;
        m_gameview = null;
        m_resource = null;
        m_vibe= null;
        m_preference = null;
        m_thread = null;
        m_moveSensor = null;
        m_myMediaPlayer = null;
        m_mySoundPool = null;
        m_link  = null;
        m_PauseData = null;
        bitmapList  = null;

    }

    public float getGameSpeed()
    {
        return m_preference.GameSpeedLoad();
    }

    public float GameSpeedDown()
    {
        float speed = m_preference.GameSpeedLoad();
        speed -= 0.5f;
        if(speed < 1.2f)speed = 0.7f;
        m_preference.GameSpeedSave(speed);
        return speed;
    }

    public float GameSpeedUp()
    {
        float speed = m_preference.GameSpeedLoad();
        speed += 0.5f;
        if(speed > 2.7f) speed = 2.7f;
        m_preference.GameSpeedSave(speed);
        return speed;

    }

    public boolean IsTablet () {
        // TODO: This hacky stuff goes away when we allow users to target devices
        int xlargeBit = 4; // Configuration.SCREENLAYOUT_SIZE_XLARGE;  // upgrade to HC SDK to get this
        Configuration config = m_context.getResources().getConfiguration();
        return (config.screenLayout & xlargeBit) == xlargeBit;
    }

}
