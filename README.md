
# Over-Scroll Support For Android's RecyclerView, ListView, GridView, ScrollView ...
 
The library provides an iOS-like over-scrolling effect applicable over almost all Android native scrollable views. It is also built to allow for very easy adaptation to support custom views.

The core effect classes are loose-[decorators](https://en.wikipedia.org/wiki/Decorator_pattern) of Android views, and are thus decoupled from the actual view classes' implementations. That allows developers to apply the effect over views while keeping them as untampered 'black-boxes'. Namely, it allows for keeping important optimizations such as view-recycling intact.

![RecyclerView demo](recyclerview_demo.gif)

# Gradle Dependency

Add the following to your module's `build.gradle` file:

```groovy
dependencies {
    // ...
    
    compile 'me.everything:overscroll-decor-android:1.0.1'
}
```

# Usage

### RecyclerView

Supports both linear and staggered-grid layout managers (i.e. all native Android layouts).
Can be easily adapted to support custom layout managers.

```java
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    
// Horizontal
OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
// Vertical
OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
```

### ListView

```java
ListView listView = (ListView) findViewById(R.id.list_view);
OverScrollDecoratorHelper.setUpOverScroll(listView);
```

### GridView

```java
GridView gridView = (GridView) findViewById(R.id.grid_view);
OverScrollDecoratorHelper.setUpOverScroll(gridView);
```

### ScrollView, HorizontalScrollView

```java
ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
OverScrollDecoratorHelper.setUpOverScroll(scrollView);
    
HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontal_scroll_view);
OverScrollDecoratorHelper.setUpOverScroll(horizontalScrollView);
```

### Any View - Text, Image... (Always Over-Scroll Ready)

```java
View view = fragmentView.findViewById(R.id.demo_view);
    
// Horizontal
OverScrollDecoratorHelper.setUpStaticOverScroll(view, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
// Vertical
OverScrollDecoratorHelper.setUpStaticOverScroll(view, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
```

# Advanced Usage

```java
// Horizontal RecyclerView
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));

// ListView (vertical)
ListView listView = (ListView) findViewById(R.id.list_view);
new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(listView));

// GridView (vertical)
GridView gridView = (GridView) findViewById(R.id.grid_view);
new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(gridView));

// A simple TextView - horizontal
View textView = findViewById(R.id.title);
new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view));
```

### RecyclerView with [ItemTouchHelper](http://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.html) based swiping / dragging
As of version 1.0.1, the effect can work smoothly with the RecyclerView's built-in mechanism for items swiping and dragging (based on [ItemTouchHelper](http://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.html)). BUT, it requires some (very little) explicit configuration work:

```java
// INSTEAD of attaching a touch-helper & callback directly, like you normally would:
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
ItemTouchHelper.Callback myCallback = new ItemTouchHelper.Callback() {
	...
};
ItemTouchHelper myHelper = new ItemTouchHelper(myCallback);
myHelper.attachToRecyclerView(recyclerView);

// Use the dedicated adapter c'tor:
new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView, myCallback));

```

For more info on the swiping / dragging mechanism, try [this useful tutorial](https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf).


    
## Custom Views

```java
public class CustomView extends View {
    // ...
}
    
final CustomView view = (CustomView) findViewById(R.id.custom_view);
new VerticalOverScrollBounceEffectDecorator(new IOverScrollDecoratorAdapter() {

    @Override
    public View getView() {
        return view;
    }

    @Override
    public boolean isInAbsoluteStart() {
	    // canScrollUp() is an example of a method you must implement
        return !view.canScrollUp();
    }

    @Override
    public boolean isInAbsoluteEnd() {
	     // canScrollDown() is an example of a method you must implement
        return !view.canScrollDown();
    }
});
```

## Effect Behavior Configuration

```java

/// Make over-scroll applied over a list-view feel more 'stiff'
new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(view),
        5f, // Default is 3
        VerticalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK,
        VerticalOverScrollBounceEffectDecorator.DEFAULT_DECELERATE_FACTOR);
                
// Make over-scroll applied over a list-view bounce-back more softly
new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(view),
        VerticalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD,
        VerticalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK,
        -1f // Default is -2
        );

```

## Credits

App icons by <a href="http://somerandomdude.com/work/iconic/">P.J. Onori</a>,
<a href="http://graphicriver.net/item/wirecons-vector-icons/4586710?ref=tmthymllr">Timothy Miller</a>,
<a href="http://icons4android.com">Icons4Android</a>, 
<a href="http://icons8.com/android-icons">Icons8.com</a>
