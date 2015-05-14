package teamwarpcbstuido.IESEKI.game;



import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Administrator on 2015-05-13.
 */
public class Monster_Type_01 extends  Monster {


    public Monster_Type_01(int DPI[])
    {
        super(AppManager.getInstance().getBitmap(R.drawable.monster_goldpig));

        m_speed = 5;
        this.InitSpriteData(5, 1 ,10, 6);//w,h, speed, coutn

    }


    public void onUpdate(long GameTime) {
        this.Move(GameTime);
    }
}
