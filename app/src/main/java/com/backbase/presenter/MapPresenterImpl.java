package com.backbase.presenter;

import com.backbase.Map;
import com.backbase.models.CityInfo;
import com.backbase.models.CoordinateInfo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.WeakReference;

public class MapPresenterImpl implements Map.Presenter {

    private final WeakReference<Map.View> mapView;

    public MapPresenterImpl(Map.View mapView) {
        this.mapView       = new WeakReference<>(mapView);
        mapView.showProgress();
    }

    @Override
    public void showCityOnMap(CityInfo cityInfo) {
        Map.View view = mapView.get();
        if (view != null) {
            CoordinateInfo coordinate = cityInfo.getCoord();
            LatLng selectedCity = new LatLng(coordinate.getLat(), coordinate.getLon());
            MarkerOptions options = new MarkerOptions()
                    .position(selectedCity)
                    .title(cityInfo.getTitle())
                    .snippet(cityInfo.getSubTile());
            CameraPosition cameraPosition
                    = new CameraPosition
                    .Builder()
                    .target(selectedCity)
                    .zoom(12)
                    .build();
            CameraUpdate camera = CameraUpdateFactory.newCameraPosition(cameraPosition);
            view.showMarkerOnmap(options, camera);
            view.hideProgress();
        }
    }
}
