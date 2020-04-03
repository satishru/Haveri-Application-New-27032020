package application.haveri.tourism.ui.activity.taluk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.databinding.ActivityTalukBinding;
import application.haveri.tourism.ui.base.BaseActivity;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.TalukDetailFragment;
import application.haveri.tourism.ui.fragment.taluk.taluk_list.TalukListFragment;

import java.util.List;

import javax.inject.Inject;

import application.haveri.tourism.utils.AppConstants;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * To Show taluk details, places, images, videos, events
 */
public class TalukActivity extends BaseActivity<ActivityTalukBinding, TalukActivityViewModel> implements
        HasSupportFragmentInjector, iTalukActivityContract.iTalukActivityNavigator,
        TalukListFragment.TalukListFragmentCallBack,
        TalukDetailFragment.TalukDetailFragmentCallBack {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityTalukBinding activityTalukBinding;
    private TalukActivityViewModel talukActivityViewModel;
    private District district;
    private Taluk selectedTaluk;
    private boolean isSingleTalukShow;

    public static Intent newIntent(Activity activity, Taluk selectedTaluk) {
        Intent intent = new Intent(activity, TalukActivity.class);
        if (selectedTaluk != null) {
            intent.putExtra(AppConstants.INTENT_SELECTED_TALUK, selectedTaluk);
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
        district = getDistrictData();
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(AppConstants.INTENT_SELECTED_TALUK) && getIntent().getSerializableExtra(
                    AppConstants.INTENT_SELECTED_TALUK) != null) {
                selectedTaluk = (Taluk) getIntent().getSerializableExtra(
                        AppConstants.INTENT_SELECTED_TALUK);
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
        if (isDistrictNotNull()) {
            this.selectedTaluk = taluk;
            loadTalukDetailFragment();
        }
    }

    @Override
    public void loadTalukListFragment() {
        if (isDistrictNotNull()) {
            loadFragment(TalukListFragment.newInstance(),
                    activityTalukBinding.fragmentContainer.getId(), true, true);
        }
    }

    @Override
    public void loadTalukDetailFragment() {
        if (isDistrictNotNull()) {
            loadFragment(TalukDetailFragment.newInstance(),
                    activityTalukBinding.fragmentContainer.getId(), true, true);
        }
    }
    /* iTalukActivityContract.iTalukActivityNavigator CallBacks Ends */

    @Override
    public void hidePopupDataTitle() {
        activityTalukBinding.layoutToolbar.tvTitle.setVisibility(View.GONE);
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
}
