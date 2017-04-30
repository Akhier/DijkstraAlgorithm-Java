#### DijkstraGraph Class (originally Graph)

Renaming this to make it state directly what it is.
Graph was much too generic.
Also of note is I will not be including a GraphMaker in the core files.
Later on a more properly named GraphFactory will be done.

##### Structure

There was the ability to grab a list of all the nodes.
From what I can tell this was mostly just there for testing.
Till I find a use for it that will be gone.

At the end there I had a lot of ways to set the source node.
Because I am breaking backwards compatibility some of them can be removed.
An important part was it only let you set one if it was already in the graph.
Also the value can just be the aggregate cost.

Another thing is that every time you added something it would call reset.
Going to have to do it some other way.
Probably can just leave it at the path calculation step.

* Source Points
* Ability to add Points and Edges
* Calculate the map
* Get the shortest path
