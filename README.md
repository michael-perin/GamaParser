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
  ``java -cp ./bin parser.AutomataParser -aut -file example/automata.txt``
- from a string
  ``java -cp ./bin parser.AutomataParser -aut -string "Aut(Idle){ * (Idle)}"

## LA SYNTAXE 

voir [SYNTAX.md](SYNTAX.md)

## DES EXEMPLES D'AUTOMATES

voir [automate0.txt](example/automata0.txt)

