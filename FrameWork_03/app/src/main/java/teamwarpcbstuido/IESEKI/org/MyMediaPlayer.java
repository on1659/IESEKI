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
    Context m_context;


    public MyMediaPlayer(Context context) {

        //MediaPlayer�� ���� �ε�
        m_music_selectmenu = MediaPlayer.create(context, R.raw.music_selectmenu);
        m_music_selectmenu.setVolume(0.1f * 5, 0.1f * 5);

        m_music_maingame = MediaPlayer.create(context, R.raw.music_maingame);
        m_music_maingame.setVolume(0.1f * 5, 0.1f * 5);

        m_context = context;
    }

    public void play(int choice) {

        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
        switch (choice) {

            case AppManager.MUSIC_SELECT_BGM:
                m_music_selectmenu.setLooping(true);
                m_music_selectmenu.start(); // (������ ��ġ���� ����) ��� ����
                break;

            case AppManager.MUSIC_MAINGAME_BGM:
                m_music_maingame.setLooping(true);
                m_music_maingame.start(); // (������ ��ġ���� ����) ��� ����
                break;
            default:
                break;
        }
        }
    }

    public void reStartplay(int choice) {

        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            switch (choice) {

                case AppManager.MUSIC_SELECT_BGM:
                    m_music_selectmenu.stop();
                    m_music_selectmenu = MediaPlayer.create(m_context, R.raw.music_selectmenu);
                    m_music_selectmenu.setVolume(0.1f * 5, 0.1f * 5);
                    m_music_selectmenu.setLooping(true);
                    m_music_selectmenu.start(); // (������ ��ġ���� ����) ��� ����
                    break;

                case AppManager.MUSIC_MAINGAME_BGM:
                    m_music_maingame.stop();
                    m_music_maingame = MediaPlayer.create(m_context, R.raw.music_maingame);
                    m_music_maingame.setVolume(0.1f * 5, 0.1f * 5);
                    m_music_maingame.setLooping(true);
                    m_music_maingame.start(); // (������ ��ġ���� ����) ��� ����
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



    public void release(int choice) {
        if (AppManager.getInstance().getPreference().MusicOptionLoad() == true) {
            switch (choice) {

                case AppManager.MUSIC_SELECT_BGM:
                  //  m_music_selectmenu.release();
                    break;
                case AppManager.MUSIC_MAINGAME_BGM:
                  //  m_music_maingame.release();
                    break;

                default:
                    break;
            }
        }
    }

    /*
    @Override
    public void onDestroy() //������ �޸�����
    {
        if (m_main_gameBgm_01 != null) m_main_gameBgm_01.release();
    }
    */

    //public void go_to_beginning(int choice) {
    //    if (choice == 1)
    //        m_music_selectmenu.seekTo(0);
    //}

    //---------------------------------------------------����
    /*
    mPlayer.setVolume(0.8f, 0.8f); // ���� ����
    mPlayer.setLooping(true); // �ݺ� ����
    mPlayer.start(); // (������ ��ġ���� ����) ��� ����


    mPlayer.stop(); // ��� ����
    mPlayer.pause(); // �Ͻ� ����
     */
    //---------------------------------------------------

}
