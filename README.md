# Zelpop

## Description

Zelpop is a maze game created in the context of a student project.
The idea is to develop a game using an agile method of developpement. (SCRUM)

## Running the game

An ant build file is given in the repository at the root of the project. 
It builds and create a jar automatically, if you want to use it, please execute the following command:

``` ant -buildfile build.xml ```


## First Sprint report

To sum up everything we have done in the first sprint, we can
say that we managed to implement all the features we planned
to have on our game for the first sprint, we shared the work
between the team members and each one did his part of the job, 
so Julien Romary worked on generating Sprite and draw the hero , 
monsters and the maze, Reynault Sies worked on creating the maze
the hero and monsters movements, Matthieu Olejniczak worked on 
generating the maze from file and Mohamed Elridha Salhi making
this report.


### Implemented Features

So the player can move in every direction on the maze, we can generate
the game's default maze from a file, the maze may contains some 
monsters and as we mentioned, with no damage and no collision
with the player. We also have a basic interface with sprites.


### Problems

We had a few problems with the generation of sprites, we changed how 
it works by adding a sprite manager for entities and one for tiles.
We had no problems implementing the rest of features.

## Group - TP3

- Julien Romary
- Reynault Sies
- Matthieu Olejniczak
- Mohamed Elridha Salhi


## Documentation

[Help with GIT branching](https://nvie.com/posts/a-successful-git-branching-model/)