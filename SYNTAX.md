# SYNTAX OF THE GAME AUTOMATA



* IV. SPECIFICATIONS DU LANGAGE DE DESCRIPTION D'AUTOMATES

** Direction = {Up, Down, Left, Right} : no diagonal


** Kind = { T = Team, E = Ennemi, N = Nothing, O = Obstacle, U = Unkwnon}

  A = Any means any kind of entity


** Behaviour 

*** the behavioue of autonomous entities are defined by automaton with transition 

   (STATE):  condition ? action :(STATE')


*** Action take time


*** Test transition do not take time

   Thus it is possible to do many test transition during one round.
   

*** [#B] At each round, each active entity tries to perform transitions

   The number of possible transitions is limited to avoid infinite loop.


** Power

   Alive entity with 0 power unit die.

   An entity can accumulate 1 power unit by doing the Power action.

   Each action cost 1 power unit, unless the power is already at 1.


** Team

   Entities of the same kind form a team which shares knowledge.


** Bag 

   Each active entity has a bag where it can store items of the games.

   NOTE: The weight of the bag is the sum of the power of what it contains. This can be used by the game engine to change the movement velocity of the entity.


** Map

   Each team has an internal map corresponding to what the team has explored.
   The map is used for path finding algorithm.


** The Entity class

Every items of the game inherits from the Entity class with the following fields

class Entity{

  int power ; 
  boolean moveable ;

  boolean act_on(Entity e){
     return (in_front_of(e) & toward(e) & this.power > e.power) 
  }

  ... basic action...

}

*** Most of the effect of atomic actions must be defined in the Entity class so that every instances will benefit of the default action.

Since all elements of the game inherits from the Entity class
- all actions are defined for all items
- an entity e can perform an action on another entity e' as soon as the condition is satisfied e.can_act_on(e'). 


*** It has strange consequence (but that's exactly the fun of this game play): 

    if in the game you can pick up an object, keep it in your bag, get it out later to throw it away;
    you will do the same with a character of the game.



** Conditions

*** Exploration

- Free_Cell (Direction d) ? : is there a free cell in direction d around me

- Dir_Closest (Kind k, Direction d) ?  is d the direction of the closest entity of kind k

- In_Front_Of_Me (Kind k) ?

- Behind Me (Kind k) ? : is the entity behind me of the kind k. 
  This is the only condition that can look behing without turning into the opposite direction.

**** For 3D world 
- Above Me (Kind k) ?
- Under Me (Kind k) ? 

*** Properties of entities 

- More_Power ? : does the entity /in front of me/ have more power than me ?

- Less_Power ? : does the entity /in front of me/ have less power than me ?

- Toward_Me ? : is the entity /in front of me/ turned toward me ?

- Moveable ? : is the entity /in front of me/ a moveable one ?

- MyDir(Direction d) ? : true if my current direction is d

*** Smart atomic condtions requiring an exploration map

- Reachable (Kind k) ? : according to my map is an entity of kind k reachable from my current position ?

- Path (int n, Kind k) ? : is there a path of n steps in my map joining an entity of kind k ?

*** Atomic condition for synchronization

- All_My_Team_Can_Do (Action a) ? 

*** Complex conditions made with operators: &&, ||, !

#define Danger =:= (In Front of Me(E) & Toward Me & More Power)


** Actions

For simplicity, an active entity is oriented in one direction and its actions only concern this direction.

For simplicity, each action take one game-time unit to be executed.

*** atomic actions (for 3D isometic games) : action returns a boolean SUCCEEDED / FAILED

**** Moving 

- One_Step : boolean = does one step forward on the ground. Feasible only if there is no obstacle, decrease the power by 1 unless the power is already at 1. 

- Jump_On  : boolean = does one step forward in the air. Feasible if there is an jump_on entities in front of me.

**** Direction

- Turn (Direction) : always succeed

- Turn(Clockwise) : always succeed

- Opposite_Direction : always succeed = 2 Turn(Clockwise) but takes only 1 time unit

- Turn_On (Kind k) : if there is an entity of kind k in my neighbourhood, turn me toward this entity.
 
- Turn_Back_On (Kind k): if there is an entity of kind k in my neighbourhood, turn me in the opposite direction (to espace for instance)

- Turn_to_Join (Kind k) : 

  always succeed, turn the current entity in the best direction to join an entity of kind k (using a path finding algorithm according to the entity map). 
  This action cost two time units: one to find the direction, one to turn.

**** Battle

- Power : always feasible, increase the power by 1. 

- Protect : with or without anything in the hands. The efficiency depends on the power of the entities and of what is in the hands.
 
- Hit : with or without anything in the hands. The efficiency depends on the power of the entities and of what is in the hands.
 
- Throw = throw what is in the hands in front of me. The distance of the drop depends on my power and the weight (power) of the item.


**** Bag management

- Pick = Picks up what is /in front of me/ if the power is sufficient or take something in the bag (randomly), there hold it in the hands. 

- Bag = Puts back in the bag what is in the hands. 

- Drop : drops on the ground in a free cell what is in the hands or something in the bag if the hands are empty. The distance of the drop depends on my power and the weight (power) of the item.

  Drop is similar to throw but the direction is not defined. 


*** Sequence of action : [ action1 ; action2 ; ... ]

Each game round, one atomic action of the sequence is executed

When one action of the sequence cannot be executed, there are MANY ways to handle it in the game engine:

1. it can cancel the remaining actions
2. it can cancel the unfeasible action but keep the others
3. it can retry the unfeasible action at the next round until it succeeds 
4. it can retry the unfeasible aciton at the next round for some round but not infinitely, then it can do 1. or 2.


*** Complex actions can be defined as MACRO using atomic action of predefined complex actions

The execution time of a complex action is the execution time of the sequence

#define Jump_Over =:= [ Jump ; Step ]

*** Complex actions with arguments 

#define Move(dir) =:= [ Turn(dir) ; One Step Forward ]

*** Adapative actions with non-determinism

#define Move(dir) =:= [ Turn(dir) ; {One Step Forward | Jump On} ]

