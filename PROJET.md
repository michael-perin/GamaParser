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
   
### Le langage de description des automates est fourni et fixé. 

Les fonctionnalités du parser sont décrites dans le fichier [README.md](README.md). 
   
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

#### entités et conditions non totalement spécifiées

   La catégorie "Other" donne la liberté de définir des entités propres à sa variante :
   chose à ramasser, autre équipe (ni ennemie, ni amie), porte, ...







* III. LIBERTÉ D'EXPRESSION

** Le thème du jeu est libre 

** But du jeu non spécifié

   - tuer l'adversaire ?
   - récupérer des éléments sur la carte ?
   - survivre un certain temps ?
   - se multiplier ?
   - bloquer les issues ?
   - gagner du terrain ?
   - marquer des points ?
   - temps limiter ?
   - jeu infini ? 


** Les paramètres des entités et leur gestion sont non specifiés

   - puissance de frappe en fonction de l'énergie
   - vitesse de déplacement en fonction de l'énergie
   - inertie en fonction de l'énergie
   - résistance en fonction de l'énergie
   - au dessous de 0 points d'énergie, on devient un zombie
   - au delà de 100 points d'énergie, on explose
   - Jeu sans fin : Lorsque l'entité du joueur meurt. Le joueur se réincarne dans l'entité la plus proche qui se tranforme temporairement en fantôme (indesctrucible et inoffensif). 
     Le joueur prend le contrôle de l'entité.  À vous de trouver une manière pour que le joueur puisse changer d'entité.




* IV. SPECIFICATIONS DU LANGAGE DE DESCRIPTION D'AUTOMATES

** Direction = {Up, Down, Left, Right} : no diagonal


** Kind = { T = Team, E = Ennemi, N = Nothing, O = Obstacle, U = Unkwnon}

  A = Any means any kind of entity


** Behaviour 

*** the behavioue of autonomous entities are defined by automaton with transition 

   (STATE):  condition ? action :(STATE')


*** Action take time


*** Test transition do not take time

   Thus it is possible to do many test transition during one round.
   

*** [#B] At each round, each active entity tries to perform transitions

   The number of possible transitions is limited to avoid infinite loop.


** Power

   Alive entity with 0 power unit die.

   An entity can accumulate 1 power unit by doing the Power action.

   Each action cost 1 power unit, unless the power is already at 1.


** Team

   Entities of the same kind form a team which shares knowledge.


** Bag 

   Each active entity has a bag where it can store items of the games.

   NOTE: The weight of the bag is the sum of the power of what it contains. This can be used by the game engine to change the movement velocity of the entity.


** Map

   Each team has an internal map corresponding to what the team has explored.
   The map is used for path finding algorithm.


** The Entity class

Every items of the game inherits from the Entity class with the following fields

class Entity{

  int power ; 
  boolean moveable ;

  boolean act_on(Entity e){
     return (in_front_of(e) & toward(e) & this.power > e.power) 
  }

  ... basic action...

}

*** Most of the effect of atomic actions must be defined in the Entity class so that every instances will benefit of the default action.

Since all elements of the game inherits from the Entity class
- all actions are defined for all items
- an entity e can perform an action on another entity e' as soon as the condition is satisfied e.can_act_on(e'). 


*** It has strange consequence (but that's exactly the fun of this game play): 

    if in the game you can pick up an object, keep it in your bag, get it out later to throw it away;
    you will do the same with a character of the game.



** Conditions

*** Exploration

- Free_Cell (Direction d) ? : is there a free cell in direction d around me

- Dir_Closest (Kind k, Direction d) ?  is d the direction of the closest entity of kind k

- In_Front_Of_Me (Kind k) ?

- Behind Me (Kind k) ? : is the entity behind me of the kind k. 
  This is the only condition that can look behing without turning into the opposite direction.

**** For 3D world 
- Above Me (Kind k) ?
- Under Me (Kind k) ? 

*** Properties of entities 

- More_Power ? : does the entity /in front of me/ have more power than me ?

- Less_Power ? : does the entity /in front of me/ have less power than me ?

- Toward_Me ? : is the entity /in front of me/ turned toward me ?

- Moveable ? : is the entity /in front of me/ a moveable one ?

- MyDir(Direction d) ? : true if my current direction is d

*** Smart atomic condtions requiring an exploration map

- Reachable (Kind k) ? : according to my map is an entity of kind k reachable from my current position ?

- Path (int n, Kind k) ? : is there a path of n steps in my map joining an entity of kind k ?

*** Atomic condition for synchronization

- All_My_Team_Can_Do (Action a) ? 

*** Complex conditions made with operators: &&, ||, !

#define Danger =:= (In Front of Me(E) & Toward Me & More Power)


** Actions

For simplicity, an active entity is oriented in one direction and its actions only concern this direction.

For simplicity, each action take one game-time unit to be executed.

*** atomic actions (for 3D isometic games) : action returns a boolean SUCCEEDED / FAILED

**** Moving 

- One_Step : boolean = does one step forward on the ground. Feasible only if there is no obstacle, decrease the power by 1 unless the power is already at 1. 

- Jump_On  : boolean = does one step forward in the air. Feasible if there is an jump_on entities in front of me.

**** Direction

- Turn (Direction) : always succeed

- Turn(Clockwise) : always succeed

- Opposite_Direction : always succeed = 2 Turn(Clockwise) but takes only 1 time unit

- Turn_On (Kind k) : if there is an entity of kind k in my neighbourhood, turn me toward this entity.
 
- Turn_Back_On (Kind k): if there is an entity of kind k in my neighbourhood, turn me in the opposite direction (to espace for instance)

- Turn_to_Join (Kind k) : 

  always succeed, turn the current entity in the best direction to join an entity of kind k (using a path finding algorithm according to the entity map). 
  This action cost two time units: one to find the direction, one to turn.

**** Battle

- Power : always feasible, increase the power by 1. 

- Protect : with or without anything in the hands. The efficiency depends on the power of the entities and of what is in the hands.
 
- Hit : with or without anything in the hands. The efficiency depends on the power of the entities and of what is in the hands.
 
- Throw = throw what is in the hands in front of me. The distance of the drop depends on my power and the weight (power) of the item.


**** Bag management

- Pick = Picks up what is /in front of me/ if the power is sufficient or take something in the bag (randomly), there hold it in the hands. 

- Bag = Puts back in the bag what is in the hands. 

- Drop : drops on the ground in a free cell what is in the hands or something in the bag if the hands are empty. The distance of the drop depends on my power and the weight (power) of the item.

  Drop is similar to throw but the direction is not defined. 


*** Sequence of action : [ action1 ; action2 ; ... ]

Each game round, one atomic action of the sequence is executed

When one action of the sequence cannot be executed, there are MANY ways to handle it in the game engine:

1. it can cancel the remaining actions
2. it can cancel the unfeasible action but keep the others
3. it can retry the unfeasible action at the next round until it succeeds 
4. it can retry the unfeasible aciton at the next round for some round but not infinitely, then it can do 1. or 2.


*** Complex actions can be defined as MACRO using atomic action of predefined complex actions

The execution time of a complex action is the execution time of the sequence

#define Jump_Over =:= [ Jump ; Step ]

*** Complex actions with arguments 

#define Move(dir) =:= [ Turn(dir) ; One Step Forward ]

*** Adapative actions with non-determinism

#define Move(dir) =:= [ Turn(dir) ; {One Step Forward | Jump On} ]



* V. AUTOMATES à réaliser en phase de test pour prendre en main le sujet

  1. une entité qui tourne sur elle même et frappe

  2. une entité qui parcourt une ligne et fait demi-tour quand elle touche un bord

  3. deux entités synchronisées qui font les actions uniquement si les deux peuvent le faire.

  4. une entité qui suit une autre

  5. une entité qui suit l'entité du joueur se déplaçant au clavier
   

