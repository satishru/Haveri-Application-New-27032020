package com.example.myapplication.ui.fragment.place.place_details.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.fragment.place.place_details.about.PlaceAboutFragment;

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
            case 2:
                return PlaceAboutFragment.newInstance(selectedPlace);
            case 3:
                return PlaceAboutFragment.newInstance(selectedPlace);
            case 4:
                return PlaceAboutFragment.newInstance(selectedPlace);
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
