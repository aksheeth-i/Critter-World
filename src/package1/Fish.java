
package package1;

import javafx.scene.paint.Color;

/**
 *
 *     Fish are not very smart nor powerful creatures. Therefore, they make very poor decisions-  they choose to fight only when they have very low energy
 *     and aren't even smart enough to flee. However, the only thing they have going for them is their reproductive rate- at every worldTimeStep,
 *     fish will multiple like crazy but will also die fairly quick.
 */
public class Fish extends Critter {
    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    @Override
    public Color viewFillColor() {
        return Color.AQUAMARINE;
    }
    public javafx.scene.paint.Color viewOutlineColor() {
        return Color.TEAL;
    }

    /**
     * Fish are mobile and lay many eggs.
     */
    @Override
    public void doTimeStep() {
        this.run(getRandomInt(8));
        for(int i =0;i<3;i++)
            reproduce(new Fish(), getRandomInt(8));
    }

    /**
     * @return the string that represents fish when displaying the world
     */
    @Override
    public String toString() {
        return "3";
    }

    /**
     * @param opponent Not considered when deciding to fight since fish aren't smart
     * @return true if low on energy, false otherwise
     */
    @Override
    public boolean fight(String opponent) {
        if (getEnergy() < 5)
            return true;
        return false;
    }
}
