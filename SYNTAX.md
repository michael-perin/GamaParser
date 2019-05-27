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


### Un automate qui ne fait rien
Un seul état, pas de transition.

```ascii
Dead(Stuck){
 * (Stuck)
}
```

### Avancer tant que la case à gauche est libre
```ascii
Blocker(GoLeft){
* (GoLeft):
    | Free(L) ? Step :(GoLeft)
    | Otherwise      :(Block)
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


## La grammaire (voir [parser_automata](GamaParser/src/ricm3/parser/parser_automata.jj))

## OLD


  <SKIP> ::= "." "|"

  <Epsilon> ::= ""


  <Description> ::= (<Definition>)* (<Automaton>)+

  <Definition> ::= "#define" <Identifier> <Optional_Parameters> "=:=" <Body>

  <Body> ::= <Condition> | <Action>
      
  <Optional_Parameters> ::=
     | <Parameters>
     | <Epsilon>

  <Parameters> ::= ( <Identifier> <More_Parameters> )

  <More_Parameters> ::= 
      | "," <Identifier> <More_Parameters>
      | <Epsilon>

  <Automaton> ::=  <Identifier> <State /the initial state/ > { (<Transition>)* }

  <State> ::=  ( <Identifier> )

  <Transition> ::= <State>  ":"  <Behaviour>  ":" <State>

  <Behaviour> ::=  <Optional_Condition> <Optional_Action> 

  <Optional_Condition> ::= 
     | <Condition> "?" 
     | <Epsilon>                 
                    
 <Optional_Action> ::=
    | <Action> 
    | <Epsilon>

 <Condition> ::= 
    | <Atomic Condition>
    | <Complex Condition>

 <Action> ::= 
    | <Atomic Action>
    | <Sequence>
    | <Choice>

 <Sequence> ::=  "[" <Action> <More Actions> "]"

 <More Actions> ::= 
   | ";" <Action> <More Actions>
   | <Epsilon>
  
 <Choice> ::= "{" <Atomic Action> <More Choices> "}"

 <Mode Choices> ::= 
   | " |" <Atomic Action> <More Choices>
   | <Epsilon> 
  

* TODO GRAMMAIRE A FINIR

 <Atomic Action> ::= ...
    

** Exemples d'automates que devra accepter votre parser de comportements

   [[file:automata.c]]]

