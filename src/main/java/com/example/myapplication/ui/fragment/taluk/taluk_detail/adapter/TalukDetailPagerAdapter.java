package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.about.TalukAboutFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.events.TalukEventFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery.TalukGalleryFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.places.TalukPlacesFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.video.TalukVideosFragment;

import org.jetbrains.annotations.NotNull;

public class TalukDetailPagerAdapter extends FragmentPagerAdapter {

    public final static int TAB_COUNT = 5;
    private final Taluk selectedTaluk;
    private final District district;

    public TalukDetailPagerAdapter(@NonNull FragmentManager fm, int behavior, Taluk selectedTaluk, District district) {
        super(fm, behavior);
        this.selectedTaluk = selectedTaluk;
        this.district = district;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TalukAboutFragment.newInstance(selectedTaluk);
            case 1:
                return TalukPlacesFragment.newInstance(selectedTaluk);
            case 2:
                return TalukGalleryFragment.newInstance(selectedTaluk);
            case 3:
                return TalukVideosFragment.newInstance(selectedTaluk);
            case 4:
                return TalukEventFragment.newInstance(selectedTaluk, district);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return HaveriApplication.getInstance().getResources().getString(
                        R.string.tab_title_about);
            case 1:
                return "Places";
            case 2:
                return "Gallery";
            case 3:
                return "Videos";
            case 4:
                return "Events";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
