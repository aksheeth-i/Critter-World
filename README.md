# Critter-World
Upon running the main method in Main.java, the user will encounter a UI to "command" critters and a grid to view the current status of the Critter world. The grid's size is defined in the Params.java file.

There are many features and options to progress the world-
- Create critters by entering the type and number in the top left. The program will create instances of the corresponding object using Java's reflection feature. Available types come from all the different versions of Critter classes in the src directory, currently including Clover, Goblin, Dragon, Eagle, Fish, and Unicorn, each of which will have their own representation in the grid and unique behavior (explained in their respective files).
- Progress time by entering the number of "time steps". Choose to either jump a certain number of time steps, or animate the process on the grid by pressing the "Start Animation" button with a defined frame speed (default 1 FPS). During each time step, each critter can potentially move to another location and/or reproduce. If multiple critters end up in the same location, they will "fight" it out, as defined in their classes.
- Enter a critter's type to view their "stats"- defined by their symbols and current numbers. Alternatively, enter "All" to check in on all active critters.
- Set a seed to reduce the randomness in critter behavior.
