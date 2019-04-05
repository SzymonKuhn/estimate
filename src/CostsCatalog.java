import javax.swing.text.html.parser.Parser;
import java.util.*;

public class CostsCatalog {
    public Map<Integer, List<Double>> mapOfCosts = new HashMap<>();

    Integer costZeroID = CostsTypes.COST_ZERO_STATE.getId();
    Integer costCrudeID = CostsTypes.COST_CRUDE.getId();
    Integer costRoofID = CostsTypes.COST_ROOF.getId();


    List<Double> costZeroList = new ArrayList<>();
    List<Double> costCrudeList = new ArrayList<>();
    List<Double> costRoofList = new ArrayList<>();
//    List<Double> costWindows = new ArrayList<>();
//    List<Double> costInstSanit = new ArrayList<>();
//    List<Double> costInstWent = new ArrayList<>();
//    List<Double> costInstElect = new ArrayList<>();
//    List<Double> costFinnishInter = new ArrayList<>();
//    List<Double> costFacadeBrics = new ArrayList<>();
//    List<Double> costFacadeEPS = new ArrayList<>();
//    List<Double> costFacadeWood = new ArrayList<>();

    public Map<Integer, List<Double>> createMapOfCosts() {
        mapOfCosts.put(costZeroID, costZeroList);
        mapOfCosts.put(costCrudeID, costCrudeList);
        mapOfCosts.put(costRoofID, costRoofList);
        return mapOfCosts;
    }

    public void addCostToCatalog (int costID, Double costToAdd) {
        mapOfCosts.get(costID).add(costToAdd);
    }

    public Double getMaxValue(Integer key) {
        Optional<Double> result = mapOfCosts.get(key).stream()
                .reduce((a, b) -> {
                    if (a > b) {
                        return a;
                    }
                    return b;
                });
        return result.get();
    }

    public Double getMinValue(Integer key) {
        Optional<Double> result = mapOfCosts.get(key).stream()
                .reduce((a, b) -> {
                    if (a < b) {
                         return a;
                    }
                    return b;
                });
        return result.get();
    }

    public Double getAvargeValue (Integer key) {
        Optional<Double> sum = mapOfCosts.get(key).stream()
                .reduce((a, b) -> a + b);
                return sum.get() / mapOfCosts.get(key).size();
    }

    public Map<Integer, List<Double>> getMapOfCosts() {
        return mapOfCosts;
    }



} //class
