package application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

class iTalukGalleryFragmentContract {
    interface iTalukGalleryFragmentNavigator extends BaseNavigator {
    }

    public interface iTalukGalleryFragmentViewModel {
        void setImageData(Taluk taluk);
    }
}
