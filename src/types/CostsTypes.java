package types;

public enum CostsTypes {
    COST_ZERO_STATE ("Stan zero", "Koszt stanu zerowego, przeliczany na powierzchnię zabudowy", AreaTypes.BUILDING_AREA),
    COST_CRUDE ("Stan surowy otwarty", "Koszt stanu surowego otwartego z konstrukcją dachu, przeliczany na powierzchnię użytkową", AreaTypes.USABLE_AREA),
    COST_ROOF ("Pokrycie dachu", "Koszt pokrycia dachu przeliczany na powierzchnię dachu", AreaTypes.ROOF_AREA),
    COST_WINDOWS ("Okna i przeszklenia", "Koszt okien i innych przeszkleń, przeliczany na powierzchnię okien", AreaTypes.WINDOWS_AREA),
    COST_INST_SANIT ("Instalacje sanitarne", "Koszt instalacji sanitarnych, przeliczany na powierzchnię użytkową", AreaTypes.USABLE_AREA),
    COST_INST_WENT ("Instalacja wentylacji mechanicznej", "Koszt instalacji mechanicznej, przeliczany na powierzchnię użtytkową", AreaTypes.USABLE_AREA),
    COST_INST_ELECT ("Instalacja elektryczna", "Koszt instalacji elektrycznej, przeliczany na powierzchnię użytkową", AreaTypes.USABLE_AREA),
    COST_FINNISH_INTER (" Stan deweloperski", "Koszt wykończenia wewnętrznego do stanu deweloperskiego, przeliczany na powierzchnię użytkową", AreaTypes.USABLE_AREA),
    COST_FACADE_BRICKS ("Elewacje z cegły", "Kosszt wykończenia elewacji z cegływraz z ociepleniem, przeliczany na powierzchnię ścian", AreaTypes.FACADE_BRICKS_AREA),
    COST_FACADE_EPS ("Elewacje metodą lekką mokrą", "Koszt wykończenia ścian styropianem i tynkiem mineralnym, przeliczany na powierzchnię ścian", AreaTypes.FACADE_EPS_AREA),
    COST_FACADE_WOOD ("Elewacje drewniane", "Koszt wykończenia ścian deskowaniem wraz z ociepleniem, przeliczany na powierzchnię ścian", AreaTypes.FACADE_WOOD_AREA);

    private String description;
    private String name;
    private AreaTypes areaType;

    CostsTypes(String name, String description, AreaTypes areaType) {
        this.name = name;
        this.description = description;
        this.areaType = areaType;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public AreaTypes getAreaType() {
        return areaType;
    }

    public static CostsTypes getEnumFromString (String input) {
        for (int i = 0; i < CostsTypes.values().length; i++) {
            if (CostsTypes.values()[i].name().equals(input)) {
                return CostsTypes.values()[i];
            }
        }
        return null; // wyjątek?
    }
}
