package com.backbase;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.backbase.ui.ListFragment;
import com.backbase.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private Context appContext;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void launchActivity() {
        appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void useAppContext() {
        assertEquals("com.backbase", appContext.getPackageName());
    }

    @Test
    public void mainActivityLayout(){
        int orientation = appContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));
        }else {
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));

            onView(withId(R.id.mapFrame))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void checkFragment(){
        ListFragment listFragment = new ListFragment();
        FragmentTransaction transaction  = activityTestRule.getActivity().getFragmentManager().beginTransaction();
        int orientation = appContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){

            transaction.replace(R.id.listFrame,listFragment).commit();

            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));

        }else {

            transaction.replace(R.id.listFrame,listFragment).commit();
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));

            transaction.replace(R.id.mapFrame,listFragment).commit();
            onView(withId(R.id.mapView))
                    .check(matches(isDisplayed()));
        }
    }
}
