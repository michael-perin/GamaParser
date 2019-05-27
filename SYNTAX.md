# Syntaxe du langage de description d'automates

La syntaxe vous est présentée sous forme d'exemples d'automates de plus en plus complexes
  
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

