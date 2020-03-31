package com.example.myapplication.ui.fragment.place.place_details.gallery;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.FragmentPlaceImageGalleryBinding;
import com.example.myapplication.ui.activity.media.image.ImageViewActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.common.adapter.ImageGalleryAdapter;
import com.example.myapplication.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class PlaceGalleryFragment extends BaseFragment<FragmentPlaceImageGalleryBinding, PlaceGalleryFragmentViewModel> implements
        iPlaceGalleryFragmentContract.iPlaceGalleryFragmentNavigator,
        ImageGalleryAdapter.ImageGalleryAdapterListener {

    @Inject
    ImageGalleryAdapter imageGalleryAdapter;

    @Inject
    Provider<GridLayoutManager> gridLayoutManager;

    private FragmentPlaceImageGalleryBinding fragmentPlaceImageGalleryBinding;
    private PlaceGalleryFragmentViewModel placeGalleryFragmentViewModel;
    private Place selectedPlace;

    public static PlaceGalleryFragment newInstance(Place selectedPlace) {
        Bundle args = new Bundle();
        PlaceGalleryFragment fragment = new PlaceGalleryFragment();
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
        return R.layout.fragment_place_image_gallery;
    }

    @Override
    public PlaceGalleryFragmentViewModel getViewModel() {
        placeGalleryFragmentViewModel = new ViewModelProvider(this, factory).get(
                PlaceGalleryFragmentViewModel.class);
        return placeGalleryFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPlaceImageGalleryBinding = getViewDataBinding();
        placeGalleryFragmentViewModel.setNavigator(this);
        getBundleData();
        if (selectedPlace != null) {
            placeGalleryFragmentViewModel.setImageData(selectedPlace);
        }
        setImageGalleryAdapter();
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_PLACE)) {
            selectedPlace = (Place) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_PLACE);
        }
    }

    private void setImageGalleryAdapter() {
        imageGalleryAdapter.setListener(this);
        fragmentPlaceImageGalleryBinding.rvImageList.setLayoutManager(gridLayoutManager.get());
        fragmentPlaceImageGalleryBinding.rvImageList.setItemAnimator(new DefaultItemAnimator());
        fragmentPlaceImageGalleryBinding.rvImageList.setAdapter(imageGalleryAdapter);
        fragmentPlaceImageGalleryBinding.rvImageList.setNestedScrollingEnabled(true);
    }

    /**
     * ImageGalleryAdapter.ImageGalleryAdapterListener
     *
     * @param position Images position
     */
    @Override
    public void onItemClick(int position) {
        if (getBaseActivity() != null && placeGalleryFragmentViewModel.getImageList() != null &&
                placeGalleryFragmentViewModel.getImageList().getValue() != null) {
            startActivityWithAnimation(
                    ImageViewActivity.newIntent(getBaseActivity(),
                            placeGalleryFragmentViewModel.getImageList().getValue(), position));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPlaceImageGalleryBinding.rvImageList.setAdapter(null);
    }
}
