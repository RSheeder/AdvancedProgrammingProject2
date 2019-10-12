import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	private TextArea textArea;
	
    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 750, 750);
        
        Spellcheck spellcheck = new Spellcheck();

        MenuBar menuBar  = new MenuBar();
        textArea = new TextArea();
        textArea.setWrapText(true);
        Menu    fileMenu = new Menu("File");
        Menu    editMenu = new Menu("Edit");

        //Create Item File:Open
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the openItem item");
                
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setTitle("Select Text File");
                //File selectedFile = fileChooser.showOpenDialog(null);
                
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                		
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null) {
                	textArea.setText(readFile(file));
                }
        
            }
        });
        
        // Item File:Save
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the saveItem item");
                
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                fileChooser.setInitialFileName("TextFile.txt");
                File savedFile = fileChooser.showSaveDialog(primaryStage);
                
                if (savedFile != null) {

        			try {
        				saveFileRoutine(savedFile);
        			}
        			catch(IOException e) {
        			}	
        		}
        		
                
            }
        });
        
        // Item File:Exit
        MenuItem exitItem = new MenuItem("Exit");       
                
        // Item Edit:Spellcheck
        MenuItem spellCheckItem = new MenuItem("Spellcheck");
        spellCheckItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Chose the spellcheck item");
                spellcheck.getWords(null);
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

        //TextArea textArea = new TextArea();
        borderPane.setCenter(textArea);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Spellcheck Project 2");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private String readFile(File file) {
    	StringBuilder stringBuffer = new StringBuilder();
    	BufferedReader bufferedReader = null;
    	
    	try {
    		bufferedReader = new BufferedReader(new FileReader(file));
    		
    		String text;
    		while ((text = bufferedReader.readLine()) != null) {
    			stringBuffer.append(text);
    		}
    	} catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    	return stringBuffer.toString();
    }
    
    private void SaveFile(String content, File file) {
    	try {
    		FileWriter fileWriter;
    		
    		fileWriter = new FileWriter(file);
    		fileWriter.write(content);
    		fileWriter.close();
    	} catch (IOException ex) {
    		
    	}
    }
    
    private void saveFileRoutine(File file)
			throws IOException{
		String txt = textArea.getText();
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(txt);
		writer.close();
	}
}