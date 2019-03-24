package com.backbase;


import android.app.FragmentTransaction;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;



@RunWith(AndroidJUnit4.class)
public class CityPresenterImplTest {

    private Context appContext;

    private City.View cityView;

    private CityPresenterImpl presenter;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void launchActivity() {
        appContext = InstrumentationRegistry.getTargetContext();
        cityView = mock(City.View.class);
        presenter = new CityPresenterImpl(appContext,cityView);
    }

    @Test
    public void checkErrorWithPresentor(){
        presenter.onFail();
        verify(cityView).showError();
    }

    @Test
    public void checkProgressWithRequestData(){
        presenter.getCitiesInfo();
        verify(cityView).showProgress();
    }

    @Test
    public void checkListshowData(){
        List<CityInfo> spyList = Mockito.spy(new ArrayList<CityInfo>());
        CityInfo tehran = new CityInfo("IR","Tehran",120L,new CoordinateInfo(44.00D,34.00D));
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
    public void checkLoadDataFromAssets(){
        CityModelImpl cityModel = new CityModelImpl(appContext,presenter);
        List<CityInfo> cities = cityModel.parseJsonCities(cityModel.getFileFromAssets());
        assertNotNull(cities);
    }
}
