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

## LA SYNTAXE 

voir [SYNTAX.md](SYNTAX.md)

## DES EXEMPLES D'AUTOMATES

voir [automate0.txt](example/automata0.txt)

## INTERPRETATION des conditions et des actions

L'inteprétation des conditions et des actions est donnée dans [INTERPRETATION.md](INTEPRETATION.md) 

## À RÉALISER : UN INTERPRETEUR D'AUTOMATES

Le parser fournit un AST (Arbre de Syntaxe Abstraite) qui correspond à l'ordre de lecture du fichier.
Ce n'est pas la représentation la plus adaptée pour faire fonctionner l'automate.

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





