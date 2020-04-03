package application.haveri.tourism.ui.fragment.common.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.databinding.LayoutPlaceItemBinding;
import application.haveri.tourism.ui.base.BaseViewHolder;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Place> placeList;
    private PlaceAdapterListener placeAdapterListener;

    public interface PlaceAdapterListener {
        void onPlaceItemClick(Place place);
    }

    public PlaceAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    public void setListener(PlaceAdapterListener placeAdapterListener) {
        this.placeAdapterListener = placeAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPlaceItemBinding layoutPlaceItemBinding = LayoutPlaceItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PlaceListAdapterViewHolder(layoutPlaceItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        Glide.clear(((PlaceListAdapterViewHolder) holder).layoutPlaceItemBinding.ivPlace);
    }

    public void addItems(List<Place> placeList) {
        if (placeList != null) {
            this.placeList.addAll(CommonUtils.mockList(placeList, AppConstants.MOCK_LIST_SIZE));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        placeList.clear();
    }

    public class PlaceListAdapterViewHolder extends BaseViewHolder implements
            PlaceListAdapterViewModel.PlaceListAdapterViewModelListener {

        private LayoutPlaceItemBinding layoutPlaceItemBinding;

        PlaceListAdapterViewHolder(LayoutPlaceItemBinding layoutPlaceItemBinding) {
            super(layoutPlaceItemBinding.getRoot());
            this.layoutPlaceItemBinding = layoutPlaceItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Place place = placeList.get(position);
            PlaceListAdapterViewModel viewModel = new PlaceListAdapterViewModel(place,
                    getLanguage(layoutPlaceItemBinding), this);
            layoutPlaceItemBinding.setViewModel(viewModel);
            layoutPlaceItemBinding.executePendingBindings();
            setAnimation(position, layoutPlaceItemBinding.cardContent, false,
                    android.R.anim.slide_in_left);
        }

        @Override
        public void onItemClick(Place place) {
            if (placeAdapterListener != null) {
                placeAdapterListener.onPlaceItemClick(place);
            }
        }
    }
}