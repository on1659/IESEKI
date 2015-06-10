package teamwarpcbstuido.IESEKI.org;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by Administrator on 2015-05-19.
 */
public class MathCalu {

    MathCalu(){return;}

   public static double InnerProduct(double x, double y,double x2, double y2)
    {
        return ( x * x2) + (y * y2);
    }

    public static double VectorScale(double x, double y, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x,2.0f) + Math.pow(y, 2.0f)) + Math.sqrt(Math.pow(x2,2.0f) + Math.pow(y2, 2.0f));
    }

    public static double getACOS(double x, double y, double x2, double y2)
    {
        double dot = InnerProduct(x, y, x2, y2);
        double length = VectorScale(x, y, x2, y2);
        double radian = Math.acos(dot / length);
        double degree = radian * 180 / Math.PI;
        return degree;
    }

    public static double getCos(double x, double y,double x2, double y2)
    {
        double a, b;
        a = -y;
        b = x;
        double tmp_dot = InnerProduct(a, b, x2, y2);
        double tmp_length = VectorScale(a, b, x2, y2);
        double tmp_radian = Math.acos(tmp_dot / tmp_length);
        double tmp_degree =  tmp_radian * 180 / Math.PI;

        if(tmp_degree < 90)
            return getACOS(x,y,x2,y2);
        else
            return 360 - getACOS(x,y,x2,y2);

    }

}
