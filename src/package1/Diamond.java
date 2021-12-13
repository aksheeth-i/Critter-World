
package package1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Diamond extends Shape {
    public Polygon thisShape;
    public Diamond()
    {
        thisShape =  new Polygon();
        Double[] arr = new Double[]{0.,15.0, 15.0, 30.0, 30.0, 15.0, 15.0, 0.0};
        thisShape.getPoints().addAll(arr);
        thisShape.setFill(Color.DARKCYAN);

    }
    public Polygon getDiamond()
    {
        return thisShape;
    }
    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
