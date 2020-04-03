package application.haveri.tourism.ui.fragment.videos.video_list;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

import java.util.List;

public interface iExploreVideoFragmentContract {
    interface iExploreVideoFragmentNavigator extends BaseNavigator {

    }

    interface iExploreVideoFragmentViewModel {
        void setVideosList(List<Taluk> talukList);
    }
}
