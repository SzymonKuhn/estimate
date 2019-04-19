package costs;

import houses.House;
import houses.HousesManager;
import types.CostsTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CostsManager {
    //klasa służy do wyliczania poszczególnych kosztów w przeliczeniu na powierzchnię, tworzenie listy tych kosztów,
    //oraz wyliczania kosztu minimalnego, średniego i maksymalnego
    // klasa bazuje na liście obiektów klasy House z HouseManager

    HousesManager housesManager = new HousesManager();

    public Double getMinCost(CostsTypes costType) {
        List<Double> costsList = new ArrayList<>();
        costsList.addAll(getListOfCostsPerUnit(costType));
        Optional<Double> reduced = costsList.stream()
                .reduce((a, b) -> {
                    if (a < b) {
                        return a;
                    }
                    return b;
                }
        );
        return reduced.get();
}

    public Double getMaxCost(CostsTypes costType) {
        List<Double> costsList = new ArrayList<>();
        costsList.addAll(getListOfCostsPerUnit(costType));
        Optional<Double> reduced = costsList.stream()
                .reduce((a, b) -> {
                            if (a > b) {
                                return a;
                            }
                            return b;
                        }
                );
        return reduced.get();
    }

    public Double getAvgCost(CostsTypes costType) {
        List<Double> costsList = new ArrayList<>();
        costsList.addAll(getListOfCostsPerUnit(costType));
        Optional<Double> sum = costsList.stream()
                .reduce((a, b) -> a+b);
        return sum.get()/costsList.size();
    }


    public List<Double> getListOfCostsPerUnit(CostsTypes costsType) {
        List<House> houseList = new ArrayList<>();
        List<Double> costList = new ArrayList<>();
        houseList.addAll(housesManager.getHouseList());
        for (House house : houseList) {
            if (house.getCost(costsType) != null) {
                costList.add(getCostPerUnit(costsType, house));
            }
        }
        return costList;
    }

    private double getCostPerUnit(CostsTypes costType, House house) {
        return (house.getCost(costType) / house.getArea(costType.getAreaType()));
    }
}
