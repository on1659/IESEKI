package teamwarpcbstuido.framework_03.game;

import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;

import teamwarpcbstuido.framework_03.org.SpriteAnimation;

/**
 * Created by Administrator on 2015-04-09.
 */
public class Player extends SpriteAnimation {

    public Player(Bitmap bitmap){
        super(bitmap);
        int w, h;
        int num;
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        num = 6;
      // this.InitSpriteData(w / num, h /2, 3, num);

      this.InitSpriteData(h,w/num,3,num);
        this.SetPosition(140,330);
    }


}
