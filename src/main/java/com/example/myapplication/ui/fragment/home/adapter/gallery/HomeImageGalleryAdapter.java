package com.example.myapplication.ui.fragment.home.adapter.gallery;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.databinding.LayoutHomeImageGalleryItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeImageGalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Images> totalImagesList;
    private List<Images> imagesList;
    private HomeImageGalleryAdapterListener homeImageGalleryAdapterListener;

    public interface HomeImageGalleryAdapterListener {
        void onItemClick(int position);
    }

    public HomeImageGalleryAdapter(List<Images> imagesList) {
        this.totalImagesList = new ArrayList<>();
        this.imagesList = imagesList;
        setList(imagesList);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHomeImageGalleryItemBinding layoutHomeImageGalleryItemBinding =
                LayoutHomeImageGalleryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new HomeImageGalleryViewHolder(layoutHomeImageGalleryItemBinding);
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
                ((HomeImageGalleryViewHolder) holder).layoutHomeImageGalleryItemBinding.ivImage);
    }

    public void addItems(List<Images> imagesList) {
        if (imagesList != null) {
            setList(CommonUtils.mockList(imagesList, 30));
            this.notifyDataSetChanged();
        }
    }

    private void setList(List<Images> list) {
        if (list != null) {
            totalImagesList.clear();
            totalImagesList.addAll(list);
            clearItems();
            if (list.size() > 20) {
                imagesList.addAll(list.subList(0, 19));
            } else {
                imagesList.addAll(list);
            }
        }
    }

    public void clearItems() {
        imagesList.clear();
    }

    public List<Images> getTotalImagesList() {
        return totalImagesList;
    }

    public void setListener(HomeImageGalleryAdapterListener homeImageGalleryAdapterListener) {
        this.homeImageGalleryAdapterListener = homeImageGalleryAdapterListener;
    }

    public class HomeImageGalleryViewHolder extends BaseViewHolder implements
            HomeImageGalleryAdapterViewModel.HomeImageGalleryAdapterViewModelListener {

        LayoutHomeImageGalleryItemBinding layoutHomeImageGalleryItemBinding;

        HomeImageGalleryViewHolder(LayoutHomeImageGalleryItemBinding layoutHomeImageGalleryItemBinding) {
            super(layoutHomeImageGalleryItemBinding.getRoot());
            this.layoutHomeImageGalleryItemBinding = layoutHomeImageGalleryItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Images image = imagesList.get(position);
            HomeImageGalleryAdapterViewModel viewModel = new HomeImageGalleryAdapterViewModel(image,
                    position, getLanguage(layoutHomeImageGalleryItemBinding), this);
            layoutHomeImageGalleryItemBinding.setViewModel(viewModel);
            layoutHomeImageGalleryItemBinding.tvPlusCount.setVisibility(View.GONE);
            if ((position + 1 == imagesList.size()) && !TextUtils.isEmpty(
                    viewModel.getRemainingCount(totalImagesList.size(), imagesList.size()))) {
                layoutHomeImageGalleryItemBinding.tvPlusCount.setVisibility(View.VISIBLE);
                layoutHomeImageGalleryItemBinding.tvPlusCount.setText(
                        viewModel.getRemainingCount(totalImagesList.size(), imagesList.size()));
            }
            layoutHomeImageGalleryItemBinding.executePendingBindings();
            setAnimation(position, layoutHomeImageGalleryItemBinding.rlContent, false,
                    R.anim.slide_from_right);
        }

        @Override
        public void onItemClick(int position) {
            if (homeImageGalleryAdapterListener != null) {
                homeImageGalleryAdapterListener.onItemClick(position);
            }
        }
    }
}
