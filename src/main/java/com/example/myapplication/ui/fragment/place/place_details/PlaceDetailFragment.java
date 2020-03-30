package com.example.myapplication.ui.fragment.place.place_details;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.FragmentPlaceDetailBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.place.place_details.adapter.PlaceDetailPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class PlaceDetailFragment extends BaseFragment<FragmentPlaceDetailBinding, PlaceDetailFragmentViewModel> implements
        iPlaceDetailFragmentContract.iPlaceDetailFragmentNavigator {

    private FragmentPlaceDetailBinding fragmentPlaceDetailBinding;
    private PlaceDetailFragmentViewModel placeDetailFragmentViewModel;
    private PlaceDetailFragmentCallBack placeDetailFragmentCallBack;
    private Place selectedPlace;

    public interface PlaceDetailFragmentCallBack {
        Place getSelectedPlace();

        void setPopupDataTitle();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        placeDetailFragmentCallBack = (PlaceDetailFragmentCallBack) context;
    }

    public static PlaceDetailFragment newInstance() {
        Bundle args = new Bundle();
        PlaceDetailFragment fragment = new PlaceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_place_detail;
    }

    @Override
    public PlaceDetailFragmentViewModel getViewModel() {
        placeDetailFragmentViewModel = new ViewModelProvider(this, factory).get(
                PlaceDetailFragmentViewModel.class);
        return placeDetailFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPlaceDetailBinding = getViewDataBinding();
        loadData();
    }

    public void refreshFragment() {
        loadData();
    }

    private void loadData() {
        if (placeDetailFragmentCallBack != null) {
            selectedPlace = placeDetailFragmentCallBack.getSelectedPlace();
            setToolBarTitle();
            placeDetailFragmentCallBack.setPopupDataTitle();
        }
        if (selectedPlace != null) {
            placeDetailFragmentViewModel.setNavigator(this);
            placeDetailFragmentViewModel.setImageUrl(selectedPlace);
            setViewPager();
        }
    }

    private void setToolBarTitle() {
        if (getBaseActivity() != null) {
            getBaseActivity().setToolBarTitle("");
        }
    }

    private void setViewPager() {
        final PlaceDetailPagerAdapter adapter = new PlaceDetailPagerAdapter(
                getChildFragmentManager(),
                fragmentPlaceDetailBinding.tabLayout.getTabCount(), selectedPlace);
        fragmentPlaceDetailBinding.pagerDetail.setAdapter(adapter);
        fragmentPlaceDetailBinding.pagerDetail.setOffscreenPageLimit(
                PlaceDetailPagerAdapter.TAB_COUNT);

        fragmentPlaceDetailBinding.pagerDetail.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(
                        fragmentPlaceDetailBinding.tabLayout));
        fragmentPlaceDetailBinding.tabLayout.setupWithViewPager(
                fragmentPlaceDetailBinding.pagerDetail);
        fragmentPlaceDetailBinding.tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        fragmentPlaceDetailBinding.pagerDetail.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }
}
