package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.databinding.FragmentTalukAboutBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.utils.AppConstants;

public class TalukAboutFragment extends BaseFragment<FragmentTalukAboutBinding, TalukAboutFragmentViewModel> implements
        iTalukAboutFragmentContract.iTalukAboutFragmentNavigator {

    private TalukAboutFragmentViewModel talukAboutFragmentViewModel;
    private Taluk selectedTaluk;

    public static TalukAboutFragment newInstance(Taluk selectedTaluk) {
        Bundle args = new Bundle();
        TalukAboutFragment fragment = new TalukAboutFragment();
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
        return R.layout.fragment_taluk_about;
    }

    @Override
    public TalukAboutFragmentViewModel getViewModel() {
        talukAboutFragmentViewModel = new ViewModelProvider(this, factory).get(TalukAboutFragmentViewModel.class);
        return talukAboutFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTalukAboutBinding fragmentTalukAboutBinding = getViewDataBinding();
        talukAboutFragmentViewModel.setNavigator(this);
        getBundleData();
        fragmentTalukAboutBinding.setTalukObj(selectedTaluk);
    }

    private void getBundleData() {
        if(getArguments() != null && getArguments().containsKey(AppConstants.INTENT_SELECTED_TALUK)) {
            selectedTaluk = (Taluk) getArguments().getSerializable(
                    AppConstants.INTENT_SELECTED_TALUK);
        }
    }
}
