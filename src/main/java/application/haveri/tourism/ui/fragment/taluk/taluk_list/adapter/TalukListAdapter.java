package application.haveri.tourism.ui.fragment.taluk.taluk_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.databinding.LayoutTalukItemBinding;
import application.haveri.tourism.ui.base.BaseViewHolder;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;

import java.util.List;

public class TalukListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Taluk> talukList;
    private TalukListAdapterListener talukListAdapterListener;

    public interface TalukListAdapterListener {
        void onItemClick(Taluk taluk);
    }

    public TalukListAdapter(List<Taluk> talukList) {
        this.talukList = talukList;
    }

    public void setListener(TalukListAdapterListener talukListAdapterListener) {
        this.talukListAdapterListener = talukListAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTalukItemBinding layoutTalukItemBinding = LayoutTalukItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TalukAdapterViewHolder(layoutTalukItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return talukList.size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        Glide.clear(((TalukAdapterViewHolder) holder).layoutTalukItemBinding.ivTaluk);
    }

    public void addItems(List<Taluk> talukList) {
        if (talukList != null) {
            this.talukList.addAll(CommonUtils.mockList(talukList, AppConstants.MOCK_LIST_SIZE));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        talukList.clear();
    }

    public class TalukAdapterViewHolder extends BaseViewHolder implements TalukListAdapterViewModel.TalukListAdapterViewModelListener {

        private LayoutTalukItemBinding layoutTalukItemBinding;

        TalukAdapterViewHolder(LayoutTalukItemBinding layoutTalukItemBinding) {
            super(layoutTalukItemBinding.getRoot());
            this.layoutTalukItemBinding = layoutTalukItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Taluk taluk = talukList.get(position);
            TalukListAdapterViewModel viewModel = new TalukListAdapterViewModel(taluk,
                    getLanguage(layoutTalukItemBinding), this);
            layoutTalukItemBinding.setViewModel(viewModel);
            layoutTalukItemBinding.executePendingBindings();
            setAnimation(position, layoutTalukItemBinding.cardContent, false,
                    android.R.anim.slide_in_left);
        }

        @Override
        public void onItemClick(Taluk taluk) {
            if (talukListAdapterListener != null) {
                talukListAdapterListener.onItemClick(taluk);
            }
        }
    }
}
