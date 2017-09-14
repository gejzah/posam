package compute;

import configs.IOProperties;
import interier.Furniture;
import interier.Room;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import solutions.FillRoom;

import java.io.IOException;
import java.util.*;

/**
 * Created by horvathg on 08.09.2017.
 */

public class Application {
    final private static int Furniture_MAX = 26;
    public static void main(String[] args) {

        IOProperties ioprops = new IOProperties(); //Nacitanie konfiguracnych premennych
        String  Output=new String();

        try {
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("iohandle-conf.xml");

            ioprops = (IOProperties) context.getBean("iohandle");

        } catch (
                Exception ert)

        {
            System.err.println(" Chyba pri nacitani prednastavených hodnôt: '" + ert +
                    "' zo súboru iohandle-conf.xml.");
            System.exit(1);

        }
      //Definicia izby
        ArrayList<String> defroom = null;
        try {
            defroom = FileIO.readFile(ioprops.getIn_room());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(" Chyba pri čítaní: " + e +
                    "' zo súboru " + ioprops.getIn_room() + ".");
            System.exit(1);
        }

        //Definicia nabytkov
        ArrayList<String> deffurniture = null;
        try {
            deffurniture = FileIO.readFile(ioprops.getIn_furniture());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Chyba pri čítaní: " + e +
                    "' zo súboru " + ioprops.getIn_furniture() + ".");
            System.exit(1);
        }


        Room mroom = Room.getInstance();
try {

       //Ziskanie tvaru izby
        mroom.fillObject(defroom);

        //Ziskanie tvaru nabytkov
        Iterator<String> iterator = deffurniture.iterator();
        while (iterator.hasNext()) {
            Furniture furniture=new Furniture();
            furniture.fillObject(iterator.next());
            FillRoom.putAll_furniture(furniture.getShape(),furniture);
        }

}catch (Exception e){
    System.err.println("Problem pri definovaní nábytku alebo izby: " + e +
            "' zo súborov " + ioprops.getIn_furniture() + "alebo " + ioprops.getIn_room()+".");
    System.exit(1);
}

        FillRoom fRoom= new FillRoom();
        Set<Character> keys = FillRoom.getAll_furniture().keySet();
        //Vyplenenie defaultneho tvaru nabytku
        fRoom.setSolutionroomShape(mroom.getObject());

        //definicia prazdneho riesenia
        List<FillRoom> allLocfFur= new ArrayList<>();
        allLocfFur.add(fRoom);

            if(keys.size() > Furniture_MAX){
                throw new Error("Prilis vela roznych typov nabytku. Je ich viac ako: " + Furniture_MAX+".");
            }
        //Ziskanie vsetkych riesenie
        for(Character key: keys) allLocfFur=FillRoom.tryPlaceFurniture(key,allLocfFur);

        //Priprava vystupu ak existuje aspon
        if(allLocfFur.size() >0)
        if(allLocfFur.get(0).getLocationsFurniture().size() == FillRoom.getAll_furniture().size()) Output=FillRoom.toString(allLocfFur);

        //Zapis do suboru
        try {
            FileIO.writeFile(Output,ioprops.getOut());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Application is succesfully done");
    }

}
