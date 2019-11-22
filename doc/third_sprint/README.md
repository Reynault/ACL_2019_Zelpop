# Backlog - Third Sprint

Development of the third iteration. In this iteration, we add an attack animation (for monsters and heros), a new monster
that focus the player during the whole game. A new monster that can go through walls. A new kind of wall : breaking wall

## Features

- Attack animation (monsters and hero)
- A new monster that focus the player (gobelin)
- A new monster (ghost) that pass through walls
- Breakable walls
- A stat system, with an interface update that display stat:
    - life
    - attack
    - defence (new): when an entity(A) attacks another entity(B), it fights back.
    (A take damages from B based on its defence stat)
    - hp regen (new): gain a certain amount of HP after a certain number of round.
- new Trap: When the hero walks on it, he is teleported in an another random location of the current maze.
- The player can destroy traps.
- A new interface which informs us how to play at the beginning of the game.

## Attribution

|     Member     |             Feature                          | State |
|:--------------:|:--------------------------------------------:|:-----:|
|   Reynault     | Attack animation                             |   -   |
|    Reda        | Gobelin                                      |   -   |
|     Reda       | Ghost                                        |   -   |
|     Jromary    | Stat system (model)                          |   -   |
|    Matthieu    | Stat system (interface)                      |   -   |
|    Reynault    | Teleport                                     |   -   |
|     Reynault   | Destructible traps                           |   -   |
|   Matthieu     | New how to use interface                     |   -   |
|   Reynault     | Update Random maze to implement new features |   -   |

## Implementation ideas

- Attack animation
    - Update SpriteManager for entities
    - Update Attack sequences diagram
- Gobelin
    - New Move implementation (new Class implements Move) --> New behavior in BehaviorFactory
    - New Class Gobelin (Entity Factory)(TextureFactory)
- Ghost
    - New Class Ghost (Entity Factory)(TextureFactory)
- Breakable walls (Tile factory)
    - New kind of wall (TextureFactory)
        - hp
        - new method to get decore
- Stat system
    - Entity -> defence, hp regen
        - new method take counter
        - new method regen
    - Change attack diagams (Add counter system)
    - Change Evolve diagrams (hp regen)
- Stat interface
    - Changing draw in maze (Max hp / current hp, Defence counter, regen counter, attack counter)
- Teleport
    - New tile. new Action type (tileFactory)
- Destructible traps
    - New Attack class
    - New isTrap method
        - Hp
        - getDamage(int damage)
        - isDestroyed()
- How to use interface
    - New GameState --> At the beginning of a game (new game) <press any key to continue>
- Update random maze
    - trap can now spawn in doors and corridors
    - new type of entities
    - new type of tiles