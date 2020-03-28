package com.example.myapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class ViewUtils {

    private ViewUtils() {
        // This class is not publicly instantiable
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorProgressDialog_Default),
                    PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static float getMarker(int theme) {
        switch (theme) {
            default:
            case ScreenUtils.THEME_ID_DarkPurple:
                return BitmapDescriptorFactory.HUE_MAGENTA;
            case ScreenUtils.THEME_ID_Dark:
            case ScreenUtils.THEME_ID_Tile:
                return BitmapDescriptorFactory.HUE_CYAN;
            case ScreenUtils.THEME_ID_Red:
                return BitmapDescriptorFactory.HUE_RED;
            case ScreenUtils.THEME_ID_AmberYellow:
                return BitmapDescriptorFactory.HUE_YELLOW;
            case ScreenUtils.THEME_ID_DeepBlue:
                return BitmapDescriptorFactory.HUE_BLUE;
        }
    }
}
