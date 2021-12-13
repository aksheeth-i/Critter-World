
package package1;


import javafx.scene.paint.Color;

/**
 *  Unicorns are majestic but proud critter's, who love to roam and eat clovers.
 *  Unicorns only make new Unicorns when they rest, and will always fight when their energy is high... unless there is a Dragon.
 */
public class Unicorn extends Critter{
    private final int FIGHT_ENEGRY = 50;
    @Override
    public String toString()
    {
        return "1";
    }
    // During Time Step, Unicorns Eat Clovers, and  Walks Half the Time

    @Override
    public CritterShape viewShape() {
        return CritterShape.DIAMOND;
    }
    public javafx.scene.paint.Color viewColor() {
        return Color.DARKMAGENTA;
    }




    /**
     * Occasionally on the move; only reproduces when resting
     */
    @Override
    public void doTimeStep() {
        boolean walked = false;
        // If a critter isn't around
        if(look(Critter.getRandomInt(8),false)==null)
        {
            // Walk in a random direction
            walk(Critter.getRandomInt(8));
            walked = true;
        }

        // If the Unicorn did not walk, then expense their energy for doing nothing
        if(!walked) {
            // Decide whether or not to reproduce - 50 - 50
            if (getRandomInt(4) < 2)
            {
                // Unicorn will attempt to reproduce twice
                Unicorn child = new Unicorn();
                reproduce(child, getRandomInt(8));
                Unicorn otherChild = new Unicorn();
                reproduce(otherChild, getRandomInt(8));

            }
        }
    }
    /**
     * Deciding whether to fight or flee
     *
     * @param opponent - reperesent the type of critter we are fighting
     * @returns true with enough energy , if clover, and not dragon
     */
    @Override
    public boolean fight(String opponent) {
        // If the opponent is a Clover
        if(opponent.equals("@"))
        {
            // Then Unicorn will choose to fight, i.e. eat the clover
            return true;
        }
        // If the Unicorn does not have the energy to fight, or the opponent is a dragon -> then run
        if(opponent.equals("2"))
        {
            this.run(getRandomInt(8));
            return false;
        }
        // Otherwise , fight!
        return true;
    }
}
