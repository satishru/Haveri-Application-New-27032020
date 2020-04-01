package com.example.myapplication.ui.activity.media.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.ActivityVideosExploreBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.fragment.videos.play_video.PlayVideoFragment;
import com.example.myapplication.ui.fragment.videos.video_list.ExploreVideoFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.example.myapplication.utils.AppConstants.INTENT_SELECTED_VIDEO;

/**
 *  To show video gallery and play video
 */
public class VideosExploreActivity extends BaseActivity<ActivityVideosExploreBinding, VideosExploreActivityViewModel> implements
        HasSupportFragmentInjector,
        iVideosExploreActivityContract.iVideosExploreActivityNavigator,
        ExploreVideoFragment.ExploreVideoFragmentCallBack,
        PlayVideoFragment.PlayVideoFragmentCallBack {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private VideosExploreActivityViewModel videosExploreActivityViewModel;
    private ActivityVideosExploreBinding activityVideosExploreBinding;

    private Videos selectedVideo;
    private boolean isSingleVideosShow;

    public static Intent newIntent(Activity activity, Videos selectedVideo) {
        Intent intent = new Intent(activity, VideosExploreActivity.class);
        if (selectedVideo != null) {
            intent.putExtra(INTENT_SELECTED_VIDEO, selectedVideo);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_videos_explore;
    }

    @Override
    public VideosExploreActivityViewModel getViewModel() {
        videosExploreActivityViewModel = new ViewModelProvider(this, factory).get(
                VideosExploreActivityViewModel.class);
        return videosExploreActivityViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVideosExploreBinding = getViewDataBinding();
        videosExploreActivityViewModel.setNavigator(this);
        setToolBar(activityVideosExploreBinding.layoutToolbar.toolbar,
                R.string.title_app_name, true);
        getBundleData();
        videosExploreActivityViewModel.startLoadingData();
    }

    private void getBundleData() {
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(INTENT_SELECTED_VIDEO) && getIntent().getSerializableExtra(
                    INTENT_SELECTED_VIDEO) != null) {
                selectedVideo = (Videos) getIntent().getSerializableExtra(INTENT_SELECTED_VIDEO);
                isSingleVideosShow = true;
            }
        }
    }

    @Override
    public boolean isSingleVideoShow() {
        return isSingleVideosShow;
    }

    @Override
    public void loadVideoListFragment() {
        loadFragment(ExploreVideoFragment.newInstance(),
                activityVideosExploreBinding.fragmentContainer.getId(), true, true);
    }

    @Override
    public void loadVideoShowFragment() {
        loadFragment(PlayVideoFragment.newInstance(),
                activityVideosExploreBinding.fragmentContainer.getId(), true, true);
    }

    /**
     * ExploreVideoFragment.ExploreVideoFragmentCallBack
     *
     * @param selectedVideo Selected Video
     */
    @Override
    public void openPlayVideoFragment(Videos selectedVideo) {
        this.selectedVideo = selectedVideo;
        loadVideoShowFragment();
    }

    /**
     * PlayVideoFragment.PlayVideoFragmentCallBack
     */
    @Override
    public Videos getSelectedVideo() {
        return selectedVideo;
    }

    @Override
    public void hideToolBar(boolean isHide) {
        if (getSupportActionBar() != null) {
            if (isHide) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getBackStackEntryCount() > 1) {
            popBackStack();
            return;
        }
        exitActivityWithAnimation();
    }
}
