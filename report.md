# Rapport de projet

## MLP

### Expérimentations et Résultats

Nous avons testé notre MLP sur les tables logiques ET, OU et XOR.
Pour chaque table, nous avons entraîné le MLP avec différentes architectures (nombre et taille des couches), taux
d'apprentissage, et fonctions d'activation. Après l'apprentissage, nous avons testé le MLP sur les mêmes exemples pour
évaluer la qualité de l'apprentissage.

Nous avons obtenu les résultats suivants :

| Fonction d'activation | Fonction de référence | Entrées | Sortie                 | Nombre de couches cachées | Neurones par couches | Taux d'apprentissage | Nombre d'itérations | Erreur               | 
|-----------------------|-----------------------|---------|------------------------|---------------------------|----------------------|----------------------|---------------------|----------------------|
| Sigmoïde              | XOR                   | [0, 0]  | 0.4990304094544354     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.5049288004653214   |
| Sigmoïde              | XOR                   | [0, 1]  | 0.49862051347133257    | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.5049288004653214   |
| Sigmoïde              | XOR                   | [1, 0]  | 0.5014561019638034     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.5049288004653214   |
| Sigmoïde              | XOR                   | [1, 1]  | 0.5010364792543377     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.5049288004653214   |
| Sigmoïde              | AND                   | [0, 0]  | 0.0010836773365809967  | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.010369685026790254 |
| Sigmoïde              | AND                   | [0, 1]  | 0.004876677875313595   | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.010369685026790254 |
| Sigmoïde              | AND                   | [1, 0]  | 0.00519661136199148    | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.010369685026790254 |
| Sigmoïde              | AND                   | [1, 1]  | 0.9896525415632641     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 0.010369685026790254 |
| Sigmoïde              | OR                    | [0, 0]  | 0.012622832150459404   | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 6.418540851227394E-5 |
| Sigmoïde              | OR                    | [0, 1]  | 0.9941833602402306     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 6.418540851227394E-5 |
| Sigmoïde              | OR                    | [1, 0]  | 0.9941764218149999     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 6.418540851227394E-5 |
| Sigmoïde              | OR                    | [1, 1]  | 0.9999358148399287     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 6.418540851227394E-5 |
| Hyperbolique          | XOR                   | [0, 0]  | 2.997975624305035E-4   | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 3.312593982461541E-4 |
| Hyperbolique          | XOR                   | [0, 1]  | 0.9992990904283804     | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 3.312593982461541E-4 |
| Hyperbolique          | XOR                   | [1, 0]  | 0.999298643504586      | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 3.312593982461541E-4 |
| Hyperbolique          | XOR                   | [1, 1]  | -1.7091106263497963E-4 | 1                         | [2, 2, 1]            | 0.1                  | 10000               | 3.312593982461541E-4 |

[//]: # (| Sigmoïde              | XOR                   | [0, 0]  | 0.5033188087509508 | 0                         | [2, 1]               | 0.1                  | 10000               | 0.5033188087509526 |)

[//]: # (| Sigmoïde              | XOR                   | [0, 1]  | 0.500364222984016  | 0                         | [2, 1]               | 0.1                  | 10000               | 0.5033188087509526 |)

[//]: # (| Sigmoïde              | XOR                   | [1, 0]  | 0.497409611780747  | 0                         | [2, 1]               | 0.1                  | 10000               | 0.5033188087509526 |)

[//]: # (| Sigmoïde              | XOR                   | [1, 1]  | 0.4944551814783214 | 0                         | [2, 1]               | 0.1                  | 10000               | 0.5033188087509526 |)

Cela signifie que :

- Avec la fonction d'activation sigmoïde, le MLP a pu apprendre parfaitement les tables ET et OU, mais a eu du mal avec
  la table XOR. Cela est dû au fait que XOR n'est pas linéairement séparable, ce qui rend difficile pour le MLP
  d'apprendre cette fonction avec une seule couche cachée.
- Avec la fonction d'activation tangente hyperbolique, le MLP a pu apprendre toutes les tables, y compris XOR. Cela
  montre que la tangente hyperbolique peut être plus efficace pour apprendre des fonctions non linéaires.
- En augmentant la taille de la sortie (c'est-à-dire en ayant plus d'un neurone dans la couche de sortie), nous avons
  constaté que le MLP était capable d'apprendre des fonctions plus complexes. Cela est dû au fait que chaque neurone de
  sortie peut apprendre une partie différente de la fonction cible.
- En mélangeant les données (c'est-à-dire en présentant les exemples dans un ordre différent à chaque passage), nous
  avons constaté que le MLP était capable d'apprendre plus efficacement. Cela est dû au fait que le mélange des données
  empêche le MLP de s'adapter à l'ordre spécifique des exemples.
