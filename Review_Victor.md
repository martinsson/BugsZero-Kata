# Retours de base :
- Nom des images sans convention et avec des espaces > pas bon.
- L'utilisateur ne peut pas répondre aux questions, il n'y a aucune lecture de la console pour avoir la réponse.
- On n'ajoute que 3 joueurs et ils auront toujours les mêmes noms, de plus le jeu est limité pour 6 joueurs dans la déclarations des variables

# Fichier `GameRunner.java` :
- Retours à la ligne en trop : l.9-15-24-27-35-36-37
- Remettre les imports dans le bon sens (package puis adaptionsoft puis java.util)
- Le `rand.nextInt(5) + 1` donne un chiffre entre 1 et 5, ce genre de dé n'existe pas > correction : `rand.nextInt(6) + 1`
- Après le roll, le méthode `playGame()` devrait appeler `didPlayerWin()` pour connaitre le résultat. Ici L'application va tourner en boucle toute seule en appelant les méthodes `wrongAnswer()` et `wasCorrectlyAnswered()` au bon vouloir de la condition sur une nombre random qui n'a rien à faire ici.



# Fichier `Game.java` :
- Retours à la ligne en trop : l.38-39-68-73-97-132-133-136
- Espaces en trop (mauvaise intendation) : l.67
- Variable `players` non typée > une ArrayList de quoi ? > faire une ArrayList de String.
- Même remarque pour les LinkedList de questions > 
- Nom des méthodes à changer : 
  - `wasCorrectlyAnswered()` > `correcAnswer()` (pour rester cohérent avec `wrongAnswer()`)
- Méthode `currentCategory()` à refacto : 
  - Faire un switch-case des possibilitées ou regrouper les ifs. 
- Méthode `add()` :
  - `return true` ne sert à rien.
- `places[currentPlayer] = places[currentPlayer] + roll;` > `places[currentPlayer] += roll;`
- Méthode `didPlayerWin()` > simplifier le return > `return purses[currentPlayer] == 6` > cela permet aussi de renvoyer TRUE quand le joueur gagne et non FALSE comme présentement.
- Méthode `askQuestion()` > on peut enlever les retours à la ligne, on ne dépasse pas les 90 caractères de longueur (après ce point dépend des conventions appliquées)
- Méthode `wasCorrectlyAnswered()` :
  - faire un seul if avec comme condition `!inPenaltyBox[currentPlayer] || isGettingOutOfPenaltyBox` pour valider la bonne réponse.
  - Ne pas return true si le joueur ne peux pas avoir la bonne réponse (l.130).
  - l.116 à l.118 > on change de currentPlayer avant de donner la pièce, donc le joueur qui a bien répondu n'a pas se récompense. > faire le `currentPlayer ++` à la fin.

# Gros correctifs à faire :
- Rajouter le fait de récupérer l'input de l'utilisateur.
- Changer le déroulement du jeu dans la boucle While de GameRunner
  - Roll > Check si gain.
- Faire une classe Player pour simplifier le suivi de sa bourse, de sa position et de sa situation par rapport à la prison.