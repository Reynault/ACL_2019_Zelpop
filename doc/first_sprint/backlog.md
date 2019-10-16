# Backlog - Premier Sprint

## Fonctionnalités

- Le héros est placé sur le plateau de jeu et peut s’y déplacer à l’intérieur.
- Labyrinthe par défaut avec murs infranchissables.
- Labyrinthe généré par lecture de fichier. Deux choix :
    - Tableau dans le fichier :   
        1 1 1 1 1  
        1 1 1 1 1  
        1 1 4 1 1  
        1 1 1 1 1  
        1 1 1 1 1  
    - Json like :  
    {  
    Width : 10,  
    Height : 10,  
    Murs : [
        {
            x : 1
            y : 1
        }, ...
        ]  
    }

## Conception

### Diagramme de classes

### Diagrammes de séquences