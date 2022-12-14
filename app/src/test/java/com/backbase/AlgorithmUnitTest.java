package com.backbase;


import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.backbase.models.CityInfo;
import com.backbase.utils.Algorithm;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AlgorithmUnitTest {

    int listLength = 100;

    private CityInfo[] cities;

    private Algorithm sortList;

    @Before
    public void setup(){
        sortList = new Algorithm();
        cities = sortList.generateCitiesInfo(listLength);
    }

    @Test
    public void arrayEntityTest(){
        System.out.println("start of test arrayEntityTest");
        assertEquals(cities.length, listLength);
        assertThat(cities[0],instanceOf(CityInfo.class));
    }

    @Test
    public void sortArrayListTest(){
        System.out.println("start of test sortArrayListTest");
        sortList.MergeSort(cities);
        for (int index = 0; index <  cities.length - 1;index++){
            System.out.println(cities[index].getCompareString() + " compare to " + cities[index + 1].getCompareString());
            int result = cities[index].getCompareString().compareToIgnoreCase(cities[index + 1].getCompareString());
            assertTrue(result < 0);
        }
        System.out.println("end of test sortArrayList");
    }

    @Test
    public void searchArrayListTest(){
        System.out.println("start of test searchArrayListTest");
        Random number               = new Random();
        int selected                = number.nextInt(cities.length);
        String cityName             = cities[selected].getCompareString();
        String prefix               = cityName.substring(0,2);

        //sort list
        sortList.MergeSort(cities);

        List<CityInfo> filteredCity = sortList.searchPrefix(prefix, Arrays.asList(cities));
        List<Long> filteredId = new ArrayList<>();
        for (CityInfo city : filteredCity){
            filteredId.add(city.get_id());
            assertTrue(city.getCompareString().startsWith(prefix));
        }

        //check other city
        for (CityInfo otherCity : cities){
            if (!filteredId.contains(otherCity.get_id())){
               assertFalse(otherCity.getCompareString().startsWith(prefix));
            }
        }
        System.out.println("End of test searchArrayListTest");
    }
}
