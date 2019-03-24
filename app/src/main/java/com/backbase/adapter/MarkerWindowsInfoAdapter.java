package com.backbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.backbase.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.lang.ref.WeakReference;


public class MarkerWindowsInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private final View customView;

    private WeakReference<Context> context;

    public MarkerWindowsInfoAdapter(Context context){
        this.context = new WeakReference<>(context);
        customView = LayoutInflater.from(this.context.get()).inflate(R.layout.layout_info_content, null);
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView tvTitle = ((TextView) customView.findViewById(R.id.title));
        tvTitle.setText(marker.getTitle());

        TextView tvSnippet = ((TextView) customView.findViewById(R.id.snippet));
        tvSnippet.setText(marker.getSnippet());

        return customView;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        return null;
    }
}
