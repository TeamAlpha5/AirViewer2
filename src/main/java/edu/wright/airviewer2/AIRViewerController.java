/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.airviewer2;
import edu.wright.airviewer2.AIRViewer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.util.UUID;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import com.itextpdf.layout.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import com.itextpdf.text.DocumentException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.AnchorPane;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import java.io.File;
/**
 *
 * @author erik
 * @author Rahul Satla
 */
public class AIRViewerController implements Initializable {

    static final String DEFAULT_PATH = "sample.pdf";

    @FXML
    private Pagination pagination;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem saveAsMenuItem, encyptPDFMenuItem;

    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem convertIntoJPEG;
    @FXML
    private MenuItem convertIntoPNG;
    @FXML
    private MenuItem addWatermark;
    @FXML
    private MenuItem convertIntoDoc;
    @FXML
    private MenuItem convertIntoText;
    @FXML
    private MenuItem convertIntoHtml;

    @FXML
    private MenuItem extractTextMenuItem;

    @FXML
    private MenuItem undoMenuItem;

    @FXML
    private MenuItem redoMenuItem;
    @FXML
    private MenuItem mergePDF;
    @FXML
    private MenuItem splitPDF;
    @FXML
    private MenuItem addBoxAnnotationMenuItem;
    
    @FXML
    private MenuItem scrollMenuItem;

    @FXML
    private MenuItem addEllipseAnnotationMenuItem;

    @FXML
    private MenuItem addTextAnnotationMenuItem;

    @FXML
    private MenuItem deleteAnnotationMenuItem;
    
    @FXML
    private MenuItem rotateMenuItem; 
	
    @FXML
    private MenuItem addPageMenuItem;
     
    @FXML
    private MenuItem removePageMenuItem;
	
    @FXML
    private MenuItem encryptMenuItem;
    
    @FXML
    private MenuItem decryptMenuItem;
	
    @FXML
    private ScrollPane scroller;
	
    private AIRViewerModel model;
    private ImageView currentPageImageView;
    private Group pageImageGroup;
    private String fileName=null;
    
	private String anFile=null;
	
    float xInPage;
    float yInPage;

    private AIRViewerModel promptLoadModel(String startPath) {

        AIRViewerModel loadedModel = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open PDF File");
            fileChooser.setInitialFileName(startPath);
            Stage stage = (Stage) pagination.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if (null != file) {
                String path = file.getCanonicalPath();
                System.out.println("Path:"+path);
                fileName=path;
                loadedModel = new AIRViewerModel(Paths.get(path));
            }
        } catch (IOException ex) {
//            Logger.getLogger(AIRViewerController.class.getName()).log(
//                    Level.INFO,
//                    "Unable to open <" + ex.getLocalizedMessage() + ">",
//                    "");
            loadedModel = null;
        }

        return loadedModel;
    }

    private void synchronizeSelectionKnobs() {
        if (null != model && null != currentPageImageView && null != pageImageGroup) {
            List<java.awt.Rectangle> selectedAreas = model.getSelectedAreas();
            ArrayList<Node> victims = new ArrayList<>(pageImageGroup.getChildren());
            
            // Delete everything in teh group that isn't currentPageImageView
            victims.stream().filter((n) -> (n != currentPageImageView)).forEach((n) -> {
                pageImageGroup.getChildren().remove(n);
            });
            
            // Add knobs to thegroup to indicate selection
            for (java.awt.Rectangle r : selectedAreas) {
                Circle knobA = new Circle(r.getX(),  (int)pageImageGroup.prefHeight(0) - r.getY(), 4);
                knobA.setStroke(Color.YELLOW);
                knobA.setStrokeWidth(2);
                pageImageGroup.getChildren().add(knobA);
                Circle knobB = new Circle(r.getX() + r.getWidth(), (int)pageImageGroup.prefHeight(0) - r.getY(), 4);
                knobB.setStroke(Color.YELLOW);
                knobB.setStrokeWidth(2);
                pageImageGroup.getChildren().add(knobB);
                Circle knobC = new Circle(r.getX() + r.getWidth(),  (int)pageImageGroup.prefHeight(0) - (r.getY() + r.getHeight()), 4);
                knobC.setStroke(Color.YELLOW);
                knobC.setStrokeWidth(2);
                pageImageGroup.getChildren().add(knobC);
                Circle knobD = new Circle(r.getX(),  (int)pageImageGroup.prefHeight(0) - (r.getY() + r.getHeight()), 4);
                knobD.setStroke(Color.YELLOW);
                knobD.setStrokeWidth(2);
                pageImageGroup.getChildren().add(knobD);
            }
        }

    }

    private void refreshUserInterface() {
        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'simple.fxml'.";
        assert openMenuItem != null : "fx:id=\"openMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert saveAsMenuItem != null : "fx:id=\"saveAsMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
      assert closeMenuItem != null : "fx:id=\"closeMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
	   assert addWatermark != null : "fx:id=\"addWatermark\" was not injected: check your FXML file 'simple.fxml'.";
        assert convertIntoJPEG != null : "fx:id=\"convertIntoJPEG\" was not injected: check your FXML file 'simple.fxml'.";
        assert closeMenuItem != null : "fx:id=\"closeMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert rotateMenuItem != null : "fx:id=\"rotateMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addPageMenuItem != null : "fx:id=\"addPageMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert removePageMenuItem != null : "fx:id=\"removePageMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
	assert encryptMenuItem != null : "fx:id=\"encryptMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert decryptMenuItem != null : "fx:id=\"decryptMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoJPEG != null : "fx:id=\"convertIntoJPEG\" was not injected: check your FXML file 'simple.fxml'.";
        assert convertIntoPNG != null : "fx:id=\"convertIntoPNG\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoDoc != null : "fx:id=\"convertIntoDoc\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoText != null : "fx:id=\"convertIntoText\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoHtml != null : "fx:id=\"convertIntoHtml\" was not injected: check your FXML file 'simple.fxml'.";

        assert extractTextMenuItem != null : "fx:id=\"extractTextMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
	assert undoMenuItem != null : "fx:id=\"undoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert redoMenuItem != null : "fx:id=\"redoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addBoxAnnotationMenuItem != null : "fx:id=\"addBoxAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert scrollMenuItem != null : "fx:id=\"scrollMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addEllipseAnnotationMenuItem != null : "fx:id=\"addEllipseAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addTextAnnotationMenuItem != null : "fx:id=\"addTextAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert deleteAnnotationMenuItem != null : "fx:id=\"deleteAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert mergePDF != null : "fx:id=\"mergePDF\" was not injected: check your FXML file 'simple.fxml'.";
	assert splitPDF != null : "fx:id=\"splitPDF\" was not injected: check your FXML file 'simple.fxml'.";
        if (null != model) {
            pagination.setPageCount(model.numPages());
            pagination.setDisable(false);
            saveAsMenuItem.setDisable(false);
	    encyptPDFMenuItem.setDisable(false);
            extractTextMenuItem.setDisable(false);
            undoMenuItem.setDisable(!model.getCanUndo());
            undoMenuItem.setText("Undo " + model.getSuggestedUndoTitle());
            redoMenuItem.setDisable(!model.getCanRedo());
            redoMenuItem.setText("Redo " + model.getSuggestedRedoTitle());
            addBoxAnnotationMenuItem.setDisable(false);
            addEllipseAnnotationMenuItem.setDisable(false);
            addTextAnnotationMenuItem.setDisable(false);
            deleteAnnotationMenuItem.setDisable(0 >= model.getSelectionSize());

            if (null != currentPageImageView) {
                int pageIndex = pagination.getCurrentPageIndex();
                currentPageImageView.setImage(model.getImage(pageIndex));
                currentPageImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        float flippedY = (float) currentPageImageView.getBoundsInParent().getHeight() - (float) me.getY();
                        System.out.println("Mouse pressed X: " + me.getX()
                                + " Y: " + Float.toString(flippedY));

                        xInPage = (float) me.getX();
                        yInPage = flippedY;

                        if (null != model) {
                            int pageIndex = pagination.getCurrentPageIndex();
                            if (!me.isMetaDown() && !me.isShiftDown()) {
                                model.deselectAll();
                            }
                            model.extendSelectionOnPageAtPoint(pageIndex,
                                    xInPage, yInPage);
                            refreshUserInterface();
                        }
                    }
                });
            }

            synchronizeSelectionKnobs();

        } else {
            pagination.setPageCount(0);
            pagination.setPageFactory(index -> {
                if (null == pageImageGroup) {
                    pageImageGroup = new Group();
                }
                currentPageImageView = new ImageView();
                pageImageGroup.getChildren().clear();
                pageImageGroup.getChildren().add(currentPageImageView);
                return pageImageGroup;
            });
            pagination.setDisable(true);
            saveAsMenuItem.setDisable(true);
	    encyptPDFMenuItem.setDisable(true);
            extractTextMenuItem.setDisable(true);
            undoMenuItem.setDisable(true);
            redoMenuItem.setDisable(true);
            addBoxAnnotationMenuItem.setDisable(true);
            scrollMenuItem.setDisable(true);
            addEllipseAnnotationMenuItem.setDisable(true);
            addTextAnnotationMenuItem.setDisable(true);
            deleteAnnotationMenuItem.setDisable(true);

        }
    }

    private AIRViewerModel reinitializeWithModel(AIRViewerModel aModel) {
        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'simple.fxml'.";
        assert openMenuItem != null : "fx:id=\"openMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert saveAsMenuItem != null : "fx:id=\"saveAsMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert closeMenuItem != null : "fx:id=\"closeMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert rotateMenuItem != null : "fx:id=\"rotateMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addPageMenuItem != null : "fx:id=\"addPageMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert removePageMenuItem != null : "fx:id=\"removePageMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert encryptMenuItem != null : "fx:id=\"encryptMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert decryptMenuItem != null : "fx:id=\"decryptMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
               
        assert convertIntoJPEG != null : "fx:id=\"convertIntoJPEG\" was not injected: check your FXML file 'simple.fxml'.";
        assert convertIntoPNG != null : "fx:id=\"convertIntoPNG\" was not injected: check your FXML file 'simple.fxml'.";
	    assert addWatermark != null : "fx:id=\"addWatermark\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoDoc != null : "fx:id=\"convertIntoDoc\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoText != null : "fx:id=\"convertIntoText\" was not injected: check your FXML file 'simple.fxml'.";
	assert convertIntoHtml != null : "fx:id=\"convertIntoHtml\" was not injected: check your FXML file 'simple.fxml'.";
        assert extractTextMenuItem != null : "fx:id=\"extractTextMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert undoMenuItem != null : "fx:id=\"undoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert redoMenuItem != null : "fx:id=\"redoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addBoxAnnotationMenuItem != null : "fx:id=\"addBoxAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert scrollMenuItem != null : "fx:id=\"scrollMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addEllipseAnnotationMenuItem != null : "fx:id=\"addEllipseAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addTextAnnotationMenuItem != null : "fx:id=\"addTextAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert deleteAnnotationMenuItem != null : "fx:id=\"deleteAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        model = aModel;
        openMenuItem.setOnAction((ActionEvent e) -> {
            System.out.println("Open ...");
            reinitializeWithModel(promptLoadModel(AIRViewerController.DEFAULT_PATH));
        });
        openMenuItem.setDisable(false);
        closeMenuItem.setOnAction((ActionEvent e) -> {
            System.out.println("closeMenuItem ...");
            Platform.exit();
        });
        closeMenuItem.setDisable(false);
        rotateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //int pageIndex = pagination.getCurrentPageIndex();
                FileInputStream fis = null;
                FileOutputStream fos = null;
         
                // Try block to check for exceptions
                try {
         
                    // Initializing both the streams with
                    // respective file directory on local machine
         
                    // Custom directory path on local machine
                    fis = new FileInputStream(
                        fileName);
         
                    // Custom directory path on local machine
                    fos = new FileOutputStream(
                        fileName+"-1");
         
                    anFile=fileName+"-1";
                    int c;
                    while ((c = fis.read()) != -1) {
                    	 
                        // Writing to output file of the specified
                        // directory
                        fos.write(c);
                    }
         
                }catch(Exception e1) {
                	System.out.println(e1);
                }
                File file = new File(fileName+"-1rotate.pdf");
                file.getParentFile().mkdirs();
                try {
			    RotateViewerModel r= new RotateViewerModel();
                   	    r.rotatefn(fileName+"-1");
					
			    //copy fileName+"-rotate.pdf" to fileName and airview the fileName/////
			
				         
	                    // Initializing both the streams with
	                    // respective file directory on local machine
	         
	                    // Custom directory path on local machine
	                    fis = new FileInputStream(
	                        fileName+"-1rotate.pdf");
	         
	                    // Custom directory path on local machine
	                    fos = new FileOutputStream(
	                        fileName);
	         
	                    anFile=fileName;
	                    int c;
	                    while ((c = fis.read()) != -1) {
	                    	 
	                        // Writing to output file of the specified
	                        // directory
	                        fos.write(c);
	                    }
	         
	                
					///////////////////////////////////////////////
					
					
					  AIRViewerModel loadedModel1 = null;
					  loadedModel1 = new AIRViewerModel(Paths.get(fileName+"-1rotate.pdf"));
					  reinitializeWithModel(loadedModel1);
					  
					  
					  
					  //displaying original one
					  AIRViewerModel loadedModel2 = null;
					  loadedModel2 = new AIRViewerModel(Paths.get(fileName));
					  reinitializeWithModel(loadedModel2);
			
					  File extra1 = new File(fileName+"-1rotate.pdf"); 
					  extra1.delete();

					  File extra2 = new File(fileName+"-1"); 
					  extra2.delete();
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               // RotateViewerModel.rotate();
               // refreshUserInterface();
            }
        });
        rotateMenuItem.setDisable(false);
        

        addPageMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //int pageIndex = pagination.getCurrentPageIndex();
                FileInputStream fis = null;
                FileOutputStream fos = null;
         
                // Try block to check for exceptions
                try {
         
                    // Initializing both the streams with
                    // respective file directory on local machine
         
                    // Custom directory path on local machine
                    fis = new FileInputStream(
                        fileName);
         
                    // Custom directory path on local machine
                    fos = new FileOutputStream(
                        fileName+"-1");
         
                    anFile=fileName+"-1";
                    int c;
                    while ((c = fis.read()) != -1) {
                    	 
                        // Writing to output file of the specified
                        // directory
                        fos.write(c);
                    }
         
                }catch(Exception e1) {
                	System.out.println(e1);
                }
                File file = new File(fileName+"-addPage.pdf");
                file.getParentFile().mkdirs();
                try {
                	addPagePdf(fileName+"-addPage.pdf");
					
			///////copy fileName+"-rotate.pdf" to fileName and airview the fileName/////
			
				         
	                    // Initializing both the streams with
	                    // respective file directory on local machine
	         
	                    // Custom directory path on local machine
	                    fis = new FileInputStream(
	                        fileName+"-addPage.pdf");
	         
	                    // Custom directory path on local machine
	                    fos = new FileOutputStream(
	                        fileName);
	         
	                    anFile=fileName;
	                    int c;
	                    while ((c = fis.read()) != -1) {
	                    	 
	                        // Writing to output file of the specified
	                        // directory
	                        fos.write(c);
	                    }
	         
	                
					///////////////////////////////////////////////
					
					
					  AIRViewerModel loadedModel1 = null;
					  loadedModel1 = new AIRViewerModel(Paths.get(fileName+"-addPage.pdf"));
					  reinitializeWithModel(loadedModel1);
					  
					  
					  
					  //displaying file
					  AIRViewerModel loadedModel2 = null;
					  loadedModel2 = new AIRViewerModel(Paths.get(fileName));
					  reinitializeWithModel(loadedModel2);
			
					  File extra1 = new File(fileName+"-addPage.pdf"); 
		                	  extra1.delete();
		                
		               		 File extra2 = new File(fileName+"-1"); 
		               		 extra2.delete();
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
               // refreshUserInterface();
            }
        });
        addPageMenuItem.setDisable(false);
        
	    
	removePageMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //int pageIndex = pagination.getCurrentPageIndex();
                FileInputStream fis = null;
                FileOutputStream fos = null;
         
                // Try block to check for exceptions
                try {
         
                    // Initializing both the streams with
                    // respective file directory on local machine
         
                    // Custom directory path on local machine
                    fis = new FileInputStream(
                        fileName);
         
                    // Custom directory path on local machine
                    fos = new FileOutputStream(
                        fileName+"-1");
         
                    anFile=fileName+"-1";
                    int c;
                    while ((c = fis.read()) != -1) {
                    	 
                        // Writing to output file of the specified
                        // directory
                        fos.write(c);
                    }
         
                }catch(Exception e1) {
                	System.out.println(e1);
                }
                File file = new File(fileName+"-1removePage.pdf");
                file.getParentFile().mkdirs();
                try {
                	//removePagePdf(fileName+"-1removePage.pdf");
                	RemovePageInPDF rp=new RemovePageInPDF();
                	rp.removePagePdf(fileName);
					
                	
                	//copy fileName+"-rotate.pdf" to fileName and airview the fileName/////
			
				         
	                    // Initializing both the streams with
	                    // respective file directory on local machine
	         
	                    // Custom directory path on local machine
	                    fis = new FileInputStream(
	                        fileName+"-1removePage.pdf");
	         
	                    // Custom directory path on local machine
	                    fos = new FileOutputStream(
	                        fileName);
	         
	                    anFile=fileName;
	                    int c;
	                    while ((c = fis.read()) != -1) {
	                    	 
	                        // Writing to output file of the specified
	                        // directory
	                        fos.write(c);
	                    }
	         
	                
					///////////////////////////////////////////////
					
					
					  AIRViewerModel loadedModel1 = null;
					  loadedModel1 = new AIRViewerModel(Paths.get(fileName+"-1removePage.pdf"));
					  reinitializeWithModel(loadedModel1);
					  
					  
					  
					  //displaying file
					  AIRViewerModel loadedModel2 = null;
					  loadedModel2 = new AIRViewerModel(Paths.get(fileName));
					  reinitializeWithModel(loadedModel2);
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
               // refreshUserInterface();
            }
        });
        removePageMenuItem.setDisable(false);
	    
	    
	    
	    
	    
	encryptMenuItem.setOnAction((ActionEvent e) -> {
            System.out.println("encrypt ...");
           // String path = file.getCanonicalPath();
            File file = new File(fileName+"-1encryted");
            file.getParentFile().mkdirs();

            try {
				//manipulatePdf(fileName+"-1encryted");
            	EncryptPDF ep=new EncryptPDF();
            	ep.encryptPdf(fileName);
            	AIRViewerModel loadedModel1 = null;
				loadedModel1 = new AIRViewerModel(Paths.get(fileName+"-1encryted"));
				reinitializeWithModel(loadedModel1);
				// refreshUserInterface();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //reinitializeWithModel(promptLoadModel(AIRViewerController.DEFAULT_PATH));
        });
        encryptMenuItem.setDisable(false);
        if (null != model) {
            Stage stage = AIRViewer.getPrimaryStage();
            assert null != stage;
            model.deselectAll();
            initScroller();
            pagination.setPageCount(model.numPages());
            pagination.setPageFactory(index -> {
                if (null == pageImageGroup) {
                    pageImageGroup = new Group();
                }
                currentPageImageView = new ImageView(model.getImage(index));
                pageImageGroup.getChildren().clear();
                pageImageGroup.getChildren().add(currentPageImageView);
                model.deselectAll();
                refreshUserInterface();
                return pageImageGroup;
            });
            saveAsMenuItem.setOnAction((ActionEvent event) -> {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);
                
                //System.out.println(fileChooser.getInitialDirectory());
                
                System.out.println();
                File file = fileChooser.showSaveDialog((Stage) pagination.getScene().getWindow());
                System.out.println(file.getAbsolutePath());
                
                if (null != file) {
                    model.save(file);
                }
            });
            convertIntoJPEG.setOnAction((ActionEvent event) -> {
                try {
					JPEG a=new JPEG(model.getStrPath());
					a.jpeg();
                    refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });
            convertIntoPNG.setOnAction((ActionEvent event) -> {
                try {
                	PNG a=new PNG(model.getStrPath());
					a.png();
                    refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });
		addWatermark.setOnAction((ActionEvent event) -> {
                try {
                	Watermarkaddition a=new Watermarkaddition(model.getStrPath());
					a.watermarkAdd();
              refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });    
                  
		  convertIntoHtml.setOnAction((ActionEvent event) -> {
                try {
					HtmlConversion a=new HtmlConversion(model.getStrPath());
					
					a.htmlConversion();
                    refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });
                
           convertIntoDoc.setOnAction((ActionEvent event) -> {
                try {
					DocConversion a=new DocConversion(model.getStrPath());
					
					a.docConversion();
                    refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });
		  convertIntoText.setOnAction((ActionEvent event) -> {
                try {
					TextConversion a=new TextConversion(model.getStrPath());
					
					a.textConversion();
                    refreshUserInterface();
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
            });
	    encyptPDFMenuItem.setOnAction((ActionEvent event) -> {
            	FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog((Stage) pagination.getScene().getWindow());
                });
            extractTextMenuItem.setOnAction((ActionEvent e) -> {
                System.out.println("extractTextMenuItem ...");
            });
            undoMenuItem.setOnAction((ActionEvent e) -> {
                model.undo();
                refreshUserInterface();
            });
            redoMenuItem.setOnAction((ActionEvent e) -> {
                model.redo();
                refreshUserInterface();
            });
            mergePDF.setOnAction((ActionEvent event) -> {
                MergePdf a=new MergePdf(model.getStrPath());
                try {
					a.mergepdf();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                refreshUserInterface();
			}
    );
		splitPDF.setOnAction((ActionEvent event) -> {
	                SplitPdf a=new SplitPdf(model.getStrPath());
	                try {
						a.splitpdf();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                refreshUserInterface();
					
			}
    );
            addBoxAnnotationMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int pageIndex = pagination.getCurrentPageIndex();
                    model.executeDocumentCommandWithNameAndArgs("AddBoxAnnotation",
                            new String[]{Integer.toString(pageIndex), "36.0", "36.0", "72.0", "72.0"});
                    refreshUserInterface();
                }
            });
            
            
            
            
            
            addEllipseAnnotationMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int pageIndex = pagination.getCurrentPageIndex();             
                    model.executeDocumentCommandWithNameAndArgs("AddCircleAnnotation",
                            new String[]{Integer.toString(pageIndex), "288", "576", "144.0", "72.0", "Sample Text!"});
                    refreshUserInterface();
                }
            });
            addTextAnnotationMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int pageIndex = pagination.getCurrentPageIndex();
                    model.executeDocumentCommandWithNameAndArgs("AddTextAnnotation",
                            new String[]{Integer.toString(pageIndex), "36", "576", "144.0", "19.0", "A Bit More Sample Text!"});
                    
                    refreshUserInterface();
                }
            });
            deleteAnnotationMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int pageIndex = pagination.getCurrentPageIndex();
                    model.executeDocumentCommandWithNameAndArgs("DeleteSelectedAnnotation",
                            new String[]{Integer.toString(pageIndex)});
                    refreshUserInterface();
                }
            });
        }

        refreshUserInterface();

        return model;
    }
	
    // function that initialize scroller
    private void initScroller() {
        
        scroller = new ScrollPane();
        AnchorPane.setTopAnchor(scroller,0.0);
        AnchorPane.setRightAnchor(scroller,0.0);
        AnchorPane.setLeftAnchor(scroller,0.0);
        AnchorPane.setBottomAnchor(scroller,70.0);
    }
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'simple.fxml'.";

        Stage stage = AIRViewer.getPrimaryStage();
        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, (WindowEvent window) -> {
            reinitializeWithModel(promptLoadModel(DEFAULT_PATH));
        });
    } 
}
