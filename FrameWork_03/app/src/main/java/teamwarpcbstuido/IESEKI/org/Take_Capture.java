package teamwarpcbstuido.IESEKI.org;

/**
 * Created by HyunJae on 2015-08-11.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***************사용법 *************************
 *
 *    View 파라미터에는 Layout을 넣어주면 스크린샷이 찍인다0
 *
 *    path 는 /DCIM/Beenzido/image명
 *
 *   Take_Capture.getInstance().takeScreenshot(linearLayout);
 *
 *
 *    전체 화면을 찍기 위해서는
 *    View rootView = findViewById(android.R.id.content).getRootView();
 *    Take_Capture.getInstance().takeScreenshot(rootView);
 *
 *    을 실행시킨다.
 */


public class Take_Capture {
    private static Take_Capture instance =null;
    public static Take_Capture getInstance(){
        if(instance==null){
            instance = new Take_Capture();
        }
        return instance;
    }

    private Take_Capture(){}


    public String takeScreenshot(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap =  createSquaredBitmap(view.getDrawingCache());
        return saveBitmap(bitmap);

    }

    public String saveBitmap(Bitmap bitmap) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");

        String namePostfix = format.format(new Date());

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/IESEKI";
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/IESEKI/ieseki"+ namePostfix +".png";

        File imagePath = new File(path);
        //Util.Log(path);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

            // bitmap = createSquaredBitmap(bitmap);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return path;

    }

    public Bitmap createSquaredBitmap(Bitmap srcBmp)
    {
        int move;
        int dim = Math.max(srcBmp.getWidth(), srcBmp.getHeight());
        Bitmap dstBmp = Bitmap.createBitmap(dim, dim, Bitmap.Config.ARGB_8888);

        move = (srcBmp.getHeight() - srcBmp.getWidth()) / 2;
        Canvas canvas = new Canvas(dstBmp);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(srcBmp, move, 0, null);

        return dstBmp;

    }

}