# Syntaxe du langage de description d'automate

La syntaxe vous est présentée sous forme d'exemples d'automates de plus en plus complexes

## Caractéristiques d'une entité 

* Une entité est orientée dans une direction de déplacement
  - Up, Down, Right, Left
  

## Formats

* **automate**
```ascii
<nom de l'automate> (<état initial>){ 
 <transitions>
}
```

* **une transition** 
```
* (<nom état source>): <condition> ? <action optionnelle> :(<nom état cible>)
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

### Tester les différentes directions et enchainer 3 actions

```ascii
Guerrier(ENTRY){
* (ENTRY): Pop :(TURN)
* (TURN):
  | MyDir(U) ? Turn(R) :(HIT1)
  | MyDir(R) ? Turn(D) :(HIT1)
  | MyDir(D) ? Turn(L) :(HIT1)
  | MyDir(L) ? Turn(U) :(HIT1)
* (HIT1): Take :(HIT2)
* (HIT2): Hit :(HIT3)
* (HIT3): Bag :(TURN)
}
```

### Si condition FAIRE action SINON ...

Les transitions sont évaluées dans l'ordre. La condition `True` est toujours satisfaite et joue le rôle de *sinon*.

```ascii
Aut4(ENTRY){
* (ENTRY): Wizz :(TurnLeft)

* (TurnLeft): Turn(L) :(GoLeft)

* (GoLeft):
  | Free(L) ? Step :(GoLeft)
  | True           :(TurnRight)

* (TurnRight): Turn(R) :(GoRight)

* (GoRight):
  | Free(R) ? Step :(GoRight)
  | True           :(TurnLeft)
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

### Synchronisation avec d'autres entités
```ascii
Aut5(Synchro){

* (Synchro):
  | All_My_Team_Can_Do( Step ) ? Step            :(Synchro)
  | All_My_Team_Can_Do( Hit  ) ? Hit             :(Synchro)
  | All_My_Team_Can_Do( Jump ) ? Jump            :(Synchro)
  | In_Front_Of_Me( N )        ? Pop             :(Synchro)
  | True                       ? Turn(ClockWise) :(Synchro)
}
```


## La grammaire (voir [parser_automata](src/ricm3/parser/parser_automata.jj))

