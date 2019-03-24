package com.backbase;

import com.backbase.models.CityInfo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.MarkerOptions;

public interface Map {

    interface Presenter {
        void showCityOnMap(CityInfo cityInfo);

    }

    interface View {
        void showMarkerOnmap(MarkerOptions options, CameraUpdate update);
        void showPopup(CityInfo cityInfo);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
