package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.databinding.LayoutTalukPlaceItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class TalukPlaceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Place> placeList;
    private TalukPlaceAdapterListener talukPlaceAdapterListener;

    public interface TalukPlaceAdapterListener {
        void onPlaceClick(Place place);
    }

    public TalukPlaceAdapter(List<Place> placeList) {
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

    public void setListener(TalukPlaceAdapterListener talukPlaceAdapterListener) {
        this.talukPlaceAdapterListener = talukPlaceAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTalukPlaceItemBinding homePlaceItemBinding = LayoutTalukPlaceItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TalukPlaceAdapterViewHolder(homePlaceItemBinding);
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
                ((TalukPlaceAdapterViewHolder) holder).layoutTalukPlaceItemBinding.ivPlace);
    }

    public class TalukPlaceAdapterViewHolder extends BaseViewHolder implements
            TalukPlaceAdapterViewModel.TalukPlaceAdapterViewModelListener {

        LayoutTalukPlaceItemBinding layoutTalukPlaceItemBinding;

        TalukPlaceAdapterViewHolder(LayoutTalukPlaceItemBinding layoutTalukPlaceItemBinding) {
            super(layoutTalukPlaceItemBinding.getRoot());
            this.layoutTalukPlaceItemBinding = layoutTalukPlaceItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Place place = placeList.get(position);
            TalukPlaceAdapterViewModel viewModel = new TalukPlaceAdapterViewModel(
                    place, getLanguage(layoutTalukPlaceItemBinding), this);
            layoutTalukPlaceItemBinding.setViewModel(viewModel);
            layoutTalukPlaceItemBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(Place place) {
            if (talukPlaceAdapterListener != null) {
                talukPlaceAdapterListener.onPlaceClick(place);
            }
        }
    }
}
