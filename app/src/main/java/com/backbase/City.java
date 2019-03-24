package com.backbase;

import com.backbase.models.CityInfo;

import java.util.List;

public interface City {

    interface Model {
        void getAllCities();
        void getCitiesByFilter(String prefix,List<CityInfo> cities_info);
    }

    interface Presenter {
        void getCitiesInfo();
        void filterCitiesInfoRequest(String prefix);
        void onFilteredCities(List<CityInfo> cities_info);
        void onSuccess(List<CityInfo> cities_info);
        void onFail();
    }

    interface View {
        void citiesInfoList(List<CityInfo> cities);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
