package application.haveri.tourism.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import application.haveri.tourism.R;

import timber.log.Timber;

public class CustomTextView extends AppCompatTextView {

    private final static String TAG = CustomTextView.class.getSimpleName();

    private final static String ASSET_PATH_SOURCE    = "source-sans-pro/";
    //private final static String ASSET_DEFAULT_SOURCE = "SourceSansPro-Regular.ttf";

    private final static String ASSET_PATH_ROBOTO    = "roboto/";
    private final static String ASSET_DEFAULT_ROBOTO = "Roboto-Regular.ttf";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public void setCustomFont(Context ctx, String asset) {
        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(ctx.getAssets(),getAssetPath(asset));
            setTypeface(typeface);
        } catch (Exception e) {
            Timber.e("Unable to load typeface - %s : %s", TAG, e.getMessage());
        }
    }

    private String getAssetPath(String asset) {
        if(TextUtils.isEmpty(asset)) {
            asset = ASSET_DEFAULT_ROBOTO;
        }
        if(asset.toLowerCase().startsWith("SourceSansPro".toLowerCase())) {
            return ASSET_PATH_SOURCE + asset;
        } else {
            return ASSET_PATH_ROBOTO + asset;
        }
    }
}
