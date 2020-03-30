package com.example.myapplication.ui.fragment.place.place_details.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.FragmentPlaceAboutBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.utils.AppConstants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class PlaceAboutFragment extends BaseFragment<FragmentPlaceAboutBinding, PlaceAboutFragmentViewModel> implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener,
        iPlaceAboutFragmentContract.iPlaceAboutFragmentNavigator {

    private FragmentPlaceAboutBinding fragmentPlaceAboutBinding;
    private PlaceAboutFragmentViewModel placeAboutFragmentViewModel;
    private Place selectedPlace;

    public static PlaceAboutFragment newInstance(Place selectedPlace) {
        Bundle args = new Bundle();
        PlaceAboutFragment fragment = new PlaceAboutFragment();
        args.putSerializable(AppConstants.INTENT_SELECTED_PLACE, selectedPlace);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_place_about;
    }

    @Override
    public PlaceAboutFragmentViewModel getViewModel() {
        placeAboutFragmentViewModel = new ViewModelProvider(this, factory).get(
                PlaceAboutFragmentViewModel.class);
        return placeAboutFragmentViewModel;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPlaceAboutBinding = getViewDataBinding();
        placeAboutFragmentViewModel.setNavigator(this);
        getBundleData();
        setUp();
        fragmentPlaceAboutBinding.setPlaceObj(selectedPlace);
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_PLACE)) {
            selectedPlace = (Place) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_PLACE);
        }
    }

    private void setUp() {
        if (map == null) {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(
                    R.id.map);
            if (supportMapFragment != null) {
                supportMapFragment.getMapAsync(this);
            }
        }
    }

    @Override
    public void setUpMap() {
        fragmentPlaceAboutBinding.incLayoutMap.ivMapDirection.setOnClickListener(
                v -> handleMap(true));
        fragmentPlaceAboutBinding.incLayoutMap.ivMapView.setOnClickListener(v -> handleMap(false));
        if (map != null && selectedPlace != null) {
            setUpMap(selectedPlace.getLatitude(), selectedPlace.getLongitude());
            map.setOnMapClickListener(this);
        }
    }

    private void handleMap(boolean isNavigation) {
        if (selectedPlace != null) {
            getBaseActivity().handleMapViewAndNavigation(selectedPlace.getLatitude(),
                    selectedPlace.getLongitude(), isNavigation);
        }
    }

    @Override
    public void openMapSingleActivity(MapSingleObject mapSingleObject) {
        openMapActivity(mapSingleObject);
    }

    @Override
    public void makePhoneCall(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNumber)));
    }

    @Override
    public void openWebLink(String webLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webLink)));
    }

    /**
     * GoogleMap.OnMapClickListener
     *
     * @param latLng Lat Lang Obj
     */
    @Override
    public void onMapClick(LatLng latLng) {
        placeAboutFragmentViewModel.onMapClick(selectedPlace);
    }

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
}
