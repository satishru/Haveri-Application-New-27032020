package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class TalukAboutFragmentViewModel extends BaseViewModel<iTalukAboutFragmentContract.iTalukAboutFragmentNavigator> implements
        iTalukAboutFragmentContract.iTalukAboutFragmentViewModel {

    public TalukAboutFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onMapClick(Taluk selectedTaluk) {
        if (selectedTaluk != null) {
            MapSingleObject singleObject = new MapSingleObject();
            singleObject.setTitleEn(selectedTaluk.getTalukNameEn());
            singleObject.setTitleKn(selectedTaluk.getTalukNameKn());
            singleObject.setLatitude(selectedTaluk.getLatitude());
            singleObject.setLongitude(selectedTaluk.getLongitude());
            getNavigator().openMapSingleActivity(singleObject);
        }
    }
}
