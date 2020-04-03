package application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery;

import androidx.recyclerview.widget.GridLayoutManager;

import application.haveri.tourism.ui.fragment.common.adapter.ImageGalleryAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukGalleryFragmentModule {

    @Provides
    ImageGalleryAdapter provideImageGalleryAdapter() {
        return new ImageGalleryAdapter(new ArrayList<>());
    }

    @Provides
    GridLayoutManager provideTalukGridLayoutManager(TalukGalleryFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(), 2, GridLayoutManager.VERTICAL, false);
    }
}
