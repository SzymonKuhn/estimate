import costs.CostsManager;
import estimates.Estimate;
import estimates.EstimatesManager;
import houses.House;
import houses.HousesManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import types.AreaTypes;
import types.CostsTypes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstimateApp extends Application {
//    private static ObservableList<House> houseList = FXCollections.observableArrayList();
    //    private static List<Estimate> estimateList = new ArrayList<>(); // usunąć
    private static Path basePath = Paths.get("D:\\Costs");
    //    private static Path fileMapOfCosts = basePath.resolve("map_of_costs.txt"); // usunąć
    private static Path fileListOfHouses = basePath.resolve("list_of_houses.txt");
    //    private static Path fileListOfEstimates = basePath.resolve("list_of_estimates.txt"); // usunąć
//    CostsCatalog costsCatalog = new CostsCatalog(); // usunąć
    private static HousesManager housesManager = new HousesManager();
    private static EstimatesManager estimatesManager = new EstimatesManager();
    private static CostsManager costsManager = new CostsManager();


    // main
    public static void main(String[] args) {
        launch(args);
    }

    //start
    @Override
    public void start(Stage primaryStage) throws Exception {
        //tworzenie mapy kosztów
        // czy ta mapa powinna być w metodzie start a nie w klasie CostsCatalog???
//        costsCatalog.createMapOfCosts();

        //import domów z pliku
//        houseList = listOfHousesImport();
        listOfHousesImport();

        //konwersja domów na katalog kosztów
//        for (House house : houseList) {
//            addHouseToCostCatalogue(house, costsCatalog);
//        }

        //wydruk na konsoli dla sprawdzenia
        System.out.println("Wydruk listy domów z importu: ");
        System.out.println(housesManager.getHouseList().toString());

//        System.out.println(costsCatalog.getMapOfCosts());

        //File menu
        Menu fileMenu = new Menu("Plik");
        MenuItem addHouse = new MenuItem("Dodaj dom...");
        MenuItem calculateHouse = new MenuItem("Oblicz...");
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem close = new MenuItem("Zakończ");
        fileMenu.getItems().addAll(addHouse, calculateHouse, separatorMenuItem, close);

        //Show menu
        Menu showMenu = new Menu("Pokaż");
        MenuItem showHousesTable = new MenuItem("Tabela domów");
        MenuItem showTableOfCosts = new MenuItem("Tabela kosztów");
        showMenu.getItems().addAll(showHousesTable, showTableOfCosts);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, showMenu);

//        okno główne
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        Scene mainScene = new Scene(borderPane);

// okno dodawania domu do bazy danych
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

        GridPane addingHouseLayout = new GridPane();

        addingHouseLayout.setPadding(new Insets(15));
        addingHouseLayout.setHgap(10);
        addingHouseLayout.setVgap(10);
        addingHouseLayout.setAlignment(Pos.CENTER);

        addingHouseLayout.getChildren().addAll(nameLabel, descriptionLabel, areaUsableLabel, volumeBruttoLabel,
                windowsAreaLabel, buildingAreaLabel, roofAreaLabel, nameField, descriptArea, areaUsableField,
                volumeBruttoFiled, windowsAreaField, buildingAreaField, roofAreaField, costZeroField, costZeroLabel,
                costCrudeLabel, costCrudeFiled, costRoofLabel, costRoofField, cancelAddingHouseButton, okAddingHouseButton);

        // okno obliczania kosztu budowy
        Label estimateNameLabel = new Label("Podaj nazwę: ");
        Label estimateDescriptionLabel = new Label("Opis:");
        Label estimateAreaUsableLabel = new Label("Powierzchnia użytkowa:");
        Label estimateVolumeBruttoLabel = new Label("Kubatura brutto:");
        Label estimateWindowsAreaLabel = new Label("Powierzchnia okien:");
        Label estimateBuildingAreaLabel = new Label("Powierzchnia zabudowy:");
        Label estimateRoofAreaLabel = new Label("Powierzchnia dachu:");

        TextField estimateNameField = new TextField();
        TextArea estimateDescriptArea = new TextArea();
        estimateDescriptArea.setPrefWidth(300);
        TextField estimateAreaUsableField = new TextField();
        TextField estimateVolumeBruttoFiled = new TextField();
        TextField estimateWindowsAreaField = new TextField();
        TextField estimateBuildingAreaField = new TextField();
        TextField estimateRoofAreaField = new TextField();

        GridPane.setConstraints(estimateNameLabel, 0, 0);
        GridPane.setConstraints(estimateDescriptionLabel, 0, 1);
        GridPane.setConstraints(estimateAreaUsableLabel, 0, 2);
        GridPane.setConstraints(estimateVolumeBruttoLabel, 0, 3);
        GridPane.setConstraints(estimateWindowsAreaLabel, 0, 4);
        GridPane.setConstraints(estimateBuildingAreaLabel, 0, 5);
        GridPane.setConstraints(estimateRoofAreaLabel, 0, 6);

        GridPane.setConstraints(estimateNameField, 1, 0);
        GridPane.setConstraints(estimateDescriptArea, 1, 1);
        GridPane.setConstraints(estimateAreaUsableField, 1, 2);
        GridPane.setConstraints(estimateVolumeBruttoFiled, 1, 3);
        GridPane.setConstraints(estimateWindowsAreaField, 1, 4);
        GridPane.setConstraints(estimateBuildingAreaField, 1, 5);
        GridPane.setConstraints(estimateRoofAreaField, 1, 6);

        GridPane estimateInputGridPane = new GridPane();
        estimateInputGridPane.setPadding(new Insets(15));
        estimateInputGridPane.setHgap(10);
        estimateInputGridPane.setVgap(10);
        estimateInputGridPane.setAlignment(Pos.CENTER);

        estimateInputGridPane.getChildren().addAll(estimateNameLabel, estimateNameField, estimateDescriptionLabel,
                estimateDescriptArea, estimateAreaUsableLabel, estimateAreaUsableField, estimateVolumeBruttoLabel,
                estimateVolumeBruttoFiled, estimateWindowsAreaLabel, estimateWindowsAreaField, estimateBuildingAreaLabel,
                estimateBuildingAreaField, estimateRoofAreaLabel, estimateRoofAreaField);

        Label estimateZeroLabel = new Label("Stan zero");
        Label estimateZeroMinLabel = new Label();
        Label estimateZeroAvgLabel = new Label();
        Label estimateZeroMaxLabel = new Label();
        Label estimateCrudeLabel = new Label("Stan surowy");
        Label estimateCrudeMinLabel = new Label();
        Label estimateCrudeAvgLabel = new Label();
        Label estimateCrudeMaxLabel = new Label();
        Label estimateRoofLabel = new Label("Pokrycie dachu");
        Label estimateRoofMinLabel = new Label();
        Label estimateRoofAvgLabel = new Label();
        Label estimateRoofMaxLabel = new Label();
        Label estimateMinLabel = new Label("Koszt minimum");
        Label estimateAvgLabel = new Label("Koszt średnio");
        Label estimateMaxLabel = new Label("Koszt maksimum");
        Label estimateLPLabel = new Label("l.p.");

        GridPane.setConstraints(estimateLPLabel, 0, 0);
        GridPane.setConstraints(estimateMinLabel, 1, 0);
        GridPane.setConstraints(estimateAvgLabel, 2, 0);
        GridPane.setConstraints(estimateMaxLabel, 3, 0);
        GridPane.setConstraints(estimateZeroLabel, 0, 1);
        GridPane.setConstraints(estimateZeroMinLabel, 1, 1);
        GridPane.setConstraints(estimateZeroAvgLabel, 2, 1);
        GridPane.setConstraints(estimateZeroMaxLabel, 3, 1);
        GridPane.setConstraints(estimateCrudeLabel, 0, 2);
        GridPane.setConstraints(estimateCrudeMinLabel, 1, 2);
        GridPane.setConstraints(estimateCrudeAvgLabel, 2, 2);
        GridPane.setConstraints(estimateCrudeMaxLabel, 3, 2);
        GridPane.setConstraints(estimateRoofLabel, 0, 3);
        GridPane.setConstraints(estimateRoofMinLabel, 1, 3);
        GridPane.setConstraints(estimateRoofAvgLabel, 2, 3);
        GridPane.setConstraints(estimateRoofMaxLabel, 3, 3);

        GridPane estimateResultTable = new GridPane();
        estimateResultTable.setPadding(new Insets(15));
        estimateResultTable.setHgap(10);
        estimateResultTable.setVgap(10);
        estimateResultTable.setAlignment(Pos.CENTER);

        estimateResultTable.getChildren().addAll(estimateLPLabel, estimateMinLabel, estimateAvgLabel, estimateMaxLabel,
                estimateZeroLabel, estimateZeroMinLabel, estimateZeroAvgLabel, estimateZeroMaxLabel, estimateCrudeLabel,
                estimateCrudeMinLabel, estimateCrudeAvgLabel, estimateCrudeMaxLabel, estimateRoofLabel,
                estimateRoofMinLabel, estimateRoofAvgLabel, estimateRoofMaxLabel);

        Button cancelEstimateButton = new Button("Anuluj");
        cancelEstimateButton.setMinWidth(60);
        Button calculateEstimateButton = new Button("Oblicz");
        calculateEstimateButton.setMinWidth(60);

        HBox estimateButtons = new HBox();
        estimateButtons.setAlignment(Pos.CENTER);
        estimateButtons.setSpacing(60);
        estimateButtons.getChildren().addAll(cancelEstimateButton, calculateEstimateButton);

        VBox estimateLayout = new VBox();
        estimateLayout.getChildren().addAll(estimateInputGridPane, estimateButtons, estimateResultTable);


        //okno wyświetlania tabeli domów
        TableView<House> housesTableView = new TableView<>();
        TableColumn<House, String> nameColumn = new TableColumn<>("Nazwa");
        nameColumn.setMinWidth(30);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<House, String> descrColumn = new TableColumn<>("Opis");
        descrColumn.setMinWidth(60);
        descrColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<House, Double> areaUsableColumn = new TableColumn<>("Pow. użytk.");
        areaUsableColumn.setMinWidth(30);
        areaUsableColumn.setCellValueFactory(new PropertyValueFactory<>("areaUsable"));

        TableColumn<House, Double> volumeBruttoColumn = new TableColumn<>("Kubatura");
        volumeBruttoColumn.setMinWidth(30);
        volumeBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("volumeBrutto"));

        TableColumn<House, Double> windowsAreaColumn = new TableColumn<>("Pow. okien");
        windowsAreaColumn.setMinWidth(30);
        windowsAreaColumn.setCellValueFactory(new PropertyValueFactory<>("windowsArea"));

        TableColumn<House, Double> buildingAreaColumn = new TableColumn<>("Pow. zabudowy");
        buildingAreaColumn.setMinWidth(30);
        buildingAreaColumn.setCellValueFactory(new PropertyValueFactory<>("buildingArea"));

        TableColumn<House, Double> roofAreaColumn = new TableColumn<>("Pow. dachu");
        roofAreaColumn.setMinWidth(30);
        roofAreaColumn.setCellValueFactory(new PropertyValueFactory<>("roofArea"));

        TableColumn<House, Double> zeroCostColumn = new TableColumn<>("Koszt stanu zero");
        zeroCostColumn.setMinWidth(30);
        zeroCostColumn.setCellValueFactory(new PropertyValueFactory<>("costZeroStateTotal"));

        TableColumn<House, Double> crudeCostColumn = new TableColumn<>("Koszt stanu surowego");
        crudeCostColumn.setMinWidth(30);
        crudeCostColumn.setCellValueFactory(new PropertyValueFactory<>("costCrudeTotal"));

        TableColumn<House, Double> roofCostColumn = new TableColumn<>("Koszt pokrycia dachu");
        roofCostColumn.setMinWidth(30);
        roofCostColumn.setCellValueFactory(new PropertyValueFactory<>("costRoofTotal"));

//        housesTableView.setItems(houseList);
        housesTableView.getColumns().addAll(nameColumn, descrColumn, areaUsableColumn, volumeBruttoColumn,
                windowsAreaColumn, buildingAreaColumn, roofAreaColumn, zeroCostColumn, crudeCostColumn,
                roofCostColumn);


        //primary stage
        primaryStage.setHeight(800);
        primaryStage.setWidth(600);
        primaryStage.setTitle("Szacunek kosztów");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeMethod();
        });

        // menu przyciski
        addHouse.setOnAction(e -> borderPane.setCenter(addingHouseLayout));
        calculateHouse.setOnAction(e -> borderPane.setCenter(estimateLayout));
        showHousesTable.setOnAction(e -> borderPane.setCenter(housesTableView));
        close.setOnAction(e -> closeMethod());

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

//                House house = new House(houseName, houseDescript);
                House house = new House(houseName, houseDescript);
                house.addArea(AreaTypes.USABLE_AREA, areaUsable);
                house.addArea(AreaTypes.VOLUME, volumeBrutto);
                house.addArea(AreaTypes.WINDOWS_AREA, windowsArea);
                house.addArea(AreaTypes.BUILDING_AREA, buildingArea);
                house.addArea(AreaTypes.ROOF_AREA, roofArea);
                house.addCost(CostsTypes.COST_ZERO_STATE, costZeroStateTotal);
                house.addCost(CostsTypes.COST_CRUDE, costCrudeTotal);
                house.addCost(CostsTypes.COST_ROOF, costRoofTotal);
                housesManager.addHouse(house);

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
                System.out.println(house);
                System.out.println(housesManager.getHouseList().toString());
                AlertWindow alertWindow = new AlertWindow();
                alertWindow.display("Dodano dom", "Dom został dodany do bazy danych");
                borderPane.setCenter(null);
            } catch (Exception e) {
                AlertWindow.display("Błędne dane", "Podano błędne dane");
                e.getMessage();
            }

        });

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
            borderPane.setCenter(null);
        });

        //przycisk utwórz kosztorys
//        calculateEstimateButton.setOnAction(a -> {
//            Double costZeroStateTotalMin = costsCatalog.getMinValue(CostsTypes.COST_ZERO_STATE.getId());
//            Double costZeroStateTotalAvg = costsCatalog.getAvargeValue(CostsTypes.COST_ZERO_STATE.getId());
//            Double costZeroStateTotalMax = costsCatalog.getMaxValue(CostsTypes.COST_ZERO_STATE.getId());
//            Double costCrudeTotalMin = costsCatalog.getMinValue(CostsTypes.COST_CRUDE.getId());
//            Double costCrudeTotalAvg = costsCatalog.getAvargeValue(CostsTypes.COST_CRUDE.getId());
//            Double costCrudeTotalMax = costsCatalog.getMaxValue(CostsTypes.COST_CRUDE.getId());
//            Double costRoofTotalMin = costsCatalog.getMinValue(CostsTypes.COST_ROOF.getId());
//            Double costRoofTotalAvg = costsCatalog.getAvargeValue(CostsTypes.COST_ROOF.getId());
//            Double costRoofTotalMax = costsCatalog.getMaxValue(CostsTypes.COST_ROOF.getId());
//            Double areaUsable = null;
//            Double volumeBrutto = null;
//            Double windowsArea = null;
//            Double buildingArea = null;
//            Double roofArea = null;
//
//            try {
//                areaUsable = Double.parseDouble(estimateAreaUsableField.getText());
//                volumeBrutto = Double.parseDouble(estimateVolumeBruttoFiled.getText());
//                windowsArea = Double.parseDouble(estimateWindowsAreaField.getText());
//                buildingArea = Double.parseDouble(estimateBuildingAreaField.getText());
//                roofArea = Double.parseDouble(estimateRoofAreaField.getText());
//            } catch (Exception e) {
//                AlertWindow.display("Błędne dane", "Podano błędne dane");
//                e.getMessage();
//            }
//            Estimate estimate = new Estimate(estimateNameField.getText(), estimateDescriptArea.getText(), areaUsable,
//                    volumeBrutto, windowsArea, buildingArea, roofArea);
//            estimate.setCostZeroStateTotalMin(costZeroStateTotalMin);
//            estimate.setCostZeroStateTotalAvg(costZeroStateTotalAvg);
//            estimate.setCostZeroStateTotalMax(costZeroStateTotalMax);
//            estimate.setCostCrudeTotalMin(costCrudeTotalMin);
//            estimate.setCostCrudeTotalAvg(costCrudeTotalAvg);
//            estimate.setCostCrudeTotalMax(costCrudeTotalMax);
//            estimate.setCostRoofTotalMin(costRoofTotalMin);
//            estimate.setCostRoofTotalAvg(costRoofTotalAvg);
//            estimate.setCostRoofTotalMax(costRoofTotalMax);
//
//            estimateZeroMinLabel.setText(estimate.getCostZeroStateTotalMin().toString());
//            estimateZeroAvgLabel.setText(estimate.getCostZeroStateTotalAvg().toString());
//            estimateZeroMaxLabel.setText(estimate.getCostZeroStateTotalMax().toString());
//            estimateCrudeMinLabel.setText(estimate.getCostCrudeTotalMin().toString());
//            estimateCrudeAvgLabel.setText(estimate.getCostCrudeTotalAvg().toString());
//            estimateCrudeMaxLabel.setText(estimate.getCostCrudeTotalMax().toString());
//            estimateRoofMinLabel.setText(estimate.getCostRoofTotalMin().toString());
//            estimateRoofAvgLabel.setText(estimate.getCostRoofTotalAvg().toString());
//            estimateRoofMaxLabel.setText(estimate.getCostRoofTotalMax().toString());
//            estimateList.add(estimate);
//        });
//
//        // przycisk anuluj kosztorys
//        cancelEstimateButton.setOnAction(a -> {
//            estimateNameField.clear();
//            estimateDescriptArea.clear();
//            estimateAreaUsableField.clear();
//            estimateVolumeBruttoFiled.clear();
//            estimateWindowsAreaField.clear();
//            estimateBuildingAreaField.clear();
//            estimateRoofAreaField.clear();
//            borderPane.setCenter(null);
//        });

    }//start

    //adding house to catalogue
//    public static void addHouseToCostCatalogue(House house, CostsCatalog costsCatalog) {
//        costsCatalog.addCostToCatalog(CostsTypes.COST_ZERO_STATE.getId(), house.getCostZeroState());
//        costsCatalog.addCostToCatalog(CostsTypes.COST_CRUDE.getId(), house.getCostCrude());
//        costsCatalog.addCostToCatalog(CostsTypes.COST_ROOF.getId(), house.getCostRoof());
//    }

    //eksport files
    public static void exportFiles() {
        try {
            if (!Files.exists(basePath)) {
                Files.createDirectory(basePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        // export map of costs
//        try {
//            if (!Files.exists(fileMapOfCosts)) {
//                Files.createFile(fileMapOfCosts);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (FileWriter writer = new FileWriter(fileMapOfCosts.toString())) {
//            writer.write(costsCatalog.getMapOfCosts().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //export list of houses
        try {
            if (!Files.exists(fileListOfHouses)) {
                Files.createFile(fileListOfHouses);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(fileListOfHouses.toString())) {
            for (House house : housesManager.getHouseList()) {
                writer.write( house.toString() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        //export list od estimates
//        try {
//            if (!Files.exists(fileListOfEstimates)) {
//                Files.createFile(fileListOfEstimates);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (FileWriter writer = new FileWriter(fileListOfEstimates.toString())) {
//            for (Estimate estimate : estimateList) {
//                writer.write(estimate.toString() + "\r\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    //import list of houses
    public static void listOfHousesImport() {
        List<String> stringList = new ArrayList<>();
        ObservableList<House> importedHouseList = FXCollections.observableArrayList();
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
//                    String[] stringArray = stringHouse.split(", ");
//                    House house = new House(stringArray[0], stringArray[1], Double.parseDouble(stringArray[2]), Double.parseDouble(stringArray[3]),
//                            Double.parseDouble(stringArray[4]), Double.parseDouble(stringArray[5]), Double.parseDouble(stringArray[6]),
//                            Double.parseDouble(stringArray[7]), Double.parseDouble(stringArray[8]), Double.parseDouble(stringArray[9]));
//                    importedHouseList.add(house);
                    House house = housesManager.importHouse(stringHouse);
                    housesManager.addHouse(house);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
//
//    //close
    private void closeMethod() {
        boolean answer = ConfirmWindow.display("Zakończ", "Czy na pewno chcesz zamknąć program?");
        if (answer) {
            exportFiles();
            Platform.exit();
        }
    }



}//class
