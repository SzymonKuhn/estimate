public enum CostsTypes {
    COST_ZERO_STATE (1, "Stan zero", "Koszt stanu zerowego, przeliczany na powierzchnię zabudowy"),
    COST_CRUDE (2, "Stan surowy otwarty", "Koszt stanu surowego otwartego z konstrukcją dachu, przeliczany na powierzchnię użytkową"),
    COST_ROOF (3, "Pokrycie dachu", "Koszt pokrycia dachu przeliczany na powierzchnię dachu");
//    COST_WINDOWS (4, "Okna i przeszklenia", "Koszt okien i innych przeszkleń, przeliczany na powierzchnię okien"),
//    COST_INST_SANIT (5, "Instalacje sanitarne", "Koszt instalacji sanitarnych, przeliczany na powierzchnię użytkową"),
//    COST_INST_WENT (6, "Instalacja wentylacji mechanicznej", "Koszt instalacji mechanicznej, przeliczany na powierzchnię użtytkową"),
//    COST_INST_ELECT (7, "Instalacja elektryczna", "Koszt instalacji elektrycznej, przeliczany na powierzchnię użytkową"),
//    COST_FINNISH_INTER (8, " Stan deweloperski", "Koszt wykończenia wewnętrznego do stanu deweloperskiego, przeliczany na powierzchnię użytkową"),
//    COST_FACADE_BRICKS (9, "Elewacje z cegły", "Kosszt wykończenia elewacji z cegływraz z ociepleniem, przeliczany na powierzchnię ścian"),
//    COST_FACADE_EPS (10, "Elewacje metodą lekką mokrą", "Koszt wykończenia ścian styropianem i tynkiem mineralnym, przeliczany na powierzchnię ścian"),
//    COST_FACADE_WOOD (11, "Elewacje drewniane", "Koszt wykończenia ścian deskowaniem wraz z ociepleniem, przeliczany na powierzchnię ścian");

    private String description;
    private String name;
    private int id;

    CostsTypes(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
