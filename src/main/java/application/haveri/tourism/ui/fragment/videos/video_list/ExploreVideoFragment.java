package application.haveri.tourism.ui.fragment.videos.video_list;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.databinding.FragmentExploreVideoBinding;
import application.haveri.tourism.ui.base.BaseFragment;
import application.haveri.tourism.ui.fragment.common.adapter.VideoListAdapter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Provider;

public class ExploreVideoFragment extends BaseFragment<FragmentExploreVideoBinding, ExploreVideoFragmentViewModel> implements
        iExploreVideoFragmentContract.iExploreVideoFragmentNavigator,
        VideoListAdapter.VideoListAdapterListener {

    @Inject
    VideoListAdapter videoListAdapter;

    @Inject
    Provider<LinearLayoutManager> layoutManager;

    private FragmentExploreVideoBinding fragmentExploreVideoBinding;
    private ExploreVideoFragmentViewModel exploreVideoFragmentViewModel;
    private ExploreVideoFragmentCallBack exploreVideoFragmentCallBack;

    public interface ExploreVideoFragmentCallBack {
        void openPlayVideoFragment(Videos selectedVideo);

        void hideToolBar(boolean isHide);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        exploreVideoFragmentCallBack = (ExploreVideoFragmentCallBack) context;
    }

    public static ExploreVideoFragment newInstance() {
        Bundle args = new Bundle();
        ExploreVideoFragment fragment = new ExploreVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore_video;
    }

    @Override
    public ExploreVideoFragmentViewModel getViewModel() {
        exploreVideoFragmentViewModel = new ViewModelProvider(this, factory).get(
                ExploreVideoFragmentViewModel.class);
        return exploreVideoFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getBaseActivity() != null) {
            getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        fragmentExploreVideoBinding = getViewDataBinding();
        exploreVideoFragmentViewModel.setNavigator(this);
        if (HaveriApplication.getInstance().getDistrict() != null) {
            exploreVideoFragmentViewModel.setVideosList(
                    HaveriApplication.getInstance().getDistrict().getTaluks());
        }
        setVideosAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (exploreVideoFragmentCallBack != null) {
            exploreVideoFragmentCallBack.hideToolBar(false);
        }
        getBaseActivity().setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setVideosAdapter() {
        videoListAdapter.setListener(this);
        fragmentExploreVideoBinding.rvVideosList.setLayoutManager(layoutManager.get());
        fragmentExploreVideoBinding.rvVideosList.setItemAnimator(new DefaultItemAnimator());
        fragmentExploreVideoBinding.rvVideosList.addItemDecoration(getVerticalDivider());
        fragmentExploreVideoBinding.rvVideosList.setAdapter(videoListAdapter);
    }

    @Override
    public void onVideoClick(Videos video) {
        if (exploreVideoFragmentCallBack != null) {
            exploreVideoFragmentCallBack.openPlayVideoFragment(video);
        }
    }
}
