package edu.wright.airviewer2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author Akhil Sabbella
 *
 */

/**
 * Class for Image Annotation Confirm Dialog
 */

/**
* Contains text fields which are used to give the desired size of the image.
*/

public class ImageAnnotationDialog extends Dialog<Object> {

    @FXML
    private TextField xTextField;

    @FXML
    private TextField yTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;
    
    @FXML
    public TextField imageFilePathTextField;

    @FXML
    private ButtonType ConfirmBtn;
    @FXML
    private Button imageFileButton;
    
    public String imageFilePath = "";
    
    public Window stage;

/**
*In this we will give the values for size of the text boxes on the dialog box
*/
	
    void setValueTextField(String x, String y) {
    	this.xTextField.setText(x);
    	this.yTextField.setText(y);
    	this.widthTextField.setText("100");
    	this.heightTextField.setText("100");
    	this.imageFilePathTextField.setText("");
    }
	
	/**
	* This is used to add the annotation to the pdf which will pop up when clicked.
	*/
    public ImageAnnotationDialog(Window owner, String title, String x, String y) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ImageAnnotation.fxml"));
            loader.setController(this);
            
            DialogPane dialogPane = loader.load();

            initOwner(owner);
            
            initModality(Modality.APPLICATION_MODAL);
            stage  = owner;
            setResizable(true);
            setTitle(title);
            setValueTextField(x, y);
            setDialogPane(dialogPane);
            
            setResultConverter(buttonType -> {
                if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, ((ButtonType) buttonType).getButtonData())) {
                    return null;
                }
                String result = xTextField.getText() + "," + yTextField.getText() + ","+ widthTextField.getText() + "," + 
                		heightTextField.getText() + ","+ imageFilePathTextField.getText();
				return result;
            });

            setOnShowing(dialogEvent -> Platform.runLater(() -> imageFileButton.requestFocus()));
        }
	    
	    /**
	 * @throws IOException
	 * @throws RuntimeException
	 */
	    
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void doSomething() {
        System.out.println("This button was clicked!");
	    
 /**
 *This is used to select the picture from the folder which we want to upload.
 */
	    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF File");
        fileChooser.setInitialFileName("");
        File file = fileChooser.showOpenDialog(stage);
        if (null != file) {
            try {
            	imageFilePath = file.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		this.imageFilePathTextField.setText(imageFilePath);
    }
    
    @FXML
    private void initialize() {

    }

}
