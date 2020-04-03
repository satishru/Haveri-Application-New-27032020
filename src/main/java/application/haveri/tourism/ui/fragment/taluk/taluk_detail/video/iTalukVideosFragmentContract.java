package application.haveri.tourism.ui.fragment.taluk.taluk_detail.video;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iTalukVideosFragmentContract {
    interface iTalukVideosFragmentNavigator extends BaseNavigator {
    }

    interface iTalukVideosFragmentViewModel {
        void setVideoData(Taluk taluk);
    }
}
