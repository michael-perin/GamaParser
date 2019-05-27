# Projet de fin d'année INFO3 à Polytech' Grenoble

## Game Play & Format d'échange imposés, mais thème libre

Le projet consiste à réaliser un moteur de jeu à base d'automates.

Ce fichier décrit les contraintes imposées et des formats de description 
- des comportements sous forme d'automates.
- des animations graphiques sous forme de sprite sheet + séquence d'animation

L'intérêt étant de pouvoir importer et échanger des comportements et des graphismes entre différents jeux.

## Game Play  

* **Jeu en 2D** : plateforme ou carte vue du dessus

* absolument **Toutes les entités* du jeu ont un comportement défini par un automate**

  - un/deux entités joueurs controlées au clavier via un automate "contrôle-clavier" 
  - des adversaires dont le comportements est défini par des automates
  - des obstacels dont le comportement est un automate "ne rien faire"

* **Fenêtre de visualisation (Viewport)**

- le monde virtuel est plus grande que le viewport
- deux joueurs = deux viewports 
- le viewport est centré sur l'entité contrôlée au clavier 
- l'entité contrôlée par le joueur est distinguée de manière graphique. Par exemple par un halo autour de lui.

* **Univers sans bord (dans au moins une dimension)**, deux solutions : 
  - génération aléatoire au fur et à mesure qu'on avance
  - retour du côté droit quand on arrive au bord du côté gauche (ex: PacMan vit sur un tore)

* **Un jeu d'action et de stratégie**
   - un jeu d'action : le(s) joueur(s) contrôle des entités
   - un jeu de stratégie : 
      le(s) joueur(s) sont accompagnés d'entités aux comportements automatiques 
      définis au moyen du langage de description de comportements

* **2 univers** avec des effets différents associées aux actions 

L'objectif pédagoqique est de vous amener à réfléchir à un structure de classe où les actions ne sont fixées dans le personnage mais dépendent de l'univers dans lequel il évolue.


## Créativité

Ces contraintes pédagogiques sont imposées, par contre... 

### Le thème du jeu est libre 

### Le but du jeu est non spécifié
- tuer l'adversaire ?
- récupérer des éléments sur la carte ?
- survivre un certain temps ?
- se multiplier ?
- bloquer les issues ?
- gagner du terrain ?
- marquer des points ?
- temps limité vs durée illimitée ? 
- ...

### Les paramètres des entités et leur gestion sont non specifiés
- puissance de frappe en fonction de l'énergie
- vitesse de déplacement en fonction de l'énergie
- inertie en fonction de l'énergie
- résistance en fonction de l'énergie
- au dessous de 0 points d'énergie, on devient un zombie ?
- au delà de 100 points d'énergie, on explose ?
- Lorsque l'entité du joueur meurt, le joueur se réincarne dans l'entité la plus proche qui se tranforme temporairement en fantôme (indesctrucible et inoffensif). Le joueur prend le contrôle de l'entité.  À vous de trouver une manière pour que le joueur puisse changer d'entité.
- ...



## Format d'animation graphique : fichier `.ani`

Pour pouvoir s'échanger les graphismes entre jeux il faut **fournir par personnage** :
* un sprite sheet *32 pixels x 32 pixels* au format `.png` 

Un sprite sheet est une grande image découpée en carrés de 32 pixels de côté qui représente plusieurs illustration d'un personnage. Une animation correspond à un enchainement d'images sélectionnées dans le sprit sheet.
     
Par exemple : JUMP = 1;3;4;5;6;9;12;1

* un fichier d'animation `.ani` sous la forme : 
```ascii
sprite_sheet = nom_du_fichier
<ACTION 1> = <SÉQUENCE D'ENTIERS> 
...
<ACTION n> = <SÉQUENCE D'ENTIERS>
```

## Menu de configuration du jeu

* Un menu permet d'attribuer à chaque entités du jeu 
  - un comportement (un fichier `.aut`) et 
  - une animation  (un fichier `.ani`)

* La configuration peut-être sauvegarder dans un fichier `.cfg` pour ne pas avoir à la redonner à chaque partie

```ascii
  Ghost.behaviour  := ghost.aut
  Wall.behaviour   := idle.aut
  PacMan.behaviour := player1.aut 

  Ghost.animation  := ghost.ani
  Wall.animation   := wall.ani
  PacMan.animation := pacman.ani
 ```

## Comportements des entités : fichier `.aut`

* ABSOLUMENT TOUTES les entités du jeu ont un comportement défini par un automate
* on peut attribuer un comportement (ne rien faire) aux obstacles, mais ils ont un automate.
* le contrôle au clavier est réalisé via un automate controle-clavier
   
### Le langage de description des automates est fourni et fixé
   
### Les actions

Les actions et conditions qui permettant de définir des automates sont fixées *mais leur interprétation est libre.*

#### Deux actions non spécifiées: Pop et Wizz

* Chaque automate doit contenir au moins une action Pop ou une action Wizz.
* Chaque équipe décidera de l'effet des actions Pop et Wizz et l'implantera dans son moteur de jeu.

  Exemples:
  - Pop = sauter, Wizz = cueillir
  - Pop = se protéger, Wizz = poser une bombe

#### Interprétation des actions stantdards (déplacement, frappe, ...)

L'interprétation est libre.

L'action *Hit* signifie bien sûr "frapper" mais ne précise pas quel type de frappe.
On obtient différente variante en interprétant différent l'action *Hit* :
   1. comme un coup sur la case voisine
   2. un laser longue portée 
   3. une poussée pour envoyer l'autre dans le décor (Pengo)
   4. une déplacement en roulade pour bousculer l'adversaire (Sonic)

#### L'action EGG crée une nouvelle entité (reproduction)

L'interprétation est libre mais doit aboutir à la création d'entité : par duplication immédiate, par dépôt d'un oeuf, ...
L'objectif pédagogique est de vous faire coder une gestion dynamique du nombre d'entités actives.

### Les conditions

Les conditions permettent de tester la présence ou l'absence d'une entités dans une direction.

## Un interpréteur d'automates

**L'interprétation des actions et des conditions** est définie dans le fichier [interpretation.md](interpretation.md)

### Des automates simples qui serviront à tester votre interpréteur

Afin de prendre en main le langage de descriptiont d'automates et de tester votre interpréteur de comportement, on vous conseille de réaliser les automates suivants :

  1. une entité qui tourne sur elle même et frappe
  2. l'automate du joueur qui effectue l'action correspondant à une touche clavier 
  3. une entité qui parcourt une ligne et fait demi-tour quand elle touche un bord
  4. deux entités synchronisées qui font les actions uniquement si les deux peuvent le faire.
  5. une entité qui en suit une autre
  6. une entité qui suit l'entité du joueur se déplaçant au clavier
  7. une entité qui se réplique et remplit les cases vides autour d'elle.
  
### Réalisation de l'interpréteur d'automates

Le parser construit un AST (Arbre de Syntaxe Abstraite) défini dans [Ast.java](src/ricm3/parser/Ast.java). 
L'AST produit correspond à l'ordre de lecture du fichier. Ce n'est pas la représentation la plus adaptée pour faire fonctionner l'automate.

On vous conseille de définir une méthode *make* dans chaque classe interne de Ast afin de générer une représentation  de l'automate qui facilitera son interprétation.

L'intepréteur ainsi construit devra posséder une méthode `step` qui fait faire un pas à l'automate.

- Les sous-classes Conditions devront fournir une méthode 
```java 
boolean eval(...)
```
- Les sous-classes Actions devront fournir une méthode 
```java
boolean exec(...)
```
(il est possible de retourner un booléen indiquant si l'action a pu s'effectuer ou non).

### Interprétation déterministe ou non-déterministe ?

Quelle transition prendre si plusieurs conditions sont satisfaites ?

À vous de choisir parmi les deux possibilités :

* Si les transitions sont évaluées dans l'ordre. La première transition dont la condition est satisfaite sera sélectionnée.
  Dans ce cas les transitions située après une condition "True" ne seront jamais prises.

* Vous pouvez opter pour un interpréteur non-déterministe qui tire au sort parmi toutes les transitions dont les conditions sont satisfaites. 

Considérez par exemple l'automate suivant :
```ascii
PowPopWiz(Init){
* (Init): 
    | True ? Pop   :(Init)
    | True ? Wizz  :(Init)
    | True ? Power :(Init)  
}
```



