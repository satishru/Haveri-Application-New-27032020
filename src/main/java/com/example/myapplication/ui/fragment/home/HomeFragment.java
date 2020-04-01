package com.example.myapplication.ui.fragment.home;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.activity.event.EventDetailActivity;
import com.example.myapplication.ui.activity.media.image.ImageViewActivity;
import com.example.myapplication.ui.activity.media.video.VideosExploreActivity;
import com.example.myapplication.ui.activity.place.PlaceActivity;
import com.example.myapplication.ui.activity.taluk.TalukActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.home.adapter.event.HomeEventsAdapter;
import com.example.myapplication.ui.fragment.home.adapter.gallery.HomeImageGalleryAdapter;
import com.example.myapplication.ui.fragment.home.adapter.place.HomePlaceAdapter;
import com.example.myapplication.ui.fragment.home.adapter.taluk.HomeTalukAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel> implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener,
        iHomeFragmentContract.iHomeFragmentNavigator,
        HomeTalukAdapter.HomeTalukAdapterListener,
        HomeImageGalleryAdapter.HomeImageGalleryAdapterListener,
        HomeEventsAdapter.HomeEventsAdapterListener,
        HomePlaceAdapter.HomePlaceAdapterListener {

    @Inject
    HomeTalukAdapter homeTalukAdapter;
    @Inject
    HomeImageGalleryAdapter homeImageGalleryAdapter;
    @Inject
    HomeEventsAdapter homeEventsAdapter;
    @Inject
    HomePlaceAdapter homePlaceAdapter;
    @Inject
    Provider<LinearLayoutManager> layoutManager;
    @Inject
    Provider<GridLayoutManager> gridLayoutManager;

    private HomeFragmentCallBack iHomeFragmentCallBack;
    private HomeFragmentViewModel homeFragmentViewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private BottomSheetBehavior bottomSheetBehavior;
    private District district;

    public interface HomeFragmentCallBack {
        void showHideHomeButton(boolean visibility);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iHomeFragmentCallBack = (HomeFragmentCallBack) context;
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeFragmentViewModel getViewModel() {
        homeFragmentViewModel = new ViewModelProvider(this, factory).get(
                HomeFragmentViewModel.class);
        return homeFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentHomeBinding = getViewDataBinding();
        homeFragmentViewModel.setNavigator(this);
        setUp();
        subscribeToHaveriDataResponseLiveData();
        homeFragmentViewModel.startLoadingLocalData();
    }

    private void setUp() {
        if (map == null) {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(
                    R.id.map);
            if (supportMapFragment != null) {
                supportMapFragment.getMapAsync(this);
            }
        }
        setupBottomSheet();
        setHomeTalukAdapter();
        setImageGalleryAdapter();
        setHomeEventsAdapter();
        setHomePlaceAdapter();
    }

    private void setHomeTalukAdapter() {
        homeTalukAdapter.setListener(this);
        fragmentHomeBinding.layoutContent.rvTalukList.setLayoutManager(layoutManager.get());
        fragmentHomeBinding.layoutContent.rvTalukList.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.layoutContent.rvTalukList.addItemDecoration(getHorizontalDivider());
        fragmentHomeBinding.layoutContent.rvTalukList.setAdapter(homeTalukAdapter);
        //fragmentHomeBinding.layoutContent.rvTalukList.setNestedScrollingEnabled(false);
    }

    private void setImageGalleryAdapter() {
        homeImageGalleryAdapter.setListener(this);
        fragmentHomeBinding.layoutContent.rvImageList.setLayoutManager(gridLayoutManager.get());
        fragmentHomeBinding.layoutContent.rvImageList.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.layoutContent.rvImageList.addItemDecoration(getHorizontalDivider());
        fragmentHomeBinding.layoutContent.rvImageList.setAdapter(homeImageGalleryAdapter);
        //fragmentHomeBinding.layoutContent.rvImageList.setNestedScrollingEnabled(false);
    }

    private void setHomeEventsAdapter() {
        homeEventsAdapter.setListener(this);
        fragmentHomeBinding.layoutContent.rvEventList.setLayoutManager(layoutManager.get());
        fragmentHomeBinding.layoutContent.rvEventList.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.layoutContent.rvEventList.addItemDecoration(getHorizontalDivider());
        fragmentHomeBinding.layoutContent.rvEventList.setAdapter(homeEventsAdapter);
        //fragmentHomeBinding.layoutContent.rvEventList.setNestedScrollingEnabled(false);
    }

    private void setHomePlaceAdapter() {
        homePlaceAdapter.setListener(this);
        fragmentHomeBinding.layoutContent.rvPlaceList.setLayoutManager(layoutManager.get());
        fragmentHomeBinding.layoutContent.rvPlaceList.setItemAnimator(new DefaultItemAnimator());
        fragmentHomeBinding.layoutContent.rvPlaceList.addItemDecoration(getHorizontalDivider());
        fragmentHomeBinding.layoutContent.rvPlaceList.setAdapter(homePlaceAdapter);
        //fragmentHomeBinding.layoutContent.rvPlaceList.setNestedScrollingEnabled(false);
    }

    private void setupBottomSheet() {
        setHomeButton(false);
        bottomSheetBehavior = BottomSheetBehavior.from(
                fragmentHomeBinding.aboutBottomSheet.bottomSheet);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                homeFragmentViewModel.handleBottomSheetState(newState);
                //handleBottomSheetState(newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    public boolean isBottomSheetOpened() {
        return bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    private void subscribeToHaveriDataResponseLiveData() {
        homeFragmentViewModel.getDistrict().observe(getViewLifecycleOwner(),
                haveriDataResponse -> {
                    this.district = haveriDataResponse;
                    HaveriApplication.getInstance().setDistrict(haveriDataResponse);
                    setUpMap();
                });
    }

    /**
     * iHomeFragmentContract.iHomeFragmentNavigator CallBacks
     */
    @Override
    public void openOrCloseBottomSheet() {
        if (isAdded()) {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    }

    @Override
    public void setUpMap() {
        fragmentHomeBinding.layoutContent.incLayoutMap.ivMapDirection.setOnClickListener(v -> handleMap(true));
        fragmentHomeBinding.layoutContent.incLayoutMap.ivMapView.setOnClickListener(v -> handleMap(false));
        if (map != null && district != null) {
            setUpMap(district.getLatitude(), district.getLongitude());
            map.setOnMapClickListener(this);
        }
    }

    private void handleMap(boolean isNavigation) {
        if (district != null) {
            getBaseActivity().handleMapViewAndNavigation(district.getLatitude(), district.getLongitude(), isNavigation);
        }
    }

    @Override
    public void openMapSingleActivity(MapSingleObject mapSingleObject) {
        openMapActivity(mapSingleObject);
    }

    @Override
    public void openVideoSingleActivity(Videos video) {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(
                    VideosExploreActivity.newIntent(getBaseActivity(), video));
        }
    }

    @Override
    public void openExploreVideosActivity() {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(
                    VideosExploreActivity.newIntent(getBaseActivity(), null));
        }
    }

    @Override
    public void openTalukActivity() {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(TalukActivity.newIntent(getBaseActivity(), null));
        }
    }

    @Override
    public void openPlaceActivity() {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(PlaceActivity.newIntent(getBaseActivity(), null));
        }
    }

    @Override
    public void setHomeButton(boolean visibility) {
        if (iHomeFragmentCallBack != null) {
            iHomeFragmentCallBack.showHideHomeButton(visibility);
        }
    }

    @Override
    public void showBottomSheetSlideButton(boolean visibility) {
        if (visibility) {
            fragmentHomeBinding.aboutBottomSheet.btnSlide.setVisibility(View.VISIBLE);
        } else {
            LayoutTransition lt = new LayoutTransition();
            lt.disableTransitionType(LayoutTransition.DISAPPEARING);
            fragmentHomeBinding.aboutBottomSheet.llBottomSheet.setLayoutTransition(lt);
            fragmentHomeBinding.aboutBottomSheet.btnSlide.setVisibility(View.GONE);
            fragmentHomeBinding.aboutBottomSheet.llBottomSheet.setLayoutTransition(null);
        }
    }

    @Override
    public void openImageViewActivity(List<Images> imagesList, int selectedPosition) {
        if (getBaseActivity() != null && imagesList != null) {
            startActivityWithAnimation(
                    ImageViewActivity.newIntent(getBaseActivity(), imagesList, selectedPosition));
        }
    }
    /* iHomeFragmentContract.iHomeFragmentNavigator CallBacks Ends */

    /**
     * OnMapReadyCallback
     *
     * @param googleMap GoogleMap Obj
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        setUpMap();
    }

    /**
     * GoogleMap.OnMapClickListener
     *
     * @param latLng Lat Lang Obj
     */
    @Override
    public void onMapClick(LatLng latLng) {
        homeFragmentViewModel.onMapClick();
    }

    /**
     * HomeTalukAdapter.HomeTalukAdapterListener
     *
     * @param taluk Taluk object
     */
    @Override
    public void onTalukItemClick(Taluk taluk) {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(TalukActivity.newIntent(getBaseActivity(), taluk));
        }
    }

    /**
     * HomePlaceAdapter.HomePlaceAdapterListener
     *
     * @param place Place
     */
    @Override
    public void onPlaceClick(Place place) {
        if (getBaseActivity() != null && isDistrictNotNull()) {
            startActivityWithAnimation(PlaceActivity.newIntent(getBaseActivity(), place));
        }
    }

    /**
     * HomeImageGalleryAdapter.HomeImageGalleryAdapterListener
     *
     * @param position Image position
     */
    @Override
    public void onItemClick(int position) {
        openImageViewActivity(homeImageGalleryAdapter.getTotalImagesList(), position);
    }

    /**
     * HomeEventsAdapter.HomeEventsAdapterListener
     *
     * @param event Event Selected/Clicked
     */
    @Override
    public void onEventClick(Event event) {
        if(isDistrictNotNull()) {
            startActivityWithAnimation(
                    EventDetailActivity.newIntent(getBaseActivity(), event));
        }
    }
}
