package application;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class LoadViewCTL extends Application {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Load and view a CTL");
        Group root = new Group();
         
         
         
        final TextArea textArea = TextAreaBuilder.create()
                .prefWidth(400)
                .wrapText(true)
                .build();
         
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setContent(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(180);
         
        Button buttonLoad = new Button("Load");
        buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
 
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(
                        new File(System.getProperty("user.home"))
                    );                 
                //Set extension filter
                fileChooser.getExtensionFilters().addAll(
                		new FileChooser.ExtensionFilter ("CTL files (*.ctl)", "*.ctl"),
                		 new FileChooser.ExtensionFilter ("TXT files (*.txt)", "*.txt")
                		);
                 
                //Show save file dialog
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    textArea.setText(readFile(file));
                }
            }
             
        });
         
        VBox vBox = VBoxBuilder.create()
                .children(buttonLoad, scrollPane)
                .build();
         
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
     
    private String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
         
        try {
 
            bufferedReader = new BufferedReader(new FileReader(file));
             
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append(System.getProperty("line.separator"));
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadViewCTL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadViewCTL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadViewCTL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        return stringBuffer.toString();
    }
}