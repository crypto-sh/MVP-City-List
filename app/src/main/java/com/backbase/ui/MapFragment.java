package com.backbase.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.backbase.Map;
import com.backbase.R;
import com.backbase.adapter.MarkerWindowsInfoAdapter;
import com.backbase.models.CityInfo;
import com.backbase.presenter.MapPresenterImpl;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, Map.View {

    private static final String ARG_CITY = "city";

    private CityInfo mCity;

    private MapView mapView;
    private TextView errorText;
    private GoogleMap googleMap;
    private ProgressBar progress;
    private MapPresenterImpl presenter;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param city Parameter 1.
     * @return A new instance of fragment MapFragment.
     */
    public static MapFragment newInstance(CityInfo city) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = getArguments().getParcelable(ARG_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        initLayout(rootView, savedInstanceState);
        presenter = new MapPresenterImpl(this);
        return rootView;
    }

    private void initLayout(View view, Bundle savedInstanceState) {
        mapView     = view.findViewById(R.id.mapView);
        progress    = view.findViewById(R.id.progress);
        errorText   = view.findViewById(R.id.error_text);
        mapView.onCreate(savedInstanceState);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapView.onResume();
        presenter.showCityOnMap(mCity);
    }

    @Override
    public void showMarkerOnmap(MarkerOptions options, CameraUpdate update) {
        googleMap.setInfoWindowAdapter(new MarkerWindowsInfoAdapter(getActivity()));
        googleMap.addMarker(options);
        googleMap.animateCamera(update);
    }

    @Override
    public void showPopup(CityInfo cityInfo) {

    }

    @Override
    public void showError() {
        progress.setVisibility(View.GONE);
        mapView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(getString(R.string.error_message));
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
        mapView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mapView.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }
}
