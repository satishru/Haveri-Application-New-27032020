package com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukImageGalleryAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukGalleryFragmentModule {

    @Provides
    TalukImageGalleryAdapter provideTalukImageGalleryAdapter() {
        return new TalukImageGalleryAdapter(new ArrayList<>());
    }

    @Provides
    GridLayoutManager provideTalukGridLayoutManager(TalukGalleryFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(), 2, GridLayoutManager.VERTICAL, false);
    }
}
