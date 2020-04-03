package application.haveri.tourism.ui.activity.media.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.databinding.ActivityVideosExploreBinding;
import application.haveri.tourism.ui.base.BaseActivity;
import application.haveri.tourism.ui.fragment.videos.play_video.PlayVideoFragment;
import application.haveri.tourism.ui.fragment.videos.video_list.ExploreVideoFragment;

import javax.inject.Inject;

import application.haveri.tourism.utils.AppConstants;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

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
            intent.putExtra(AppConstants.INTENT_SELECTED_VIDEO, selectedVideo);
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
            if (getIntent().hasExtra(AppConstants.INTENT_SELECTED_VIDEO) && getIntent().getSerializableExtra(
                    AppConstants.INTENT_SELECTED_VIDEO) != null) {
                selectedVideo = (Videos) getIntent().getSerializableExtra(
                        AppConstants.INTENT_SELECTED_VIDEO);
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
