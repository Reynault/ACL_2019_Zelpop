# Zelpop

## Description

Zelpop is a maze game created in the context of a student project.
The idea is to develop a game using an agile method of developpement. (SCRUM)

## Running the game

An ant build file is given in the repository at the root of the project. 
It builds and create a jar automatically, if you want to use it, please execute the following command:

``` ant -buildfile build.xml ```

## How to play

- Move with Z = UP, Q = LEFT, S = DOWN, D = RIGHT
- Attack with SPACE
- Change level above stairs with E
- Save with O
- Menu with ESCAPE

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

## Second Sprint report

During this sprint, we manage to implement all the features explained in the backlog.
However, there was a problem with the save use case. We wanted to use the serialization to
simplify the process, but it wasn't as simple as we expected and we got a lot of bugs. We had
to change some aspects of our conception. 

For example, we can't serialize things such as Buffered images,
and some objects where singleton, and static objects can't be serialize.
So in the next sprint, we will make sure that the implementation of such things are possible.

Furthermore, we searched a lot about the generation of random mazes, we have tested multiple algorithms before
ending up with the current one.

So to summarize, we were a little overconfident on certain aspects, in the future we will make sure to 
estimate better our choices.

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