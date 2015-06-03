package teamwarpcbstuido.IESEKI.game;

import teamwarpcbstuido.IESEKI.R;
import teamwarpcbstuido.IESEKI.org.AppManager;

/**
 * Created by Dev.YT on 2015-06-02.
 */

public class Item_BloodyShield extends Item {

    public Item_BloodyShield(int DPI[]) {
        super(AppManager.getInstance().getBitmap(R.drawable.itm_bloodyshield));
        this.InitSpriteData(1, 1, 1, 1);
        m_speed = DPI[1] * 5;
    }
}
