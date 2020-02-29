## Setting up

Requirements: 
- Java 8 and above
- Gradle (latest)
- Spring (latest)

## To test
In the root directory, open up a cmd terminal and type:
`gradlew bootRun`. This will build and run the jar file. 
Navigate into /frontend where the test files are. Open up another terminal and type:
`rspec`. This will run the test files.


## Responsibilites of different classes:

GamesController: Serves as the entry point to the application. recieves http requests and process them.

ApplicationManager: Serves as the main model/logic component. Handles the state of the app including all the games.

Game: Logic class for handling a game. Contains all the information required to process a game.

RandomGenerator: Utility class to generate random sequences of strings used as boards or random user auth token.

BoardFactory: Generates different types of boards (default / custom).

Dictionary: Parses the dictionary text file into a dictionary

GameInfo: Data transfer object for http requests.

Solver: Interface to handle solving a game.

DfsSolver: A concrete implementation of solving a game which uses depth_first_search.

GameFactory: Generates games

Common: Util class 

SpringContext: Util class to allow non-spring beans to manage spring beans


## Design decisions:

GamesController/ApplicationManager: Respectively the Controller/Model in the MVC framework. large components which can be reused throughout the application. Follows the Singleton pattern

GameFactory: abstracts the details of game creation with handling of game logic. Follows the Factory design pattern. This can be useful in future if we were to add different kinds of games, then the GameManager which manages games do not need to care about what kind game is being created.

Solver/DfsSolver: abstracts the solving of a game(i.e how to find out if a move is valid). The GameManager does not need to care about how to solve the problem, it just needs to call the method defined in Solver interface. This follows the strategy design pattern, abstracting the work that needs to be done from how to do it. DfsSolver in particular uses depth_first_search to do its job. This can be done in worst-case O(n^2) time complexity where n is the number of vertices(in this case tiles). This is because in worst-case we do a search for each tile and each search traverses all possible tiles. Another approach could be to generate all possible words for a given board at the start and check validity at runtime in O(m) time using a hashset where m is the length of the longest string possible.

BoardFactory/RandomGenerator: abstracts the generation of the objects from how to generate it. GameManager does not need to care about how to create the board. similar idea as GameFactory

In general, the classes aim to minimise coupling and maximise cohesion. Each class does one thing and one thing only such as creating an entity or handling input data. There is also an inversion-of-control so that higher level modules do not depend on lower ones. For example, GameManager which is a higher level module is not dependent on lower level ones such as board or tiles. In particular, Spring is used as a dependency inversion container to inject dependencies into the classes itself. An example is how the solver and generator is "injected" into the GameManager class.

## To be improved:

1. Better documentation.
2. Creating more interfaces to specify the contract between classes.
3. Better error handling. I have no idea whats the expected behaviour in different cases.
4. Writing unit/integration testing :0
5. Remove some hard coding.
6. Handling of edge cases.


## Approach to solving this problem:

1. Planning architecture and components as well as their responsibilities (1/2 day)
2. Writing code (~1-2 days)
3. Figuring out how to efficiently check if a word exist on the board (1/2 day)
4. Refactoring (1/2 day)
5. Debugging (1 day)

