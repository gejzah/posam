package interier;

import solutions.FillRoom;
import objects.FillSpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by horvathg on 08.09.2017.
 */
public class Room extends FillSpace {

    private static Room room = new Room( );
    protected Room() { }

    public static Room getInstance() {
        return room;
    }

    //Zadefinovanie tvaru izby
    @Override
    public void fillObject(ArrayList<String> defffile) throws NumberFormatException{


        Hashtable<String, Character> hashtable =
                new Hashtable<String, Character>();

        String[] size=defffile.get(0).trim().split(",");

        if(!(isInteger(size[0]) && isInteger(size[1]))) throw new NumberFormatException("Velkost izby je zle zadefinovana, " + defffile.get(0)+ ".");
        this.setLengthX(Integer.parseInt(size[0]));
        this.setLengthY(Integer.parseInt(size[1]));

        for (int i = 1; i < Integer.parseInt(size[0])+1; i++) {
            char[] c_arr=defffile.get(i).toCharArray();

            for (int j = 0; j < Integer.parseInt(size[1]); j++) {
                hashtable.put(i-1+","+j,c_arr[j]);
            }
        }
this.setObject(hashtable);


    };

    @Override
    public void fillObject(String defffile){
    };
}
