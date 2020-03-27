package com.example.myapplication.ui.fragment.videos.video_list;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.FragmentExploreVideoBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.common.adapter.VideoListAdapter;

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
