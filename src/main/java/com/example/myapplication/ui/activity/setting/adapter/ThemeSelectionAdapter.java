package com.example.myapplication.ui.activity.setting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.LayoutThemeListItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.ScreenUtils;

import java.util.List;

public class ThemeSelectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<AppTheme> themeList;
    private int selectedTheme;
    private ThemeSelectionAdapterListener listener;

    public ThemeSelectionAdapter(int selectedTheme, ThemeSelectionAdapterListener listener) {
        this.selectedTheme = selectedTheme;
        this.listener = listener;
        this.themeList = ScreenUtils.getThemeColors();
    }

    public interface ThemeSelectionAdapterListener {
        void updateSelectedTheme(int themeId);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutThemeListItemBinding layoutThemeListItemBinding = LayoutThemeListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ThemeSelectionViewHolder(layoutThemeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public class ThemeSelectionViewHolder extends BaseViewHolder {
        LayoutThemeListItemBinding layoutThemeListItemBinding;

        ThemeSelectionViewHolder(LayoutThemeListItemBinding layoutThemeListItemBinding) {
            super(layoutThemeListItemBinding.getRoot());
            this.layoutThemeListItemBinding = layoutThemeListItemBinding;
        }

        @Override
        public void onBind(int position) {
            AppTheme appTheme = themeList.get(position);
            layoutThemeListItemBinding.cvColor.setCardBackgroundColor(
                    layoutThemeListItemBinding.getRoot().getContext().getResources().getColor(
                            appTheme.getColor_id()));
            layoutThemeListItemBinding.ivSelected.setVisibility(View.GONE);
            if (appTheme.getId() == selectedTheme) {
                layoutThemeListItemBinding.ivSelected.setVisibility(View.VISIBLE);
            }
            layoutThemeListItemBinding.cvColor.setOnClickListener(v -> {
                if (listener != null && selectedTheme != appTheme.getId()) {
                    listener.updateSelectedTheme(appTheme.getId());
                }
            });
        }
    }
}
