package houses;

import types.AreaTypes;
import types.CostsTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HousesManager {
    private List<House> houseList = new ArrayList<>();

    public HousesManager() {
        this.houseList = houseList;
    }

    public void addHouse (House house) {
        houseList.add(house);
    }

    public boolean addCostToHouse (House house, CostsTypes costType, Double value){
        house.addCost(costType, value);
        return true;
    }

    public boolean addAreaToHouse (House house, AreaTypes areaType, Double value){
        house.addArea(areaType, value);
        return true;
    }

    public boolean deleteHouse (int id) {
        for (int i =0; i < houseList.size(); i++) {
            House house = houseList.get(i);
            if (id == house.getId()) {
                houseList.remove(house);
                return true;
            }
        }
        return false;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public House importHouse (String input){
        String[] stringList = input.split("//");
        int id = Integer.parseInt(stringList[0]);
        String name = stringList[1];
        String description = stringList[2];
        String areas = stringList[3];
        String costs = stringList[4];
        House house = new House (name, description);
        house.setAreasOfHouse(makeAreaMapFromString(areas));
        house.setCostsOfHouse(makeCostsMapFromString(costs));
        return house;
    }

    private Map<AreaTypes, Double> makeAreaMapFromString (String input) {
        String cuttedInput = input.substring(1,input.length()-2);
        Map<AreaTypes, Double> map = new HashMap<>();
        String[] inputArray = cuttedInput.split(", ");
        for (int i = 0; i < inputArray.length; i++) {
            String[] array = inputArray[i].split("=");
            map.put(AreaTypes.getEnumFromString(array[0]), Double.parseDouble(array[1]));
        }
        return map;
    }

    private Map<CostsTypes, Double> makeCostsMapFromString (String input) {
        String cuttedInput = input.substring(1, input.length()-2);
        Map<CostsTypes, Double> map = new HashMap<>();
        String[] inputArray = cuttedInput.split(", ");
        for (int i = 0; i < inputArray.length; i++) {
            String[] array = inputArray[i].split("=");
            map.put(CostsTypes.getEnumFromString(array[0]), Double.parseDouble(array[1]));
        }
        return map;
    }
}
