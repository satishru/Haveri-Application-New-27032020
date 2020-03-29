package com.example.myapplication.ui.fragment.place.place_details.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.TestBinding;
import com.example.myapplication.utils.AppConstants;

public class PlaceAboutFragment extends Fragment {

    public static PlaceAboutFragment newInstance(Place selectedPlace) {
        Bundle args = new Bundle();
        PlaceAboutFragment fragment = new PlaceAboutFragment();
        args.putSerializable(AppConstants.INTENT_SELECTED_PLACE, selectedPlace);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TestBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.test, container,
                false);
        return dataBinding.getRoot();
    }
}
