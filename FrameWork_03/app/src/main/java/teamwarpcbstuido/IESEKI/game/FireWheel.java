package teamwarpcbstuido.IESEKI.game;


import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Administrator on 2015-05-15.
 */
public class FireWheel extends Item{

        public FireWheel(int DPI[])
        {
            super(AppManager.getInstance().getBitmap(R.drawable.monster_dolphin));
            this.InitSpriteData(1,1,1,1);
            m_speed = DPI[1] * 5;

        }

}
