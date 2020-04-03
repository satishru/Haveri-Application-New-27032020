package application.haveri.tourism.ui.fragment.place.place_details.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.fragment.place.place_details.about.PlaceAboutFragment;
import application.haveri.tourism.ui.fragment.place.place_details.events.PlaceEventFragment;
import application.haveri.tourism.ui.fragment.place.place_details.gallery.PlaceGalleryFragment;
import application.haveri.tourism.ui.fragment.place.place_details.video.PlaceVideosFragment;

import org.jetbrains.annotations.NotNull;

public class PlaceDetailPagerAdapter extends FragmentPagerAdapter {

    public final static int TAB_COUNT = 4;
    private final Place selectedPlace;

    public PlaceDetailPagerAdapter(@NonNull FragmentManager fm, int behavior, Place selectedPlace) {
        super(fm, behavior);
        this.selectedPlace = selectedPlace;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case 0:
                return PlaceAboutFragment.newInstance(selectedPlace);
            case 1:
                return PlaceGalleryFragment.newInstance(selectedPlace);
            case 2:
                return PlaceVideosFragment.newInstance(selectedPlace);
            case 3:
                return PlaceEventFragment.newInstance(selectedPlace);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return getTitle(R.string.tab_title_about);
            case 1:
                return getTitle(R.string.tab_title_gallery);
            case 2:
                return getTitle(R.string.tab_title_videos);
            case 3:
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
