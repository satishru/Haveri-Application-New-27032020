package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukAboutBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.utils.AppConstants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class TalukAboutFragment extends BaseFragment<FragmentTalukAboutBinding, TalukAboutFragmentViewModel> implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener,
        iTalukAboutFragmentContract.iTalukAboutFragmentNavigator {

    private TalukAboutFragmentViewModel talukAboutFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukAboutFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukAboutFragment fragment = new TalukAboutFragment();
        args.putSerializable(AppConstants.INTENT_SELECTED_TALUK, selectedTaluk);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_taluk_about;
    }

    @Override
    public TalukAboutFragmentViewModel getViewModel() {
        talukAboutFragmentViewModel = new ViewModelProvider(this, factory).get(TalukAboutFragmentViewModel.class);
        return talukAboutFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTalukAboutBinding fragmentTalukAboutBinding = getViewDataBinding();
        talukAboutFragmentViewModel.setNavigator(this);
        getBundleData();
        setUp();
        fragmentTalukAboutBinding.setTalukObj(selectedTaluk);
    }

    private void getBundleData() {
        if(getArguments() != null && getArguments().containsKey(AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
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
        if (map != null && selectedTaluk != null) {
            setUpMap(selectedTaluk.getLatitude(), selectedTaluk.getLongitude());
            map.setOnMapClickListener(this);
        }
    }

    @Override
    public void openMapSingleActivity(MapSingleObject mapSingleObject) {
        openMapActivity(mapSingleObject);
    }

    /**
     * GoogleMap.OnMapClickListener
     *
     * @param latLng Lat Lang Obj
     */
    @Override
    public void onMapClick(LatLng latLng) {
        talukAboutFragmentViewModel.onMapClick(selectedTaluk);
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
