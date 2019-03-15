# Jokeapi
Sample Android Project that displays Jokes


**AsyncTaskLoaders**

Loaders in Android is an abstract class implementation of Loader and two default classes, which are provided by Android.It can fetch data 
asynchronously without blocking the main thread and it manages it's own lifecycle during onDestroy() and configuration changes.

There are three main parts to implement loader with Android:

1.LoaderManager: 
LoaderManager is used to initialize loaders in activity/fragment. Loaders can be initialized mainly in onCreate () / 
onStart ().

2.LoaderManager.LoaderCallBacks:
It provide a set of three methods which Loader with call sequentially:

OnCreateLoader(int,bundle)

OnLoadFinished()

OnLoaderReset()


**Adapter**

A bridge between an AdapterView and the underlying data for that view.An AdapterView is a group of widgets components in Android that 
include the ListView, Spinner, and GridView [AdapterView](https://developer.android.com/reference/android/widget/AdapterView.html).


**RecyclerView**

The RecyclerView is a more advanced and more flexible version of the ListView [Recyclerview](https://www.binpress.com/android-recyclerview-cardview-guide/)
This new component is a big step because the ListView is one of the most used UI widgets.


**CardView**


CardView uses elevation property on Lollipop for shadows and falls back to a custom emulated shadow implementation on older platforms.
[here](https://www.binpress.com/android-recyclerview-cardview-guide/).


**AlertDialog**

 Alert dialog box is used to show alerts to the users, get confirmation from the users. In order to make an alert dialog, we need 
 to make an object of AlertDialogBuilder which is an inner class of AlertDialog [androidalertdialog](https://www.journaldev.com/9463/android-alertdialog).
