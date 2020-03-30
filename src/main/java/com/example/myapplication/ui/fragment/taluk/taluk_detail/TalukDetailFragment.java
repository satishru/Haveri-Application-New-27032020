package com.example.myapplication.ui.fragment.taluk.taluk_detail;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukDetailBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukDetailPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class TalukDetailFragment extends BaseFragment<FragmentTalukDetailBinding, TalukDetailFragmentViewModel> implements
        iTalukDetailFragmentContract.iTalukDetailFragmentNavigator {

    private FragmentTalukDetailBinding fragmentTalukDetailBinding;
    private TalukDetailFragmentViewModel talukDetailFragmentViewModel;
    private TalukDetailFragmentCallBack talukDetailFragmentCallBack;
    private Taluk selectedTaluk;
    private District district;

    public interface TalukDetailFragmentCallBack {
        District getDistrict();
        Taluk getSelectedTaluk();
        void setPopupDataTitle();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        talukDetailFragmentCallBack = (TalukDetailFragmentCallBack) context;
    }

    public static TalukDetailFragment newInstance() {
        Bundle args = new Bundle();
        TalukDetailFragment fragment = new TalukDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_taluk_detail;
    }

    @Override
    public TalukDetailFragmentViewModel getViewModel() {
        talukDetailFragmentViewModel = new ViewModelProvider(this, factory).get(
                TalukDetailFragmentViewModel.class);
        return talukDetailFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTalukDetailBinding = getViewDataBinding();
        loadData();
    }

    public void refreshFragment() {
        loadData();
    }

    private void loadData() {
        if (talukDetailFragmentCallBack != null) {
            selectedTaluk = talukDetailFragmentCallBack.getSelectedTaluk();
            district = talukDetailFragmentCallBack.getDistrict();
            setToolBarTitle();
            talukDetailFragmentCallBack.setPopupDataTitle();
        }
        if (selectedTaluk != null) {
            talukDetailFragmentViewModel.setNavigator(this);
            talukDetailFragmentViewModel.setImageUrl(selectedTaluk);
            setViewPager();
        }
    }

    private void setToolBarTitle() {
        if(getBaseActivity() != null) {
            getBaseActivity().setToolBarTitle("");
        }
    }

    private void setViewPager() {
        final TalukDetailPagerAdapter adapter = new TalukDetailPagerAdapter(
                getChildFragmentManager(),
                fragmentTalukDetailBinding.tabLayout.getTabCount(), selectedTaluk, district);
        fragmentTalukDetailBinding.pagerDetail.setAdapter(adapter);
        fragmentTalukDetailBinding.pagerDetail.setOffscreenPageLimit(
                TalukDetailPagerAdapter.TAB_COUNT);

        fragmentTalukDetailBinding.pagerDetail.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(
                        fragmentTalukDetailBinding.tabLayout));
        fragmentTalukDetailBinding.tabLayout.setupWithViewPager(
                fragmentTalukDetailBinding.pagerDetail);
        fragmentTalukDetailBinding.tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        fragmentTalukDetailBinding.pagerDetail.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }
}
