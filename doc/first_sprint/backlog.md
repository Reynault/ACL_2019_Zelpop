# Backlog - First Sprint

Development of the first iteration. There is several simple features such as a graphical interface,
some kinds of tiles (trap, treasure, wall), a basic maze, and the posibility to generate one from
a file.

## Features

- The player can move in every directions.
- There is an empty default maze.
- We can generate a maze using a file.
- We have different tiles : trap, treasure, wall
- There is some monsters with basic movement. (no damage, no collision with
player)
- There is a basic interface with sprites.

## Conception

### Class Diagram

Three class diagrams has been made :

- entity shows the entity part where we have the hero and the monsters that moved in the maze.
- global shows a global view of the model.
- tiles shows the tiles part.

### Sequences Diagram

- Moving the player

Mattthieuxx -> Deplacement entité : deux (mouvement libre, mouvement pas possible à cause d'une case)

Jromary -> Génération de labyrinthe
        
Mohamed -> Affichage des textures

