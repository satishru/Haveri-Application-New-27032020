package com.example.myapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.setting.adapter.AppTheme;

import java.util.ArrayList;
import java.util.List;

public final class ScreenUtils {

    public static final int THEME_ID_DarkPurple  = 1; //Default
    public static final int THEME_ID_Tile        = 2;
    public static final int THEME_ID_Dark        = 3;
    public static final int THEME_ID_Red         = 4;
    public static final int THEME_ID_AmberYellow = 5;
    public static final int THEME_ID_DeepBlue    = 6;

    private ScreenUtils() {
        // This class is not publicly instantiable
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static List<AppTheme> getThemeColors() {
        List<AppTheme> themeList = new ArrayList<>();

        AppTheme appTheme;

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_DarkPurple);
        appTheme.setColor_id(R.color.colorPrimary_DarkPurple);
        appTheme.setColor_name("DarkPurple");
        themeList.add(appTheme);

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_Tile);
        appTheme.setColor_id(R.color.colorPrimary_Default);
        appTheme.setColor_name("Default");
        themeList.add(appTheme);

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_Dark);
        appTheme.setColor_id(R.color.colorPrimary_Dark);
        appTheme.setColor_name("Dark");
        themeList.add(appTheme);

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_Red);
        appTheme.setColor_id(R.color.colorPrimary_Red);
        appTheme.setColor_name("Red");
        themeList.add(appTheme);

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_AmberYellow);
        appTheme.setColor_id(R.color.colorPrimary_AmberYellow);
        appTheme.setColor_name("AmberYellow");
        themeList.add(appTheme);

        appTheme = new AppTheme();
        appTheme.setId(THEME_ID_DeepBlue);
        appTheme.setColor_id(R.color.colorPrimary_DeepBlue);
        appTheme.setColor_name("Blue");
        themeList.add(appTheme);

        return themeList;
    }
}
