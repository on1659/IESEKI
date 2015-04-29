package teamwarpcbstuido.framework_03.org;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//All of resource put in this Class
public class AppManager {

    private static AppManager s_instance;
    private GameView m_gameview;
    private Resources m_resource;

    static final int X = 0;
    static final int Y = 1;
    int DIwidth, DiSheight;

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
    public GameView getGameView(){
        return m_gameview;
    }
    public Resources getresource(){
        return m_resource;
    }
    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resource,r);
    }

}
