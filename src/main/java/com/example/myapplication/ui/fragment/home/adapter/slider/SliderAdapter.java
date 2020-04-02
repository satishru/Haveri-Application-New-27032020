package com.example.myapplication.ui.fragment.home.adapter.slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_image_slider_item, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
        /*Images sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.item_view.getContext())
                .load(sliderItem.getImageUrl())
                .fitCenter()
                .into(viewHolder.iv_slider_image);*/
        viewHolder.bindData(position);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View item_view;
        ImageView iv_slider_image;
        LayoutImageSliderItemBinding imageSliderItemBinding;

        SliderAdapterViewHolder(View itemView) {
            super(itemView);
            item_view = itemView;
            iv_slider_image = itemView.findViewById(R.id.iv_slider_image);
        }
        void bindData(final int position) {
            imageSliderItemBinding.setImageObj(mSliderItems.get(position));
        }
    }
}
