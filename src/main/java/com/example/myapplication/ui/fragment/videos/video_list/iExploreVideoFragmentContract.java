package com.example.myapplication.ui.fragment.videos.video_list;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

import java.util.List;

public interface iExploreVideoFragmentContract {
    interface iExploreVideoFragmentNavigator extends BaseNavigator {

    }

    interface iExploreVideoFragmentViewModel {
        void setVideosList(List<Taluk> talukList);
    }
}
