package com.backbase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.backbase.R;
import com.backbase.models.CityInfo;

public class MainActivity extends BaseActivity implements ListFragment.onCitySelectedListener {

    private boolean isDualPaged;

    @Override
    int getResourceLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View mapFrame = findViewById(R.id.mapFrame);
        isDualPaged = mapFrame != null && mapFrame.getVisibility() == View.VISIBLE;
        setFragment(R.id.listFrame,ListFragment.newInstance(isDualPaged));
    }

    @Override
    public void citySelected(CityInfo cityInfo) {
        if (isDualPaged){
            setFragment(R.id.mapFrame,MapFragment.newInstance(cityInfo));
        }else {
            Intent mapIntent = new Intent(this,MapActivity.class);
            mapIntent.putExtra("data",cityInfo);
            startActivity(mapIntent);
        }
    }
}
