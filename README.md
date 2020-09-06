# Bobble Assignment 2020
by [Yashovardhan Dhanania](https://github.com/yashovardhan99)

## Challenge - Android

### Question 1
I have developed an android app using the latest alpha version of [Jetpack Compose](https://developer.android.com/jetpack/compose).

All relevant files are available in the [BobbleBigText](BobbleBigText) folder

More details about this app are available [here](BobbleBigText/README.md)

Please note that since Jetpack Compose is in alpha, the app won't perform as well as other apps. Building a standard app with Views and RecyclerView would have been easy but I wanted to try this app using Compose.

### Question 2
```
Exception in thread "main" java.util.ConcurrentModificationException     
  at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:859)     
  at java.util.ArrayList$Itr.next(ArrayList.java:831)     
  at main.java.Bobble.main(Test.java:25)
```

#### What could be wrong?
There could be multiple things going wrong here depending on how the ArrayList is being used - 
- The ArrayList might have been modified concurrently by 2 different threads.
  This only happens in multi-threaded environments. However, the next error I mention is more likely:
- Modifying the ArrayList while iterating through it.

#### understanding the issue
A common mistake many people make is modify an ArrayList like this:-
```kotlin
val list = ArrayList<Int>()
list.addAll(....)
for(item in list) {
  if(item >= 2 && item <= 6)
      list.remove(item)
```
Here, the list is being modified while we are iterating over it. This results in the above-mentioned error being thrown. The above code can also be used to reproduce this error.

#### How to fix it

