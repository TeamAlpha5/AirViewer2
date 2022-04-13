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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
 */
public class AIRViewerController implements Initializable {

    static final String DEFAULT_PATH = "sample.pdf";

    @FXML
    private Pagination pagination;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem saveAsMenuItem;

    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem convertIntoJPEG;
    @FXML
    private MenuItem convertIntoPNG;

    @FXML
    private MenuItem extractTextMenuItem;

    @FXML
    private MenuItem undoMenuItem;

    @FXML
    private MenuItem redoMenuItem;

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
     
	
	
   
    private AIRViewerModel model;
    private ImageView currentPageImageView;

    private Group pageImageGroup;
    
    private String fileName=null;
    
    private String anFile=null;

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
        assert rotateMenuItem != null : "fx:id=\"rotateMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addPageMenuItem != null : "fx:id=\"addPageMenuItem\" was not injected: check your FXML file 'simple.fxml'.";

        assert convertIntoJPEG != null : "fx:id=\"convertIntoJPEG\" was not injected: check your FXML file 'simple.fxml'.";
        assert convertIntoPNG != null : "fx:id=\"convertIntoPNG\" was not injected: check your FXML file 'simple.fxml'.";
        assert extractTextMenuItem != null : "fx:id=\"extractTextMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert undoMenuItem != null : "fx:id=\"undoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert redoMenuItem != null : "fx:id=\"redoMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addBoxAnnotationMenuItem != null : "fx:id=\"addBoxAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert scrollMenuItem != null : "fx:id=\"scrollMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addEllipseAnnotationMenuItem != null : "fx:id=\"addEllipseAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert addTextAnnotationMenuItem != null : "fx:id=\"addTextAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";
        assert deleteAnnotationMenuItem != null : "fx:id=\"deleteAnnotationMenuItem\" was not injected: check your FXML file 'simple.fxml'.";

        if (null != model) {
            pagination.setPageCount(model.numPages());
            pagination.setDisable(false);
            saveAsMenuItem.setDisable(false);
            extractTextMenuItem.setDisable(false);
            undoMenuItem.setDisable(!model.getCanUndo());
            undoMenuItem.setText("Undo " + model.getSuggestedUndoTitle());
            redoMenuItem.setDisable(!model.getCanRedo());
            redoMenuItem.setText("Redo " + model.getSuggestedRedoTitle());
            addBoxAnnotationMenuItem.setDisable(false);
            scrollMenuItem.setDisable(false);
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

                        float xInPage = (float) me.getX();
                        float yInPage = flippedY;

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
        assert addPageMenuItem != null : "fx:id=\"rotateMenuItem\" was not injected: check your FXML file 'simple.fxml'.";

        
        

        assert convertIntoJPEG != null : "fx:id=\"convertIntoJPEG\" was not injected: check your FXML file 'simple.fxml'.";
        assert convertIntoPNG != null : "fx:id=\"convertIntoPNG\" was not injected: check your FXML file 'simple.fxml'.";
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
                File file = new File(fileName+"-rotate.pdf");
                file.getParentFile().mkdirs();
                try {
					rotatePdf(fileName+"-rotate.pdf");
					
					///////copy fileName+"-rotate.pdf" to fileName and airview the fileName/////
			
				         
	                    // Initializing both the streams with
	                    // respective file directory on local machine
	         
	                    // Custom directory path on local machine
	                    fis = new FileInputStream(
	                        fileName+"-rotate.pdf");
	         
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
					  loadedModel1 = new AIRViewerModel(Paths.get(fileName+"-rotate.pdf"));
					  reinitializeWithModel(loadedModel1);
					  
					  
					  
					  //displaying original one
					  AIRViewerModel loadedModel2 = null;
					  loadedModel2 = new AIRViewerModel(Paths.get(fileName));
					  reinitializeWithModel(loadedModel2);
					  
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
					  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
               // refreshUserInterface();
            }
        });
        addPageMenuItem.setDisable(false);
        
	    
	    
	    
	    
	    
        
        if (null != model) {
            Stage stage = AIRViewer.getPrimaryStage();
            assert null != stage;

            model.deselectAll();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'simple.fxml'.";

        Stage stage = AIRViewer.getPrimaryStage();
        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, (WindowEvent window) -> {
            reinitializeWithModel(promptLoadModel(DEFAULT_PATH));
        });

    }
    
    
       
    protected void rotatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(anFile), new PdfWriter(fileName+"-rotate.pdf"));

        for (int p = 1; p <= pdfDoc.getNumberOfPages(); p++) {
            PdfPage page = pdfDoc.getPage(p);
            int rotate = page.getRotation();
            if (rotate == 0) {
                page.setRotation(90);
            } else {
                page.setRotation((rotate + 90) % 360);
            }
        }

        pdfDoc.close();
    }


    protected void addPagePdf(String dest) throws Exception {
	   		  
		  File file = new File(anFile);
	      PDDocument document = PDDocument.load(file);
	       
	      //Listing the number of existing pages
	      int noOfPages= document.getNumberOfPages();
	      //System.out.print(noOfPages);
	       
	      //Removing the pages
	      PDPage my_page = new PDPage();
	      
	      document.addPage(my_page);
	      
	      System.out.println("page added");

	      //Saving the document
	      document.save(dest);

	      //Closing the document
	      document.close();  
	}
  
   
  
}
