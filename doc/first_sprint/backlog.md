# Backlog - Premier Sprint

Développement de la première partie du projet, à savoir, quelques fonctionnalités 
de bases et un affichage en console. (pas d'intégration graphique pour le moment)

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

Trois diagrammes de classes ont été faits :

- character : Affiche la partie Personnage de l'application.
- global : Diagramme de classe global de la partie modèle.
- tiles : Diagramme de classe de la partie Cases.

### Diagrammes de séquences