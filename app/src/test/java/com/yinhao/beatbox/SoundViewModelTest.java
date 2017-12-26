package com.yinhao.beatbox;

import com.yinhao.beatbox.manager.BeatBox;
import com.yinhao.beatbox.manager.Sound;
import com.yinhao.beatbox.viewModel.SoundViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hp on 2017/12/25.
 */

public class SoundViewModelTest {

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsAtitle() {
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void cannsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClick();
        verify(mBeatBox).play(mSound);
    }
}
