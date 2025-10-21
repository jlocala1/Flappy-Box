# Homework 1 Discussion

## Part I: Object-Oriented Programming Concepts

1. **Class**: A class is a template for the methods and variables of a certain object. An example in the case of this project is the Pipe class, which holds all the properties and functions for pipes, the obstacles in the game.

2. **Object**: An object is an instance of a class. In our example, you can create Pipe objects using the Pipe class. Typing "Pipe pipe1 = new Pipe(100,200)" will instantiate a pipe at specified position in the game.

3. **Encapsulation**: Encapsulation is when some aspects of an object are hidden while only necessary fields are accessible from the outside. For example, in FallingBox, "fallingAcceleration" is a private field and is accessed by public methods

4. **Abstraction**: Abstraction is keeping trivial information hidden to the user and only exposing key features so that the user can use the objects easily. In our project, the jump() method in FlappyBox abstracts the intricate movement calculations and allows the user to simply call jump.

5. **Data type**: The type of data any given variable stores. In our project, we used many different data types, such as doubles for things like coordinates or acceleration, and integers for things like score.

6. **Composite data type**: A composite data type is a data type made up of other types. For example, List(pipes)  is a List data type, but it contains objects of data type Pipe

7. **Method**: A segment of code that executes a function or task to organize code. For example, jump() is a segment of code that changes the velocity of the fallingBox 

8. **Constructor**: A method used to create/initialize objects. The FlappyBox constructor, for example, takes two double parameters and creates a flappy box at the specified coordinates

9. **Instance variable**: A variable in a class such that each instance of that class has a value of that variable. For example, fallingSpeed jumpVelocity carry the respective falling speeds and velocities of each flappy box object.

10. **Local variable**: A variable declared inside a method that cannot be accessed outside the method. In our project, a local variable is firstLeft in the box.intersects method, which stores the coordinate of the left edge of the first box during an intersection check. This variable is only used during the intersects method

11. **Parameter**: A variable of some type that is passed into a method to input specific data into the function. For example, doubles x and y in the FlappyBox constructor are parameters that initialize the box's position upon creation.

12. **Return type**: The type of variable returned at the end of a none void function. In our project we had many different return types. For example, we had a bunch of getter methods that had to return a double, such as "public double getX()" would return the x coordinate as a double. On the other hand, not all return types return a number, as in our intersects method we return a boolean that comes back true if two boxes overlap at any point. 

13. **Inheritance**: When a class inherits properties from another class. In our case, the FlappyBox is an extension of the fallingBox, meaning it inherits the fallingBox methods, which is why a flappyBox falls in the same way that the fallingBox does but has the added ability to jump from its own additional methods.

14. **Type Hierarchy**: The structured relationship between classes through inheritance. For example, FlappyBox extends FallingBox which extends Box. This means that box is the parent, and a fallingBox is a certain type of box, and a flappyBox is a type of fallingbox.

15. **Apparent type**: The type that a variable is declared as, which is not necessarily the type of the object at runtime. "FallingBox box = new FlappyBox(100,100)" the apparent type is a FallingBox because that is what the compiler will see.

16. **Actual type**: The actual type of a variable at runtime. In the same example "FallingBox box = new FlappyBox(100,100)", the actual type is FlappyBox.

17. **Is-a relationship**: The inheritance relationship where the derived class is a specific type of the parent class. For example, FallingBox derives from Box, and you can say that "a FallingBox is-a type of Box" meaning they have an is-a relationship

18. **Has-a relationship**: When one class contains another one, but the subsequent class doesn't inherit the parent. For example, the Game class "has-a" FlappyBox and Pipe classes because it uses these objects in the running of the game, but these do not inherit from the Game class.

19. **Method overloading**: When two methods share a name but take in different parameters. Our StdDraw class provides a good example of method overloading with the setCanvasSize() method. We have two versions of setCanvasSize, one with no parameters that sets the canvas to the default pixel size, and one that takes in two integer parameters for the pixel width and pixel height of the desired canvas. While they share the same name, they have different purposes, and the one that will be used is determined at compile time.

20. **Method overriding**: A derived class has a more specific implementation of a method in the parent class. For example, in FallingBox, there is a move() function which overrides the previous move function from superclass Sprite. It still uses super.move() to have the basic logic of a moving sprite from the superclass, but it now also accounts for acceleration changes and stops at the bottom. This move function overrides the previous one from the Sprite class.

21. **Static polymorphism**: When which method to use is determined at compile time by method overloading. In the case of our setCanvasSize method in StdDraw, we have the version with no parameters and the version with two parameters for width and height. At compile time, the compiler will decide which method to execute based on the parameters it is given for the call, making use of static polymorphism.

22. **Dynamic polymorphism**: When the method to use is determined at runtime by method overriding. With the overriding example from before, java dynamically decides at runtime to call the move function from FallingBox because of its additional functionality that is more specific to a flappyBox.

## Part II: Data Structures

Array List: In this project, the game uses array lists to manage pipes (pipes = new ArrayList<>()). This data structure is used because it supports dynamic resizing and fast retrieval of elements by index, which is helpful for adding and removing pipes during the flow of the game.

I also used a List<Pipes> to keep track of the pipes that the flappyBox had passed in order to prevent the tracker from counting multiple times. Using this general list helped me make sure the score was only incremented by 1 each pass.

## Part III: Algorithm Example

One example of an algorithm used in this project is the collision detection algorithm. The handleCollisions() method in Game iterates through each pipe in the pipes array list and checks if the flappyBox intersects at any point using the "intersects" method from Box. If at any point the FlappyBox collides with a pipe/box, the game ends. This helps the implementation of the game because it ends the game automatically after collision, forcing the user to maneuver through the pipes.
