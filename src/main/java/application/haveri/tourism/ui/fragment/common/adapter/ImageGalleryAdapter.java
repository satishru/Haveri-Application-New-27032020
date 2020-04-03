package application.haveri.tourism.ui.fragment.common.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.databinding.LayoutImageGalleryItemBinding;
import application.haveri.tourism.ui.base.BaseViewHolder;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;

import java.util.List;

public class ImageGalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Images> imagesList;
    private ImageGalleryAdapterListener talukImageGalleryAdapterListener;

    public interface ImageGalleryAdapterListener {
        void onItemClick(int position);
    }

    public ImageGalleryAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutImageGalleryItemBinding imageGalleryItemBinding =
                LayoutImageGalleryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
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
                ((TalukImageGalleryViewHolder) holder).layoutImageGalleryItemBinding.ivImage);
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

    public void setListener(ImageGalleryAdapterListener talukImageGalleryAdapterListener) {
        this.talukImageGalleryAdapterListener = talukImageGalleryAdapterListener;
    }

    public class TalukImageGalleryViewHolder extends BaseViewHolder implements
            ImageGalleryAdapterViewModel.ImageGalleryAdapterViewModelListener {

        LayoutImageGalleryItemBinding layoutImageGalleryItemBinding;

        TalukImageGalleryViewHolder(LayoutImageGalleryItemBinding layoutImageGalleryItemBinding) {
            super(layoutImageGalleryItemBinding.getRoot());
            this.layoutImageGalleryItemBinding = layoutImageGalleryItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Images image = imagesList.get(position);
            ImageGalleryAdapterViewModel viewModel = new ImageGalleryAdapterViewModel(
                    position,
                    image, this,
                    getLanguage(layoutImageGalleryItemBinding));
            layoutImageGalleryItemBinding.setViewModel(viewModel);
            layoutImageGalleryItemBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int position) {
            if (talukImageGalleryAdapterListener != null) {
                talukImageGalleryAdapterListener.onItemClick(position);
            }
        }
    }
}
