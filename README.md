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
A simple fix for the above problem is to use an iterator instead of manually iterating over the list.
So the above code becomes:-
```kotlin
val list = ArrayList<Int>()
list.addAll(....)
val iterator = list.iterator()
while(iterator.hasNext()) {
  val item = iterator.next()
  if(item >= 2 && item <= 6)
    iterator.remove()
}
```
The above code does exactly the same thing - Remove all numbers between 2 and 6 (both inclusive) from the ArrayList.
However, the main difference being `iterator#remove()` calls are safe.

Another possible alternative is to add all the elements you intend to remove in a separate list and use `ArrayList#removeAll()`.

#### Side note on Multi-threaded situations
In case the above was not the use-case and the arrayList was being used by multiple threads, it can also result in the mentioned error.

In such a case, there are 2 possible solutions:-
- Use Vector instead of ArrayList. ArrayList is not synchronized while Vector is.
- Lock any list modifications in a synchronized block. This prevents multiple threads from accessing it at the same time.

## About me
I am an android developer and a kotlin enthusiast. I am currently trying out new tools and improving my knowledge of tools available on android. You can find some of my common profiles below:-
- [GitHub](https://github.com/yashovardhan99)
- [LinkedIn](https://www.linkedin.com/in/yashovardhan99/)
- [Medium](https://medium.com/@yashovardhan99)
- [Twitter](https://twitter.com/yashovardhan99)
