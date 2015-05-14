package teamwarpcbstuido.framework_03.org;

import android.graphics.Rect;

/**
 * Created by Administrator on 2015-05-15.
 */
public class Collision {

    void Collision(){

    }

    public static boolean collisionCircle(int x1, int y1, int r1, int x2, int y2, int r2)
    {
        double a,b, dis = 0;
        a = (double)Math.abs(x2 - x1);
        b = (double)Math.abs(y2 - y1);
        dis = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        if( r1 + r2 >= dis)return true;
        else return false;
    }
    public static boolean collisionCircle(Rect A,Rect B)
    {
        int x1,y1,r1 = 0;
        int x2,y2,r2 = 0;
        double a,b, dis = 0;
        x1 = A.left;
        y1 = A.top;
        r1 = A.right;

        x2 = B.left;
        y2 = B.top;
        r2 = B.right;

        a = (double)Math.abs(x2 - x1);
        b = (double)Math.abs(y2 - y1);
        dis = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        if( r1 + r2 >= dis)return true;
        else return false;
    }

    public static boolean collisionCircle(int x1,int y1,int r1, Rect B){
        int x2,y2,r2 = 0;
        double a,b, dis = 0;
        x2 = B.left + (B.right - B.left) / 2;
        y2 = B.top  + (B.bottom - B.top) / 2;
        r2 = (B.right - B.left) / 2;

        a = (double)Math.abs(x2 - x1);
        b = (double)Math.abs(y2 - y1);
        dis = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        if( r1 + r2 >= dis)return true;
        else return false;
    }


}
