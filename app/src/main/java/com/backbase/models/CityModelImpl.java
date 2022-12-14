package com.backbase.models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import androidx.annotation.VisibleForTesting;

import com.backbase.City;
import com.backbase.utils.Algorithm;
import com.backbase.utils.AppExecuter;
import com.backbase.utils.LogHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;


public class CityModelImpl implements City.Model {

    private String FILE_NAME = "cities.json";

    private final City.Presenter presenter;

    private final WeakReference<Context> context;

    private final Algorithm sort = new Algorithm();

    private final LogHelper logHelper = new LogHelper(this.getClass());

    public CityModelImpl(Context context, City.Presenter presenter) {
        this.context = new WeakReference<>(context);
        this.presenter = presenter;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    public List<CityInfo> parseJsonCities(String cities_json) {
        CityInfo[] inputArr =  new Gson().fromJson(cities_json, CityInfo[].class);
        //MergeSort list
        sort.MergeSort(inputArr);
        return Arrays.asList(inputArr);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    public String getFileFromAssets() {
        if (context.get() != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(context.get().getAssets().open(FILE_NAME)));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                return builder.toString();
            } catch (IOException ex) {
                logHelper.e(ex);
            }
        }
        return null;
    }

    @Override
    public void getAllCities() {
        AppExecuter.getIoExecutor().execute(() -> {
            String citiesJson = getFileFromAssets();
            if (!logHelper.StringIsEmptyOrNull(citiesJson)) {
                List<CityInfo> items = parseJsonCities(citiesJson);
                if (presenter != null) {
                    new Handler(Looper.getMainLooper()).post(() -> presenter.onSuccess(items));
                }
                return;
            }
            if (presenter != null) {
                new Handler(Looper.getMainLooper()).post(presenter::onFail);
            }
        });
    }

    @Override
    public void getCitiesByFilter(String prefix, List<CityInfo> cities_info) {
        List<CityInfo> filteredList = sort.searchPrefix(prefix.toLowerCase(), cities_info);
        presenter.onFilteredCities(filteredList);
    }
}
