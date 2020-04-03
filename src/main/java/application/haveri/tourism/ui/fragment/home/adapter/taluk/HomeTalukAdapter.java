package application.haveri.tourism.ui.fragment.home.adapter.taluk;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import application.haveri.tourism.R;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.databinding.LayoutHomeTalukItemBinding;
import application.haveri.tourism.ui.base.BaseViewHolder;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;

import java.util.List;

public class HomeTalukAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Taluk> talukList;
    private HomeTalukAdapterListener homeTalukAdapterListener;

    public interface HomeTalukAdapterListener {
        void onTalukItemClick(Taluk taluk);
    }

    public HomeTalukAdapter(List<Taluk> talukList) {
        this.talukList = talukList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHomeTalukItemBinding layoutHomeTalukItemBinding = LayoutHomeTalukItemBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeTalukAdapterViewHolder(layoutHomeTalukItemBinding);
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
        Glide.clear(((HomeTalukAdapterViewHolder) holder).layoutHomeTalukItemBinding.ivTaluk);
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

    public void setListener(HomeTalukAdapterListener homeTalukAdapterListener) {
        this.homeTalukAdapterListener = homeTalukAdapterListener;
    }

    public class HomeTalukAdapterViewHolder extends BaseViewHolder implements HomeTalukAdapterViewModel.HomeTalukAdapterViewModelListener {

        private LayoutHomeTalukItemBinding layoutHomeTalukItemBinding;

        HomeTalukAdapterViewHolder(LayoutHomeTalukItemBinding layoutHomeTalukItemBinding) {
            super(layoutHomeTalukItemBinding.getRoot());
            this.layoutHomeTalukItemBinding = layoutHomeTalukItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Taluk taluk = talukList.get(position);
            HomeTalukAdapterViewModel viewModel = new HomeTalukAdapterViewModel(taluk,
                    getLanguage(layoutHomeTalukItemBinding), this);
            layoutHomeTalukItemBinding.setViewModel(viewModel);
            layoutHomeTalukItemBinding.executePendingBindings();
            setAnimation(position, layoutHomeTalukItemBinding.cardContent, false,
                    R.anim.slide_from_right);
        }

        @Override
        public void onItemClick(Taluk taluk) {
            if (homeTalukAdapterListener != null) {
                homeTalukAdapterListener.onTalukItemClick(taluk);
            }
        }
    }
}
