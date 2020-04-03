package application.haveri.tourism.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.R;

import java.util.Locale;

public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void setLocale(Context context, int selectedLang) {
        Locale locale = new Locale(Language.valueOf(selectedLang).getName());
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());

        setApplicationLocale(selectedLang);
    }

    private static void setApplicationLocale(int selectedLang) {
        Locale locale = new Locale(Language.valueOf(selectedLang).getName());
        Locale.setDefault(locale);

        Resources res = HaveriApplication.getInstance().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
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
