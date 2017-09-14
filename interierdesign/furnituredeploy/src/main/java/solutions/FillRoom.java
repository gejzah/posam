package solutions;

import interier.Furniture;
import interier.Room;

import java.util.*;

/**
 * Created by horvathg on 11.09.2017.
 */
// Trieda ktora sa vyuziva na vypocet rieseni umiestnenia nabytku v miestnosti
public class FillRoom extends Room {
private static HashMap<Character, Furniture> all_furniture = new HashMap();
private  Map<String,Character> SolutionroomShape;
private List<LocationFurniture> locationsFurniture =  new ArrayList<>();

    public List<LocationFurniture> getLocationsFurniture() {
        return locationsFurniture;
    }

    public void setLocationsFurniture(List<LocationFurniture> locationsFurniture) {
        this.locationsFurniture = locationsFurniture;
    }

    public void addLocationsFurniture(LocationFurniture locationsFurniture) {
        this.locationsFurniture.add(locationsFurniture);
    }

    public FillRoom(FillRoom copyInstance) {
        this.SolutionroomShape = new HashMap<>(copyInstance.SolutionroomShape);
        this.locationsFurniture = new ArrayList<>(copyInstance.locationsFurniture);
    }

    public FillRoom() {
        SolutionroomShape = this.object;
    }


    public Map<String, Character> getSolutionroomShape() {
        return SolutionroomShape;
    }

     public void setSolutionroomShape(Map<String, Character> solutionroomShape) {
       SolutionroomShape = solutionroomShape;
    }

    public static HashMap<Character, Furniture> getAll_furniture() {
        return all_furniture;
    }

    public static void setAll_furniture(HashMap<Character, Furniture> all_furniture) {
        FillRoom.all_furniture = all_furniture;
    }

    public static void putAll_furniture(Character key, Furniture furniture) {
        FillRoom.all_furniture.put(key,furniture) ;
    }


    public static  List<FillRoom> tryPlaceFurniture(Character key,  List<FillRoom> fillRooms){
        List<FillRoom> new_fillRooms = new ArrayList<>();
        Set<String> OccupiedPossition;

        Furniture furniture= FillRoom.getAll_furniture().get(key);

        Iterator<FillRoom> iteratorFR = fillRooms.iterator();
        while (iteratorFR.hasNext()) {
            FillRoom posSolFillRoom= iteratorFR.next();

            for (int rpositionx = 0; rpositionx < Room.getInstance().getLengthX(); rpositionx++) {
                for (int rpositiony = 0; rpositiony < Room.getInstance().getLengthY(); rpositiony++) {
                    boolean placingOk=false;
                    FillRoom new_posSolFillRoom = new FillRoom(posSolFillRoom);
                    OccupiedPossition= new HashSet<>();
                    if((rpositionx+furniture.getLengthX() <= Room.getInstance().getLengthX()) && (rpositiony+furniture.getLengthY() <= Room.getInstance().getLengthY())){

                        for (int fpositionx = 0; fpositionx < furniture.getLengthX(); fpositionx++) {
                            for (int fpositiony = 0; fpositiony < furniture.getLengthY(); fpositiony++) {
                                if(
                                        ((posSolFillRoom.getSolutionroomShape()).get((rpositionx+fpositionx)+","+(rpositiony+fpositiony)).equals('#')) ||
                                                (furniture.getObject().get((fpositionx+","+fpositiony)).equals('.')))  {
                                    if(furniture.getObject().get(fpositionx+","+fpositiony).equals('#')){
                                        OccupiedPossition.add((rpositionx+fpositionx)+","+(rpositiony+fpositiony));
                                    }
                                    placingOk=true;
                                }else{
                                    placingOk=false;
                                    break;
                                }
                            }
                            if (!placingOk)
                                break;

                        }
                    }
                    if(placingOk) {
                        Iterator<String> iterator = OccupiedPossition.iterator();
                        while (iterator.hasNext()) {
                            new_posSolFillRoom.getSolutionroomShape().put(iterator.next(),key);
                        }
                        new_posSolFillRoom.addLocationsFurniture((new LocationFurniture(rpositionx,rpositiony,key)));
                        new_fillRooms.add(new_posSolFillRoom);
                    }

                }
            }


        }

        return new_fillRooms;
    }
// Príprava výstupu na zápis do súboru
    public static String toString(List<FillRoom> fillRooms) {
    String content=new String();
        Boolean isFirstSol=true;
        Iterator<FillRoom> soluFillRoom = fillRooms.iterator();
        while (soluFillRoom.hasNext()) {
            if(!isFirstSol) content+="\r\n";
            isFirstSol=false;

            FillRoom oneSolFillRoom=soluFillRoom.next();
            List<LocationFurniture> solLocFurniture=oneSolFillRoom.getLocationsFurniture();

            Iterator<LocationFurniture> soluLocFurn = solLocFurniture.iterator();
            Boolean isFirst=true;
            while (soluLocFurn.hasNext()) {
                LocationFurniture concretLocFurn = soluLocFurn.next();
                if (!isFirst) content += " ";
                isFirst = false;
                content += concretLocFurn.getShape() + "(" + concretLocFurn.getX_startposition() + "," + concretLocFurn.getY_startposition() + ")";
            }
        }

        return content;
    }



}
