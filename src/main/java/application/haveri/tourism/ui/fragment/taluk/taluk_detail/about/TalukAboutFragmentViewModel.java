package application.haveri.tourism.ui.fragment.taluk.taluk_detail.about;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.MapSingleObject;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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
