import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 750, 750);

        MenuBar menuBar  = new MenuBar();
        Menu    fileMenu = new Menu("File");
        Menu    editMenu = new Menu("Edit");

        //Create Item File:Open
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the openItem item");
            }
        });
        
        //Create Item File:Save
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the saveItem item");
            }
        });
        
        //Create Item File:Exit
        MenuItem exitItem = new MenuItem("Exit");       
                
        //Create Item Edit:Spellcheck
        MenuItem spellCheckItem = new MenuItem("Spellcheck");
        spellCheckItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the spellcheck item");
            }
        });
        
        // Set Accelerator for Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
 
        // When user click on the Exit item
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        editMenu.getItems().addAll(spellCheckItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);
        borderPane.setTop(menuBar);

        TextArea textArea = new TextArea();
        borderPane.setCenter(textArea);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}