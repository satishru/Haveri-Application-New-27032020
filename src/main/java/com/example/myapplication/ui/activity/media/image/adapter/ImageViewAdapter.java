package com.example.myapplication.ui.activity.media.image.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.databinding.LayoutFullscreenImageBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageViewAdapter extends PagerAdapter {

    private int selectedLanguage;
    private List<Images> imagesList;

    public ImageViewAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    public void addItems(int selectedLanguage, List<Images> imagesList) {
        this.selectedLanguage = selectedLanguage;
        this.imagesList = imagesList;
        this.notifyDataSetChanged();
    }

    public void clearItems() {
        imagesList.clear();
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        LayoutFullscreenImageBinding fullscreenImageBinding = LayoutFullscreenImageBinding.inflate(
                LayoutInflater.from(container.getContext()), container, false);
        fullscreenImageBinding.setSelectedLanguage(selectedLanguage);
        fullscreenImageBinding.setImageObj(imagesList.get(position));
        container.addView(fullscreenImageBinding.getRoot());
        return fullscreenImageBinding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }
}
