package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by horvathg on 11.09.2017.
 */
// Abstraktna trieda, ktora sa pouziva na definovanie prvkov
public abstract class FillSpace implements Deployment {
    private Character shape;
    private int size;
    protected Hashtable<String,Character> object;

    private int lengthX;
    private int lengthY;

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public Character getShape() {
        return shape;
    }

    public void setShape(Character shape) {
        this.shape = shape;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Hashtable<String, Character> getObject() {
        return object;
    }

    public void setObject(Hashtable<String, Character> object) {
        this.object = object;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
