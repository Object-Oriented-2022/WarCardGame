# WarCardGame
War card game assignment for Object Oriented systems
OCP - PlayerBehavior interface with player class that implements and behaviorone and behaviortwo classes extending player class

Information Experts:
Cards - knows everything about cards
Players - only handles their own moves
war - instills the rules of the game on the cards given to them by the player

Low Coupling:
Cards create card objects, within its own package that does not call other packages (also number 2 on requirements sheet, checked with him and it counts)
Polymorphism - playerbehavior interface, behaviorone and behaviortwo classes use the same function names but both do different things
