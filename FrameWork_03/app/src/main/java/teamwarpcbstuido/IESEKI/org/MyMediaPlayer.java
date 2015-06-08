package teamwarpcbstuido.IESEKI.org;

import android.content.Context;
import android.media.MediaPlayer;

import teamwarpcbstuido.IESEKI.R;

/**
 * Created by JYJ on 2015-06-08.
 */
public class MyMediaPlayer {
    //Bgm

    public MediaPlayer m_music_selectmenu;

    public MyMediaPlayer(Context context) {

        loadSound(context);
        InitSound(5);
    }

    public void loadSound(Context context) {

        m_music_selectmenu = MediaPlayer.create(context, R.raw.music_selectmenu);
    }

    public void InitSound(int val) {

        m_music_selectmenu.setVolume(0.1f * val, 0.1f * val);
    }


    public void start(int choice) {
        if (choice == 1) {
            m_music_selectmenu.setLooping(true);
            m_music_selectmenu.start(); // (중지된 위치에서 부터) 재생 시작
        }
    }


    public void stop(int choice) {

        if (choice == 1)
            m_music_selectmenu.stop();
    }

    public void pause(int choice) {

        if (choice == 1)
            m_music_selectmenu.pause();
    }


    /*

    @Override
    public void onDestroy() //사운드의 메모리해제
    {
        if (m_main_gameBgm_01 != null) m_main_gameBgm_01.release();
    }
    */

    public void go_to_beginning(int choice) {
        if (choice == 1)
            m_music_selectmenu.seekTo(0);
    }

    //---------------------------------------------------참고

    /*
    mPlayer.setVolume(0.8f, 0.8f); // 볼륨 설정
    mPlayer.setLooping(true); // 반복 연주
    mPlayer.start(); // (중지된 위치에서 부터) 재생 시작


    mPlayer.stop(); // 재생 멈춤
    mPlayer.pause(); // 일시 정지
     */

    //---------------------------------------------------

}
