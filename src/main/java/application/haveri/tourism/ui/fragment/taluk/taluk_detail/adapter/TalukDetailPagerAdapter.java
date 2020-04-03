package application.haveri.tourism.ui.fragment.taluk.taluk_detail.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.about.TalukAboutFragment;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.events.TalukEventFragment;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery.TalukGalleryFragment;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.places.TalukPlacesFragment;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.video.TalukVideosFragment;

import org.jetbrains.annotations.NotNull;

public class TalukDetailPagerAdapter extends FragmentPagerAdapter {

    public final static int TAB_COUNT = 5;
    private final Taluk selectedTaluk;

    public TalukDetailPagerAdapter(@NonNull FragmentManager fm, int behavior, Taluk selectedTaluk) {
        super(fm, behavior);
        this.selectedTaluk = selectedTaluk;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case 0:
                return TalukAboutFragment.newInstance(selectedTaluk);
            case 1:
                return TalukPlacesFragment.newInstance(selectedTaluk);
            case 2:
                return TalukGalleryFragment.newInstance(selectedTaluk);
            case 3:
                return TalukVideosFragment.newInstance(selectedTaluk);
            case 4:
                return TalukEventFragment.newInstance(selectedTaluk);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return getTitle(R.string.tab_title_about);
            case 1:
                return getTitle(R.string.tab_title_places);
            case 2:
                return getTitle(R.string.tab_title_gallery);
            case 3:
                return getTitle(R.string.tab_title_videos);
            case 4:
                return getTitle(R.string.tab_title_events);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    private String getTitle(int str_id) {
        return HaveriApplication.getInstance().getResources().getString(str_id);
    }
}
