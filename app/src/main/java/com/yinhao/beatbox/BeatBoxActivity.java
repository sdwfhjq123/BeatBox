package com.yinhao.beatbox;

import android.support.v4.app.Fragment;

import com.yinhao.beatbox.fragment.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
