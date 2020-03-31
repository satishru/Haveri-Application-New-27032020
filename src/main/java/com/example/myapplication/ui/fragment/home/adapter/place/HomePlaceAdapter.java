package com.example.myapplication.ui.fragment.home.adapter.place;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.LayoutHomePlaceItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;


public class HomePlaceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Place> placeList;
    private HomePlaceAdapterListener homePlaceAdapterListener;

    public interface HomePlaceAdapterListener {
        void onPlaceClick(Place place);
    }

    public HomePlaceAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    public void addItems(List<Place> placeList) {
        if (placeList != null) {
            this.placeList.addAll(CommonUtils.mockList(placeList, AppConstants.MOCK_LIST_SIZE));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        placeList.clear();
    }

    public void setListener(HomePlaceAdapterListener homePlaceAdapterListener) {
        this.homePlaceAdapterListener = homePlaceAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHomePlaceItemBinding homePlaceItemBinding = LayoutHomePlaceItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new HomePlaceAdapterViewHolder(homePlaceItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        Glide.clear(
                ((HomePlaceAdapterViewHolder) holder).homePlaceItemBinding.ivPlace);
    }

    public class HomePlaceAdapterViewHolder extends BaseViewHolder implements
            HomePlaceAdapterViewModel.HomePlaceAdapterViewModelListener {

        LayoutHomePlaceItemBinding homePlaceItemBinding;

        HomePlaceAdapterViewHolder(LayoutHomePlaceItemBinding homePlaceItemBinding) {
            super(homePlaceItemBinding.getRoot());
            this.homePlaceItemBinding = homePlaceItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Place place = placeList.get(position);
            HomePlaceAdapterViewModel viewModel = new HomePlaceAdapterViewModel(
                    place, getLanguage(homePlaceItemBinding), this);
            homePlaceItemBinding.setViewModel(viewModel);
            homePlaceItemBinding.executePendingBindings();
            homePlaceItemBinding.cardContent.setOnClickListener(v -> onItemClick(place));
        }

        @Override
        public void onItemClick(Place place) {
            if (homePlaceAdapterListener != null) {
                homePlaceAdapterListener.onPlaceClick(place);
            }
        }
    }
}