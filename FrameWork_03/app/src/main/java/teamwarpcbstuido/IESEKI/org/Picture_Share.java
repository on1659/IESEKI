package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


public class Picture_Share {
    private static Picture_Share instance =null;
    public static Picture_Share getInstance(){
        if(instance==null){
            instance = new Picture_Share();
        }
        return instance;
    }
    private Picture_Share(){}

    public void share(Context context,String path){
        String type = "image/*";
        Log.d("test", path);
        if(isExternalStorageReadable()){
            createInstagramIntent(context,type,path);
        }else{
            Toast.makeText(context, "no sdcard", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }
    private void createInstagramIntent(Context context,String type, String mediaName) {
      //  String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Beenzido";

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);
        // Set the MIME type
        share.setType(type);
        // Create the URI from the media

        //File media = new File(path,mediaName);
        File media = new File(mediaName);
        if (media.exists()) {
            Uri uri = Uri.fromFile(media);
            // Add the URI to the Intent.
            share.putExtra(Intent.EXTRA_STREAM, uri);
            // Broadcast the Intent.
            context.startActivity(Intent.createChooser(share, "Share to"));
        }else{
            Toast.makeText(context, "no Image", Toast.LENGTH_SHORT).show();
        }
    }
}