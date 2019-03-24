package com.backbase.ui;

import android.os.Bundle;

import com.backbase.R;
import com.backbase.models.CityInfo;

public class MapActivity extends BaseActivity {

    @Override
    int getResourceLayout() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra("data")){
            CityInfo cityInfo = getIntent().getParcelableExtra("data");
            setFragment(R.id.mapFrame,MapFragment.newInstance(cityInfo));
        }else {
            finish();
        }
    }
}
