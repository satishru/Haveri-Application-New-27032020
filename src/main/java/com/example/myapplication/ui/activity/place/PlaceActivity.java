package com.example.myapplication.ui.activity.place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.ActivityPlaceBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.fragment.place.place_details.PlaceDetailFragment;
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
        PlaceListFragment.PlaceListFragmentCallBack,
        PlaceDetailFragment.PlaceDetailFragmentCallBack {

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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    public static Intent newIntent(Activity activity, Place selectedPlace) {
        Intent intent = new Intent(activity, PlaceActivity.class);
        if (selectedPlace != null) {
            intent.putExtra(INTENT_SELECTED_PLACE, selectedPlace);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        district = getDistrictData();
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(INTENT_SELECTED_TALUK) && getIntent().getSerializableExtra(
                    INTENT_SELECTED_TALUK) != null) {
                selectedTaluk = (Taluk) getIntent().getSerializableExtra(INTENT_SELECTED_TALUK);
            }
            if (getIntent().hasExtra(INTENT_SELECTED_PLACE) && getIntent().getSerializableExtra(
                    INTENT_SELECTED_PLACE) != null) {
                selectedPlace = (Place) getIntent().getSerializableExtra(INTENT_SELECTED_PLACE);
                isSinglePlaceShow = true;
            }
        }
    }

    private void setUpPopUp() {
        activityPlaceBinding.layoutToolbar.tvTitle.setOnClickListener(v -> {
            List<Place> placeList = placeActivityViewModel.getPlaceList(district, selectedTaluk);
            PopupMenu popup = new PopupMenu(PlaceActivity.this, v, Gravity.TOP);
            for (int i = 0; i < placeList.size(); i++) {
                popup.getMenu().add(i, i, i, (getLanguage() == languageEnglish() ?
                        placeList.get(i).getPlaceNameEn() : placeList.get(i).getPlaceNameKn()));
                if (selectedPlace.getPlaceId().equals(placeList.get(i).getPlaceId())) {
                    popup.getMenu().setGroupCheckable(i, true, true);
                    popup.getMenu().getItem(i).setChecked(true);
                }
            }
            popup.setOnMenuItemClickListener(item -> {
                selectedPlace = placeList.get(item.getOrder());
                Fragment fragment = getSupportFragmentManager().findFragmentById(
                        R.id.fragment_container);
                if (fragment instanceof PlaceDetailFragment) {
                    ((PlaceDetailFragment) fragment).refreshFragment();
                }
                return true;
            });
            popup.show();
        });
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
        if (isDistrictNotNull()) {
            loadFragment(PlaceListFragment.newInstance(),
                    activityPlaceBinding.fragmentContainer.getId(), true, true);
        }
    }

    @Override
    public void loadPlaceDetailFragment() {
        if (isDistrictNotNull()) {
            loadFragment(PlaceDetailFragment.newInstance(),
                    activityPlaceBinding.fragmentContainer.getId(), true, true);
        }
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
        if(isDistrictNotNull()) {
            selectedPlace = place;
            loadPlaceDetailFragment();
        }
    }

    @Override
    public void hidePopupDataTitle() {
        activityPlaceBinding.layoutToolbar.tvTitle.setVisibility(View.GONE);
    }

    /**
     * PlaceDetailFragment.PlaceDetailFragmentCallBack
     *
     * @return Place
     */
    @Override
    public Place getSelectedPlace() {
        return selectedPlace;
    }

    @Override
    public void setPopupDataTitle() {
        activityPlaceBinding.layoutToolbar.tvTitle.setVisibility(View.VISIBLE);
        activityPlaceBinding.layoutToolbar.tvTitle.setText(
                getLanguage() == languageEnglish() ? selectedPlace.getPlaceNameEn() :
                        selectedPlace.getPlaceNameKn());
    }
}
