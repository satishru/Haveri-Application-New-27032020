package com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukImageGalleryBinding;
import com.example.myapplication.ui.activity.media.image.ImageViewActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukImageGalleryAdapter;
import com.example.myapplication.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukGalleryFragment extends BaseFragment<FragmentTalukImageGalleryBinding, TalukGalleryFragmentViewModel> implements
        iTalukGalleryFragmentContract.iTalukGalleryFragmentNavigator,
        TalukImageGalleryAdapter.TalukImageGalleryAdapterListener {

    @Inject
    TalukImageGalleryAdapter talukImageGalleryAdapter;

    @Inject
    Provider<GridLayoutManager> gridLayoutManager;

    private FragmentTalukImageGalleryBinding fragmentTalukImageGalleryBinding;
    private TalukGalleryFragmentViewModel talukGalleryFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukGalleryFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukGalleryFragment fragment = new TalukGalleryFragment();
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
        return R.layout.fragment_taluk_image_gallery;
    }

    @Override
    public TalukGalleryFragmentViewModel getViewModel() {
        talukGalleryFragmentViewModel = new ViewModelProvider(this, factory).get(
                TalukGalleryFragmentViewModel.class);
        return talukGalleryFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTalukImageGalleryBinding = getViewDataBinding();
        talukGalleryFragmentViewModel.setNavigator(this);
        getBundleData();
        if (selectedTaluk != null) {
            talukGalleryFragmentViewModel.setImageData(selectedTaluk);
        }
        setImageGalleryAdapter();
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
        }
    }

    private void setImageGalleryAdapter() {
        talukImageGalleryAdapter.setListener(this);
        fragmentTalukImageGalleryBinding.rvImageList.setLayoutManager(gridLayoutManager.get());
        fragmentTalukImageGalleryBinding.rvImageList.setItemAnimator(new DefaultItemAnimator());
        //fragmentTalukImageGalleryBinding.rvImageList.addItemDecoration(getHorizontalDivider());
        fragmentTalukImageGalleryBinding.rvImageList.setAdapter(talukImageGalleryAdapter);
        fragmentTalukImageGalleryBinding.rvImageList.setNestedScrollingEnabled(true);
    }

    /**
     * TalukImageGalleryAdapter.TalukImageGalleryAdapterListener
     *
     * @param position Images position
     */
    @Override
    public void onItemClick(int position) {
        if (getBaseActivity() != null && talukGalleryFragmentViewModel.getImageList() != null &&
                talukGalleryFragmentViewModel.getImageList().getValue() != null) {
            startActivityWithAnimation(
                    ImageViewActivity.newIntent(getBaseActivity(),
                            talukGalleryFragmentViewModel.getImageList().getValue(), position));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTalukImageGalleryBinding.rvImageList.setAdapter(null);
    }
}
