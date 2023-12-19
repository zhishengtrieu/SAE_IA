
## Défi 2 : Rush Hour Puzzle

### Formalisation en terme <S, A, T, C>
### S : ensemble des états
On représente un état de jeu par un tableau à 2 dimensions de taille 6, chaque caractère représentant une case du plateau de jeu.
Les caractères possibles sont les suivants :

- '.' : case vide
- 'A', 'B', 'C', ... : voiture ou camion de taille 2 ou 3
- 'R' : voiture rouge (celle qu'on doit faire sortir)

### A : ensemble des actions
On représente une action par une chaine "voiture-direction" : "A-Up", "A-Down", "B-Left", ...

### T : fonction de transition
La fonction de transition prend en entrée un état et une action, et renvoie l'état résultant de l'application de l'action à l'état.

### C : fonction de coût
Chaque action coûte un

### Exemples
- S0 : L'état initial est le suivant :
  ```
  . . A . C C
  . . A . . .
  R R A . . .
  B B B . . D
  . . . . . D
  . . . . . D
  ```
- SBut : il n'y plus que des case vides devant la voiture rouge

### Résultats

#### Algorithme DFS

##### Configuration 1
- Nombre d'états explorés : 270 nodes
- Max depth : 21
- Cout de la solution : 20

##### Configuration 2
- Nombre d'états explorés : 44807 nodes
- Max depth : 34
- Cout de la solution : 33

##### Configuration 3
- Nombre d'états explorés : 41360 nodes
- Max depth : 65
- Cout de la solution : 64

##### Configuration 4
- Nombre d'états explorés : 9444 nodes
- Max depth : 43
- Cout de la solution : 42


#### Algorithme BFS

##### Configuration 1
- Nombre d'états explorés :
- Max depth :
- Cout de la solution :

##### Configuration 2
- Nombre d'états explorés :
- Max depth :
- Cout de la solution :

##### Configuration 3
- Nombre d'états explorés :
- Max depth :
- Cout de la solution :

##### Configuration 4
- Nombre d'états explorés :
- Max depth :
- Cout de la solution :


#### Algorithme A*

##### Configuration 1
- Nombre d'états explorés : 136 nodes
- Max depth : 21
- Cout de la solution : 20

##### Configuration 2
- Nombre d'états explorés : 22405 nodes
- Max depth : 34
- Cout de la solution : 33

##### Configuration 3
- Nombre d'états explorés : 20979 nodes
- Max depth : 65
- Cout de la solution : 64

##### Configuration 4
- Nombre d'états explorés : 4690 nodes
- Max depth : 43
- Cout de la solution : 42
