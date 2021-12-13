
package package1;

import javafx.scene.paint.Color;

/**
 *     Eagles are very nimble creatures who specialize in swift, frequent movement and make intelligent decisions. Consequently, the eagle chooses to always
 *     run in its time steps, and also choose to only fight fish so they can eat (and run otherwise). Eagles also reproduce in batches of
 *     eggs, so occasionally, they will create 4 baby eagles at the same time.
 */
public class Eagle extends Critter{

    @Override
    public CritterShape viewShape() {
        return CritterShape.STAR;
    }
    public Color viewFillColor()
    {
        return Color.DARKRED;
    }
    public Color viewOutlineColor()
    {
        return Color.DARKBLUE;
    }
    /**
     * Always on the move, and occasionally, hatch 4 eggs
     */
    @Override
    public void doTimeStep() {
        run(getRandomInt(8));
        if(getRandomInt(8) == 5)
        {
            for(int i =0;i<4;i++)
                reproduce(new Eagle(), getRandomInt(8));
        }


    }

    /**
     *
     * @param opponent Eagle only fights fish and only "look" down to fight, flees otherwise
     * @return true if the opponent is a fish
     */
    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("3"))
            return true;
        if(look(6,true)!=null)
            return true;
        this.run(getRandomInt(8));
        return false;
    }

    /**
     * @return the string that represents eagle when displaying the world
     */
    @Override
    public String toString() {
        return "4";
    }


}
