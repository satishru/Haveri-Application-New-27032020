package com.example.myapplication.ui.activity.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.ViewModelProviderFactory;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.databinding.ActivityMapSingleBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.ViewUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

/**
 * To show map full
 */
public class MapSingleActivity extends BaseActivity<ActivityMapSingleBinding, MapSingleActivityViewModel> implements
        OnMapReadyCallback {

    @Inject
    ViewModelProviderFactory factory;

    private GoogleMap map;
    private MapSingleActivityViewModel mapSingleActivityViewModel;
    private ActivityMapSingleBinding activityMapSingleBinding;
    private MapSingleObject mapSingleObject;

    public static Intent newIntent(Activity activity, MapSingleObject mapSingleObject) {
        Intent intent = new Intent(activity, MapSingleActivity.class);
        intent.putExtra(AppConstants.INTENT_MAP_SINGLE, mapSingleObject);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_single;
    }

    @Override
    public MapSingleActivityViewModel getViewModel() {
        mapSingleActivityViewModel = new ViewModelProvider(this, factory).get(
                MapSingleActivityViewModel.class);
        return mapSingleActivityViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapSingleBinding = getViewDataBinding();
        setToolBar(activityMapSingleBinding.layoutToolbar.toolbar, R.string.app_name, true);
        setUp();
    }

    private void setUp() {
        if (getIntent().hasExtra(AppConstants.INTENT_MAP_SINGLE) &&
                getIntent().getParcelableExtra(AppConstants.INTENT_MAP_SINGLE) != null) {
            mapSingleObject = getIntent().getParcelableExtra(AppConstants.INTENT_MAP_SINGLE);
            setToolBarTitle(getLanguage() == languageEnglish() ?
                    mapSingleObject.getTitleEn() : mapSingleObject.getTitleKn());
        }
        if (map == null) {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(
                    R.id.map);
            if (supportMapFragment != null) {
                supportMapFragment.getMapAsync(this);
            }
        }
    }

    private void setupMap() {
        activityMapSingleBinding.ivMapDirection.setOnClickListener(v -> handleMap(true));
        activityMapSingleBinding.ivMapView.setOnClickListener(v -> handleMap(false));
        if (map != null && mapSingleObject != null) {
            map.clear();
            LatLng latLong = new LatLng(mapSingleObject.getLatitude(),
                    mapSingleObject.getLongitude());
            map.addMarker(new MarkerOptions()
                    .title(mapSingleActivityViewModel.getDataManager().getSelectedLanguage() == 0 ?
                            mapSingleObject.getTitleEn() : mapSingleObject.getTitleKn())
                    .position(latLong)
                    .icon(BitmapDescriptorFactory.defaultMarker(ViewUtils.getMarker(0))));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 15));
            //marker.showInfoWindow();
            map.setOnMarkerClickListener(marker1 -> true);
        }
    }

    private void handleMap(boolean isNavigation) {
        if (mapSingleObject != null) {
            handleMapViewAndNavigation(mapSingleObject.getLatitude(), mapSingleObject.getLongitude(), isNavigation);
        }
    }

    /**
     * OnMapReadyCallback
     *
     * @param googleMap GoogleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        setupMap();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            exitActivityWithAnimation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityWithAnimation();
    }
}
