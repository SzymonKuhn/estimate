package estimates;

import types.AreaTypes;
import types.CostsTypes;

import java.util.HashMap;
import java.util.Map;

public class Estimate {
    // dane budynku
    private static int COUNT = 1;
    private int id;
    private String name;
    private String description;
    //    private Double areaUsable = 0.00; //usunąć
//    private Double volumeBrutto = 0.00; //usunąć
//    private Double windowsArea = 0.00; // usunąć
//    private Double buildingArea = 0.00; // usunąć
//    private Double roofArea = 0.00; // usunąć
    private Map<CostsTypes, Double> costsOfEstimate = new HashMap<>(); // kontynuować
    private Map<AreaTypes, Double> areasOfEstimate = new HashMap<>(); // kontynuować

    public void addCost (CostsTypes key, Double value) {
        costsOfEstimate.put(key, value);
    }

    public Double getCost (CostsTypes key) {
        return costsOfEstimate.get(key);
    }

    public void addArea (AreaTypes key, Double area) {
        areasOfEstimate.put(key, area);
    }

    public Double getArea (AreaTypes key) {
        return areasOfEstimate.get(key);
    }

    public int getId () {
        return this.id;
    }


    // koszty etapów
//    private Double costZeroStateTotal = 0.00; // stan zerowy
//    private Double costCrudeTotal = 0.00; // stan surowy otwarty bez pokrycia dachu
//    private Double costRoofTotal = 0.00; // pokrycie dachu
//    private Double costWindowsTotal = 0.00; // koszt okien i drzwi zewenętrznych
//    private Double costSanitInstTotal = 0.00; // koszt instalacji sanitarnych bez wentylacji mechanciznej
//    private Double costElectInstTotal = 0.00; // koszt instalacji elektrycznych
//    private Double costWentInstTotal = 0.00; // ksozt wentylacji mechanicznej
//    private Double costInterFinnishTotal = 0.00; // koszt wykończenia wewnętrznego do stanu deweloperskiego
//    private Double costFacadesBrickTotal = 0.00; // kosszt wykończenia zewnętrznego


    public Estimate(String name, String description) {
//    Double areaUsable, Double volumeBrutto, Double windowsArea, Double buildingArea, Double roofArea, Double costZeroStateTotal, Double costCrude, Double costRoof) {
        this.id = COUNT++;
        this.name = name;
        this.description = description;
//        this.areaUsable = areaUsable;
//        this.volumeBrutto = volumeBrutto;
//        this.windowsArea = windowsArea;
//        this.buildingArea = buildingArea;
//        this.roofArea = roofArea;
//        this.costZeroStateTotal = costZeroStateTotal;
//        this.costCrudeTotal = costCrude;
//        this.costRoofTotal = costRoof;
    }



//    public Double getCostZeroState() {
//        return costZeroStateTotal / buildingArea;
//    }

//    public Double getCostCrude() {
//        return costCrudeTotal / areaUsable;
//    }

//    public Double getCostRoof() {
//        return costRoofTotal / roofArea;
//    }

//    @Override
//    public String toString() {
//        return "houses.House{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", areaUsable=" + areaUsable +
//                ", volumeBrutto=" + volumeBrutto +
//                ", windowsArea=" + windowsArea +
//                ", buildingArea=" + buildingArea +
//                ", roofArea=" + roofArea +
//                ", costZeroStateTotal=" + costZeroStateTotal +
//                ", costCrudeTotal=" + costCrudeTotal +
//                ", costRoofTotal=" + costRoofTotal +
//                '}';
//    }


    public String toExport() {
        return id + ", " + name + ", " + description + ", " + costsOfEstimate.toString() + ", " + areasOfEstimate.toString();
//                + areaUsable + ", " + volumeBrutto + ", " + windowsArea + ", " + buildingArea +
//                ", " + roofArea + ", " + costZeroStateTotal + ", " + costCrudeTotal + ", " + costRoofTotal;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

//    public Double getAreaUsable() {
//        return areaUsable;
//    }

//    public Double getVolumeBrutto() {
//        return volumeBrutto;
//    }

//    public Double getWindowsArea() {
//        return windowsArea;
//    }

//    public Double getBuildingArea() {
//        return buildingArea;
//    }

//    public Double getRoofArea() {
//        return roofArea;
//    }

//    public Double getCostZeroStateTotal() {
//        return costZeroStateTotal;
//    }

//    public Double getCostCrudeTotal() {
//        return costCrudeTotal;
//    }

//    public Double getCostRoofTotal() {
//        return costRoofTotal;
//    }

} //class






//    private String name;
//    private String description;
//    private Double areaUsable = 0.00;
//    private Double volumeBrutto = 0.00;
//    private Double windowsArea = 0.00;
//    private Double buildingArea = 0.00;
//    private Double roofArea = 0.00;
//
//
//    // koszty etapów
//    private Double costZeroStateTotalMin = 0.00; // stan zerowy minimum
//    private Double costZeroStateTotalAvg = 0.00; // stan zerowy średnio
//    private Double costZeroStateTotalMax = 0.00; // stan zerowy maksimum
//    private Double costCrudeTotalMin = 0.00; // stan surowy otwarty bez pokrycia dachu minimum
//    private Double costCrudeTotalAvg = 0.00; // stan surowy otwarty bez pokrycia dachu średnio
//    private Double costCrudeTotalMax = 0.00; // stan surowy otwarty bez pokrycia dachu maksimum
//    private Double costRoofTotalMin = 0.00; // pokrycie dachu minimum
//    private Double costRoofTotalAvg = 0.00; // pokrycie dachu średnio
//    private Double costRoofTotalMax = 0.00; // pokrycie dachu maksimum
//    private Double costTotalMin = 0.00; // łącznie minimum
//    private Double costTotalAvg = 0.00; // łącznie średnio
//    private Double costTotalMax = 0.00; // łącznie maksimum
//
//    public Estimate(String name, String description, Double areaUsable, Double volumeBrutto, Double windowsArea, Double buildingArea, Double roofArea) {
//        this.name = name;
//        this.description = description;
//        this.areaUsable = areaUsable;
//        this.volumeBrutto = volumeBrutto;
//        this.windowsArea = windowsArea;
//        this.buildingArea = buildingArea;
//        this.roofArea = roofArea;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Double getAreaUsable() {
//        return areaUsable;
//    }
//
//    public Double getVolumeBrutto() {
//        return volumeBrutto;
//    }
//
//    public Double getWindowsArea() {
//        return windowsArea;
//    }
//
//    public Double getBuildingArea() {
//        return buildingArea;
//    }
//
//    public Double getRoofArea() {
//        return roofArea;
//    }
//
//    public Double getCostZeroStateTotalMin() {
//        return costZeroStateTotalMin;
//    }
//
//    public Double getCostZeroStateTotalAvg() {
//        return costZeroStateTotalAvg;
//    }
//
//    public Double getCostZeroStateTotalMax() {
//        return costZeroStateTotalMax;
//    }
//
//    public Double getCostCrudeTotalMin() {
//        return costCrudeTotalMin;
//    }
//
//    public Double getCostCrudeTotalAvg() {
//        return costCrudeTotalAvg;
//    }
//
//    public Double getCostCrudeTotalMax() {
//        return costCrudeTotalMax;
//    }
//
//    public Double getCostRoofTotalMin() {
//        return costRoofTotalMin;
//    }
//
//    public Double getCostRoofTotalAvg() {
//        return costRoofTotalAvg;
//    }
//
//    public Double getCostRoofTotalMax() {
//        return costRoofTotalMax;
//    }
//
//    public void setCostZeroStateTotalMin(Double costZeroStateTotalMin) {
//        this.costZeroStateTotalMin = costZeroStateTotalMin * buildingArea;
//    }
//
//    public void setCostZeroStateTotalAvg(Double costZeroStateTotalAvg) {
//        this.costZeroStateTotalAvg = costZeroStateTotalAvg * buildingArea;
//    }
//
//    public void setCostZeroStateTotalMax(Double costZeroStateTotalMax) {
//        this.costZeroStateTotalMax = costZeroStateTotalMax * buildingArea;
//    }
//
//    public void setCostCrudeTotalMin(Double costCrudeTotalMin) {
//        this.costCrudeTotalMin = costCrudeTotalMin * areaUsable;
//    }
//
//    public void setCostCrudeTotalAvg(Double costCrudeTotalAvg) {
//        this.costCrudeTotalAvg = costCrudeTotalAvg * areaUsable;
//    }
//
//    public void setCostCrudeTotalMax(Double costCrudeTotalMax) {
//        this.costCrudeTotalMax = costCrudeTotalMax * areaUsable;
//    }
//
//    public void setCostRoofTotalMin(Double costRoofTotalMin) {
//        this.costRoofTotalMin = costRoofTotalMin * roofArea;
//    }
//
//    public void setCostRoofTotalAvg(Double costRoofTotalAvg) {
//        this.costRoofTotalAvg = costRoofTotalAvg * roofArea;
//    }
//
//    public void setCostRoofTotalMax(Double costRoofTotalMax) {
//        this.costRoofTotalMax = costRoofTotalMax * roofArea;
//    }
//
//    @Override
//    public String toString() {
//        return "estimates.Estimate{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", areaUsable=" + areaUsable +
//                ", volumeBrutto=" + volumeBrutto +
//                ", windowsArea=" + windowsArea +
//                ", buildingArea=" + buildingArea +
//                ", roofArea=" + roofArea +
//                ", costZeroStateTotalMin=" + costZeroStateTotalMin +
//                ", costZeroStateTotalAvg=" + costZeroStateTotalAvg +
//                ", costZeroStateTotalMax=" + costZeroStateTotalMax +
//                ", costCrudeTotalMin=" + costCrudeTotalMin +
//                ", costCrudeTotalAvg=" + costCrudeTotalAvg +
//                ", costCrudeTotalMax=" + costCrudeTotalMax +
//                ", costRoofTotalMin=" + costRoofTotalMin +
//                ", costRoofTotalAvg=" + costRoofTotalAvg +
//                ", costRoofTotalMax=" + costRoofTotalMax +
//                '}';
//    }
//}
