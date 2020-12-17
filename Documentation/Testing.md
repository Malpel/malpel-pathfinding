# Testing

## Performance testing
The algorithms' performances was tested on five different maps of three different sizes, or 15 maps in total. The maps can be found in the [MovingAi city/street maps collection](https://movingai.com/benchmarks/street/index.html), and are also included with the project.

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
Unit tests can be run by using the command `./gradlew test` in the project's root directory. A coverage report can be genereate by the command `./gradlew jacocoTestReport`. Check the [user guide](User_guide.md) for more information.
