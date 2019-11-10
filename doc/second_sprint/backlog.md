# Backlog - Second Sprint

Development of the second iteration. In this iteration, we add a score system for the player, an attack system, 
an health system so the can die, random generated mazes, a saving and loading system and the 
behavior of tiles such as traps and treasures which are based on the other systems. (life, score) 

## Features

- There is now an HP system, the player loses if his life is at 0. (same for a monster)
- The player can attack nearby monsters.
- There is now a collision with the monster, the player can't pass through it.
- Monsters can hit the player when he is nearby. (for now, just next to the monster)
- There is a score that increases when the player beat a Maze, a monster or find a chest.
- There is now the behavior of traps and treasures.
    - Traps hit the player.
    - Treasures increase the score of the player.
- We can save the game.
- We can continue the previous game.
- On each level, there is a stairs which lead to the next maze.
- Each maze is randomly generated.
- There is now a menu. (New game, Continue, Exit)

## Attribution

|     Member     |             Feature             | State | Tests |
|:--------------:|:-------------------------------:|:-----:|:-----:|
|      Reda      | Attack system                   |   -   |   -   |
|    Reynault    | HP system                       |   -   |   -   |
|     Julien     | Maze randomly generated         |   -   |   -   |
|    Matthieu    | Tiles behavior (Trap, treasure) |   -   |   -   |
|    Reynault    | Score system                    |   -   |   -   |
|    Matthieu    | Save and continue a game        |   -   |   -   |
|     Julien     | The stairs tile                 |   -   |   -   |
| Reynault, Reda | New menu interface              |   -   |   -   |

## Conception

### Class Diagram

### Sequences Diagram
