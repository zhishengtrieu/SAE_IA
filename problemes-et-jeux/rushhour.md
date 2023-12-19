
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
On part du principe que les voitures ne peuvent se déplacer que d'une seule case à la fois.

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

### Solutions avec A*

#### Configuration 1
Situation initiale :
```
. . A . C C
. . A . . .
R R A . . .
B B B . . D
. . . . . D
. . . . . D
```

Solution :
```
B-Right > C-Left > B-Right > D-Up > D-Up > D-Up > B-Right > A-Down > A-Down > A-Down > R-Right > R-Right > R-Right > A-Up > A-Up > A-Up > B-Left > D-Down > D-Down > D-Down
Solved !
```

Situation finale :
```
. . A C C . 
. . A . . . 
. . A R R . 
. . B B B D 
. . . . . D 
. . . . . D 
```

#### Configuration 2
Situation initiale :
```
E E E F . C
. . A F . C
R R A . . H
. . A G G H
B B B . D .
. I I . D .
```

Solution :
```
F-Down > E-Right > H-Down > C-Down > E-Right > E-Right > A-Up > G-Left > D-Up > D-Up > D-Up > G-Right > A-Down > E-Left > B-Right > C-Up > B-Right > H-Up > I-Left > B-Right > E-Left > E-Left > A-Down > A-Down > F-Up > R-Right > R-Right > D-Up > R-Right > A-Up > A-Up > B-Left > H-Down
Solved !
```

Situation finale :
```
E E E F D C 
. . A F D C 
. . A R R . 
. . A G G H 
. . B B B H 
I I . . . . 
```

#### Configuration 3
Situation initiale :
```
E E F C . .
A . F C . .
A R R C . .
A . . G G G
B B B . D H
I I . . D H
```

Solution :
```
G-Left > G-Left > D-Up > B-Right > D-Up > B-Right > H-Up > A-Down > H-Up > H-Up > I-Right > A-Down > D-Up > R-Left > F-Down > G-Right > G-Right > B-Right > F-Down > F-Down > R-Right > A-Up > I-Left > F-Down > G-Left > G-Left > A-Up > E-Right > A-Up > G-Left > C-Down > E-Right > E-Right > E-Right > C-Up > G-Right > G-Right > G-Right > F-Up > A-Down > I-Right > A-Down > A-Down > R-Left > F-Up > F-Up > G-Left > B-Left > F-Up > R-Right > A-Up > A-Up > G-Left > A-Up > D-Down > B-Left > D-Down > G-Left > C-Down > E-Left > B-Left > C-Down > H-Up > C-Down
Solved !
```

Situation finale :
```
A . F E E H 
A . F . . H 
A R R . . . 
G G G C D . 
B B B C D . 
. I I C . . 
```

#### Configuration 4
Situation initiale :
```
A B C C D E
A B . . D E
F R R I . J
F H H I . J
F . L M M M
K K L N N N
```

Solution :
```
I-Up > H-Right > H-Right > L-Up > K-Right > F-Down > R-Left > L-Up > D-Down > M-Left > J-Down > C-Right > E-Down > C-Right > L-Up > L-Up > R-Right > I-Up > R-Right > B-Down > B-Down > B-Down > R-Left > I-Down > C-Left > R-Left > L-Down > E-Up > C-Left > C-Left > I-Up > J-Up > L-Down > M-Right > L-Down > R-Right > R-Right > D-Up > R-Right > L-Up > M-Left > J-Down
Solved !
```

Situation finale :
```
A C C I D E 
A . . I D E 
. . L R R . 
F B L H H J 
F B M M M J 
F K K N N N 
```

