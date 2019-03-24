package com.backbase;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.backbase.models.CityInfo;
import com.backbase.presenter.MapPresenterImpl;
import com.backbase.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class MapPresenterImplTest {

    Context context;

    MapPresenterImpl mapPresenter;

    @Mock
    CityInfo cityInfo;

    @Rule
    ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void launchActivity(){
        context = InstrumentationRegistry.getTargetContext();
        Map.View mapView = mock(Map.View.class);
        mapPresenter = new MapPresenterImpl(mapView);
    }

    @Test
    public void testShowCityOnMap(){
        mapPresenter.showCityOnMap(cityInfo);
        onView(withId(R.id.mapView)).check(matches(isDisplayed()));
    }

}
