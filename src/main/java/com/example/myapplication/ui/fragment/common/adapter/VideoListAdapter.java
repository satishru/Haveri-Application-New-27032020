package com.example.myapplication.ui.fragment.common.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.LayoutVideoListItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Videos> videosList;
    private VideoListAdapterListener videoListAdapterListener;

    public interface VideoListAdapterListener {
        void onVideoClick(Videos selectedVideo);
    }

    public VideoListAdapter(List<Videos> videosList) {
        this.videosList = videosList;
    }

    public void setListener(VideoListAdapterListener videoListAdapterListener) {
        this.videoListAdapterListener = videoListAdapterListener;
    }

    public void addItems(List<Videos> videosList) {
        if (videosList != null) {
            clearItems();
            this.videosList.addAll((CommonUtils.mockList(videosList, AppConstants.MOCK_LIST_SIZE)));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        videosList.clear();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutVideoListItemBinding layoutExploreVideoItemBinding = LayoutVideoListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VideoListViewHolder(layoutExploreVideoItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class VideoListViewHolder extends BaseViewHolder implements
            VideoListAdapterViewModel.VideoListAdapterViewModelListener {
        private LayoutVideoListItemBinding layoutVideoListItemBinding;

        VideoListViewHolder(LayoutVideoListItemBinding layoutVideoListItemBinding) {
            super(layoutVideoListItemBinding.getRoot());
            this.layoutVideoListItemBinding = layoutVideoListItemBinding;
        }

        @Override
        public void onBind(int position) {
            VideoListAdapterViewModel videoListAdapterViewModel =
                    new VideoListAdapterViewModel(position, videosList.get(position), this,
                            getLanguage(layoutVideoListItemBinding));
            layoutVideoListItemBinding.setViewModel(videoListAdapterViewModel);
            layoutVideoListItemBinding.executePendingBindings();
            setAnimation(position, layoutVideoListItemBinding.cardContent, false,
                    android.R.anim.slide_in_left);
        }

        @Override
        public void onVideoClick(int position) {
            if (videoListAdapterListener != null) {
                videoListAdapterListener.onVideoClick(videosList.get(position));
            }
        }
    }
}
