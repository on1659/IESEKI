package teamwarpcbstuido.framework_03.org;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//All of resource put in this Class
public class AppManager {

    private static AppManager s_instance;
    private GameView m_gameview;
    private Resources m_resource;
    private moveSensor m_moveSensor;

    static final int X = 0;
    static final int Y = 1;
    int screen_width, screen_height;

    public static AppManager getInstance(){
        if(s_instance  == null) s_instance = new AppManager();
        return s_instance;
    }

    void setGameView(GameView _gameview){
        m_gameview = _gameview;
    }
    void setResuorces(Resources _resource){
        m_resource = _resource;
    }
    void setMoveSensor(moveSensor _moveSensor){m_moveSensor = _moveSensor;}

    void setSize(int _width, int _height){
        screen_width = _width;
        screen_height = _height;
    }
   public float getSensorX(){return m_moveSensor.getX();}
   public float getSensorY(){return m_moveSensor.getY();}
   public int getWidth(){return screen_width;}
    public int getHeight(){return screen_height;}

    public GameView getGameView(){
        return m_gameview;
    }
    public Resources getresource(){
        return m_resource;
    }
    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resource, r);
    }

}
