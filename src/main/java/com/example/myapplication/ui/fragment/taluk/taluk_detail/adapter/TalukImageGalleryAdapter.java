package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.databinding.LayoutTalukImageGalleryItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class TalukImageGalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Images> imagesList;
    private TalukImageGalleryAdapterListener talukImageGalleryAdapterListener;

    public interface TalukImageGalleryAdapterListener {
        void onItemClick(int position);
    }

    public TalukImageGalleryAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTalukImageGalleryItemBinding imageGalleryItemBinding =
                LayoutTalukImageGalleryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new TalukImageGalleryViewHolder(imageGalleryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        Glide.clear(
                ((TalukImageGalleryViewHolder) holder).layoutTalukImageGalleryItemBinding.ivImage);
    }

    public void addItems(List<Images> imagesList) {
        if (imagesList != null) {
            this.imagesList.addAll(CommonUtils.mockList(imagesList, AppConstants.MOCK_LIST_SIZE));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        imagesList.clear();
    }

    public void setListener(TalukImageGalleryAdapterListener talukImageGalleryAdapterListener) {
        this.talukImageGalleryAdapterListener = talukImageGalleryAdapterListener;
    }

    public class TalukImageGalleryViewHolder extends BaseViewHolder implements
            TalukImageGalleryAdapterViewModel.TalukImageGalleryAdapterViewModelListener {

        LayoutTalukImageGalleryItemBinding layoutTalukImageGalleryItemBinding;

        TalukImageGalleryViewHolder(LayoutTalukImageGalleryItemBinding layoutTalukImageGalleryItemBinding) {
            super(layoutTalukImageGalleryItemBinding.getRoot());
            this.layoutTalukImageGalleryItemBinding = layoutTalukImageGalleryItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Images image = imagesList.get(position);
            TalukImageGalleryAdapterViewModel viewModel = new TalukImageGalleryAdapterViewModel(
                    position,
                    image, this,
                    getLanguage(layoutTalukImageGalleryItemBinding));
            layoutTalukImageGalleryItemBinding.setViewModel(viewModel);
            layoutTalukImageGalleryItemBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int position) {
            if (talukImageGalleryAdapterListener != null) {
                talukImageGalleryAdapterListener.onItemClick(position);
            }
        }
    }
}
