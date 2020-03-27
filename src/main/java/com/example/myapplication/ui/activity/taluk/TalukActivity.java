package com.example.myapplication.ui.activity.taluk;

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
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.databinding.ActivityTalukBinding;
import com.example.myapplication.ui.activity.media.video.VideosExploreActivity;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.TalukDetailFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.video.TalukVideosFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_list.TalukListFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.example.myapplication.utils.AppConstants.INTENT_HAVERI_DATA;
import static com.example.myapplication.utils.AppConstants.INTENT_SELECTED_TALUK;

/**
 * To Show taluk details, places, images, videos, events
 */
public class TalukActivity extends BaseActivity<ActivityTalukBinding, TalukActivityViewModel> implements
        HasSupportFragmentInjector, iTalukActivityContract.iTalukActivityNavigator,
        TalukListFragment.TalukListFragmentCallBack,
        TalukDetailFragment.TalukDetailFragmentCallBack,
        TalukVideosFragment.TalukVideosFragmentCallBack {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityTalukBinding activityTalukBinding;
    private TalukActivityViewModel talukActivityViewModel;
    private District district;
    private Taluk selectedTaluk;
    private boolean isSingleTalukShow;

    public static Intent newIntent(Activity activity, District district, Taluk selectedTaluk) {
        Intent intent = new Intent(activity, TalukActivity.class);
        intent.putExtra(INTENT_HAVERI_DATA, district);
        if (selectedTaluk != null) {
            intent.putExtra(INTENT_SELECTED_TALUK, selectedTaluk);
        }
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_taluk;
    }

    @Override
    public TalukActivityViewModel getViewModel() {
        talukActivityViewModel = new ViewModelProvider(this, factory).get(
                TalukActivityViewModel.class);
        return talukActivityViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTalukBinding = getViewDataBinding();
        talukActivityViewModel.setNavigator(this);
        setToolBar(activityTalukBinding.layoutToolbar.toolbar, R.string.title_activity_taluk, true);
        getBundleData();
        setUpPopUp();
        talukActivityViewModel.startLoadingData();
    }

    private void getBundleData() {
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(INTENT_HAVERI_DATA)) {
                district = (District) getIntent().getSerializableExtra(
                        INTENT_HAVERI_DATA);
            }
            if (getIntent().hasExtra(INTENT_SELECTED_TALUK) && getIntent().getSerializableExtra(
                    INTENT_SELECTED_TALUK) != null) {
                selectedTaluk = (Taluk) getIntent().getSerializableExtra(INTENT_SELECTED_TALUK);
                isSingleTalukShow = true;
            }
        }
    }

    private void setUpPopUp() {
        activityTalukBinding.layoutToolbar.tvTitle.setOnClickListener(v -> {
            List<Taluk> talukList = talukActivityViewModel.getTalukList(district);
            PopupMenu popup = new PopupMenu(TalukActivity.this, v, Gravity.TOP);
            for (int i = 0; i < talukList.size(); i++) {
                popup.getMenu().add(i, i, i, (getLanguage() == languageEnglish() ?
                        talukList.get(i).getTalukNameEn() : talukList.get(i).getTalukNameKn()));
                if (selectedTaluk.getTalukId().equals(talukList.get(i).getTalukId())) {
                    popup.getMenu().setGroupCheckable(i, true, true);
                    popup.getMenu().getItem(i).setChecked(true);
                }
            }
            popup.setOnMenuItemClickListener(item -> {
                selectedTaluk = talukList.get(item.getOrder());
                Fragment fragment = getSupportFragmentManager().findFragmentById(
                        R.id.fragment_container);
                if (fragment instanceof TalukDetailFragment) {
                    ((TalukDetailFragment) fragment).refreshFragment();
                }
                return true;
            });
            popup.show();
        });
    }

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
        //super.onBackPressed();
        exitActivityWithAnimation();
    }

    /**
     * iTalukActivityContract.iTalukActivityNavigator CallBacks
     */
    @Override
    public boolean isSingleTalukShow() {
        return isSingleTalukShow;
    }

    @Override
    public void openTalukDetailFragment(Taluk taluk) {
        this.selectedTaluk = taluk;
        loadTalukDetailFragment();
    }

    @Override
    public void loadTalukListFragment() {
        loadFragment(TalukListFragment.newInstance(),
                activityTalukBinding.fragmentContainer.getId(), true, true);
    }

    @Override
    public void loadTalukDetailFragment() {
        loadFragment(TalukDetailFragment.newInstance(),
                activityTalukBinding.fragmentContainer.getId(), true, true);
    }
    /* iTalukActivityContract.iTalukActivityNavigator CallBacks Ends */

    @Override
    public void hidePopupDataTitle() {
        activityTalukBinding.layoutToolbar.tvTitle.setVisibility(View.GONE);
    }

    /**
     * TalukListFragmentCallBack and TalukDetailFragmentCallBack
     */
    @Override
    public District getDistrict() {
        return district;
    }

    @Override
    public Taluk getSelectedTaluk() {
        return selectedTaluk;
    }

    @Override
    public void setPopupDataTitle() {
        activityTalukBinding.layoutToolbar.tvTitle.setVisibility(View.VISIBLE);
        activityTalukBinding.layoutToolbar.tvTitle.setText(
                getLanguage() == languageEnglish() ? selectedTaluk.getTalukNameEn() :
                        selectedTaluk.getTalukNameKn());
    }
    /* TalukListFragmentCallBack and TalukDetailFragmentCallBack Ends */

    /**
     * TalukVideosFragment.TalukVideosFragmentCallBack
     * @param selectedVideo SelectedVideo
     */
    @Override
    public void openVideoSingleActivity(Videos selectedVideo) {
        if (isDistrictNotNull()) {
            startActivityWithAnimation(
                    VideosExploreActivity.newIntent(this, selectedVideo));
        }
    }
}
