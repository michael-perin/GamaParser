# GamaParser = A parser of Game Automata in Java CC

## PARSER
Le parser fourni dans parser_automata.jj prend en paramètre de ligne de commande un nom de fichier et génére l'Abstract Syntaxe Tree (AST) au format .dot sur la sortie standard.


Le fichier .dot décrit l'AST qu'on peut visualiser avec l'outil graphviz.

## TODO

Votre tâche consiste à créer une méthode *make* pour chaque classe interne de Ast
afin de générer un interpréteur de transitions. L'intepréteur doit posséder une méthode qui lance le calcul. Par exemple
- *boolean eval()* pour les Conditions
- *... exec()* pour les Actions (il est possible de rendre un booléen qui indique si l'action a pu s'effectuer ou non).


## DEMO
java -cp ./bin ricm3.parser.AutomataParser example/automata.txt > ast.dot

# LES DIRECTIONS

## Absolute
- N = North
- S = South
- E = East
- O = Ouest

## Relative
- F = front
- B = back
- L = on my left
- R = on my right


# LES TOUCHES
- les lettres a,...,z
- les chiffres 0...9
- SPACE, ENTER,
- les flêches: FU, FD, FR, FL


# LES ENTITÉS

- V = Void
- T = Team = une entité de mon équipe
- A = un Adversaire
- D = un Danger
- P = un élément qu on peut Prendre,stocker,lancer,déposer
- J = un élément sur lequel on peut sauter
- G = un Gate
- M = un Missile

# LES CONDITIONS

## Les conditions booléennes de base
-  True
-  Key(Touche) : la Touche est enfoncée
-  MyDir(Direction) : l'entité est orientée dans la Direction
-  Cell(Direction, Entité) : La cellule dans la Direction contient une Entité
-  Closest(Entité, Direction) : la plus proche Entité est dans la Direction
-  GotPower : reste t'il de l'énergie à l'entité ?

## Les opérateurs sur les conditions
- conjonction: Condition1 & Condition2
- disjunction: Condition1 / condition2
- negation:  not(Condition)


# LES ACTIONS

## Les actions peuvent avoir ou non une direction.

- Si dans votre jeu une action Wizz n'a pas de direction.
alors vous interpréterait Wizz(U) comme Wizz.

- Si, au contraire, l'action Wizz doit avoir une direction
et que l'automate n'en donne pas. Vous interpreterez Wizz comme Wizz(F).

## Deux actions essentielles pour votre jeu (direction optionnelle, par défaut F)
-  Wizz(Direction) = ?
-  Pop(Direction)  = ?

## Déplacements (direction optionnelle, par défaut F)
-  Move(Direction) = déplacement
-  Jump(Direction) = saut

## Rotation (direction optionnelle, par défaut R)
- Turn(Direction) = changement de direction (sans déplacement)
- Example:
    - Turn(R) = +90 degree : clockwise
    - Turn(B) = 180 degree
    - Turn(L) = -90 degree : counter-clockwise

## Affrontements (direction optionnelle, par défaut F)
-  Hit(Direction) = frapper
-  Protect(Direction) = protection

## Collecte (direction optionnelle, par défaut F)
-  Pick(Direction) = ramasser une chose
-  Throw(Direction) = lancer/déposer ce que l entité a dans la main.

## Stockage (action sans argument)
-  Store = mettre en réserve (dans son sac)
-  Get   = prendre une entité dans sa réserve si aucune en main,
        changer d entité si une en main (elle est remise dans le sac)

## Power (action sans argument)
-  Power = pas d'action, mais récupération d'énergie.
-  Kamikaze   =  disparition, suicide, explosion, tranformation en autre chose, ...
