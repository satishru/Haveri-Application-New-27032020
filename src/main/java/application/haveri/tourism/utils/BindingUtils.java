package application.haveri.tourism.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.List;

import application.haveri.tourism.BuildConfig;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.data.remote.ApiEndPoint;
import application.haveri.tourism.ui.fragment.common.adapter.EventsAdapter;
import application.haveri.tourism.ui.fragment.common.adapter.ImageGalleryAdapter;
import application.haveri.tourism.ui.fragment.common.adapter.PlaceAdapter;
import application.haveri.tourism.ui.fragment.common.adapter.VideoListAdapter;
import application.haveri.tourism.ui.fragment.home.adapter.event.HomeEventsAdapter;
import application.haveri.tourism.ui.fragment.home.adapter.gallery.HomeImageGalleryAdapter;
import application.haveri.tourism.ui.fragment.home.adapter.place.HomePlaceAdapter;
import application.haveri.tourism.ui.fragment.home.adapter.taluk.HomeTalukAdapter;
import application.haveri.tourism.ui.fragment.taluk.taluk_list.adapter.TalukListAdapter;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addTalukItems(RecyclerView recyclerView, List<Taluk> talukList) {
        if (recyclerView.getAdapter() instanceof HomeTalukAdapter) {
            HomeTalukAdapter adapter = (HomeTalukAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(talukList);
            }
        } else if (recyclerView.getAdapter() instanceof TalukListAdapter) {
            TalukListAdapter adapter = (TalukListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(talukList);
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void addImageGalleryItems(RecyclerView recyclerView, List<Images> imagesList) {
        if (recyclerView.getAdapter() instanceof HomeImageGalleryAdapter) {
            HomeImageGalleryAdapter adapter = (HomeImageGalleryAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(imagesList);
            }
        } else if (recyclerView.getAdapter() instanceof ImageGalleryAdapter) {
            ImageGalleryAdapter adapter = (ImageGalleryAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(imagesList);
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void addVideoGalleryItems(RecyclerView recyclerView, List<Videos> videosList) {
        if (recyclerView.getAdapter() instanceof VideoListAdapter) {
            VideoListAdapter adapter = (VideoListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(videosList);
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void addPlaceItems(RecyclerView recyclerView, List<Place> placeList) {
        if (recyclerView.getAdapter() instanceof HomePlaceAdapter) {
            HomePlaceAdapter adapter = (HomePlaceAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(placeList);
            }
        } else if (recyclerView.getAdapter() instanceof PlaceAdapter) {
            PlaceAdapter adapter = (PlaceAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(placeList);
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void addEventItems(RecyclerView recyclerView, List<Event> eventList) {
        if (recyclerView.getAdapter() instanceof EventsAdapter) {
            EventsAdapter adapter = (EventsAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(eventList);
            }
        } else if (recyclerView.getAdapter() instanceof HomeEventsAdapter) {
            HomeEventsAdapter adapter = (HomeEventsAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(eventList);
            }
        }
    }

    /**
     * Using androidx.swiperefreshlayout:swiperefreshlayout
     *
     * @param context Context
     * @return CircularProgressDrawable
     */
    private static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(2f);
        circularProgressDrawable.setCenterRadius(40f);
        //circularProgressDrawable.setColorSchemeColors(R.color.color_white);
        circularProgressDrawable.start();
        return circularProgressDrawable;
        //return context.getDrawable(R.drawable.ic_image_place_holder);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrlSafeLoad(ImageView imageView, List<Taluk> talukList) {
        Taluk taluk = null;
        if (talukList != null && talukList.size() > 0) {
            taluk = talukList.get(0);
        }
        if (taluk != null && taluk.getPlaces() != null && taluk.getPlaces().size() > 0 && taluk.getPlaces().get(
                0).getMediaGallery().getImagesData().size() > 0) {
            String url = taluk.getPlaces().get(0).getMediaGallery().getImagesData().get(
                    0).getImageUrl();
            Glide.with(imageView.getContext()).
                    load(url).
                    placeholder(getProgressDrawable(imageView.getContext())).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (!CommonUtils.checkNullOrEmpty(url)) {
            Glide.with(imageView.getContext()).
                    load(url).
                    placeholder(getProgressDrawable(imageView.getContext())).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter("imageFullUrl")
    public static void setImageFullUrl(ImageView imageView, String url) {
        if (!CommonUtils.checkNullOrEmpty(url)) {
            Glide.with(imageView.getContext()).
                    load(url).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter("imageSplashUrl")
    public static void imageSplashUrl(ImageView imageView, String url) {
        if (!CommonUtils.checkNullOrEmpty(url) && imageView.getContext() != null) {
            try {
                Glide.with(imageView.getContext()).
                        load(url).
                        into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @BindingAdapter("imageYouTubeUrl")
    public static void setYouTubeImageUrl(ImageView imageView, String videoId) {
        if (!CommonUtils.checkNullOrEmpty(videoId)) {
            Glide.with(imageView.getContext()).
                    load(String.format(ApiEndPoint.YOUTUBE_THUMB_URL, videoId)).
                    placeholder(getProgressDrawable(imageView.getContext())).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter("imageCropUrl")
    public static void setCropImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).
                load(url).
                placeholder(getProgressDrawable(imageView.getContext())).
                override(100, 80).
                animate(R.anim.fade_in).
                into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, Taluk taluk) {
        if (taluk != null && taluk.getPlaces().size() > 0 && taluk.getPlaces().get(
                0).getMediaGallery() != null && taluk.getPlaces().get(
                0).getMediaGallery().getImagesData().size() > 0) {
            String url = taluk.getPlaces().get(0).getMediaGallery().getImagesData().get(
                    0).getImageUrl();
            Glide.with(imageView.getContext()).
                    load(url).
                    placeholder(getProgressDrawable(imageView.getContext())).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, Place place) {
        if (place != null && place.getMediaGallery() != null && place.getMediaGallery().getImagesData().size() > 0) {
            String url = place.getMediaGallery().getImagesData().get(0).getImageUrl();
            Glide.with(imageView.getContext()).
                    load(url).
                    placeholder(getProgressDrawable(imageView.getContext())).
                    animate(R.anim.fade_in).
                    into(imageView);
        }
    }

    @BindingAdapter({"android:drawableEnd"})
    public static void setDrawableLeft(TextView textView, int selectedLanguage) {
        Drawable drawable = null;
        switch (textView.getId()) {
            case R.id.tv_language_english:
                if (selectedLanguage == Language.EN.getValue()) {
                    drawable = ContextCompat.getDrawable(textView.getContext(),
                            R.drawable.ic_selected);
                }
                break;
            case R.id.tv_language_kannada:
                if (selectedLanguage == Language.KN.getValue()) {
                    drawable = ContextCompat.getDrawable(textView.getContext(),
                            R.drawable.ic_selected);
                }
                break;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    @BindingAdapter({"setImageText", "selectedLanguage"})
    public static void setImageText(TextView textView, Images image, int selectedLanguage) {
        textView.setText((selectedLanguage == Language.EN.getValue()) ?
                image.getImageTitleEn() :
                image.getImageTitleKn());
    }

    @BindingAdapter("setVersionText")
    public static void setVersionText(TextView textView, int selectedLanguage) {
        textView.setText((selectedLanguage == Language.EN.getValue()) ?
                "Version "+BuildConfig.VERSION_NAME :
                "ವರ್ಷನ್  "+BuildConfig.VERSION_NAME);
    }
}
