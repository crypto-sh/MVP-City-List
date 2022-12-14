package com.backbase;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.backbase.ui.ListFragment;
import com.backbase.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private Context appContext;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void launchActivity() {
        appContext = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void useAppContext() {
        assertEquals("com.backbase", appContext.getPackageName());
    }

    @Test
    public void mainActivityLayout() {
        int orientation = appContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));

            onView(withId(R.id.mapFrame))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void checkFragment() {
        ListFragment listFragment = new ListFragment();
        FragmentTransaction transaction = activityTestRule.getActivity().getFragmentManager().beginTransaction();
        transaction.replace(R.id.listFrame, listFragment).commit();
        int orientation = appContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.list_view))
                    .check(matches(isDisplayed()));
            transaction.replace(R.id.mapFrame, listFragment).commit();
            onView(withId(R.id.mapView))
                    .check(matches(isDisplayed()));
        }
    }
}
