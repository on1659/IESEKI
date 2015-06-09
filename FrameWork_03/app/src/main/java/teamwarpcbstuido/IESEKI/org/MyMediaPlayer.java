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
    public MediaPlayer m_music_maingame;
    public MyMediaPlayer(Context context) {

        //MediaPlayer용 사운드 로드
        m_music_selectmenu = MediaPlayer.create(context, R.raw.music_selectmenu);
        m_music_selectmenu.setVolume(0.1f * 5, 0.1f * 5);

        m_music_maingame = MediaPlayer.create(context, R.raw.music_maingame);
        m_music_maingame.setVolume(0.1f * 5, 0.1f * 5);
    }

    public void play(int choice) {

        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
        switch (choice) {

            case AppManager.MUSIC_SELECT_BGM:
                m_music_selectmenu.setLooping(true);
                m_music_selectmenu.start(); // (중지된 위치에서 부터) 재생 시작
                break;

            case AppManager.MUSIC_MAINGAME_BGM:
                m_music_maingame.setLooping(true);
                m_music_maingame.start(); // (중지된 위치에서 부터) 재생 시작
                break;
            default:
                break;
        }
        }
    }


    public void stop(int choice) {
        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            switch (choice) {

                case AppManager.MUSIC_SELECT_BGM:
                    m_music_selectmenu.stop();
                    break;

                case AppManager.MUSIC_MAINGAME_BGM:
                    m_music_maingame.stop();
                    break;

                default:
                    break;
            }
        }
    }

    public void pause(int choice) {
        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            switch (choice) {

                case AppManager.MUSIC_SELECT_BGM:
                    m_music_selectmenu.pause();
                    break;
                case AppManager.MUSIC_MAINGAME_BGM:
                    m_music_maingame.pause();
                    break;

                default:
                    break;
            }
        }
    }

    /*
    @Override
    public void onDestroy() //사운드의 메모리해제
    {
        if (m_main_gameBgm_01 != null) m_main_gameBgm_01.release();
    }
    */

    //public void go_to_beginning(int choice) {
    //    if (choice == 1)
    //        m_music_selectmenu.seekTo(0);
    //}

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
