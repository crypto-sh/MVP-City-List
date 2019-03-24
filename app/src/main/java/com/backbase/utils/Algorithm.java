package com.backbase.utils;

import com.backbase.models.CityInfo;
import com.backbase.models.CoordinateInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithm {

    private int count = 0;
    private CityInfo[] array;
    private CityInfo[] tempMergArr;
    private int length;


    /**
     * Merge Sort with Big O(n log n)
     * for sort list city info by title
     *
     * start Merge Sort
     *
     * @param inputArr  array of city Info for sort.
     *
     * */
    public void MergeSort(CityInfo inputArr[]) {
        this.array       = inputArr;
        this.length      = inputArr.length;
        this.tempMergArr = new CityInfo[length];
        doMergeSort(0, length - 1);
    }

    /**
     * Merge Sort with Big O(n log n)
     * for sort list city info by title
     *
     * breaking an array into its individual components
     * */
    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    /**
     * Merge Sort with Big O(n log n)
     * for sort list city info by title
     *
     *
     * pairs up an individual with another.
     * putting them into their proper place (sorted) with reference to each other
     * */
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            //compare name for sort list
            if (tempMergArr[i].getCompareString().compareToIgnoreCase(tempMergArr[j].getCompareString()) < 0) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }

    /**
     * Binary Search for find item start with
     * The Prefix in sorted list.
     *
     * Start Binary Search
     *
     *
     * @param list  array of city Info for search.
     * @param prefix A String for find similar city start with it.
     *
     * */
    public List<CityInfo> searchPrefix(String prefix,List<CityInfo> list) {
        count = 0;
        boolean matchedSuffix = false;
        List<CityInfo> matchesList = new ArrayList<>();
        if (list == null || list.size() == 0){
            return matchesList;
        }
        int startFrom = binarySearchOverList(list, prefix);
        if (startFrom == -1) {
            System.out.println("no data found");
            return matchesList;
        } else {
            for (int i = startFrom; ; i++) {
                CityInfo cityInfo = list.get(i);
                if (cityInfo.getCompareString().startsWith(prefix)) {
                    //here you will get matching title cities
                    matchesList.add(cityInfo);
                    matchedSuffix = true;
                } else {
                    if (matchedSuffix) {
                        break;
                    }
                }

            }
        }
        return matchesList;
    }

    /**
     * Binary Search for find item start with
     * The Prefix in sorted list.
     *
     * @param input  array of city Info for search.
     * @param prefix A String for find similar city start with it.
     *
     * */
    private int binarySearchOverList(List<CityInfo> input, String prefix) {
        count++;
        int size = input.size();
        int midpoint = size / 2;
        int startPoint = 0;

        String stringToTest = input.get(midpoint).getCompareString();
        if (stringToTest.startsWith(prefix)) {
            startPoint = midpoint - 1;
            while (true) {
                if (!input.get(startPoint).getCompareString().startsWith(prefix)) {
                    startPoint++;
                    break;
                }
                if (startPoint == 0) {
                    break;
                }
                startPoint--;
            }
            return startPoint;
        }

        if (stringToTest.compareTo(prefix) > 0) {
            List<CityInfo> sublist = input.subList(0, midpoint);
            return binarySearchOverList(sublist, prefix);
        }

        if (stringToTest.compareTo(prefix) < 0) {
            if (input.get(input.size() - 1).getCompareString().compareTo(prefix) < 0) {
                return -1;
            }
            List<CityInfo> sublist = input.subList(midpoint, input.size());
            return binarySearchOverList(sublist, prefix);
        }
        return 0;
    }

    /**
     * generate fake data for test
     *
     * */
    public CityInfo[] generateCitiesInfo(int listLenght){
        ArrayList<CityInfo> citties = new ArrayList<>();
        String[] countries = new String[]{"UA","RU","NP","IN"};
        for (int index = listLenght; index > 0;index--){
            Random rand = new Random();
            String name     = rand.nextInt(listLenght) + "city" + index;
            String country  = countries[rand.nextInt(4)];
            CityInfo city = new CityInfo(country,name, (long) index,new CoordinateInfo(Double.valueOf("34.283333"),Double.valueOf("44.549999")));
            citties.add(city);
        }
        return citties.toArray(new CityInfo[citties.size()]);
    }
}
