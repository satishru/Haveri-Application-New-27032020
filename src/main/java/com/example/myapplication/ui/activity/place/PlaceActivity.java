package com.example.myapplication.ui.activity.place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.ActivityPlaceBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.fragment.place.place_list.PlaceListFragment;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.example.myapplication.utils.AppConstants.INTENT_SELECTED_PLACE;
import static com.example.myapplication.utils.AppConstants.INTENT_SELECTED_TALUK;

/**
 * To Show place details, images, videos, events
 */
public class PlaceActivity extends BaseActivity<ActivityPlaceBinding, PlaceActivityViewModel>
        implements HasSupportFragmentInjector,
        iPlaceActivityContract.iPlaceActivityNavigator,
        PlaceListFragment.PlaceListFragmentCallBack {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityPlaceBinding activityPlaceBinding;
    private PlaceActivityViewModel placeActivityViewModel;
    private District district;
    private Taluk selectedTaluk;
    private Place selectedPlace;
    private boolean isSinglePlaceShow;

    public static Intent newIntent(Activity activity, Taluk selectedTaluk, Place selectedPlace) {
        Intent intent = new Intent(activity, PlaceActivity.class);
        if (selectedTaluk != null) {
            intent.putExtra(INTENT_SELECTED_TALUK, selectedTaluk);
        }
        if (selectedPlace != null) {
            intent.putExtra(INTENT_SELECTED_PLACE, selectedPlace);
        }
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_place;
    }

    @Override
    public PlaceActivityViewModel getViewModel() {
        placeActivityViewModel = new ViewModelProvider(this, factory).get(
                PlaceActivityViewModel.class);
        return placeActivityViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPlaceBinding = getViewDataBinding();
        placeActivityViewModel.setNavigator(this);
        setToolBar(activityPlaceBinding.layoutToolbar.toolbar, R.string.title_activity_place, true);
        getBundleData();
        setUpPopUp();
        placeActivityViewModel.startLoadingData();
    }

    private void getBundleData() {
        district = HaveriApplication.getInstance().getDistrict();
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(INTENT_SELECTED_PLACE) && getIntent().getSerializableExtra(
                    INTENT_SELECTED_PLACE) != null) {
                selectedPlace = (Place) getIntent().getSerializableExtra(INTENT_SELECTED_PLACE);
                isSinglePlaceShow = true;
            }
        }
    }

    private void setUpPopUp() {

    }

    /**
     * iPlaceActivityContract.iPlaceActivityNavigator
     *
     * @return boolean
     */
    @Override
    public boolean isSinglePlaceShow() {
        return isSinglePlaceShow;
    }

    @Override
    public void loadPlaceListFragment() {
        loadFragment(PlaceListFragment.newInstance(),
                activityPlaceBinding.fragmentContainer.getId(), true, true);
    }

    @Override
    public void loadPlaceDetailFragment() {
    }
    /* iPlaceActivityContract.iPlaceActivityNavigator Ends */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getBackStackEntryCount() > 1) {
            popBackStack();
            return;
        }
        exitActivityWithAnimation();
    }

    /**
     * PlaceListFragment.PlaceListFragmentCallBack
     *
     * @return Place List
     */
    @Override
    public List<Place> getPlaceList() {
        return placeActivityViewModel.getPlaceList(district, selectedTaluk);
    }

    @Override
    public void openPlaceDetailFragment(Place place) {
        selectedPlace = place;
        loadPlaceDetailFragment();
    }

    @Override
    public void hidePopupDataTitle() {
        activityPlaceBinding.layoutToolbar.tvTitle.setVisibility(View.GONE);
    }
}
