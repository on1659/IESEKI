package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import teamwarpcbstuido.IESEKI.R;

/**
 * Created by JYJ on 2015-06-08.
 */
public class MySoundPool {

    SoundPool pool;

    int button_sound;

    public MySoundPool(Context context) {
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        loadSound(context);
    }

    private void loadSound(Context context) {
        button_sound = pool.load(context, R.raw.music_selectmenu, 1);

    }

    public void start(int choice) {

        if (choice == 1) {
            //������ ���� ID, ���ʺ���, �����ʺ���, ����켱����, �ݺ�����, ����ӵ�
            pool.play(button_sound, 1, 1, 0, 0, 1);
        }

    }

    public void stop(int choice) {

        if (choice == 1)
            pool.stop(button_sound);

    }

    public void pause(int choice) {

        if (choice == 1)
            pool.pause(button_sound);

    }
}
