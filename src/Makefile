graphviz_executable = open
java_interpreter    = java
dot_output_file     = ~/Desktop/ast.dot
project_directory   = ~/git/github/GamaParser/
parser_class        = AutomataParser

test1 = "Philosopher(Think){ * (Think) }"

test2 = "Kamikaze(Init){ * (Init): True ? Kamikaze :(Init) }"

test3 = "Blocker(Init){ * (Init): !(True & True) ? Power(N,T) :(Init) }"

test4 = "Blocker(Init){ * (Init): !(True) ? Power :(Init) }"

test5 = "Mover(Init){ * (Init): True ? Move :(Init) }"

test6 = "Turner(Turn_0){ \
	* (Turn_0): True ? Turn(R) :(Turn_1) \
  * (Turn_1): True ? Turn(R) :(Turn_2) \
  * (Turn_2): True ? Turn(R) :(Turn_3) \
  * (Turn_3): True ? Turn(R) :(Turn_4) \
  * (Turn_4): True ? Kamikaze :() \
}"

test7 = "Master(Think){ \
 * (Think_0): True ? Power :(Think_1) \
 * (Think_1): True ? Power :(Think_2) \
 * (Think_2): True ? Power :(Think_3) \
 * (Think_3): True ? Power :(Think_4) \
 * (Think_4): True ? Power :(Happy_0) \
\
 * (Happy_0): True ? Power :(Happy_1) \
 * (Happy_1): True ? Power :(Happy_2) \
 * (Happy_2): True ? Power :(Happy_3) \
 * (Happy_3): True ? Power :(Happy_4) \
 * (Happy_4): True ? Power :(Happy_5) \
\
 * (Happy_5): True ? Kamikaze   :(X) \
}"

test8 = "Explorer(Explore_R){ \
* (Explore_R): \
  | Cell(F,V) ? Move    :(Explore_R) \
  | Cell(R,V) ? Turn(R) :(Explore_R) \
  | Cell(L,V) ? Turn(L) :(Explore_R) \
  | True ? Turn(B) :(Explore_L) \
\
* (Explore_L): \
    | Cell(F,V) ? Move    :(Explore_L) \
    | Cell(L,V) ? Turn(L) :(Explore_L) \
    | Cell(R,V) ? Turn(R) :(Explore_L) \
    | True ? Turn(B) :(Explore_R) \
}"

test9 = "\
SuperHero(Explore){\
\
* (Explore):\
  | Cell(F,P) ? Pick    :(Happy)\
  | Cell(F,V) ? Move(F) :(Run)\
  | Cell(F,J) ? Jump    :(Explore)\
  | True ? Turn(L)      :(Explore)\
\
* (Run):\
  | Cell(F,V) ? Move(F) :(Explore)\
  | Cell(F,E) / Cell(B,E) / Cell(L,E) / Cell(R,E) ? Wizz :(Escape)\
  | True ? Pop :(Explore)\
\
* (Escape):\
  | Cell(F,E) & Cell(B,V) ? Move(B) :(Escape)\
  | Cell(B,E) & Cell(F,V) ? Move(F) :(Escape)\
  | Cell(R,E) & Cell(L,V) ? Move(L) :(Escape)\
  | Cell(L,E) & Cell(R,V) ? Move(R) :(Escape)\
  | Cell(F,E) & !(Cell(B,V)) ? Hit :(Fight)\
  | True ? Power :(Explore)\
\
* (Fight):\
  | Cell(F,E) & Cell(L,E) & !(Cell(R,V) / Cell(B,V)) ? Hit :(Angry)\
  | Cell(F,E) & Cell(R,E) & !(Cell(L,V) / Cell(B,V)) ? Hit :(Angry)\
  | Cell(F,E) & Cell(B,E) & !(Cell(L,V) / Cell(R,V)) ? Hit :(Angry)\
  | Cell(L,E) & Cell(R,E) & !(Cell(F,V) / Cell(B,V)) ? Turn(R) :(Angry)\
  | True ? Pop :(Escape)\
\
* (Angry):\
  | GotPower ? Hit          :(Angry_11)\
  | !(GotPower) ? Protect :(Angry_11)\
\
* (Angry_11): True ? Turn(B) :(Angry_12)\
\
* (Angry_12):\
  | GotPower ? Hit :(Angry_13)\
  | !(GotPower) ? Protect :(Angry_13)\
\
* (Angry_13): True ? Turn(R) :(Angry_14)\
* (Angry_14):\
  | GotPower ? Hit :(Angry_15)\
  | !GotPower ? Protect :(Angry_15)\
\
* (Angry_16): True ? Turn(B) :(Angry_17)\
* (Angry_17):\
  | GotPower ? Hit  :(Angry_18)\
  | !GotPower ? Protect :(Angry_18)\
\
* (Angry_18): True ? Turn(R) :(Rest)\
\
* (Rest):\
  | Cell(F,V) & Cell(B,V) & Cell(L,V) & Cell(R,V) ? Power : (Rest)\
  | True ? Power :(Run)\
\
* (Happy):\
| Cell(F,P) ? Pick :(Happy_2)\
| Cell(B,P) ? Turn(B) :(Happy)\
| Cell(L,P) ? Turn(L) :(Happy)\
| Cell(R,P) ? Turn(R) :(Happy)\
| True ? Turn(L) :(Explore)\
\
*(Happy): True ? Store :(Crazy)\
\
* (Crazy)  : True ? Get     :(Crazy_1)\
* (Crazy_1): True ? Get     :(Crazy_2)\
* (Crazy_2): True ? Turn(B) :(Crazy_3)\
* (Crazy_3): True ? Throw   :(Think)\
\
* (Think):\
  | Cell(F,J) ? Jump    :(Build)\
  | Cell(B,J) ? Turn(B) :(Think)\
  | Cell(L,J) ? Turn(L) :(Think)\
  | Cell(R,J) ? Turn(R) :(Think)\
\
* (Build):\
  | GotStuff ? Get :(Build_1)\
  | True ? Wizz :(Explore)\
\
* (Build_1): True ? Throw :(Build) \
}"

test%:
	@ clear
	@ echo ``Parsing = \"$(test$*)\"``
	@ cd $(project_directory)/bin ; $(java_interpreter) $(parser_class) $(test$*) > $(dot_output_file)
	@ less $(dot_output_file)
	@ $(graphviz_executable) $(dot_output_file)
