package teamwarpcbstuido.IESEKI.game;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Dev.YT on 2015-06-03.
 */

public class Item_Meruss extends Item {

    public Item_Meruss(int DPI[]) {
        super(AppManager.getInstance().getBitmap(R.drawable.dragon_mou));
        this.InitSpriteData(1, 1, 1, 1);
        m_speed = DPI[1] * 5;
    }
}
