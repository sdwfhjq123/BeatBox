package com.yinhao.beatbox.manager;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 定位，管理以及播放
 */
public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    //实现音频播放功能
    private SoundPool mSoundPool;

    private float mPlayRote = 1.0f;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        //This old constructor is deprecated ,but we need it for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }

    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        //音频id，left,right,priority,loop,播放速率
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, mPlayRote);
    }

    /**
     * 载入全部文件
     *
     * @param sound
     * @throws IOException
     */
    private void load(Sound sound) throws IOException {
        Log.i(TAG, "sound.getAssetPath:" + sound.getAssetPath());
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        Log.i(TAG, "load: soundId" + soundId);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void release() {
        mSoundPool.release();
    }

    public void setPlayRate(float rate) {
        mPlayRote = rate;
    }
}
