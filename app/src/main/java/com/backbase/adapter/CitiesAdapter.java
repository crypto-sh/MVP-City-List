package com.backbase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.backbase.models.CityInfo;
import com.backbase.R;

import java.util.List;

public class CitiesAdapter extends BaseAdapter {

    ViewHolder holder;

    List<CityInfo> cities;

    public CitiesAdapter(List<CityInfo> cities) {
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public CityInfo getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CityInfo city = getItem(position);
        holder.title.setText(city.getTitle());
        holder.subtitle.setText(city.getSubTile());
        return convertView;
    }

    class ViewHolder {

        TextView title;
        TextView subtitle;

        ViewHolder(View view){
            title    = view.findViewById(R.id.titleTextView);
            subtitle = view.findViewById(R.id.subtitleTextView);
        }
    }
}
