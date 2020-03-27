package com.example.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.Settings;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;

import java.util.Locale;

public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void setLocale(int selectedLang) {
        Locale myLocale = new Locale(Language.valueOf(selectedLang).getName());
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        HaveriApplication.getInstance().getResources().updateConfiguration(config,
                HaveriApplication.getInstance().getResources().getDisplayMetrics());
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }
}
