package application.haveri.tourism.ui.fragment.common.adapter;

import androidx.databinding.ObservableField;

import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.utils.Language;

public class VideoListAdapterViewModel {
    public ObservableField<String> videoId;
    public ObservableField<String> videoTitle;

    private int position;
    private VideoListAdapterViewModelListener mListener;

    VideoListAdapterViewModel(int position, Videos video, VideoListAdapterViewModelListener listener, int language) {
        this.position = position;
        this.mListener = listener;
        videoId = new ObservableField<>(video.getVedioUrl());
        videoTitle = new ObservableField<>(
                Language.EN.getValue() == language ? video.getVideoTitleEn() :
                        video.getVideoTitleKn());
    }

    public void onVideoClick() {
        if (mListener != null) {
            mListener.onVideoClick(position);
        }
    }

    public interface VideoListAdapterViewModelListener {
        void onVideoClick(int position);
    }
}
