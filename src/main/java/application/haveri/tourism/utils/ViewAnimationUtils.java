package application.haveri.tourism.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import application.haveri.tourism.R;

public final class ViewAnimationUtils {

    public final static int ROTATE_360 = 360;

    public final static int SCALE_DURATION_100 = 100;
    public final static int SCALE_DURATION_200 = 200;
    public final static int SCALE_DURATION_300 = 300;


    private ViewAnimationUtils() {
        // This class is not publicly instantiable
    }

    public static void scaleAnimateView(View view, int duration) {
        if(view != null) {
            ScaleAnimation animation =
                    new ScaleAnimation(
                            1.15f, 1, 1.15f, 1,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);

            view.setAnimation(animation);
            animation.setDuration(duration);
            animation.start();
        }
    }

    public static void rotate(View view, float rotation) {
        if(view != null) {
            view.setRotation(rotation);
        }
    }

    /**
     * Animates a view so that it slides in from the left of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_from_left);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the left.
     *
     * @param context
     * @param view
     */
    public static void slideOutToLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_to_left);
    }

    /**
     * Animates a view so that it slides in the from the right of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_from_right);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the right.
     *
     * @param context
     * @param view
     */
    public static void slideOutToRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_to_right);
    }

    /**
     * Runs a simple animation on a View with no extra parameters.
     *
     * @param context
     * @param view
     * @param animationId
     */
    private static void runSimpleAnimation(Context context, View view, int animationId) {
        view.startAnimation(AnimationUtils.loadAnimation(
                context, animationId
        ));
    }
}
