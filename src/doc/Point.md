#### Point Class (original Vector2D)

The only thing this actually has to do is store an aggregate cost and if it was visited.
To allow for an easier use though this class from what I have seen generally ends up with things like the (x,y) coords and an Id.
One of the things I did add before this startover was a static count of how many had been visited.
This was to allow an easier check on if there were more to visit.

One of the things about this class that has bugged me is what to call it.
I currently have it as 'Point' but 'Tile' is also up there in my list.
The only thing stopping me from just deciding on one is the use of these name in other things.
'Point' is used everywhere and 'Tile' is likely to be used in roguelike style games which is the main target use for this.
There isn't really a 'unique' identifier for it sadly as it is just representation of a point.
Going to stick with point for the moment because meh.

##### Structure

The first change I have decided for this version is there will be an optional 'Z' variable to represent different levels which are interconnected.

Another thing is I am just removing the deadend stuff.
This works in the abstract maps but with how a 2d tile map is shaped there are too few 'deadends' to make it worth my while to bother with.

Other than that it should be basically the same.
Minor changes to note is that the Id will be final.
Also the compareTo will actual be the proper use of it.
Anyway what this class needs is as follows.

* An ID
* X, Y, and Z
* Count of total visited
* Aggregate Cost
* toString override
* compareTo override
