package teamwarpcbstuido.IESEKI.game;



import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;
public class Monster_Type_01 extends  Monster {



    public Monster_Type_01(int DPI[])
    {
        //super(AppManager.getInstance().getBitmap(R.drawable.monster_goldpig));
        super(AppManager.getInstance().getBitmap(R.drawable.enemy));

        //this.InitSpriteData(5, 1 ,10, 6);//w,h, speed, coutn
        this.InitSpriteData(1, 1 ,1, 1);//w,h, speed, coutn

        m_speed = DPI[1] * 5;
        m_speedX = DPI[1] * 5;
        m_speedY = DPI[1] * 5;
    }

    public void MonUpdate(float frame_time) {

        this.onUpdate(frame_time);
    }
}