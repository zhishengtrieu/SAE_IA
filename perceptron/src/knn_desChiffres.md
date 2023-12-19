# Résultats
## KNN
- Nb imagettes : 1000 
- Pourcentage de reussite : 78% 
- Nombre de reussite : 789 
- Nombre d'echec : 211
## MLP
### Configuration 1
- Nb imagettes : 1000
- Fonction d'activation : tanh
- Neurones : 
  - Entrées : 784
  - Cachées : [9]
  - Sorties : 10
- Accuracy: 56.599999999999994%
### Configuration 2
- Nb imagettes : 1000
- Fonction d'activation : sigmoid
- Neurones :
    - Entrées : 784
    - Cachées : [9]
    - Sorties : 10
- Accuracy: 13.100000000000001%
### Configuration 3
- Nb imagettes : 1000
- Fonction d'activation : tanh
- Neurones :
    - Entrées : 784
    - Cachées : [100]
    - Sorties : 10
- Accuracy: 47.099999999999994%
### Configuration 4
- Nb imagettes : 1000
- Fonction d'activation : sigmoid
- Neurones :
    - Entrées : 784
    - Cachées : [100]
    - Sorties : 10
- Accuracy: 9.9%
### Configuration 5
- Nb imagettes : 1000
- Fonction d'activation : tanh
- Neurones :
    - Entrées : 784
    - Cachées : [250]
    - Sorties : 10
- Accuracy: 33.800000000000004%
### Configuration 6
- Nb imagettes : 1000
- Fonction d'activation : sigmoid
- Neurones :
    - Entrées : 784
    - Cachées : [250]
    - Sorties : 10
- Accuracy: 15.5%
### Configuration 7
- Nb imagettes : 1000
- Fonction d'activation : tanh
- Neurones :
  - Entrées : 784
  - Cachées : [100, 100]
  - Sorties : 10
- Accuracy: 25.6%
### Configuration 8
- Nb imagettes : 1000
- Fonction d'activation : sigmoid
- Neurones :
  - Entrées : 784
  - Cachées : [100, 100]
  - Sorties : 10
- Accuracy: 9.9%

## Conclusion
Le KNN est plus performant que le MLP pour la classification des chiffres manuscrits.
Concernant le choix de la fonction d'activation du MLP, la fonction tanh est plus performante que la fonction sigmoid.
Une couche cachée de 9 neurones semble être la plus performante pour la fonction tanh et une couche cachée de 250 neurones semble être la plus performante pour la fonction sigmoid.
L'ajout d'une couche cachée supplémentaire ne semble pas améliorer les performances du MLP.