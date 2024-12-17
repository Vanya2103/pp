package com.example.grafica_1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    private Button div;

    @FXML
    private Button mul;

    @FXML
    private Button ost;

    @FXML
    private Button sub;

    @FXML
    private Button sum;

    @FXML
    private Label resultLabel;

    @FXML
    private void onSumButtonClick() {
        double x = Double.parseDouble(X.getText());
        double y = Double.parseDouble(Y.getText());
        double result = x + y;
        resultLabel.setText("Result: " + result);
    }

    @FXML
    private void onSubButtonClick() {
        double x = Double.parseDouble(X.getText());
        double y = Double.parseDouble(Y.getText());
        double result = x - y;
        resultLabel.setText("Result: " + result);
    }

    @FXML
    private void onMulButtonClick() {
        double x = Double.parseDouble(X.getText());
        double y = Double.parseDouble(Y.getText());
        double result = x * y;
        resultLabel.setText("Result: " + result);
    }

    @FXML
    private void onDivButtonClick() {
        double x = Double.parseDouble(X.getText());
        double y = Double.parseDouble(Y.getText());
        if (y != 0) {
            double result = x / y;
            resultLabel.setText("Result: " + result);
        } else {
            resultLabel.setText("Error: Division by zero");
        }
    }

    @FXML
    private void onOstButtonClick() {
        double x = Double.parseDouble(X.getText());
        double y = Double.parseDouble(Y.getText());
        double result = x % y;
        resultLabel.setText("Result: " + result);
    }
}