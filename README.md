# GamaParser = A parser of Game Automata in Java CC

## PARSER

Le parser fourni dans parser_automata.jj prend en paramètre de ligne de commande un nom de fichier et génére l'Abstract Syntaxe Tree (AST) au format .dot sur la sortie standard.


Le fichier .dot décrit l'AST qu'on peut visualiser avec l'outil [graphviz](https://www.graphviz.org).

## TODO

Votre tâche consiste à créer une méthode *make* pour chaque classe interne de Ast
afin de générer un interpréteur de transitions. L'intepréteur doit posséder une méthode qui lance le calcul. Par exemple
- *boolean eval()* pour les Conditions
- *... exec()* pour les Actions (il est possible de rendre un booléen qui indique si l'action a pu s'effectuer ou non).


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

## GRAMMAR (voir [parser_automata](src/ricm3/parser/parser_automata.jj))

## EXAMPLES (voir [automata](example/automata0.txt))


