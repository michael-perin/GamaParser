# GamaParser = A parser of Game Automata in Java CC

## PARSER 

Le parser [parser_automata.jj](src/ricm3/parser/parser_automata.jj) écrit en JavaCC prend en paramètre de ligne de commande un nom de fichier et génére l'Abstract Syntaxe Tree (AST) au format `.dot` sur la sortie standard.

Le fichier `.dot` décrit l'AST qu'on peut visualiser avec l'outil [graphviz](https://www.graphviz.org).

## USAGE

The parser can generate two graphical output in .dot format
- with option: ``-ast`` it produces the Abstract Syntax Tree
  (see example/ast.dot)
- with option: ``-aut`` it produces the graphical representation of the parsed automata
  (see example/aut.dot)

The parser can take input
- from a file
  ``java -cp ./bin ricm3.parser.AutomataParser -aut -file example/automata.txt``
- from a string
  ``java -cp ./bin ricm3.parser.AutomataParser -aut -string "Aut(Idle){ * (Idle)}"

## LA [SYNTAX](SYNTAX.md)

## DES [EXEMPLES D'AUTOMATES](example/automata0.txt)

## INTERPRETATION des conditions et des actions

L'inteprétation des conditions et des actions est donnée dans [INTERPRETATION.md](INTEPRETATION.md) 

## À RÉALISER : UN INTERPRETEUR D'AUTOMATES

Votre tâche consiste à créer une méthode *make* pour chaque classe interne de Ast afin de générer un interpréteur de transitions. 

L'intepréteur doit posséder une méthode qui fait faire un pas à l'automate.

- Les conditions doivent fournir une méthode 
```java 
boolean eval()
```
- *... exec()* pour les Actions (il est possible de rendre un booléen qui indique si l'action a pu s'effectuer ou non).





