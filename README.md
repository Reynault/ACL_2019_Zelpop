# Zelpop

## Description

Zelpop is a maze game created in the context of a student project.
The idea is to develop a game using an agile method of developpement. (SCRUM)

### Running the game

A release has been made, it gives you a .jar and all the sources of the project. (tag = v1.0)
You can download it and execute the .jar from git.

If you want to execute it from the main project, please follow the following steps: 

## Installing and running the game in Intellij

We worked with Intellij so in order to get the project
to work on it you'll need to import it with intellij and to set
the project structure. So you'll have to open the file menu, then project structure,
and project. At this point, you'll have to specify the output directory which is usually
the out/ directory, after that, you'll need to lower project language level to 10.

Finally, go to the modules section, you'll need to specify the source folder which is src, 
the exclude folder which is out and the resources folder which is res. (This point is very important, 
cause when you will run the project on intellij, it will needs to retrieve files such as textures from it)

### Junit 5

- After uploading the project make sure that you add the JUnit 5 which been
used to create the project tests.

## Execute Using the JAR file

- To run the game all you have to do is to execute the jar file which 
can be found by the following path :

```
ACL_2019_Zelpop\out\artifacts\ACL_2019_Zelpop_jar\ACL_2019_Zelpop.jar
```

- You can execute the jar using this commands :

```
java -jar ACL_2019_Zelpop\out\artifacts\ACL_2019_Zelpop_jar\ACL_2019_Zelpop.jar
```


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