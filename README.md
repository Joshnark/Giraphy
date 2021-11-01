#PLEASE READ AS IT CONTAINS INFO ABOUT THE WORK DONE HERE
Well, I must confess that not all the answers are very insightful but some of them explains a few things (i.e. 1

# The 'super' Gif App
I really didn't have any better idea for a name

Ok, I will sumarize very quickly what I have done based on what FreshWorks asked. 
This text have been done very quickly because time shortage due to my current job, so much job, so I apologize for ortographic english mistakes.

# 1. All gif must be running

Done, fairly easy, Glide is quite one of the best libraries around for image and Gif management. I also noticed that Giphy API lets you work with .mp4 files, if that were the case, Exoplayer is the best library for video display in Android.

-------------------------------------------------------------------------

# 2. User must see a list of trending gifs when launching the app

Done, also it is in a pretty instagram like UI because I love pretty UI.

-------------------------------------------------------------------------

# 3. The user must be able to type any search term into a search area on the first screen and have the view refresh the list

Done, in the same first view as asked.

-------------------------------------------------------------------------

# 4. The user must see a grid of his/her favourite GIFs in the second screen
# 5. The user must be able to add and remove GIFS to and from thei favourites

Done, with Room of course.

-------------------------------------------------------------------------

# 6. There should be one activity with two fragments
# 7. The fragments should be swipeable unsing a TabLayout and ViewPager

Done, with ViewPager2.

-------------------------------------------------------------------------

# 8. Change Configuration must be suported

You can see two examples of this, I manage orientation changes inside FavoritesFragment.kt and also I have one specific XML file for landscape configuration for the GifsListFragment.kt, you can check that last thing in the layout folder inside res.

In the other hand, I didn't have much problem with lifecycle handling since I work with MVVM.

-------------------------------------------------------------------------

# 9. First Fragment contains a Search Bar at the top
# 10. First Fragment contains a RecyclerView that dispays searched Gif
# 11. Recycler View should load trending GIFs if there is not search term provided
# 12. Shows a loading indicator when loading Gif or refreshing the list

Done, literally an editText the searchBar, and displays searched gifs in the same recycler for trending gif.
All the loading and error handling is done with the same paging library tools.

OMG I JUST NOTICED I FORGOT THE REFRESH. Easily can be done with SwipeRefreshLayout and the refresh method of paging library. But is worth to mention that I forgot to do it. Please pardon that little detail.

-------------------------------------------------------------------------

# 13. Second Fragment contains a RecyclerView that displays a grid of the user favourite GIFs stored locally

Done, displays two columns in portrait, four in landscape.

-------------------------------------------------------------------------

# 14. Should containt a layout including a running GIF

I didn't fully understand this one, I guess you refer that all the gif must be running in the grid, which is done.

# 15. Should contain a button which allows the user to add or remove a GIF from their favourites list

All the item views in the lists and grids have an 'add to favourite' hearth button with fully functionality.

# 16 USE A SOFTWARE ARCHITECTURE PATTERN

Finally, the part I love the most.

Long story short, I used MVVM architecture pattern and a Martin Fowler's clean architecture approach (which is widely used in android).

Layers structure are like this:

DOMAIN (entity layer) : Contains models and Repositories definition

DATA : Contains repositories implementation and data source definitions, also paging library data paging implementation that is used inside repositories impls.

USECASES: Contains purely use cases, the smallest layer.

FRAMEWORK: As it name implies, all the integration with retrofit and Room is included here, in consequence, data source implementation are here too bc they provide data to the repositories being like a bridge through networking, data persistence and the presentation layer.

PRESENTATION: ViewModels, Adapters, Fragments, Activities and every UI related stuff is included here

The dependency relationship beetween those layers goes like this:

FRAMEWORK -> DATA -> DOMAIN <- USE CASES <- PRESENTATION

PD: For MVVM i also used live data, which makes part of the observer design pattern, I didn't used kotlin flow because is too new.

-------------------------------------------------------------------------

# 16 Use coroutines and Room

Done, also i did use room with coroutines, lot of fun.


-------------------------------------------------------------------------

# 17. kotlin instead of java

Done.

-------------------------------------------------------------------------

# 18. Add pagination

I implemented infinite scrool using Paging Library 3 quite the best library for the job, the feature also support error handling and loader display.


# 19. ADD UNIT TEST FOR YOUR CODE

Unfonrtunatelly i was not able to add unit test due to time shortage. I added a few classes in domain and data layers for testing but they are just like the barebones, I only did one test and it is in data layer test folder. It is very small, but You can see how my unit testing approach in that example.

I didn't have the chance to implement instrumentation test tho.


-------------------------------------------------------------------------

CONCLUSION

This project is small, very easy, but also was difficult due to the low amount of free time I have with my current job. I hope you accept my apologies.
However I think I displayed a lot of my abilities and also had a lot of fun doing this. I had a lot of time I didn't do anything from scratch.
I think you would notice my love for design and architectural patterns and I hope you enjoy that.

Thanks and Cheers.
