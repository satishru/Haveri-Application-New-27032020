package com.example.myapplication.ui.fragment.taluk.taluk_detail.video;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.FragmentTalukVideosBinding;
import com.example.myapplication.ui.activity.media.video.VideosExploreActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.common.adapter.VideoListAdapter;
import com.example.myapplication.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukVideosFragment extends BaseFragment<FragmentTalukVideosBinding, TalukVideosFragmentViewModel> implements
        iTalukVideosFragmentContract.iTalukVideosFragmentNavigator,
        VideoListAdapter.VideoListAdapterListener {

    @Inject
    VideoListAdapter videoListAdapter;

    @Inject
    Provider<LinearLayoutManager> layoutManager;

    private FragmentTalukVideosBinding fragmentTalukVideosBinding;
    private TalukVideosFragmentViewModel talukVideosFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukVideosFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukVideosFragment fragment = new TalukVideosFragment();
        args.putSerializable(AppConstants.INTENT_SELECTED_TALUK, selectedTaluk);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_taluk_videos;
    }

    @Override
    public TalukVideosFragmentViewModel getViewModel() {
        talukVideosFragmentViewModel = new ViewModelProvider(this, factory).get(
                TalukVideosFragmentViewModel.class);
        return talukVideosFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTalukVideosBinding = getViewDataBinding();
        talukVideosFragmentViewModel.setNavigator(this);
        getBundleData();
        if (selectedTaluk != null) {
            talukVideosFragmentViewModel.setVideoData(selectedTaluk);
        }
        setVideosAdapter();
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
        }
    }

    private void setVideosAdapter() {
        videoListAdapter.setListener(this);
        fragmentTalukVideosBinding.rvVideosList.setLayoutManager(layoutManager.get());
        fragmentTalukVideosBinding.rvVideosList.setItemAnimator(new DefaultItemAnimator());
        fragmentTalukVideosBinding.rvVideosList.addItemDecoration(getVerticalDivider());
        fragmentTalukVideosBinding.rvVideosList.setAdapter(videoListAdapter);
        fragmentTalukVideosBinding.rvVideosList.setNestedScrollingEnabled(true);
    }

    /**
     * VideoListAdapter.VideoListAdapterListener
     *
     * @param selectedVideo Video
     */
    @Override
    public void onVideoClick(Videos selectedVideo) {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(
                    VideosExploreActivity.newIntent(getBaseActivity(), selectedVideo));
        }
    }
}
