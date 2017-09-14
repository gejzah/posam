package interier;

import objects.FillSpace;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by horvathg on 22.03.2017.
 */
public class Furniture extends FillSpace {

    @Override //Zadefinovanie tvaru nabytku
    public void fillObject(String defFFile) throws NumberFormatException{

        Hashtable<String, Character> hashtable =
                new Hashtable<String, Character>();

        int startline=0;
        String str_size=defFFile.substring(1,2);
     this.setShape(defFFile.substring(0,1).charAt(0));

     if(!this.isInteger(str_size)) throw new NumberFormatException("Velkost nábytku je zle zadefinovana, " + str_size+ ".");
     this.setSize(Integer.parseInt(str_size));

        char[] c_arr=defFFile.trim().substring(2).toCharArray();

        int rows=c_arr.length/this.getSize();

        this.setLengthX(rows);
        this.setLengthY(this.getSize());

        for (int i = 0; i < rows; i++) {
        for (int j = startline; j < c_arr.length; j++) {

                hashtable.put(i+","+(j-i*getSize()),c_arr[j]);
            if(((j+1) % this.getSize()) == 0){
                startline=j+1;
                break;
            }
            }
        }

        this.setObject(hashtable);
    };

    @Override
    public  void fillObject(ArrayList<String> defFFile){};



}
