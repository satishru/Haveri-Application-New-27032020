package com.example.myapplication.ui.fragment.videos.play_video;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.FragmentPlayVideoBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

import org.jetbrains.annotations.NotNull;

public class PlayVideoFragment extends BaseFragment<FragmentPlayVideoBinding, PlayVideoFragmentViewModel> implements
        iPlayVideoFragmentContract.iPlayVideoFragmentNavigator {

    private FragmentPlayVideoBinding fragmentPlayVideoBinding;
    private PlayVideoFragmentViewModel playVideoFragmentViewModel;
    private PlayVideoFragmentCallBack playVideoFragmentCallBack;
    private YouTubePlayer currentYouTubePlayer = null;

    public interface PlayVideoFragmentCallBack {
        Videos getSelectedVideo();

        void hideToolBar(boolean isHide);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        playVideoFragmentCallBack = (PlayVideoFragmentCallBack) context;
    }

    public static PlayVideoFragment newInstance() {
        Bundle args = new Bundle();
        PlayVideoFragment fragment = new PlayVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_video;
    }

    @Override
    public PlayVideoFragmentViewModel getViewModel() {
        playVideoFragmentViewModel = new ViewModelProvider(this, factory).get(
                PlayVideoFragmentViewModel.class);
        return playVideoFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeFullScreen();
        if (playVideoFragmentCallBack != null) {
            playVideoFragmentCallBack.hideToolBar(true);
        }
        getBaseActivity().setOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        fragmentPlayVideoBinding = getViewDataBinding();
        playVideoFragmentViewModel.setNavigator(this);
        loadVideo();
    }

    private void makeFullScreen() {
        if(getBaseActivity() != null) {
            getBaseActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (currentYouTubePlayer != null) {
            currentYouTubePlayer.pause();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(getBaseActivity() != null) {
            getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        releaseVideo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releaseVideo();
    }

    private void releaseVideo() {
        if (currentYouTubePlayer != null) {
            currentYouTubePlayer.pause();
            fragmentPlayVideoBinding.youtubePlayerView.release();
        }
    }

    private void loadVideo() {
        if (playVideoFragmentCallBack != null) {
            fragmentPlayVideoBinding.youtubePlayerView.getYouTubePlayerWhenReady(
                    youTubePlayer -> {
                        currentYouTubePlayer = youTubePlayer;

                        currentYouTubePlayer.loadVideo(
                                playVideoFragmentCallBack.getSelectedVideo().getVedioUrl(), 0);
                        currentYouTubePlayer.play();
                    });

            getLifecycle().addObserver(fragmentPlayVideoBinding.youtubePlayerView);
        }
    }

    @Override
    public void onBackIconClicked() {
        getBaseActivity().onBackPressed();
    }
}
