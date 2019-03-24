package com.backbase.presenter;

import android.content.Context;

import com.backbase.City;
import com.backbase.models.CityInfo;
import com.backbase.models.CityModelImpl;
import com.backbase.utils.AppExecuter;
import com.backbase.utils.LogHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CityPresenterImpl implements City.Presenter {

    private List<CityInfo> cities_info = new ArrayList<>();
    private final CityModelImpl cityModel;
    private final WeakReference<City.View> cityView;
    private final LogHelper logHelper = new LogHelper(this.getClass());

    public CityPresenterImpl(Context context, City.View cityView) {
        this.cityView = new WeakReference<>(cityView);
        this.cityModel = new CityModelImpl(context, this);
        AppExecuter.stopExecutor();
    }

    @Override
    public void getCitiesInfo() {
        City.View view = cityView.get();
        if (view != null) {
            view.showProgress();
            cityModel.getAllCities();
        }
    }

    @Override
    public void filterCitiesInfoRequest(String prefix) {
        City.View view = cityView.get();
        if (logHelper.StringIsEmptyOrNull(prefix)) {
            if (view != null) {
                view.citiesInfoList(cities_info);
            }
            return;
        }
        cityModel.getCitiesByFilter(prefix, cities_info);
    }

    @Override
    public void onFilteredCities(List<CityInfo> cities_info) {
        City.View view = cityView.get();
        if (view != null) {
            view.citiesInfoList(cities_info);
        }
    }

    @Override
    public void onSuccess(List<CityInfo> cities_info) {
        this.cities_info = cities_info;
        City.View view = cityView.get();
        if (view != null) {
            view.hideProgress();
            view.citiesInfoList(this.cities_info);
        }
    }

    @Override
    public void onFail() {
        City.View view = cityView.get();
        if (view != null) {
            view.hideProgress();
            view.showError();
        }
    }
}
