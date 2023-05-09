package com.example.music.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;


import com.example.music.app.MusicPlayerApplication;
import com.example.music.type.Music;
import com.example.music.util.Consts;

import java.io.IOException;
import java.util.List;

public class PlayMusicService extends Service {
    private MediaPlayer player;
    private List<Music> musics;
    private int currentMusicIndex;
    private int pausePosition;

    //内部广播接收者对象
    private InnerReceiver receiver;

    @Override
    public void onCreate() {
        player = new MediaPlayer();
        player.setOnCompletionListener(mediaPlayer -> next());

        //获取数据
        MusicPlayerApplication application = (MusicPlayerApplication) getApplication();
        musics = application.getMusics();

        //注册广播接收者
        receiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Consts.PLAY_OR_PAUSE);
        filter.addAction(Consts.PREVIOUS);
        filter.addAction(Consts.NEXT);
        filter.addAction(Consts.PLAY_APPOINT);
        filter.addAction(Consts.SEEK_T0);
        registerReceiver(receiver, filter);
    }

    /**
     * 更新进度条的线程
     */
    private class InnerThread extends Thread {
        //线程的循环控制变量
        private boolean isRunning;

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        //不要在循环中new对象
        Intent intent = new Intent(Consts.UPDATE_PROGRESS);

        @Override
        public void run() {
            while (isRunning) {
                //计算相关数据
                //子线程做的事越多越好，主线程做的事越少越好
                int currentPosition = player.getCurrentPosition();
                int percent = currentPosition * 100 / player.getDuration();

                intent.putExtra(Consts.EXTRA_CURRENT_POSITION, currentPosition);
                intent.putExtra(Consts.EXTRA_PERCENT, percent);
                sendBroadcast(intent);
                //休眠
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //更新进度的线程
    private InnerThread innerThread;

    /**
     * 开启更新进度的线程
     */
    private void startUpdateProgressThread() {
        if (innerThread == null) {
            innerThread = new InnerThread();
            innerThread.setRunning(true);
            innerThread.start();
        }
    }

    /**
     * 关闭更新进度的线程
     */
    private void stopUpdateProgressThread() {
        if (innerThread != null) {
            innerThread.setRunning(false);
            innerThread = null;
        }
    }

    //播放
    private void play() {
        try {
            player.reset();
            player.setDataSource(musics.get(currentMusicIndex).getPath());
            player.prepare();
            player.seekTo(pausePosition);
            player.start();
            //发送广播要求界面显示为播放状态
            Intent intent = new Intent(Consts.SET_PLAY_STATE);
            intent.putExtra(Consts.EXTRA_CURRENT_MUSIC_INDEX, currentMusicIndex);
            intent.putExtra(Consts.EXTRA_DURATION, player.getDuration());
            sendBroadcast(intent);
            //开启更新进度的线程
            startUpdateProgressThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //播放指定位置歌曲
    private void play(int position) {
        currentMusicIndex = position;
        pausePosition = 0;
        play();
    }

    //暂停
    private void pause() {
        player.pause();
        pausePosition = player.getCurrentPosition();
        //发送广播要求界面显示为暂停状态
        Intent intent = new Intent(Consts.SET_PAUSE_STATE);
        sendBroadcast(intent);
        //停止更新进度的线程
        stopUpdateProgressThread();
    }

    //上一首
    private void previous() {
        currentMusicIndex--;
        if (currentMusicIndex < 0) {
            currentMusicIndex = musics.size() - 1;
        }
        pausePosition = 0;
        play();
    }

    //播放下一首
    private void next() {
        currentMusicIndex++;
        if (currentMusicIndex >= musics.size()) {
            currentMusicIndex = 0;
        }
        pausePosition = 0;
        play();
    }

    /**
     * 快进到指定位置再开始播放
     *
     * @param percent 要播放到的百分比，取值例如50
     */
    private void seekTo(int percent) {
        //停止更新进度的线程
        stopUpdateProgressThread();
        //计算要快进到的位置，并作为暂停位置
        pausePosition = percent * player.getDuration() / 100;
        play();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 内部广播接收者
     */
    private class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取广播中的Action
            String action = intent.getAction();
            //判断Action
            if (Consts.PLAY_OR_PAUSE.equals(action)) {
                //判断应该播放或暂停
                if (player.isPlaying()) {
                    pause();
                } else {
                    play();
                }
            } else if (Consts.PREVIOUS.equals(action)) {
                previous();
            } else if (Consts.NEXT.equals(action)) {
                next();
            } else if (Consts.PLAY_APPOINT.equals(action)) {
                int position = intent.getIntExtra(Consts.EXTRA_POSITION, 0);
                play(position);
            } else if (Consts.SEEK_T0.equals(action)) {
                //获取快进到的位置
                int percent = intent.getIntExtra(Consts.EXTRA_SEEK_TO_PERCENT, 0);
                seekTo(percent);
            }
        }
    }

    @Override
    public void onDestroy() {
        //注销广播接收者
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}