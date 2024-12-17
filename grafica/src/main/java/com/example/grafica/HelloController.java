package com.example.grafica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private PieChart pie;

    @FXML
    private Label result;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    void ostAction(ActionEvent event) {
        calculateAndUpdate("Modulus");
    }

    @FXML
    void divClick(ActionEvent event) {
        calculateAndUpdate("Division");
    }

    @FXML
    void mulClick(ActionEvent event) {
        calculateAndUpdate("Multiplication");
    }

    @FXML
    void subClick(ActionEvent event) {
        calculateAndUpdate("Subtraction");
    }

    @FXML
    void onSumButtonClick(ActionEvent event) {
        calculateAndUpdate("Addition");
    }

    private void calculateAndUpdate(String operation) {
        try {
            double num1 = Double.parseDouble(x.getText());
            double num2 = Double.parseDouble(y.getText());
            double total = 0;

            switch (operation) {
                case "Addition":
                    total = num1 + num2;
                    break;
                case "Subtraction":
                    total = num1 - num2;
                    break;
                case "Multiplication":
                    total = num1 * num2;
                    break;
                case "Division":
                    if (num2 != 0) {
                        total = num1 / num2;
                    } else {
                        result.setText("Cannot divide by zero.");
                        return;
                    }
                    break;
                case "Modulus":
                    total = num1 % num2;
                    break;
            }

            result.setText("" + total);
            updatePieChart(num1, num2,total, operation);

        } catch (NumberFormatException e) {
            result.setText("Please enter valid numbers.");
        }
    }

    private void updatePieChart(double num1, double num2,double total, String operation) {
        pie.getData().clear();
        PieChart.Data slice1 = new PieChart.Data("X: " + num1, num1);
        PieChart.Data slice2 = new PieChart.Data("Y: " + num2, num2);
        PieChart.Data slice3 = new PieChart.Data(operation+":"+total, total);
        pie.getData().addAll(slice1, slice2, slice3);
    }

    @FXML
    public void initialize() {
        updatePieChart(0, 0,0, "None");
    }
}