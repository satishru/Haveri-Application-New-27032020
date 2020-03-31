package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukPlacesBinding;
import com.example.myapplication.ui.activity.place.PlaceActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.common.adapter.PlaceAdapter;
import com.example.myapplication.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukPlacesFragment extends BaseFragment<FragmentTalukPlacesBinding, TalukPlacesFragmentViewModel>
        implements iTalukPlacesFragmentContract.iTalukPlacesFragmentNavigator,
        PlaceAdapter.PlaceAdapterListener {

    @Inject
    PlaceAdapter placeAdapter;
    @Inject
    Provider<LinearLayoutManager> layoutManager;
    private FragmentTalukPlacesBinding fragmentTalukPlacesBinding;
    private TalukPlacesFragmentViewModel talukPlacesFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukPlacesFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukPlacesFragment fragment = new TalukPlacesFragment();
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
        return R.layout.fragment_taluk_places;
    }

    @Override
    public TalukPlacesFragmentViewModel getViewModel() {
        talukPlacesFragmentViewModel = new ViewModelProvider(this, factory).get(
                TalukPlacesFragmentViewModel.class);
        return talukPlacesFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTalukPlacesBinding = getViewDataBinding();
        talukPlacesFragmentViewModel.setNavigator(this);
        getBundleData();
        talukPlacesFragmentViewModel.setPlaceList(selectedTaluk);
        setPlaceAdapter();
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
        }
    }

    private void setPlaceAdapter() {
        placeAdapter.setListener(this);
        fragmentTalukPlacesBinding.rvPlaceList.setLayoutManager(layoutManager.get());
        fragmentTalukPlacesBinding.rvPlaceList.setItemAnimator(new DefaultItemAnimator());
        fragmentTalukPlacesBinding.rvPlaceList.addItemDecoration(getVerticalDivider());
        fragmentTalukPlacesBinding.rvPlaceList.setAdapter(placeAdapter);
        fragmentTalukPlacesBinding.rvPlaceList.setNestedScrollingEnabled(true);
    }

    /**
     * PlaceAdapter.PlaceAdapterListener
     *
     * @param place Place
     */
    @Override
    public void onItemClick(Place place) {
        if (getBaseActivity() != null) {
            startActivityWithAnimation(PlaceActivity.newIntent(getBaseActivity(), selectedTaluk, place));
        }
    }
}
