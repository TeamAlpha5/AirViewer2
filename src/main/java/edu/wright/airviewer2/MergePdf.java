package edu.wright.airviewer2;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import com.itextpdf.text.DocumentException;
//import com.google.java.contract.Invariant;
//import com.google.java.contract.Requires;
//import com.google.java.contract.Ensures;
//@Invariant("numberOfPages != null && numberOfPages.length() > 0")
public class MergePdf {
	
	String numberOfPages;
	String filePath;
	double parsedNumberOfPages;	
//@Requires("numberOfPages != null && numberOfPages.length() > 0")
	public MergePdf(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	@SuppressWarnings("deprecation")
	public void mergepdf() throws Exception, NullPointerException
    {
		
		PDDocument document = null;
	
	     File file1 = new File(filePath); 
	     
		document = PDDocument.load(file1);
    PDFRenderer pdfRenderer = new PDFRenderer(document);
    PDFMergerUtility obj = new PDFMergerUtility();
    
    JFrame frame = new JFrame("PDF");  
    // Setting the destination file path
    
    obj.setDestinationFileName(
        "C:/Users/wsucatslabs/Desktop/Amazon/hello.pdf");

    // Add all source files, to be merged
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    int result = fileChooser.showOpenDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
    
    File file2=new File(selectedFile.getAbsolutePath());
    try {
		obj.addSource(file1);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    try {
		obj.addSource(file2);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

    // Merging documents

    try {
		obj.mergeDocuments();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
                
            }
    }
	
}