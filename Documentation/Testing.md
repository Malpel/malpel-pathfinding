# Testing

## Performance testing

### Algorithms
The algorithms' performances was tested on five different maps of three different sizes, or 15 maps in total. The maps can be found in the [MovingAi city/street maps collection](https://movingai.com/benchmarks/street/index.html), and are also included with the project. The tests use the longest path found in the maps' scenario files.

#### Results

 | Map | Path length | Dijkstra's algorithm|  A* | Jump Point Search |
 |:----------|:-----:|:-----:|:-----:| :------:|
 | Berlin_0_256 | 371.62950897 | 9.917243ms | 3.620455ms | 1.007111ms |
 | Berlin_0_512 | 746.80317382 | 48.473685ms | 24.323067ms | 8.399733ms |
 | Berlin_0_1024 | 1539.80230712 | 212.727073ms | 85.336718ms | 30.896166ms |
 | London_0_256 | 399.67113952 | 8.700666ms | 14.059538ms | 9.86284ms |
 | London_0_512 | 831.07020111 | 50.553196ms | 24.62263ms | 24.559686ms |
 | London_0_1024 | 1603.75562591 | 219.211557ms | 370.861596ms | 1216.803605ms |
 | Moscow_1_256 | 439.96046142 | 8.64196ms | 6.019834ms | 3.111429ms |
 | Moscow_1_512 | 787.51385192 | 50.539897ms | 9.129342ms | 4.780055ms |
 | Moscow_1_1024 | 1546.84898833 | 239.555232ms | 152.951267ms | 240.561535ms |
 | NewYork_0_256 | 362.90158691 | 9.607566ms | 4.135921ms | 2.866083ms |
 | NewYork_0_512 | 714.54328918 | 50.161381ms | 14.179066ms | 27.365501ms |
 | NewYork_0_1024 | 1467.27539519 | 230.305382ms | 136.899126ms | 784.454114ms |
 | Paris_0_256 | 390.30360718 | 8.760021ms | 5.12568ms | 2.906305ms |
 | Paris_0_512 | 723.61940765 | 47.72416ms | 42.611185ms | 49.540291ms |
 | Paris_0_1024 | 1527.81955108 | 214.208697ms | 100.320212ms | 58.23837ms |

 The numbers at the ends of the maps' names indicate their size: 256x256, 512x512, and 1024x1024. The tests were repeated 101 times, discarding the first result. The performance of the algorithms is measured by the median computation time in milliseconds.

 For supposed improvements over Dijkstra's algorithm, A* and Jump Point Search perform surprisingly poorly on some maps, even losing to Dijkstra's. The most baffling are the London_0_256 and London_0_1024 maps where their performance is the worst, Jump Point Search taking over a second(!). With Dijkstra and A* there is no element of unfamiliarity, but Jump Point Search was a completely new algorithm, so its implementation may be poor or even outright failed. It could also be that something in maps similar to London_0_xxx just disadvantage the algorithm. In most of the smaller maps their performances are more in line with what I was expecting: Jump Point Search fastest, then A*, and Dijkstra's.

 A major hindrance for the algorithms is the `MinHeap` implementation, which, regardless of good benchmarking results, is in practice massively slower than `java.util.PriorityQueue`. With Java's PriorityQueue<> the times for smaller maps, for example, drop below one millisecond.

 ### Data structures

 The performance of data structures was tested by doing a 1000 runs of 10000 additions, pollings, and gets. The times are the median in nanoseconds.

 #### `pathfinding.domain.MinHeap` vs `java.util.PriorityQueue` 
 | Operation | `pathfinding.domain.MinHeap` | `java.util.PriorityQueue` |
 |:-----|:-------:|:-------:|
 | add(Node) | 32 | 31 |
 | poll() | 32 | 30 |


#### `pathfinding.domain.List` vs `java.util.PriorityQueue` 
 | Operation | `pathfinding.domain.List` | `java.util.PriorityQueue` |
 |:-----|:-------:|:-------:|
 | add(Node) | 24 | 28 |
 | get(int) | 18 | 17 |

## Unit testing
[![codecov](https://codecov.io/gh/Malpel/malpel-pathfinding/branch/main/graph/badge.svg?token=LJB8SADPGQ)](https://codecov.io/gh/Malpel/malpel-pathfinding)

Unit tests were done using JUnit and cover the implemented algorithms and datastructrures. Only files in `pathfinding.benchmarking`, `pathfinding.io`, `pathfinding.ui`, and `pathfinding.util` were not tested, although some parts of them are indrectly tested in some of the tests.

### Algorithms
All algorithms share the same tests: three tests of finding the shortest path, as well as one test where the result should be a null path.

### Data Structures

- `MinHeapTest`: The tests focus on verifying the ordering of `Nodes` after adding and polling. There are several similar tests that make sure that the ordering works when done on different sides and depths of the tree.

- `ListTest`: Since the only functionality offered by the `List` focuses on accessing a `Node` at a given index, and adding a new `Node` to the list, the tests only test that adding to the `List` works.

- `DequeTest`: These tests rely on `ListTest`. They only verify that the functions call the proper `List` functions.

## Running the tests

### Performance tests
Performance tests can be run by simply running the program and selecting option 2) in the menu. Check the [user guide](User_guide.md) for more information.

### Unit tests
Unit tests can be run by using the command `./gradlew test` in the project's root directory. A coverage report can be generated by the command `./gradlew jacocoTestReport`. The report can be found in `/malpel-pathfinding/build/reports/jacoco/tests/index.html`.
