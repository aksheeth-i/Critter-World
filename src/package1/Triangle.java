
package package1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Triangle extends Shape {
    public Polygon thisShape;
    public Triangle()
    {
        thisShape =  new Polygon();
        Double[] arr = {14.99, 0.01, 0.01, 29.99, 14.99, 29.99 , 29.99, 29.99};
        thisShape.getPoints().addAll(arr);
        thisShape.setFill(Color.BLUE);

    }
    public Polygon getTriangle()
    {
        return thisShape;
    }
    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
