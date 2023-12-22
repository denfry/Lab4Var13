package com.lina.lab4var13;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

public class TableApp extends Application {
    private final TableView<SportEquipment> table = new TableView<>();
    private final ObservableList<SportEquipment> sportEquipment = FXCollections.observableArrayList();

    private boolean ascendingSort = true;

    private final TextField typeTextField = new TextField();
    private final TextField priceTextField = new TextField();
    private final TextField sumTextField = new TextField();
    private final TextField brandTextField = new TextField();
    private final CheckBox isProffessionalCheckBox = new CheckBox("Is Professional");
    private final ComboBox<String> sportEquipmentTypeComboBox = new ComboBox<>();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sport Equipment Table App");

        TableColumn<SportEquipment, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<SportEquipment, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<SportEquipment, Integer> sumColumn = getSumColumn();

        TableColumn<SportEquipment, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableColumn<SportEquipment, Boolean> isProffessionalColumn = getIsProffessionalColumn();
        TableColumn<SportEquipment, String> sportEquipmentTypeTableColumn = new TableColumn<>("Sport Equipment Type");
        sportEquipmentTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("sportEquipmentType"));

        isProffessionalColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        sportEquipmentTypeTableColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));



        table.getColumns().addAll(typeColumn, priceColumn, sumColumn, brandColumn, isProffessionalColumn, sportEquipmentTypeTableColumn);
        table.setItems(sportEquipment);

        Button addButton = new Button("Add Sport Equipment");
        addButton.setOnAction(e -> addSportEquipment());

        Button addFromArrayButton = new Button("Add Sport Equipment from Array");
        addFromArrayButton.setOnAction(e -> addAllSportEquipmentFromArray());

        Button sortButton = new Button("Sort by Price");
        sortButton.setOnAction(e -> sortSportEquipmentByPrice());


        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(sortButton);
        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                new Label("Type:"),
                typeTextField,
                new Label("Price:"),
                priceTextField,
                new Label("Brand:"),
                brandTextField,
                new Label("Sum:"),
                sumTextField,
                new Label("Is Proffessional:"),
                isProffessionalCheckBox,
                new Label("Sport Equipment Type:"),
                sportEquipmentTypeComboBox,
                addButton
        );


        sportEquipmentTypeComboBox.getItems().addAll("Bikes", "Skates");
        sportEquipmentTypeComboBox.setValue("Bikes");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, inputBox, addFromArrayButton, buttonBox);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<SportEquipment, Integer> getSumColumn() {
        TableColumn<SportEquipment, Integer> sumColumn = new TableColumn<>("Sum");
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

        sumColumn.setCellFactory(column -> new TableCell<SportEquipment, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));
                    int sumValue = item;

                    if (sumValue >= 20) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("-fx-background-color: lightcoral;");
                    }
                }
            }
        });

        return sumColumn;
    }


    private static TableColumn<SportEquipment, Boolean> getIsProffessionalColumn() {
        TableColumn<SportEquipment, Boolean> isProffessionalColumn = new TableColumn<>("Is Proffessional");
        isProffessionalColumn.setCellValueFactory(cellData -> {
            SportEquipment sportEquipment = cellData.getValue();
            if (sportEquipment instanceof Skates) {
                return new SimpleBooleanProperty(((Skates) cellData.getValue()).getProffessional());
            }
            return new SimpleBooleanProperty().asObject();
        });
        isProffessionalColumn.setCellFactory(column -> new CheckBoxTableCell<SportEquipment, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setAlignment(Pos.CENTER);
                setAlignment(Pos.CENTER);

                checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                    if (isSelected) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("-fx-background-color: lightcoral;");
                    }
                });
            }

            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                    if (!item) {
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setStyle("-fx-background-color: lightgreen;");
                    }
                }
            }
        });
        return isProffessionalColumn;
    }


    private void addSportEquipment() {
        try {
            String type = typeTextField.getText();
            String brand = brandTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int sum = Integer.parseInt(sumTextField.getText());
            String sportEquipmentType = sportEquipmentTypeComboBox.getValue();


            if (isProffessionalCheckBox.isSelected()) {
                if ("bikes".equalsIgnoreCase(sportEquipmentType)) {
                    throw new IllegalArgumentException("Invalid combination: 'isProffessional' cannot be selected for Bikes.");
                }
                boolean isProffessional = true;
                sportEquipmentType = "Skates";
                sportEquipment.add(new Skates(type, brand, price, sum, isProffessional, sportEquipmentType, 37));
            } else {
                if ("bikes".equalsIgnoreCase(sportEquipmentType)) {
                    sportEquipment.add(new Bikes(type, brand, price, sum, 30, sportEquipmentType));
                } else {
                    throw new IllegalArgumentException("Invalid combination: 'isProffessional' must be selected for Skates.");
                }
            }

            typeTextField.clear();
            priceTextField.clear();
            sumTextField.clear();
            brandTextField.clear();
            isProffessionalCheckBox.setSelected(false);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid combination");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void addAllSportEquipmentFromArray() {
        sportEquipment.addAll(getSportEquipmentFromArray());
    }

    private ObservableList<SportEquipment> getSportEquipmentFromArray() {
        ObservableList<SportEquipment> sportEquipmentArray = FXCollections.observableArrayList();
        sportEquipmentArray.add(new Bikes("Mountain Bike", "Cannondale", 35000, 20, 24,
                "Bikes"));
        sportEquipmentArray.add(new Bikes("Road Bike", "Trek", 50000, 15, 26,
                "Bikes"));
        sportEquipmentArray.add(new Bikes("BMX Bike", "GT Bicycles", 25000, 10, 20,
                "Bikes"));
        sportEquipmentArray.add(new Bikes("Hybrid Bike", "Specialized", 45000, 18, 28,
                "Bikes"));
        sportEquipmentArray.add(new Bikes("Electric Bike", "Raleigh", 70000, 8, 22,
                "Bikes"));
        sportEquipmentArray.add(new Skates("Ice Hockey", "Sportmaster", 39000, 50, true,
                "Skates", 37));
        sportEquipmentArray.add(new Skates("Figure Skating", "Nike", 45000, 30, true,
                "Skates", 38));
        sportEquipmentArray.add(new Skates("Speed Skating", "Adidas", 55000, 20, true,
                "Skates", 40));
        sportEquipmentArray.add(new Skates("Casual Skating", "Puma", 30000, 40, true,
                "Skates", 39));
        sportEquipmentArray.add(new Skates("Roller Hockey", "Reebok", 42000, 25, true,
                "Skates", 42));

        return sportEquipmentArray;
    }

    private void sortSportEquipmentByPrice() {
        ascendingSort = !ascendingSort;
        Comparator<SportEquipment> comparator = ascendingSort
                ? Comparator.comparingDouble(SportEquipment::getPrice)
                : (r1, r2) -> Double.compare(r2.getPrice(), r1.getPrice());

        sportEquipment.sort(comparator);
    }
}