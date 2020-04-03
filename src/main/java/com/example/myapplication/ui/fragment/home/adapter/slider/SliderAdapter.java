package com.example.myapplication.ui.fragment.home.adapter.slider;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.databinding.LayoutImageSliderItemBinding;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private List<Images> mSliderItems;
    private static final int MAX_SIZE = 10;

    public SliderAdapter(List<Images> sliderItems) {
        this.mSliderItems = sliderItems;
    }

    public void addItems(List<Images> imagesList) {
        if (imagesList != null) {
            clearItems();
            List<Images> subImageList = CommonUtils.mockList(imagesList,
                    AppConstants.MOCK_LIST_SIZE);
            if (subImageList.size() > MAX_SIZE) {
                mSliderItems.addAll(subImageList.subList(0, MAX_SIZE));
            } else {
                mSliderItems.addAll(subImageList);
            }
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        mSliderItems.clear();
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutImageSliderItemBinding imageSliderItemBinding =
                LayoutImageSliderItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new SliderAdapterViewHolder(imageSliderItemBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
        viewHolder.bindData(position);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        private LayoutImageSliderItemBinding imageSliderItemBinding;

        SliderAdapterViewHolder(LayoutImageSliderItemBinding imageSliderItemBinding) {
            super(imageSliderItemBinding.getRoot());
            this.imageSliderItemBinding = imageSliderItemBinding;
        }

        void bindData(final int position) {
            imageSliderItemBinding.setImageObj(mSliderItems.get(position));
        }
    }
}
