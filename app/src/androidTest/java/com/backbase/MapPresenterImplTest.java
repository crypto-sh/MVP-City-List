package com.backbase;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.backbase.models.CityInfo;
import com.backbase.presenter.MapPresenterImpl;
import com.backbase.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MapPresenterImplTest {

    Context context;

    MapPresenterImpl mapPresenter;

    CityInfo cityInfo = new CityInfo();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void launchActivity(){
        context = ApplicationProvider.getApplicationContext();
        Map.View mapView = mock(Map.View.class);
        mapPresenter = new MapPresenterImpl(mapView);
    }

    @Test
    public void testShowCityOnMap(){
        mapPresenter.showCityOnMap(cityInfo);
        onView(withId(R.id.mapView)).check(matches(isDisplayed()));
    }

}
