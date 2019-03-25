# MVP City List

This project created base on [Backbase](https://backbase.com) assignment for hiring process.


It has a list of cities containing around 200k entries in JSON format. It should be displayed in the sorted list with capability of filter list.
The Application should be able to rotate orientational both portrait and landscape. In the landscape mode list and map display side by side and after user
selects a city, map show's the location with marker on map, and for the portrait orientation user can just see list after selecting a city, 
The app is going to show map in another page.


# Description

- MainActivity is the launcher activity. Default fragment for this activity is ListFragment which shows list of cities.
    in the portrait orientation it just shows one ListFragment but in the horizontal orientation it displays two Fragments at the same time. one of them is ListFragment and the other one is MapFragment.
    MapFragment is responsible for displaying the location of the selected city on tha map and after clicking on the marker it opens a popup that contains information about the city, In this step users are able to use navigation. 

- ListFragment : 
    - CitiesPresenterImpl is presenter of ListFragment and cityModelImpl class is responsible for fetching list of data from json file assets and convert it to JsonArray with Gson library and notify presenter when data is ready.
    - CityModelImpl is also sorted and filtered data with Algorithm class. 
    - Algorithm : In this class I implemented merge of algorithms for sort and binary search to filter. I'll describe about this algorithm and compare them with eachother.
    - CitiesAdapter : final part for ListFragment is CitiesAdapter which is responsible to show the list item.

- MapFragment : MapPresenterImpl is the presenter for this fragment.

- Test Class 
    - androidTest
            - CityPresenterImplTest for test CitiesPresenterImpl. 
            - MainActivityInstrumentedTest for test MainActivity and orientations.
            - MapPresenterImplTest for test MapFragment.
    - test
            - AlgorithmUnitTest for test algorithm sort and search.


# Algorithm

 - Sort Algorithm. Merge sort is one of the most efficient sorting algorithms. It works on the principle of Divide and Conquer. Merge sort repeatedly breaks down a list into several sublists until each sublist consists of a single element and merging those sublists in a manner that results into a sorted list. you can see comparison sort algorithm in the below image.
    
    ![comparison sort algorithms](https://user-images.githubusercontent.com/38876424/54883196-ef5ada80-4e80-11e9-952c-b323b928aadb.png)
 
 - Search Algorithm. Binary Search is one of the most fundamental and useful algorithms in Computer Science. It describes the process of searching for a specific value in an ordered collection. you can see comparison search algorithm in the below image. 
 
    ![comparison search algorithms](https://user-images.githubusercontent.com/38876424/54883197-ef5ada80-4e80-11e9-8d06-5fddf5335120.png)


Prerequisites
=============

    - Android Studio 3.3.2
    - Gradle version 4.10.1+
    - JDK 1.8
     


    
## Author

[Ali Shatergholi](https://github.com/alishatergholi)

