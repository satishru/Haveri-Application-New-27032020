package com.example.myapplication.ui.activity.media.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.databinding.ActivityImageViewBinding;
import com.example.myapplication.ui.activity.media.image.adapter.ImageViewAdapter;
import com.example.myapplication.ui.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.myapplication.utils.AppConstants.INTENT_IMAGE_LIST;
import static com.example.myapplication.utils.AppConstants.INTENT_SELECTED_IMAGE;

/**
 * To show image gallery
 */
public class ImageViewActivity extends BaseActivity<ActivityImageViewBinding, ImageViewActivityViewModel> implements
        iImageViewActivityContract.iImageViewActivityNavigator {

    @Inject
    ImageViewAdapter imageViewAdapter;

    private ActivityImageViewBinding activityImageViewBinding;
    private ImageViewActivityViewModel imageViewActivityViewModel;

    public static Intent newIntent(Activity activity, List<Images> imagesList, int selectedPosition) {
        Intent intent = new Intent(activity, ImageViewActivity.class);
        intent.putExtra(INTENT_IMAGE_LIST, (Serializable) imagesList);
        intent.putExtra(INTENT_SELECTED_IMAGE, selectedPosition);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_view;
    }

    @Override
    public ImageViewActivityViewModel getViewModel() {
        imageViewActivityViewModel = new ViewModelProvider(this, factory).get(
                ImageViewActivityViewModel.class);
        return imageViewActivityViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        activityImageViewBinding = getViewDataBinding();
        imageViewActivityViewModel.setNavigator(this);
        getBundleData();
    }

    @SuppressWarnings("unchecked")
    private void getBundleData() {
        if (getIntent() != null && getIntent().hasExtra(INTENT_IMAGE_LIST) &&
                getIntent().getSerializableExtra(INTENT_IMAGE_LIST) != null) {
            ArrayList<Images> imagesList = (ArrayList<Images>) getIntent().getSerializableExtra(
                    INTENT_IMAGE_LIST);
            if (imagesList != null && imagesList.size() > 0) {
                activityImageViewBinding.tvTotalCount.setText(
                        String.format(getString(R.string.text_separator), "" + imagesList.size()));
                setUpViewPager(imagesList);
            }
        }
    }

    private void setUpViewPager(ArrayList<Images> imagesList) {
        imageViewAdapter.clearItems();
        activityImageViewBinding.imagePager.setAdapter(imageViewAdapter);
        imageViewAdapter.addItems(imageViewActivityViewModel.getDataManager().getSelectedLanguage(),
                imagesList);
        activityImageViewBinding.imagePager.setCurrentItem(
                getIntent().getIntExtra(INTENT_SELECTED_IMAGE, 0));
        setCurrentItemText();
        activityImageViewBinding.imagePager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        setCurrentItemText();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
    }

    private void setCurrentItemText() {
        activityImageViewBinding.tvCurrentCount.setText(
                String.valueOf(activityImageViewBinding.imagePager.getCurrentItem() + 1));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityWithAnimation();
    }

    /**
     * iImageViewActivityContract.iImageViewActivityNavigator callback
     */
    @Override
    public void closeActivity() {
        exitActivityWithAnimation();
    }
}
