package com.example.myapplication.ui.base;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int lastPosition = -1;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);

    protected void setAnimation(int position, View viewToAnimate, boolean repeatAnimationOnScroll, int animation_resource) {
        if (viewToAnimate != null && position > lastPosition && animation_resource > 0) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), animation_resource);
            viewToAnimate.startAnimation(animation);
            if (!repeatAnimationOnScroll)
                lastPosition = position;
        }
    }

    protected int getLanguage(ViewDataBinding viewDataBinding) {
        return ((BaseActivity)viewDataBinding.getRoot().getContext()).getViewModel().getDataManager().getSelectedLanguage();
    }
}
