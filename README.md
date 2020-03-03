# GamaParser = Un parser d'automates en Java CC

Le parser d'automates est une partie clef du [projet](PROJET.md) de fin d'année INFO3.
Tout élément du jeu sera défini par un automate Gama, avec une syntaxe imposée, ce qui permet aux différentes équipes de s'échanger leurs automates.

## LE PARSER 

Le parser [parser_automata.jj](src/ricm3/parser/parser_automata.jj) écrit en JavaCC prend en paramètre de ligne de commande un nom de fichier et construit l'Abstract Syntaxe Tree (AST). Il peut exporter sur la sortie standard l'AST et/ou le graphe de l'automate au format `.dot`. 

Le fichier `.dot` peut être visualisé avec l'outil [graphviz](https://www.graphviz.org).

## USAGE

Le parser peut générer deux sorties graphiques au format `.dot`
- avec l'option: ``-aut`` il produit la représentation graphique de l'automate
  (voir example/aut.dot)
- avec l'option: ``-ast`` il produit un graphe représentant l'Abstract Syntax Tree du parsing
  (voir example/ast.dot). Cette sortie est surtout utile pour déboguer le parser.

Le parser accepte deux sortes d'entrées
- un fichier
  ``java -cp ./bin parser.AutomataParser -aut -file example/automata.txt``
- une chaîne de caractères
  ``java -cp ./bin parser.AutomataParser -aut -string "Aut(Idle){ * (Idle)}"

## LA SYNTAXE DES AUTOMATES

voir [SYNTAX.md](SYNTAX.md)

## L'INTERPRÉTATION DES ACTIONS ET CONDITIONS DES TRANSITIONS

On a fixé les actions et les conditions disponibles pour écrire des automates,
ainsi que leur [interprétation](INTERPRETATION.md)

## DES EXEMPLES D'AUTOMATES

voir [automate0.txt](example/automata0.txt)
