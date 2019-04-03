import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EstimateMain {

    public static void main(String[] args) {
        CostsCatalog costsCatalog = new CostsCatalog();
        costsCatalog.createMapOfCosts();

        House house1 = new House("dom", "domek pierwszy", 200.00, 900.00, 40.00, 160.00, 170.00, 90000.00, 190000.00, 130000.00);
        House house2 = new House("dom2", "domek drugi", 190.00, 850.00, 25.00, 100.00, 120.00, 75000, 150000, 100000);
        addHouseToCostCatalogue(house1, costsCatalog);
        addHouseToCostCatalogue(house2, costsCatalog);

        System.out.println(costsCatalog.getMapOfCosts());
        System.out.println(costsCatalog.getAvargeValue(CostsTypes.COST_CRUDE.getId()));
        System.out.println(costsCatalog.getMaxValue(CostsTypes.COST_CRUDE.getId()));
        System.out.println(costsCatalog.getMinValue(CostsTypes.COST_CRUDE.getId()));

        exportMapOfCosts(costsCatalog);

    } // main

    public static void addHouseToCostCatalogue (House house, CostsCatalog costsCatalog) {
        costsCatalog.addCostToCatalog(CostsTypes.COST_ZERO_STATE.getId(), house.getCostZeroState());
        costsCatalog.addCostToCatalog(CostsTypes.COST_CRUDE.getId(), house.getCostCrude());
        costsCatalog.addCostToCatalog(CostsTypes.COST_ROOF.getId(), house.getCostRoof());
    }

    public static void exportMapOfCosts (CostsCatalog costsCatalog) {
        Path basePath = Paths.get("D:\\Costs");
        try {
            if (!Files.exists(basePath)) {
                Files.createDirectory(basePath);
            }
            } catch (IOException e) {
            e.printStackTrace();
        }

        Path fileMapOfCosts = basePath.resolve("map_of_costs.txt");

        try {
            if (!Files.exists(fileMapOfCosts)) {
                Files.createFile(fileMapOfCosts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(fileMapOfCosts.toString())) {
            writer.write(costsCatalog.getMapOfCosts().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

} // class
