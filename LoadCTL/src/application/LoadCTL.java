package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public final class LoadCTL extends Application {
 
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");
 
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Open a CTL");
        TextArea textBox = new TextArea();
        textBox.setEditable(false);
//        final Button openMultipleButton = new Button("Open Pictures...");     
      
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        openFile(file);
                    }
                }
            });
 
//        openMultipleButton.setOnAction(
//            new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(final ActionEvent e) {
//                    configureFileChooser(fileChooser);                               
//                    List<File> list = 
//                        fileChooser.showOpenMultipleDialog(stage);
//                    if (list != null) {
//                        for (File file : list) {
//                            openFile(file);
//                        }
//                    }
//                }
//            });
 
 
        final GridPane inputGridPane = new GridPane();
 
        GridPane.setConstraints(openButton, 0, 1);
//        GridPane.setConstraints(openMultipleButton, 1, 1);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, textBox);
        
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
 
        stage.setScene(new Scene(rootGroup));
        stage.show();
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
    private static void configureFileChooser(
        final FileChooser fileChooser) {      
            fileChooser.setTitle("Get CTL");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("CTL", "*.jpg")
//                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    }
 
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(LoadCTL.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }
}