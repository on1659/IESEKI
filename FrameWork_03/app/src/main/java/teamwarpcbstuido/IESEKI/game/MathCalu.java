package teamwarpcbstuido.IESEKI.game;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by Administrator on 2015-05-19.
 */
public class MathCalu {

    MathCalu(){return;}

    static double InnerProduct(double x, double y,double x2, double y2)
    {
        return ( x * x2) + (y * y2);
    }

    static double VectorScale(double x, double y, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x,2.0f) + Math.pow(y, 2.0f)) + Math.sqrt(Math.pow(x2,2.0f) + Math.pow(y2, 2.0f));
    }

    static double getCos(double x, double y, double x2, double y2)
    {
        double photowonder;
        double dot = InnerProduct(x, y, x2, y2);
        if(dot > 0)
        {
            photowonder = 0;
        }
        else
        {
            photowonder = 180;
        }
        double length = VectorScale(x, y, x2, y2);
        double radian = Math.acos(dot / length);
        double degree = radian * 180 / Math.PI;
        return degree + photowonder;
    }

}
