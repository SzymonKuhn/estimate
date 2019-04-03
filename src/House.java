public class House {
    // dane budynku
    private String name;
    private String description;
    private double areaUsable = 0.00;
    private double volumeBrutto = 0.00;
    private double windowsArea = 0.00;
    private double buildingArea = 0.00;
    private double roofArea = 0.00;


    // koszty etapów
    private double costZeroStateTotal = 0.00; // stan zerowy
    private double costCrudeTotal = 0.00; // stan surowy otwarty bez pokrycia dachu
    private double costRoofTotal = 0.00; // pokrycie dachu
//    private double costWindowsTotal = 0.00; // koszt okien i drzwi zewenętrznych
//    private double costSanitInstTotal = 0.00; // koszt instalacji sanitarnych bez wentylacji mechanciznej
//    private double costElectInstTotal = 0.00; // koszt instalacji elektrycznych
//    private double costWentInstTotal = 0.00; // ksozt wentylacji mechanicznej
//    private double costInterFinnishTotal = 0.00; // koszt wykończenia wewnętrznego do stanu deweloperskiego
//    private double costFacadesBrickTotal = 0.00; // kosszt wykończenia zewnętrznego


    public House(String name, String description, double areaUsable, double volumeBrutto, double windowsArea, double buildingArea, double roofArea, double costZeroStateTotal, double costCrude, double costRoof) {
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
}
