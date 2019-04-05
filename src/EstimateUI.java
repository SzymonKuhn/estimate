import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EstimateUI extends Application {
    private static List<House> houseList = new ArrayList<>();
    private static Path basePath = Paths.get("D:\\Costs");
    private static Path fileMapOfCosts = basePath.resolve("map_of_costs.txt");
    private static Path fileListOfHouses = basePath.resolve("list_of_houses.txt");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean gridVisible = false;
        CostsCatalog costsCatalog = new CostsCatalog();
        costsCatalog.createMapOfCosts();
        houseList = listOfHousesImport();
        System.out.println(houseList);
        System.out.println();
        for (House house : houseList) {
            addHouseToCostCatalogue(house, costsCatalog);
        }
        System.out.println(costsCatalog.getMapOfCosts());

        primaryStage.setTitle("Szacunek kosztów");

        //File menu
        Menu fileMenu = new Menu("File");

        // Menu Items
        MenuItem addHouse = new MenuItem("Dodaj dom...");
        fileMenu.getItems().add(addHouse);

        MenuItem calculateHouse = new MenuItem("Oblicz...");
        fileMenu.getItems().add(calculateHouse);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

//        Button addHouseButton = new Button("Dodaj kosztorys");
//        addHouseButton.setMinSize(120, 50);
//        addHouseButton.setMaxSize(160,60);

//        Button estimateProjectButton = new Button("Oblicz koszt");
//        estimateProjectButton.setMinSize(120, 50);
//        estimateProjectButton.setMaxSize(160,60);

//        okno główne
        VBox vBox = new VBox();
        vBox.setSpacing(25.0);
        vBox.setAlignment(Pos.TOP_CENTER);



        vBox.getChildren().addAll(menuBar);
        Scene mainScene = new Scene(vBox);

// okno dodawania kosztorysu
        Label nameLabel = new Label("Nazwa:");
        Label descriptionLabel = new Label("Opis:");
        Label areaUsableLabel = new Label("Powierzchnia użytkowa:");
        Label volumeBruttoLabel = new Label("Kubatura brutto:");
        Label windowsAreaLabel = new Label("Powierzchnia okien:");
        Label buildingAreaLabel = new Label("Powierzchnia zabudowy:");
        Label roofAreaLabel = new Label("Powierzchnia dachu:");
        Label costZeroLabel = new Label("Koszt stanu zero:");
        Label costCrudeLabel = new Label("Koszt stanu surowego:");
        Label costRoofLabel = new Label("Koszt pokrycia dachu:");
        TextField nameField = new TextField();
        TextArea descriptArea = new TextArea();
        descriptArea.setPrefWidth(300);
        TextField areaUsableField = new TextField();
        TextField volumeBruttoFiled = new TextField();
        TextField windowsAreaField = new TextField();
        TextField buildingAreaField = new TextField();
        TextField roofAreaField = new TextField();
        TextField costZeroField = new TextField();
        TextField costCrudeFiled = new TextField();
        TextField costRoofField = new TextField();
        Button cancelAddingHouseButton = new Button("Anuluj");
        cancelAddingHouseButton.setMinWidth(60);
        Button okAddingHouseButton = new Button("Ok");
        okAddingHouseButton.setMinWidth(60);

        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(descriptionLabel, 0, 1);
        GridPane.setConstraints(areaUsableLabel, 0, 2);
        GridPane.setConstraints(volumeBruttoLabel, 0, 3);
        GridPane.setConstraints(windowsAreaLabel, 0, 4);
        GridPane.setConstraints(buildingAreaLabel, 0, 5);
        GridPane.setConstraints(roofAreaLabel, 0, 6);
        GridPane.setConstraints(costZeroLabel, 0, 7);
        GridPane.setConstraints(costCrudeLabel, 0, 8);
        GridPane.setConstraints(costRoofLabel, 0, 9);
        GridPane.setConstraints(cancelAddingHouseButton, 0, 10);

        GridPane.setConstraints(nameField, 1, 0);
        GridPane.setConstraints(descriptArea, 1, 1);
        GridPane.setConstraints(areaUsableField, 1, 2);
        GridPane.setConstraints(volumeBruttoFiled, 1, 3);
        GridPane.setConstraints(windowsAreaField, 1, 4);
        GridPane.setConstraints(buildingAreaField, 1, 5);
        GridPane.setConstraints(roofAreaField, 1, 6);
        GridPane.setConstraints(costZeroField, 1, 7);
        GridPane.setConstraints(costCrudeFiled, 1, 8);
        GridPane.setConstraints(costRoofField, 1, 9);
        GridPane.setConstraints(okAddingHouseButton, 1, 10);

        GridPane addingHouse = new GridPane();


        addingHouse.setPadding(new Insets(15));
        addingHouse.setHgap(10);
        addingHouse.setVgap(10);
        addingHouse.setAlignment(Pos.CENTER);
        addingHouse.setGridLinesVisible(gridVisible); // widoczność krawędzi


        addingHouse.getChildren().addAll(nameLabel, descriptionLabel, areaUsableLabel, volumeBruttoLabel,
                windowsAreaLabel, buildingAreaLabel, roofAreaLabel, nameField, descriptArea, areaUsableField,
                volumeBruttoFiled, windowsAreaField, buildingAreaField, roofAreaField, costZeroField, costZeroLabel,
                costCrudeLabel, costCrudeFiled, costRoofLabel, costRoofField, cancelAddingHouseButton, okAddingHouseButton);
        Scene addingHouseScene = new Scene(addingHouse);

        // okno obliczania kosztu budowy
        Label estimateNameLabel = new Label("Podaj nazwę: ");
        Label estimateDescriptionLabel = new Label("Opis:");
        Label estimateAreaUsableLabel = new Label("Powierzchnia użytkowa:");
        Label estimateVolumeBruttoLabel = new Label("Kubatura brutto:");
        Label estimateWindowsAreaLabel = new Label("Powierzchnia okien:");
        Label estimateBuildingAreaLabel = new Label("Powierzchnia zabudowy:");
        Label estimateRoofAreaLabel = new Label("Powierzchnia dachu:");
        Label estimateZeroStateLabel = new Label("Koszt stanu zero: ");
        Label estimateCrudeLabel = new Label("Koszt stanu surowego: ");
        Label estimateRoofLabel = new Label("Koszt pokrycia dachu: ");


        TextField estimateNameField = new TextField();
        TextArea estimateDescriptArea = new TextArea();
        estimateDescriptArea.setPrefWidth(300);
        TextField estimateAreaUsableField = new TextField();
        TextField estimateVolumeBruttoFiled = new TextField();
        TextField estimateWindowsAreaField = new TextField();
        TextField estimateBuildingAreaField = new TextField();
        TextField estimateRoofAreaField = new TextField();
        Label estimateZeroAvg = new Label();
        Label estimateCrudeAvg = new Label();
        Label estimateRoofAvg = new Label();

        Button cancelEstimateButton = new Button("Anuluj");
        cancelEstimateButton.setMinWidth(60);
        Button calculateEstimateButton = new Button("Oblicz");
        calculateEstimateButton.setMinWidth(60);

        GridPane.setConstraints(estimateNameLabel, 0, 0);
        GridPane.setConstraints(estimateDescriptionLabel, 0, 1);
        GridPane.setConstraints(estimateAreaUsableLabel, 0, 2);
        GridPane.setConstraints(estimateVolumeBruttoLabel, 0, 3);
        GridPane.setConstraints(estimateWindowsAreaLabel, 0, 4);
        GridPane.setConstraints(estimateBuildingAreaLabel, 0, 5);
        GridPane.setConstraints(estimateRoofAreaLabel, 0, 6);
        GridPane.setConstraints(cancelEstimateButton, 0, 7);
        GridPane.setConstraints(estimateZeroStateLabel, 0, 8);
        GridPane.setConstraints(estimateCrudeLabel, 0, 9);
        GridPane.setConstraints(estimateRoofLabel, 0, 10);


        GridPane.setConstraints(estimateNameField, 1, 0);
        GridPane.setConstraints(estimateDescriptArea, 1, 1);
        GridPane.setConstraints(estimateAreaUsableField, 1, 2);
        GridPane.setConstraints(estimateVolumeBruttoFiled, 1, 3);
        GridPane.setConstraints(estimateWindowsAreaField, 1, 4);
        GridPane.setConstraints(estimateBuildingAreaField, 1, 5);
        GridPane.setConstraints(estimateRoofAreaField, 1, 6);
        GridPane.setConstraints(calculateEstimateButton, 1, 7);
        GridPane.setConstraints(estimateZeroAvg, 1, 8);
        GridPane.setConstraints(estimateCrudeAvg, 1, 9);
        GridPane.setConstraints(estimateRoofAvg, 1, 10);

        GridPane estimateLayout = new GridPane();
        estimateLayout.setPadding(new Insets(15));
        estimateLayout.setHgap(10);
        estimateLayout.setVgap(10);
        estimateLayout.setAlignment(Pos.CENTER);
        estimateLayout.setGridLinesVisible(gridVisible);

        estimateLayout.getChildren().addAll(estimateNameLabel, estimateNameField, estimateDescriptionLabel,
                estimateDescriptArea, estimateAreaUsableLabel, estimateAreaUsableField, estimateVolumeBruttoLabel,
                estimateVolumeBruttoFiled, estimateWindowsAreaLabel, estimateWindowsAreaField, estimateBuildingAreaLabel,
                estimateBuildingAreaField, estimateRoofAreaLabel, estimateRoofAreaField, cancelEstimateButton, calculateEstimateButton,
                estimateZeroStateLabel, estimateZeroAvg, estimateCrudeLabel, estimateCrudeAvg, estimateRoofLabel, estimateRoofAvg);
        Scene estimateScene = new Scene(estimateLayout);


        primaryStage.setHeight(800);
        primaryStage.setWidth(600);
        primaryStage.setScene(mainScene);
        primaryStage.show();

//        addHouseButton.setOnAction(a -> primaryStage.setScene(addingHouseScene));
//        estimateProjectButton.setOnAction(a -> primaryStage.setScene(estimateScene));

        addHouse.setOnAction(e -> primaryStage.setScene(addingHouseScene));
        calculateHouse.setOnAction(e -> primaryStage.setScene(estimateScene));

        //przycisk anuluj dodawanie domu
        cancelAddingHouseButton.setOnAction(a -> {
                    nameField.clear();
                    descriptArea.clear();
                    areaUsableField.clear();
                    volumeBruttoFiled.clear();
                    windowsAreaField.clear();
                    buildingAreaField.clear();
                    roofAreaField.clear();
                    costZeroField.clear();
                    costCrudeFiled.clear();
                    costRoofField.clear();
                    primaryStage.setScene(mainScene);
                }
        );
        //przycis anuluj dodawanie domu

        //przycisk dodaj dom
        okAddingHouseButton.setOnAction(a -> {
            String houseName = nameField.getText();
            String houseDescript = descriptArea.getText();
            Double areaUsable = 0.0;
            Double volumeBrutto = 0.0;
            Double windowsArea = 0.0;
            Double buildingArea = 0.0;
            Double roofArea = 0.0;
            Double costZeroStateTotal = 0.0;
            Double costCrudeTotal = 0.0;
            Double costRoofTotal = 0.0;

            try {
                areaUsable = Double.parseDouble(areaUsableField.getText());
                volumeBrutto = Double.parseDouble(volumeBruttoFiled.getText());
                windowsArea = Double.parseDouble(windowsAreaField.getText());
                buildingArea = Double.parseDouble(buildingAreaField.getText());
                roofArea = Double.parseDouble(roofAreaField.getText());
                costZeroStateTotal = Double.parseDouble(costZeroField.getText());
                costCrudeTotal = Double.parseDouble(costCrudeFiled.getText());
                costRoofTotal = Double.parseDouble(costRoofField.getText());

                House house = new House(houseName, houseDescript, areaUsable, volumeBrutto, windowsArea, buildingArea,
                        roofArea, costZeroStateTotal, costCrudeTotal, costRoofTotal);
                nameField.clear();
                descriptArea.clear();
                areaUsableField.clear();
                volumeBruttoFiled.clear();
                windowsAreaField.clear();
                buildingAreaField.clear();
                roofAreaField.clear();
                costZeroField.clear();
                costCrudeFiled.clear();
                costRoofField.clear();
                addHouseToCostCatalogue(house, costsCatalog);
                houseList.add(house);

                System.out.println(houseList);
                System.out.println(costsCatalog.getMapOfCosts());
                exportFiles(costsCatalog);
                primaryStage.setScene(mainScene);
            } catch (Exception e) {
                AlertWindow.display("Błędne dane", "Podano błędne dane");
                e.getMessage();
            }

        });
        //przycisk dodaj dom

        //przycisk oblicz kosztorys
        calculateEstimateButton.setOnAction(a -> {
                    Double estimateZero;
                    Double estimateCrude;
                    Double estimateRoof;

                    try {
                        estimateZero = (Double.parseDouble(estimateBuildingAreaField.getText())) * (costsCatalog.getAvargeValue(CostsTypes.COST_ZERO_STATE.getId()));
                        estimateCrude = (Double.parseDouble(estimateAreaUsableField.getText())) * (costsCatalog.getAvargeValue(CostsTypes.COST_CRUDE.getId()));
                        estimateRoof = (Double.parseDouble(estimateRoofAreaField.getText())) * (costsCatalog.getAvargeValue(CostsTypes.COST_ROOF.getId()));
                        estimateZeroAvg.setText(String.valueOf(estimateZero));
                        estimateCrudeAvg.setText(String.valueOf(estimateCrude));
                        estimateRoofAvg.setText(String.valueOf(estimateRoof));
                    } catch (Exception e) {
                        AlertWindow.display("Błędne dane", "Podano błędne dane");
                        e.getMessage();
                    }

                }
        ); //przycisk oblicz kosztorys

        // przycisk anuluj kosztorys
        cancelEstimateButton.setOnAction(a -> {
                    estimateNameField.clear();
                    estimateDescriptArea.clear();
                    estimateAreaUsableField.clear();
                    estimateVolumeBruttoFiled.clear();
                    estimateWindowsAreaField.clear();
                    estimateBuildingAreaField.clear();
                    estimateRoofAreaField.clear();
                    estimateZeroAvg.setText("");
                    estimateCrudeAvg.setText("");
                    estimateRoofAvg.setText("");
                    primaryStage.setScene(mainScene);
                }
        ); //przycisk anuluj kosztorys

        //okienko błedne dane





    }//start


    public static void addHouseToCostCatalogue(House house, CostsCatalog costsCatalog) {
        costsCatalog.addCostToCatalog(CostsTypes.COST_ZERO_STATE.getId(), house.getCostZeroState());
        costsCatalog.addCostToCatalog(CostsTypes.COST_CRUDE.getId(), house.getCostCrude());
        costsCatalog.addCostToCatalog(CostsTypes.COST_ROOF.getId(), house.getCostRoof());
    }


    public static void exportFiles(CostsCatalog costsCatalog) {
        try {
            if (!Files.exists(basePath)) {
                Files.createDirectory(basePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // export map of costs
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

        //export list of houses
        try {
            if (!Files.exists(fileListOfHouses)) {
                Files.createFile(fileListOfHouses);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(fileListOfHouses.toString())) {
            for (House house : houseList) {
                writer.write(house.toExport() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //import list of houses
    public static List<House> listOfHousesImport() {
        List<String> stringList = new ArrayList<>();
        List<House> importedHouseList = new ArrayList<>();
        String string;
        try {
            if (Files.exists(fileListOfHouses)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileListOfHouses.toString()))) {
                    do {
                        string = reader.readLine();
                        stringList.add(string);
                    } while (string != null);
                } catch (IOException ioe) {
                    ioe.getMessage();
                }
                for (String stringHouse : stringList) {
                    String[] stringArray = stringHouse.split(", ");
                    House house = new House(stringArray[0], stringArray[1], Double.parseDouble(stringArray[2]), Double.parseDouble(stringArray[3]),
                            Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5]), Double.parseDouble(stringArray[6]),
                            Double.parseDouble(stringArray[7]), Double.parseDouble(stringArray[8]), Double.parseDouble(stringArray[9]));
                    importedHouseList.add(house);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return importedHouseList;
    }


    public static void clearFields() {
    } //czy da się stworzyć zewnętrzna metodę do czyszczenia UI? (nie widzi)

}//class
