public class House {
    // dane budynku
    private String name;
    private String description;
    private Double areaUsable = 0.00;
    private Double volumeBrutto = 0.00;
    private Double windowsArea = 0.00;
    private Double buildingArea = 0.00;
    private Double roofArea = 0.00;


    // koszty etapów
    private Double costZeroStateTotal = 0.00; // stan zerowy
    private Double costCrudeTotal = 0.00; // stan surowy otwarty bez pokrycia dachu
    private Double costRoofTotal = 0.00; // pokrycie dachu
//    private Double costWindowsTotal = 0.00; // koszt okien i drzwi zewenętrznych
//    private Double costSanitInstTotal = 0.00; // koszt instalacji sanitarnych bez wentylacji mechanciznej
//    private Double costElectInstTotal = 0.00; // koszt instalacji elektrycznych
//    private Double costWentInstTotal = 0.00; // ksozt wentylacji mechanicznej
//    private Double costInterFinnishTotal = 0.00; // koszt wykończenia wewnętrznego do stanu deweloperskiego
//    private Double costFacadesBrickTotal = 0.00; // kosszt wykończenia zewnętrznego


    public House(String name, String description, Double areaUsable, Double volumeBrutto, Double windowsArea, Double buildingArea, Double roofArea, Double costZeroStateTotal, Double costCrude, Double costRoof) {
        this.name = name;
        this.description = description;
        this.areaUsable = areaUsable;
        this.volumeBrutto = volumeBrutto;
        this.windowsArea = windowsArea;
        this.buildingArea = buildingArea;
        this.roofArea = roofArea;
        this.costZeroStateTotal = costZeroStateTotal;
        this.costCrudeTotal = costCrude;
        this.costRoofTotal = costRoof;
    }



    public Double getCostZeroState() {
        return costZeroStateTotal / buildingArea;
    }

    public Double getCostCrude() {
        return costCrudeTotal / areaUsable;
    }

    public Double getCostRoof() {
        return costRoofTotal / roofArea;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", areaUsable=" + areaUsable +
                ", volumeBrutto=" + volumeBrutto +
                ", windowsArea=" + windowsArea +
                ", buildingArea=" + buildingArea +
                ", roofArea=" + roofArea +
                ", costZeroStateTotal=" + costZeroStateTotal +
                ", costCrudeTotal=" + costCrudeTotal +
                ", costRoofTotal=" + costRoofTotal +
                '}';
    }

    public String toExport() {
        return name + ", " + description + ", "
                + areaUsable + ", " + volumeBrutto + ", " + windowsArea + ", " + buildingArea +
                ", " + roofArea + ", " + costZeroStateTotal + ", " + costCrudeTotal + ", " + costRoofTotal;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getAreaUsable() {
        return areaUsable;
    }

    public Double getVolumeBrutto() {
        return volumeBrutto;
    }

    public Double getWindowsArea() {
        return windowsArea;
    }

    public Double getBuildingArea() {
        return buildingArea;
    }

    public Double getRoofArea() {
        return roofArea;
    }

    public Double getCostZeroStateTotal() {
        return costZeroStateTotal;
    }

    public Double getCostCrudeTotal() {
        return costCrudeTotal;
    }

    public Double getCostRoofTotal() {
        return costRoofTotal;
    }
}
