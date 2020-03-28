package com.example.myapplication.ui.fragment.place.place_list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.FragmentPlaceListBinding;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.fragment.place.place_list.adapter.PlaceAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class PlaceListFragment extends BaseFragment<FragmentPlaceListBinding, PlaceListFragmentViewModel> implements
        iPlaceListFragmentContract.iPlaceListFragmentNavigator,
        PlaceAdapter.PlaceAdapterListener {

    @Inject
    PlaceAdapter placeAdapter;

    @Inject
    Provider<LinearLayoutManager> mLayoutManager;

    private FragmentPlaceListBinding fragmentPlaceListBinding;
    private PlaceListFragmentViewModel placeListFragmentViewModel;
    private PlaceListFragmentCallBack placeListFragmentCallBack;

    public interface PlaceListFragmentCallBack {
        List<Place> getPlaceList();

        void openPlaceDetailFragment(Place place);

        void hidePopupDataTitle();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        placeListFragmentCallBack = (PlaceListFragmentCallBack) context;
    }

    public static PlaceListFragment newInstance() {
        Bundle args = new Bundle();
        PlaceListFragment fragment = new PlaceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_place_list;
    }

    @Override
    public PlaceListFragmentViewModel getViewModel() {
        placeListFragmentViewModel = new ViewModelProvider(this, factory).get(
                PlaceListFragmentViewModel.class);
        return placeListFragmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPlaceListBinding = getViewDataBinding();
        placeListFragmentViewModel.setNavigator(this);
        setPlaceListAdapter();
        if (placeListFragmentCallBack != null) {
            placeListFragmentViewModel.startLoadingData(placeListFragmentCallBack.getPlaceList());
        }
    }

    private void setPlaceListAdapter() {
        placeAdapter.setListener(this);
        fragmentPlaceListBinding.rvPlaceList.setLayoutManager(mLayoutManager.get());
        fragmentPlaceListBinding.rvPlaceList.setItemAnimator(new DefaultItemAnimator());
        fragmentPlaceListBinding.rvPlaceList.addItemDecoration(getVerticalDivider());
        fragmentPlaceListBinding.rvPlaceList.setAdapter(placeAdapter);
    }

    /**
     * PlaceAdapter.PlaceListAdapterListener
     *
     * @param place Place
     */
    @Override
    public void onItemClick(Place place) {
        if (placeListFragmentCallBack != null) {
            placeListFragmentCallBack.openPlaceDetailFragment(place);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getBaseActivity() != null) {
            if (placeListFragmentCallBack != null) {
                placeListFragmentCallBack.hidePopupDataTitle();
            }
            getBaseActivity().setToolBarTitle(getString(R.string.title_activity_place));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPlaceListBinding.rvPlaceList.setAdapter(null);
    }
}
