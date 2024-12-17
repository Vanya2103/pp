package com.example.zapisnaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HelloController {

    @FXML
    private TextField enter;

    @FXML
    private Button enterButton;

    @FXML
    private TextArea result;

    @FXML
    private Button resultButton;

    @FXML
    private Button readButton;

    @FXML
    private Button rewriteButton;

    @FXML
    private Button cleanButton;

    private File currentFile;

    @FXML
    private void initialize() {
        enterButton.setOnAction(event -> addText());
        resultButton.setOnAction(event -> saveToFile());
        readButton.setOnAction(event -> readFromFile());
        rewriteButton.setOnAction(event -> rewriteFile());
        cleanButton.setOnAction(event -> clearTextArea());
        enter.setOnAction(event -> addText());
    }

    private void addText() {
        String text = enter.getText();
        if (!text.isEmpty()) {
            result.appendText(text + "\n");
            enter.clear();
        }
    }

    private void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));

        File file = fileChooser.showSaveDialog(result.getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(result.getText());
                currentFile = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));

        File file = fileChooser.showOpenDialog(result.getScene().getWindow());
        if (file != null) {
            try (FileReader reader = new FileReader(file);
                 Scanner scanner = new Scanner(reader)) {

                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                result.setText(content.toString());
                currentFile = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void rewriteFile() {
        if (currentFile != null) {
            try (FileWriter writer = new FileWriter(currentFile)) {
                writer.write(result.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveToFile();
        }
    }

    private void clearTextArea() {
        result.clear();
    }
}
