import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        CheckSpelling checkSpelling = new CheckSpelling();
        WordLists wordLists = new WordLists();

        MenuBar menuBar  = new MenuBar();
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefRowCount(200);
        Menu    fileMenu = new Menu("File");
        Menu    editMenu = new Menu("Edit");
        String textAreaString = textArea.toString();

        //Create Item File:Open
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	
            	FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Text File");
                //File selectedFile = fileChooser.showOpenDialog(null);
                
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
            		
                File file = fileChooser.showOpenDialog(primaryStage);
                try {
					textArea.clear();
                	FileInputStream fis = new FileInputStream(file);
					BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
					String line = reader.readLine().trim();
					while(line != null) {
						textArea.appendText(line + "\n");
						line = reader.readLine();
					}	
				} catch (FileNotFoundException e) {	
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        // Item File:Save
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {

            	FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
                fileChooser.setInitialDirectory(new File(currentPath));
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
                //spellcheck.getWords(null);
            	
            	String str = textArea.getText();
            	wordLists.setwordList(str);
            	String[] strArray = str.split(" ");
            	try {
					checkSpelling.main(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            		wordLists.setTextAreaString(str);
            	}
        });
        
        // Set Accelerator for Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
 
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
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String getAllWords() {
		String allWords = textArea.getText();
    	return allWords;
    }
}