# Progress report 3

The third week began by discovering some massive bugs in the algorithms. I also found a flaw in the design of the Nodes that are used, but thought up a couple of solutions (which have not been implemented yet). Thanks to unit tests, at least the most obvious mistakes have been fixed. I also started working on my own implementation of the priority queue, or rather the minimum heap I should say. Currently, it has most of the functionality needed in the algorithms - and it has tests! Some of the branches of the various if-else segments in one of the functions are not tested, but getting those covered would mean writing the same test over and over again (which, to be honest, I have already sort of done). In addition, the code now has javadoc comments.

The MinHeap still needs work. It needs at least a way to utilise a custom comparator, because the ordering of Nodes depends on different things in A* and Dijkstra's algorithm. Next week, I hope that MinHeap will be completed, and that work has at least started on implementing the list datastructure.

Time spent this week: ~12h