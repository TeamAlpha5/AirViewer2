package edu.wright.airviewer2;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Window;


/*
 * annnotaion confirm dialog 
 */
public class AnnotationDialog extends Dialog<Object> {

    @FXML
    private TextField xTextField;

    @FXML
    private TextField yTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;
    
    @FXML
    private TextField contentTextField;

    @FXML
    private ButtonType ConfirmBtn;
    
    // set value to textfields of this dialog
    void setValueTextField(String x, String y) {
    	this.xTextField.setText(x);
    	this.yTextField.setText(y);
    	this.widthTextField.setText("100");
    	this.heightTextField.setText("100");
    }
    // constructor
    public AnnotationDialog(Window owner, String title, String x, String y) {
        try {
        	// load the fxml 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Annotation.fxml"));
            loader.setController(this);
            DialogPane dialogPane = loader.load();
            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);
     	    setResizable(true);
            setTitle(title);
            setValueTextField(x, y);
            setDialogPane(dialogPane);
            setResultConverter(buttonType -> {
                if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, ((ButtonType) buttonType).getButtonData())) {
                    return null;
                }
                String result = xTextField.getText() + "," + yTextField.getText() + ","+ widthTextField.getText() + "," + 
                		heightTextField.getText() + ","+ contentTextField.getText();
				return result;
            });

            setOnShowing(dialogEvent -> Platform.runLater(() -> contentTextField.requestFocus()));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() {

    }
}
