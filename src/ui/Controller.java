package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.Administrator;

import java.io.*;
import java.util.Scanner;

public class Controller {
    Administrator admin;
    public Controller(){ admin = new Administrator();}
    @FXML
    private VBox vboxMainPane;



    @FXML
    void actnSelectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo con las rutas");
        File file = fileChooser.showOpenDialog(vboxMainPane.getScene().getWindow());
        try {
            readFile(file);
        } catch (IOException e) {
            System.out.println("El archivo seleccionado no existe o no tiene más lineas");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/Screen2.fxml"));
        fxmlLoader.setController(this);

        try {
            Parent root = fxmlLoader.load();
            vboxMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(File file) throws IOException {
        admin.readFile(file);
    }


}
