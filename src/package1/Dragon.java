
package package1;

import javafx.scene.paint.Color;

/**
*    Dragon's are mighty creatures, and are the terror of all other critters. They seldom leave their lairs, and are always ready to tear down their enemies.
*    Dragon's are also rare, and only lay a single egg: once every 10 years.
 */
public class Dragon extends Critter{
    private int hatchlingCount;

    public String toString()
    {
        return "2";
    }

    @Override
    public CritterShape viewShape() {
        return CritterShape.STAR;
    }
    public javafx.scene.paint.Color viewColor() {
        return Color.BLACK;
    }
    /**
     * Dragon's Rarely move, and rarely lay eggs
     */
    @Override
    public void doTimeStep() {
        // Dragons only move when they feel they must fly away; they do not move very much
        if(getRandomInt(10) == 1)
        {
            run(getRandomInt(4));
            return;
        }
        // Dragons Eggs take a very long time to hatch, if at all
        if(getRandomInt(10) == 1)
        {
            // Once every half century or so a dragon is born
            Dragon hatchling = new Dragon();
            reproduce(hatchling, getRandomInt(8));
            hatchlingCount++;
        }




    }
    /**
     * @param opponent not considered, dragon's fear no creatures
     * @return false if low on energy, false  if hatchling count is high ; true otherwise
     */
    @Override
    public boolean fight(String opponent) {
        // Compare the dragons children to RANDOM int
        if(getRandomInt((this.hatchlingCount + 1) *  3 ) > getRandomInt(15))
        {
            // Fly away from fight
            run(getRandomInt(8));
            return false;
        }
        // Otherwise
        return true;
    }



}
