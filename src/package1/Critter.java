
package package1;

import javafx.scene.layout.GridPane;

import javafx.scene.shape.*;

import java.util.*;



public abstract class Critter {

    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    protected final String look(int direction, boolean steps) {

        this.energy-= Params.LOOK_ENERGY_COST;
        Critter someCrit = new Clover();
        someCrit.setCoordinates(this.x_coord,this.y_coord);
        if(steps)
            someCrit.move(direction);
        someCrit.move(direction);

        /*
            for(Position position : Positions)
            {
                if(position.equals(someCrit.x_coord, comeCrit.y_coord))
                {
                    return map.get(positon).toString();
                }

            }
         */

        for(Critter critter : critters)
            if(someCrit.samePosition(critter))
                return critter.toString();
        return null;
    }

    public static String runStats(List<Critter> critters) {
        StringBuilder finalOut = new StringBuilder();
        finalOut.append("").append(critters.size()).append(" critters as follows -- ");
        Map<String, Integer> critter_count = new HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            critter_count.put(crit_string,
                    critter_count.getOrDefault(crit_string, 0) + 1);
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            finalOut.append(prefix).append(s).append(":").append(critter_count.get(s));
            prefix = ", ";
        }
        return finalOut.toString();
    }


    public static void displayWorld(Object pane) {
        // TypeCast pane as a GridPane
        GridPane grid = (GridPane) pane;
        // clear the grid
        grid.getChildren().clear();
        // Add the grid back
        Main.paintGridLines(grid, Main.size);
        // For every Critter in Critters
        for(Critter critter : critters)
        {
            CritterShape critterShape = critter.viewShape();
            Shape s;
            switch(critterShape) {
                case SQUARE:   s = new Rectangle(Main.size * .99 , Main.size  * .99);break;
                case CIRCLE:   s = new Circle((Main.size + 0.0)/2);break;
                case DIAMOND:  s = new Diamond().getDiamond();break;
                case TRIANGLE: s = new Triangle().getTriangle();break;
                case STAR:     s = new Star().getStar();break;
                default: s = new Circle((Main.size + 0.0)/2);break;
            }
            s.setStroke(critter.viewOutlineColor());
            s.setFill(critter.viewFillColor());

            grid.add(s, critter.x_coord, critter.y_coord);
        }

    }

    private static HashSet<Critter> critters = new HashSet<Critter>();
    private static HashMap<Critter, Boolean> hasMoved= new HashMap<Critter, Boolean>();
    // Added for look functionality
    private static ArrayList<Position> Positions = new ArrayList<Position>();
    private static HashMap<Position, Critter>  map = new HashMap<Position, Critter>();


    private int energy = 0;

    private int x_coord;
    private int y_coord;

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * //@throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {

        // Create a new critter
        try {
            Object crit = Class.forName(myPackage + "." + critter_class_name).newInstance();
            Critter newCrit = (Critter)crit;
            // Add it to the critter collections
            critters.add(newCrit);
            hasMoved.put(newCrit, false);
            newCrit.energy = Params.START_ENERGY;
            newCrit.x_coord = getRandomInt(Params.WORLD_WIDTH);
            newCrit.y_coord = getRandomInt(Params.WORLD_HEIGHT);
        }
        // NOTE: Added NoClassDefFoundError after create "fish"(instead of fish) created such an error
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | NoClassDefFoundError e1)
        {
            throw new InvalidCritterException(critter_class_name);
        }
        // TODO: Complete this method
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * //@throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Complete this method

        
        try {
            Object crit = Class.forName(myPackage + "." + critter_class_name).newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            throw new InvalidCritterException(critter_class_name);
        }
        ArrayList<Critter> instances = new ArrayList<>();
        // TODO: Complete this method
        //for each critter in critters
        for(Critter critter : critters)
        {
            Class c = critter.getClass();
            if(c.getName().equals(myPackage + "." + critter_class_name))
            {
                instances.add(critter);
            }
        }


        return instances;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {

        critters.clear();
        hasMoved.clear();

        // TODO: Complete this method
    }

    public static void worldTimeStep() {
        /*
        // Track all the current positions of the current Critters
        // Clear the positions
        Positions.clear();
        map.clear();
        for(Critter critter : critters)
        {
            Positions.add(new Position(critter.x_coord, critter.y_coord));
            map.put(Positions.get(Positions.size() - 1), critter);
        }*/



        // Iterate through all the critters
        HashSet<Critter> deadCrits = new HashSet<>();


        for (Critter critter : critters) {
            // For the next critter, execute time step
            critter.doTimeStep();
            critter.energy -= Params.REST_ENERGY_COST;
            // If the critter is dead then remove them
            if(!critter.isAlive()){
                deadCrits.add(critter);
                hasMoved.remove(critter);
            }
        }
        critters.removeAll(deadCrits);
        /*for(Critter crit : deadCrits)
        {
            critters.remove(crit);
        }*/
        deadCrits.clear();
        //If two or more critters are occupying the same space then we are going to call the encounter method
        //For each critter
        for(Critter critter : critters)
        {
            // for every other critter
            for(Critter otherCritter : critters)
            {
                // not including itself
                if(critter == otherCritter)
                {
                    continue;
                }
                // if they share the  same space on the map
                if(critter.samePosition(otherCritter) && !deadCrits.contains(critter) && !deadCrits.contains(otherCritter))
                {
                    // Do encounter

                    Critter possRemoval = doEncounters(critter, otherCritter);
                    if(possRemoval!=null){
                        deadCrits.add(possRemoval);
                        hasMoved.remove(possRemoval);
                    }
                }
            }
        }
        critters.removeAll(deadCrits);

        // For all new Critter spawned during doTimeStep and fight()/ doEncounter()
        // Add all the babies to the set
        critters.addAll(babies);
        // Add all the babies to the HasMoved HashMap
        for(Critter baby : babies)
        {
            hasMoved.put(baby, false);
        }

        // Clear the babies list, since all babies have been added to the collection
        babies.clear();


        // Once all the critters have been updated, methods invoked, movement and encounters resolved
        // and all offspring created
        // We must cull all the dead critters from the critter collection


        // Note: Previously we had a code to remove all dead critters; as per the document
        // we are now removing during fight or immediately after time step and during doEncounters after fighting


        // Create and add new clovers to the world
        for(int count = 0; count <  Params.REFRESH_CLOVER_COUNT; count ++)
        {
            try {
                createCritter("Clover");
            } catch (InvalidCritterException e) {
                //what is there to catch?
            }
        }

        // Say all the critters have not moved
        for(Critter crit: hasMoved.keySet())
        {
            hasMoved.put(crit, false);
        }
/*
        for(Critter crit : critters)
        {
            if(crit.x_coord == -1)
            {
                continue;
            }

            for(Critter otherCrit : critters)
            {
                if(crit == otherCrit)
                {
                    continue;
                }
                if(crit.samePosition(otherCrit))
                {
                    System.out.println(crit + " " + crit.energy +  " " +  otherCrit + " " + otherCrit.energy);

                }
            }
        }*/


    }

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    /**
     * "Moves" the critter one step in the specified direction by updating their x and y coord field members. Accounts for torus projection by wrapping the
     * critter around when they move off-grid in some direction.
     * @param direction the direction you want the critter to move in, specified by numbers 0-7 (where 0 represents east, 1 represents northEast,
     *                  2 represents NE, etc.)
     */
    private final void move(int direction)
    {
        if(direction==0) {
            x_coord++;
        }
        else if(direction==1)
        {
            x_coord++;
            y_coord--;
        }
        else if(direction==2)
        {
            y_coord--;
        }
        else if(direction==3)                     //change coordinates accordingly
        {
            x_coord--;
            y_coord--;
        }
        else if(direction==4)
        {
            x_coord--;

        }
        else if(direction==5)
        {
            x_coord--;
            y_coord++;
        }
        else if(direction==6)
        {
            y_coord++;
        }
        else if(direction==7)
        {
            x_coord++;
            y_coord++;
        }
        // Determine if the Critters stepped off the grid
        // IF the critter steped over the right boundary
        if (x_coord > Params.WORLD_WIDTH - 1)
            // place them at the left edge
            x_coord = 0;
        // If the critter stepped over the left edge
        if (x_coord < 0)
            // Placce them at the right edge
            x_coord = Params.WORLD_WIDTH - 1;
        // If the critter stepped over the "ceiling" or top edge
        if (y_coord > Params.WORLD_HEIGHT - 1)
            // PLace them on the floor or bottom edge
            y_coord = 0;
        // If the critter stepped over teh "floor" or bottom edge
        if (y_coord < 0)
            // PLace them on the top or "ceiling"
            y_coord = Params.WORLD_HEIGHT - 1;
    }

    /**
     * Deducts walk energy cost and allows the critter to move one step by calling move once as long as it hasn't moved so far in the timeStep.
     * @param direction the direction you want the critter to move in, specified by numbers 0-7 (where 0 represents east, 1 represents northEast,
     *                  2 represents NE, etc.)
     */
    protected final void walk(int direction) {
        this.energy-= Params.WALK_ENERGY_COST;
        /*
            directions: 0 = East, 1 = South East, 2 = South, 3 = South West, etc.
        */
        // Then move in the indicated direction
        if(!hasMoved.get(this))
        {
            hasMoved.put(this, true);
            move(direction);
        }

    }
    /**
     * Deducts run energy cost and allows the critter to move two steps by calling move twice as long as it hasn't moved so far in the timeStep.
     * @param direction the direction you want the critter to move in, specified by numbers 0-7 (where 0 represents east, 1 represents northEast,
     *                  2 represents NE, etc.)
     */
    protected final void run(int direction) {
        // Call move twice to "Run" in the same direction
        if(!hasMoved.get(this))
        {
            hasMoved.put(this, true);
            move(direction);
            move(direction);
        }
        // Decrement by Run_enery_cost
        this.energy -= Params.RUN_ENERGY_COST;

    }

    /**
     * Determines if a baby can be added, and adds it to the world if appropriate
     *
     * @param direction - Specifies the direction to be moved in
     * @param offspring - a critter object potentially be added to babies
     */
    protected final void reproduce(Critter offspring, int direction) {
        // First confirm that the parent critter (this) has enough energy to reproduce
        if(this.energy < Params.MIN_REPRODUCE_ENERGY)
        {
            //if it doesnt then do nothing
            return;
        }

        // TODO: verify this is compliant with the document
        //Assign the child energy with 1/2 of the parents energy, rounded down(integer division takes care of this)
        offspring.energy =(this.energy / 2);
        this.energy *= .5;
        // Assign the offspring to the parents current position, then move it in the direction indicated
        offspring.setCoordinates(this.x_coord, this.y_coord);

        offspring.move(direction);

        babies.add(offspring);


    }

    /**
     * Changes a critter object coordinates to the specified values
     *
     * @param x - new x coordinate
     * @param y - new y coordinate
     */
    private void setCoordinates(int x, int y){this.x_coord = x; this.y_coord = y;}

    /**
     * Determines if a critter , this, is in the same position as another critter
     *
     * @param otherCritter - critter we are comparing values with this
     * @return boolean representing whether or not they are in the same position
     */
    private boolean samePosition(Critter otherCritter)
    {
        if(this.x_coord == otherCritter.x_coord && this.y_coord == otherCritter.y_coord)
        {
            return true;
        }

        return false;
    }
    /**
     * Expends walk energy cost, and moves 1 unit if it is  a valid request.
     *
     * @return boolean representing whether or not this critter is alive
     */
    private boolean isAlive()
    {
        return this.energy > 0;
    }
    /**
     * Determines if an illegal move was made during doEncounters; namely , checks whether or not  a critter tries to move into
     * a space occupied by another during movment during their fight method
     *
     * @param x - Specifies the old x
     * @param y - Specifies the old y
     * @param crit - specifies the Critter we want to check
     */
    private static void resetIfInvalid(int x, int y, Critter crit)
    {
        for(Critter critter : critters)
        {
            if(critter == crit)
            {
                continue;
            }
            // If critter moved to the same space as another critter, and that space is NOT the same space they were at before
            if(critter.x_coord == crit.x_coord && critter.y_coord == crit.y_coord && critter.x_coord != x && critter.y_coord != y)
            {
                // reset them to their space, because it was an illegal move during fight
                critter.x_coord = x;
                critter.y_coord = y;
            }

        }
    }
    /**
     * Runs the fight method for two critters occupying the same space. If neither leave and neither are dead, then the encounter must ensue.
     * During the encounter, the critter who chose to fight rolls a number between 0 and its fight energy; same for the other.
     * If either chose not to fight then their (whichever critter chose not to fight ) roll is zero.
     * Whoever has the highest roll takes half the others energy and the other critter is dead; during a tie critter wins.
     *
     * @param critter - one critter in the encounter
     * @param otherCritter - the other critter in the counter
     */
    private static Critter doEncounters(Critter critter, Critter otherCritter)
    {
        //Note: Fight has an input paramater: string and an output parameter - boolean
        // Boolean represents whether or not critter wants to fight

        // Note: Critters are not allowed to move to a space inhabited by another critter during fight

        // Store the previous coordinates of critter
        int x = critter.x_coord;
        int y = critter.y_coord;
        // Call their fight function
        boolean critterWantsToFight      =  critter.fight(otherCritter.toString());
        // Validate that their move was legal(if there was one), and reset if necessary
        resetIfInvalid(x,y, critter);


        // repeat for other critter
        x = otherCritter.x_coord;
        y = otherCritter.y_coord;
        boolean otherCritterWantsToFight = otherCritter.fight(critter.toString());
        resetIfInvalid(x,y, otherCritter);



        //if both critters are still alive, and both are in the same position then they will "fight"



        if(!critter.isAlive())
        {
            return critter;
        }
        if(!otherCritter.isAlive())
        {
            return otherCritter;
        }
        if(critter.samePosition(otherCritter))
        {
            int critterRoll = 0;
            int otherCritterRoll = 0;
            // if critter
            // IF critter elected to fight, tehn critter rolls a number between 0 and its energy level
            if(critterWantsToFight){ critterRoll = getRandomInt(critter.energy);}
            // Otherwise its zero

            // Repeat for other critter
            if(otherCritterWantsToFight){otherCritterRoll = getRandomInt(otherCritter.energy);}

            // The higher roll wins and survives the encounter
            // If both roll the same, then arbitrarily we select critter
            // if other critter had a higher roll
            if(otherCritterRoll > critterRoll)
            {
                //then other critter wins
                // Award 1/2 of critter's eneergy to other critter
                otherCritter.energy += .5 * critter.energy;
                // Since critter lost the fight it is dead and must be removed
                return critter;

            }
            // Otherwise critter wins, otherCritter is dead
            else
            {
                critter.energy +=  .5 * otherCritter.energy;
                return otherCritter;
            }

        }
        return null;
    }
    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        protected static List<Critter> getPopulation() {
            ArrayList<Critter> pop = new ArrayList<Critter>(critters);
            return pop;
        }

        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
