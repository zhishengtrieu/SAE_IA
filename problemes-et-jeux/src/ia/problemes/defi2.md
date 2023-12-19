# Défis 2 : Modélisation d'un problème de recherche (Rush Hour)
## Formalisation du terme <S, A, T, C>
### S : ensemble des états
Pour le jeu Rush Hour, un état est représenté par un tableau de 6x6 cases, chaque case peut contenir une voiture ou être vide. Une voiture est représentée par un char (couleur) et sa position est représentée par un couple (x, y) où x et y sont des entiers compris entre 0 et 5.
### A : ensemble des actions
Pour le jeu Rush Hour, une action est représentée par un couple (voiture, déplacement) où voiture est une voiture et déplacement est une direction (UP, DOWN, LEFT, RIGHT). Une action est valide si la voiture peut se déplacer dans la direction donnée.
### T : fonction de transition
Pour le jeu Rush Hour, la fonction de transition prend en paramètre un état et une action et retourne l'état résultant de l'application de l'action sur l'état.
### C : fonction de coût
Pour le jeu Rush Hour, la fonction de coût prend en paramètre un état et une action et retourne le coût de l'action sur l'état (nombre de coups pour .