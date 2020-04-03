package application.haveri.tourism.ui.fragment.taluk.taluk_detail.events;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.databinding.FragmentTalukEventBinding;
import application.haveri.tourism.ui.activity.event.EventDetailActivity;
import application.haveri.tourism.ui.base.BaseFragment;
import application.haveri.tourism.ui.fragment.common.adapter.EventsAdapter;
import application.haveri.tourism.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Provider;

public class TalukEventFragment extends BaseFragment<FragmentTalukEventBinding, TalukEventFragmentViewModel> implements
        iTalukEventFragmentContract.iTalukEventFragmentNavigator,
        EventsAdapter.EventsAdapterListener {

    @Inject
    EventsAdapter eventsAdapter;

    @Inject
    Provider<LinearLayoutManager> layoutManager;

    private FragmentTalukEventBinding fragmentTalukEventBinding;
    private TalukEventFragmentViewModel talukEventFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukEventFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukEventFragment fragment = new TalukEventFragment();
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
    }

    private void setEventAdapter() {
        eventsAdapter.setListener(this);
        fragmentTalukEventBinding.rvEventList.setLayoutManager(layoutManager.get());
        fragmentTalukEventBinding.rvEventList.setItemAnimator(new DefaultItemAnimator());
        fragmentTalukEventBinding.rvEventList.addItemDecoration(getHorizontalDivider());
        fragmentTalukEventBinding.rvEventList.setAdapter(eventsAdapter);
        fragmentTalukEventBinding.rvEventList.setNestedScrollingEnabled(true);
    }

    /**
     * EventsAdapter.EventsAdapterListener
     *
     * @param event Event
     */
    @Override
    public void onEventClick(Event event) {
        startActivityWithAnimation(
                EventDetailActivity.newIntent(getBaseActivity(), event));
    }
}