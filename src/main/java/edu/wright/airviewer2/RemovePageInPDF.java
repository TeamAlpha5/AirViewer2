package edu.wright.airviewer2;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
/*
 * @author erraramdixit
 */
public class RemovePageInPDF {

	/*
	 * The below method removes page from the pdf so that user can edit pdf
	 * File created date 04/10/2022 no changes made after this date
	 * if any changes made to it please update the date and document the reason for the upgrade
	 */
	public RemovePageInPDF() {
		// TODO Auto-generated constructor stub
	}

	
	 protected void removePagePdf(String fileName) throws Exception {
	   	  
		  File file = new File(fileName);
	      PDDocument document = PDDocument.load(file);
	       
	      //Listing the number of existing pages
	      //int noOfPages= document.getNumberOfPages();
	      //System.out.print(noOfPages);
	       
	      //Removing the pages
	      JFrame frame = new JFrame();
	      int result = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter page number to remove:"));
	      
	      if(result>0) {
	      document.removePage(result-1);
	      
	      System.out.println("page removed");
	      }
	      else {
	    	  return;
	      }
	      //Saving the document
	      document.save(fileName+"-1removePage.pdf");

	      //Closing the document
	      document.close();  
	}
	 
}
