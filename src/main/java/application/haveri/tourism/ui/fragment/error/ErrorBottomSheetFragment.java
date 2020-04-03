package application.haveri.tourism.ui.fragment.error;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import application.haveri.tourism.R;
import application.haveri.tourism.utils.AppConstants;

import application.haveri.tourism.databinding.LayoutErrorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

public class ErrorBottomSheetFragment extends BottomSheetDialogFragment {

    private ErrorBottomSheetFragmentCallBack errorBottomSheetFragmentCallBack;
    private LayoutErrorBinding mLayoutErrorBinding;
    private int drawable;
    private String errorMessage;

    public interface ErrorBottomSheetFragmentCallBack {
        void onRetryButtonClick();
    }

    public static ErrorBottomSheetFragment newInstance() {
        return new ErrorBottomSheetFragment();
    }

    public static ErrorBottomSheetFragment newInstance(int drawable, String errorMessage) {
        ErrorBottomSheetFragment fragment = new ErrorBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.INTENT_ERROR_ICON, drawable);
        bundle.putString(AppConstants.INTENT_ERROR_MESSAGE, errorMessage);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ErrorBottomSheetFragment newInstance(int drawable) {
        ErrorBottomSheetFragment fragment = new ErrorBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.INTENT_ERROR_ICON, drawable);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ErrorBottomSheetFragment newInstance(String errorMessage) {
        ErrorBottomSheetFragment fragment = new ErrorBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.INTENT_ERROR_MESSAGE, errorMessage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        errorBottomSheetFragmentCallBack = (ErrorBottomSheetFragmentCallBack) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drawable = getArguments().getInt(AppConstants.INTENT_ERROR_ICON, R.drawable.ic_error);
            errorMessage = getArguments().getString(AppConstants.INTENT_ERROR_MESSAGE,
                    getString(R.string.label_error));
        }
        if (drawable <= 0) {
            drawable = R.drawable.ic_error;
        }
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.label_error);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutErrorBinding = DataBindingUtil.inflate(inflater, R.layout.layout_error, container,
                false);
        return mLayoutErrorBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutErrorBinding.ivError.setImageDrawable(getResources().getDrawable(drawable));
        mLayoutErrorBinding.tvMessage.setText(errorMessage);
        mLayoutErrorBinding.btnRetry.setOnClickListener(v -> {
            if (errorBottomSheetFragmentCallBack != null) {
                errorBottomSheetFragmentCallBack.onRetryButtonClick();
            }
            this.dismiss();
        });
    }
}
