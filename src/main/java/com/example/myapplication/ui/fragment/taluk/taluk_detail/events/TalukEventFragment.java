package com.example.myapplication.ui.fragment.taluk.taluk_detail.events;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukEventBinding;
import com.example.myapplication.ui.activity.event.EventDetailActivity;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukEventsAdapter;
import com.example.myapplication.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukEventFragment extends BaseFragment<FragmentTalukEventBinding, TalukEventFragmentViewModel> implements
        iTalukEventFragmentContract.iTalukEventFragmentNavigator,
        TalukEventsAdapter.TalukEventsAdapterListener {

    @Inject
    TalukEventsAdapter talukEventsAdapter;

    @Inject
    Provider<LinearLayoutManager> layoutManager;

    private FragmentTalukEventBinding fragmentTalukEventBinding;
    private TalukEventFragmentViewModel talukEventFragmentViewModel;
    private Taluk selectedTaluk;
    private District district;

    public static TalukEventFragment newInstance(Taluk selectedTaluk, District district) {
        Bundle args = new Bundle();
        TalukEventFragment fragment = new TalukEventFragment();
        args.putSerializable(AppConstants.INTENT_SELECTED_TALUK, selectedTaluk);
        args.putSerializable(AppConstants.INTENT_HAVERI_DATA, district);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_taluk_event;
    }

    @Override
    public TalukEventFragmentViewModel getViewModel() {
        talukEventFragmentViewModel = new ViewModelProvider(this, factory).get(
                TalukEventFragmentViewModel.class);
        return talukEventFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTalukEventBinding = getViewDataBinding();
        talukEventFragmentViewModel.setNavigator(this);
        getBundleData();
        if (selectedTaluk != null) {
            talukEventFragmentViewModel.setEventData(selectedTaluk);
        }
        setEventAdapter();
    }

    private void getBundleData() {
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
        }
        if (getArguments() != null && getArguments().containsKey(
                AppConstants.INTENT_HAVERI_DATA)) {
            district = (District) getArguments().getSerializable(
                    AppConstants.INTENT_HAVERI_DATA);
        }
    }

    private void setEventAdapter() {
        talukEventsAdapter.setListener(this);
        fragmentTalukEventBinding.rvEventList.setLayoutManager(layoutManager.get());
        fragmentTalukEventBinding.rvEventList.setItemAnimator(new DefaultItemAnimator());
        fragmentTalukEventBinding.rvEventList.addItemDecoration(getHorizontalDivider());
        fragmentTalukEventBinding.rvEventList.setAdapter(talukEventsAdapter);
        fragmentTalukEventBinding.rvEventList.setNestedScrollingEnabled(true);
    }

    @Override
    public void onEventClick(Event event) {
        startActivityWithAnimation(
                EventDetailActivity.newIntent(getBaseActivity(), district, event));
    }
}