DragSortExpandiableListView
================

I had customize this DragSortExpandiableListView refer to this https://github.com/bauerca/drag-sort-listview to make android default Expandible ListView can perform drag and drop. Thanks to this repo author @bauerca 

Overview
--------

DragSortExpandiableListView (DSLV) is an extension of the Android ExpandiableListView that enables
drag-and-drop reordering of list group items only. It is a ~~major overhaul~~ complete
rewrite of
the [TouchInterceptor](https://github.com/android/platform_packages_apps_music/blob/master/src/com/android/music/TouchInterceptor.java) (TI) 
meant to give drag-sorting a polished feel. Some key features are:

1. drag and drop
2. Intuitive and smooth scrolling while dragging.
3. Support for heterogeneous item heights.
4. Public `startDrag()` and `stopDrag()` methods.
5. Public interface for customizing the floating View.

I hope you find it useful; and please, help me improve the thing!

Widget usage
------------

Three major elements define the drag-sort process. Roughly, in
order of importance, they are:

1. **Data reordering**. Drag-sorts reorder the data
underlying your list. Since DSLV
cannot know how you organize your data, the reordering must be
performed by you using the provided Listener interfaces.
2. **Drag start/stop**. Drags are started and stopped by
calling `startDrag()` and
`stopDrag()` on your DSLV instance; but some help that is.
The convenience class, DragSortController, provides all kinds of
boiler-plate for common start/stop/remove drag patterns.
3. **Floating View**. The floating View appearance and behavior is
controlled by an
implementation of the FloatViewManager interface. With this, you
can display any View you like as the floating View, and update its
appearance/location on every touch event. The DragSortController
helper class also implements this interface for convenience.

Number 1 is essential. As mentioned above, 2 and 3 can
be handled by the DragSortController helper class. Keep reading,
then head to the
demo and start studying some examples.

you also may refer to the demo how DragSortExpandibleListView is implemented

#### XML attributes

* `collapsed_height`: (dimension, 1px) Height of placeholder at original
drag position. Cannot be zero.
* `drag_scroll_start`: (float, 0.3) Start of drag-scroll regions
(defined by a
fraction of the total DSLV height; i.e. between 0 and 1).
* `max_drag_scroll_speed`: (float, 0.5) Maximum drag-scroll speed for
default linear drag-scroll profile. Units of pixels/millisecond.
* `float_alpha`: (float, 1.0) Transparency of floating View. Value from
0 to 1 where 1 is opaque.
* `slide_shuffle_speed`: (float, 0.7) Speed of shuffle animations
underneath floating View. A value
of 0 means a shuffle animation is always in progress, whereas a value
of 1 means items snap from position to position without animation.
* `drop_animation_duration`: (int, 150) Drop animation smoothly centers
  the floating View over the drop slot before destroying it. Duration
  in milliseconds.
* `remove_animation_duration`: (int, 150) Remove animation smoothly
  collapses the empty slot when an item is removed. Duration
  in milliseconds.
* `track_drag_sort`: (bool, false) Debugging option; explained below.
* `use_default_controller`: (bool, true) Have DSLV create a
  DragSortController instance and pass the following xml attributes
  to it. If you set this to false, ignore the following attributes.
* `float_background_color`: (color, BLACK) Set the background
  color of the floating View when using the default
  DragSortController. Floating View in this case is a snapshot of
  the list item to be dragged.
* `drag_handle_id`: (id, 0) Android resource id that points to a
  child View of a list item (or the root View of the list item
  layout). This identifies the "drag handle," or the View within a
  list item that must
  be touched to start a drag-sort of that item.
  Required if drags are to be enabled using the default
  DragSortController.
* `sort_enabled`: (bool, true) Enable sorting of dragged item (disabling
  is useful when you only want item removal).
* `drag_start_mode`: (enum, "onDown") Sets the gesture for starting
  a drag.
    + "onDown": Drag starts when finger touches down
      on the drag handle.
    + "onDrag": Drag starts when finger touches down on drag handle
      and then drags (allows item clicks and long clicks).
    + "onLongPress": Drag starts on drag handle long press (allows
      item clicks).
* `remove_enabled`: (bool, false) Enable dragged item removal by one
  of the `remove_mode` options below.
* `remove_mode`: (enum, "flingRight") Sets the gesture for removing the
  dragged item.
    + "clickRemove": Click on item child View with id `click_remove_id`.
    + "flingRemove": Fling horizontal anywhere on item.
* `click_remove_id`: (id, 0) Android resource id that points to a
  child View of a list item. When `remove_mode="clickRemove"` and
  `remove_enabled="true"`, a click on this child View removes the
  containing item. This attr is used by DragSortController.
* `fling_handle_id`: (id, 0) Android resource id that points to a
  child View of a list item. When `remove_mode="flingRemove"` and
  `remove_enabled="true"`, a fling that originates on this child
  View removes the containing item. This attr is used by
  DragSortController.

Contributing
------------

First of all, thank you! Many of your pull requests have added
tremendous value to this project. Your efforts are truly appreciated!

Now that the project is fairly mature, I would like to lay out
some (loose) rules for future pull requests. So far I have
only one (of course, you should help me add more). Here's the list:

* Avoid pull requests that are small tweaks to default
  DragSortController behavior. This class is merely a guide/helper
  for creating more complex drag-sort interactions. For example,
  if you don't
  like the feel of the default fling-remove initiation for your
  app, then you should not create a pull request that "fixes"
  the behavior. Rather, try to modify or subclass DragSortController
  for your particular needs. That said, if a "must-have" touch
  pattern arises, I think there is some wiggle room in this rule.

License
-------

```
A subclass of the Android ListView component that enables drag
and drop re-ordering of list items.

Copyright 2012 Carl Bauer

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

