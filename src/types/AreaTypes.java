package types;

public enum AreaTypes {
    BUILDING_AREA ("Powierzchnia zabudowy"),
    USABLE_AREA ("Powierzchnia użytkowa"),
    VOLUME ("Kubatura brutto"),
    WINDOWS_AREA ("Powierzchnia okien i przeszkleń"),
    ROOF_AREA ("Powierzchnia dachu"),
    FACADE_EPS_AREA ("Powierzchnia elewacji tynkowanych"),
    FACADE_BRICKS_AREA ("Powierzchnia elewacji ceglanych"),
    FACADE_WOOD_AREA ("Powierzchnia elewacji drewnianych");

    private String description;

    AreaTypes(String description) {
        this.description = description;
    }



    public static AreaTypes getEnumFromString (String input) {
    for (int i = 0; i < AreaTypes.values().length; i++) {
        if (AreaTypes.values()[i].name().equals(input)) {
            return AreaTypes.values()[i];
        }
    }
    return null; // wyjątek?
    }
}
