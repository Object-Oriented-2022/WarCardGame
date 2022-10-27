# WarCardGame
## War card game assignment for Object Oriented systems

#### **Created By: GeneJackett & crawlive**

### **OCP:** 

PlayerBehavior interface with player class that implements and BehaviorOne and BehaviorTwo classes extending Player class. The interface keeps players closed to adjustments while having the Player class only implement the uniform functions allows it to be open to more implementations of war in the future.

### **Information Experts:**

  - Cards - knows everything about cards
  - Players - only handles their own moves
  - War - instills the rules of the game on the cards given to them by the player

### **Low Coupling:**

Cards create card objects, within its own package that does not call other packages (also number 2 on requirements sheet, checked with him and it counts)

### **Polymorphism**

- PlayerBehavior interface, BehaviorOne and BehaviorTwo classes use the same function names but both do different things
