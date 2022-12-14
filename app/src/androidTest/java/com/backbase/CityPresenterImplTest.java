package com.backbase;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.app.FragmentTransaction;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.backbase.models.CityInfo;
import com.backbase.models.CityModelImpl;
import com.backbase.models.CoordinateInfo;
import com.backbase.presenter.CityPresenterImpl;
import com.backbase.ui.ListFragment;
import com.backbase.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class CityPresenterImplTest {

    private Context appContext;

    private City.View cityView = mock(City.View.class);

    private CityPresenterImpl presenter = new CityPresenterImpl(appContext, cityView);

    @Before
    public void launchActivity() {
        appContext = ApplicationProvider.getApplicationContext();
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testPackage() {
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.backbase", appContext.getPackageName());
    }

    @Test
    public void checkErrorWithPresenter() {
        presenter.onFail();
        verify(cityView).showError();
    }

    @Test
    public void checkProgressWithRequestData() {
        presenter.getCitiesInfo();
        verify(cityView).showProgress();
    }

    @Test
    public void checkListShowData() {
        List<CityInfo> spyList = Mockito.spy(new ArrayList<>());
        CityInfo tehran = new CityInfo("IR", "Tehran", 120L, new CoordinateInfo(44.00D, 34.00D));
        spyList.add(tehran);
        Mockito.verify(spyList).add(tehran);
        presenter.onSuccess(spyList);
        verify(cityView).hideProgress();
        FragmentTransaction transaction = activityTestRule.getActivity().getFragmentManager().beginTransaction();
        ListFragment fragment = new ListFragment();
        transaction.replace(R.id.listFrame,fragment).commit();
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLoadDataFromAssets() {
        CityModelImpl cityModel = new CityModelImpl(appContext, presenter);
        List<CityInfo> cities = cityModel.parseJsonCities(cityModel.getFileFromAssets());
        assertNotNull(cities);
    }
}
