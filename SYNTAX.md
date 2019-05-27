# Syntaxe du langage de description d'automates

La syntaxe vous est présentée sous forme d'exemples d'automates de plus en plus complexes

## Caractéristiques d'une entité 

* Une entité est orientée dans une direction Up, Down, Right, Left
  
## Formats

* **automate**
```ascii
<nom de l'automate> (<état initial>){ 
 <transitions>
}
```

* **une transition avec action** 
```
* (<nom état source>): <condition> ? <action> :(<nom état cible>)
```

* **une transition sans action** 
```
* (<nom état source>): <condition> :(<nom état cible>)
```

* **transitions multiples**
```
* (<nom état source>):
    | <condition 1> ? <action 1> :(<état cible 1>)
      ...
    | <condition n> ? <action n> :(<état cible n>)
```
* **état spécifique sans nom** `* ()` = destruction de l'automate

* **les directions**
  - N,S,E,O sont des directions absolues
  - F,B,L,R sont des directions relatives à l'orientation de l'entité

* **Les paramètres des actions sont optionnels**
  + Move
    - Move sans paramètre avance suivant l'orientation actuelle de l'entité
    - Move(d) avance dans la direction d
  + Hit
    - Hit frappe selon l'orientation actuelle de l'entité
    - Hit(B) donne un coup en arrière
    - Hit(N) donne un coup au nord
  + Turn
    - Turn = Turn(R) tourne dans le sens horaire
    - Turn(L) tourne dans le sens anti-horaire
    - Turn(B) fait demi-tour par rapport à l'orientation actuelle de l'entité
    - Turn(S) tourne l'entité vers le sud
  
* **Les opérateurs sur les conditions**
  - conjonction: Condition1 & Condition2
  - disjunction: Condition1 / condition2
  - negation: not(Condition) ou bien ! Condition

## Des exemples

### Un automate qui ne fait rien
Un seul état, pas de transition.

```ascii
Philosopher0(Think){
  * (Think)
}
```

### Avancer tant que la case à gauche est libre
```ascii
Blocker(GoLeft){
* (GoLeft):
    | Cell(L,V) ? Move :(GoLeft)
    | True             :(Block)
* (Block)
}
```

### Examiner le contenu des cases autour de soi

```ascii
Escape(Init){
* (Init):
  | Cell(N,V) & Cell(S,V) & Cell(E,V) & Cell(O,V) :(Init)
  | Cell(F,V) ? Move(F) :(Init)
  | Cell(L,V) ? Turn(L) :(Init)
  | Cell(R,V) ? Turn(R) :(Init)
  | Cell(B,V) ? Move(B) :(Init)
}
```

### Interprétation déterministe ou non-déterministe ? à vous de choisir

Quelle transition prendre si plusieurs conditions sont satisfaites ?

* Si les transitions sont évaluées dans l'ordre. La première transition dont la condition est satisfaite sera sélectionnée.
  Dans ce cas les transitions située après une condition "True" ne seront jamais prises.

* Vous pouvez opter pour un interpréteur non-déterministe qui tire au sort parmi toutes les transitions dont les conditions sont satisfaites. 

```ascii
PopWiz(Init){
* (Init): 
    | Cell(F,E) ? Pop(F)  :(Init)
    | Cell(B,E) ? Wizz(B) :(Init)
    | True ? Power        :(Init)  
}
```

### L'automate du joueur

```
Player(Init){
  * (Init):
  | Key(FU) ? Move(N) :(Init)
  | Key(FD) ? Move(S) :(Init)
  | Key(FL) ? Move(O) :(Init)
  | Key(FR) ? Move(E) :(Init)
  | Key(SPACE) ? Hit  :(Init)
  | Key(ENTER) ? Jump :(Init)
  | Key(b)  ? Jump(B) :(Init)
  | Key(d)  ? Move(D) :(Init)
  | Key(e)  ? Move(E) :(Init)
  | Key(f)  ? Turn(B) :(Init)
  | Key(p)  ? Pop     :(Init)
  | Key(w)  ? Wizz    :(Init)
  | Key(g)  ? Get     :(Init)
  | Key(t)  ? Throw   :(Init)
  | True    ? Power   :(Init)
}
```

# LES DIRECTIONS

## Absolue
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
- P = un élément qu on peut Prendre, stocker, lancer, déposer
- J = un élément sur lequel on peut sauter
- G = un Gate
- M = un Missile


# LES CONDITIONS

## Les conditions booléennes de base
-  True : toujours vraie
-  Key(Touche) : vraie si la Touche est enfoncée
-  MyDir(Direction) : vraie si l'entité est orientée dans la Direction
-  Cell(Direction, Entité) : vraie si la cellule dans la Direction contient une Entité
-  Closest(Entité, Direction) : vraie si la plus proche Entité est dans la Direction
-  GotPower : vraie s'il reste de l'énergie à l'entité
-  GotStuff : vraie s'il reste des choses dans le store

## Les opérateurs sur les conditions
- conjonction: Condition1 & Condition2
- disjunction: Condition1 / condition2
- negation: not(Condition)


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
-  Get   = prendre une entité dans sa réserve si aucune en main, changer d'entité si une en main (elle est remise dans le sac)

## Power (action sans argument)
-  Power = pas d'action, mais récupération d'énergie.
-  Kamikaze =  disparition, suicide, explosion, tranformation en autre chose, ...
