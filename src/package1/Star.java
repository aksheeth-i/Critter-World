
package package1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Star extends Shape {
    public Polygon thisShape;
    public Star()
    {
        thisShape =  new Polygon();
        thisShape.getPoints().addAll(0.01,30.0, 15.0,0.01, 30.0,30.0, 0.01,15.0, 30.0,15.0, 0.01,30.0);
        thisShape.setFill(Color.BLUE);

    }
    public Polygon getStar()
    {
        return thisShape;
    }
    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
