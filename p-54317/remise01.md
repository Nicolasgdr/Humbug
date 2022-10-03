# Matricule G54317

## Le modèle v0.1

### Énumération SquareType et Classe Square

Package `g54317.humbug.model` : OK

Javadoc mise à jour : OK

### Énumération Direction

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, l'en-tête ne contient que l'auteur.

Le code doit être formaté (via la commande Format de Netbeans Alt-Shift-F)

##### Littéraux

Aucune remarque.

#### Attributs

Aucune remarque.

#### Méthodes

Aucune remarque.

### Classe Position

#### Généralités

##### Documentation

Javadoc incomplète, les paramètres du constructeur ne sont pas décrits, les objets retournés ne sont pas décrits, l'en-tête ne contient que l'auteur.

##### Tests unitaires 

Valides et suffisants.

Pourquoi le nom de la classe de test ne correspond pas au nom de la classe ?

#### Attributs

Les attributs peuvent être déclarés `final`.

#### Méthodes

##### `equals` et `hascode`

Aucune remarque.

##### Méthode `next(Direction)`

Quelle est l'utilité de la variable `placement` ?

### Classe Board

#### Généralités

##### Documentation

##### Tests unitaires 

Valides et suffisants.

Appel à la méthode `setUp()` au lieu d'une correction des  plugins de Maven comme proposé sur poEsi : https://poesi.esi-bru.be/mod/page/view.php?id=1968

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `getInitialBoard()`

Aucune remarque.

##### Méthode `isInside(Position)`

L'ensemble de ces blocs `if` est inutile et peut être transformé en une seule variable booléenne retournée directement. Ce changement va rendre la méthode plus lisible.

##### Méthode `getSquareType(Position)`

Le paramètre `position` doit être validé grâce à la méthode `isInside`.

##### Méthode `getNbRow()` `getNbColumn()`

Aucune remarque.

##### Méthode `setSquare` 

Le paramètre `position` doit être validé grâce à la méthode `isInside`.

##### Méthode `setGrass` 

La méthode n'est jamais utilisée.

### Classe View

#### Généralités

##### Documentation

La Javadoc est incomplète dans l'interface et la classe.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `displayBoard(Board board)`

La complexité de la méthode est trop grande, le nombre d'opération effectuée peut être diminuée.

Pour un plateau de 3x3 et 2 animaux sur le plateau, l'affichage va effectuer 3x3x2 = 18 opérations. On peut réduire ce nombre d'opération à 3x3+2 = 11 opérations.  Quelle solution proposes-tu ?

On peut remarquer que plus la taille du plateau et le nombre d'animaux grandit, plus l’écart entre les deux solutions devient important.

N'hésite pas à ajouter des méthodes `private` pour rendre ton code plus lisible.

##### Méthode `displayError(String message)`

Aucune remarque.

##### Méthode `askPosition()`

Les deux boucles contiennent du code redondant. Une méthode `private` pourrait rendre plus lisible cette méthode.

##### Méthode `askDirection()`

Aucune remarque.

##### Méthode `main()`

La méthode peut être enlevée de cette classe.

## Le modèle v0.2

### Classe Abstraite Animal

#### Généralités

##### Documentation

La Javadoc est incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `abstract Position move()`

Aucune remarque.

### Classe Snail

#### Généralités

##### Documentation

La Javadoc est incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

un `else` après un bloc `if` qui se termine par `return` n'est pas utile

### Classe Spider

#### Généralités

##### Documentation

La Javadoc est incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

Code redondant entre `Spider` et `Snail`. Est-ce normal selon toi et comment corrigerais-tu cette redondance ?

### Classe Game

#### Généralités

##### Documentation

La Javadoc est incomplète.

##### Implémentation de l'interface

#### Attributs

Les attributs doivent être de visibilité `private` dans ce cas.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Board getBoard()`

Aucune remarque.

##### Méthode `Animal[] getAnimals()`

Aucune remarque.

##### Méthode `void startLevel(int level)`

les méthodes `board` et `animals` retournent des éléments mais ne les construisent. Appeler ces méthodes n'a aucun intérêt. Par contre il faudrait construire le niveau en fonction du `level` en paramètre. Comme il n'y a qu'un seul niveau dans cette itération, la construction par défaut des attributs `board` et `animals` en ligne 10 et 11 effectue le travail, mais n'est pas logique vu le paramètre de la méthode.

##### Méthode `boolean levelIsOver()`

Si un suel animal n'est pas sur une étoile, on peut arrêter de parcourir le tableau d’animaux.

##### Méthode `void move(Position position, Direction direction)`

- quelle est l'utilité de la variable `verif`
- le paramètre `position` n'est jamais utilisé, comment la méthode fait-elle pour sélectionner un animal à déplacer ?

### Classe Controller

#### Généralités

##### Documentation

Javadoc incomplète.

##### Gestion des exceptions

#### Attributs et interfaces

Les attributs peuvent être déclarés `final` et doivent être de visibilité `private`

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `startGame`

- la variable `position` n'est jamais utilisée
- des instructions `System.out.println()` sont présentes hors de la classe `View` qui doit gérer l'affichage
- pourquoi est-ce toujours le même animal qui doit se déplacer ? `game.getAnimals()[0].move()`