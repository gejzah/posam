package solutions;

import interier.Furniture;
import java.util.Comparator;
/**
 * Created by horvathg on 11.09.2017.
 */
// Trieda definujuca yaciatok umiestnenia nabytku v ramci izby
public class LocationFurniture extends Furniture {
    private int x_startposition;
    private int y_startposition;
    boolean is_correct;




    public LocationFurniture(int x_startposition, int y_startposition,Character shape) {
        this.x_startposition = x_startposition;
        this.y_startposition = y_startposition;
        this.setShape(shape);
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public int getX_startposition() {
        return x_startposition;
    }

    public void setX_startposition(int x_startposition) {
        this.x_startposition = x_startposition;
    }

    public int getY_startposition() {
        return y_startposition;
    }

    public void setY_startposition(int y_startposition) {
        this.y_startposition = y_startposition;
    }

    /*Comparator for sorting the list by LocationFurniture Name*/
    public static Comparator<LocationFurniture> ShapeComparator = new Comparator<LocationFurniture>() {

        public int compare(LocationFurniture s1, LocationFurniture s2) {
            Character LocationFurnitureShape1 = s1.getShape();
            Character LocationFurnitureShape2 = s2.getShape();

            //ascending order
            return LocationFurnitureShape1.compareTo(LocationFurnitureShape2);

            //descending order
            //return LocationFurnitureName2.compareTo(LocationFurnitureName1);
        }};

}
