package application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.databinding.FragmentTalukImageGalleryBinding;
import application.haveri.tourism.ui.activity.media.image.ImageViewActivity;
import application.haveri.tourism.ui.base.BaseFragment;
import application.haveri.tourism.ui.fragment.common.adapter.ImageGalleryAdapter;
import application.haveri.tourism.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukGalleryFragment extends BaseFragment<FragmentTalukImageGalleryBinding, TalukGalleryFragmentViewModel> implements
        iTalukGalleryFragmentContract.iTalukGalleryFragmentNavigator,
        ImageGalleryAdapter.ImageGalleryAdapterListener {

    @Inject
    ImageGalleryAdapter imageGalleryAdapter;

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
        imageGalleryAdapter.setListener(this);
        fragmentTalukImageGalleryBinding.rvImageList.setLayoutManager(gridLayoutManager.get());
        fragmentTalukImageGalleryBinding.rvImageList.setItemAnimator(new DefaultItemAnimator());
        //fragmentTalukImageGalleryBinding.rvImageList.addItemDecoration(getHorizontalDivider());
        fragmentTalukImageGalleryBinding.rvImageList.setAdapter(imageGalleryAdapter);
        fragmentTalukImageGalleryBinding.rvImageList.setNestedScrollingEnabled(true);
    }

    /**
     * ImageGalleryAdapter.ImageGalleryAdapterListener
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
