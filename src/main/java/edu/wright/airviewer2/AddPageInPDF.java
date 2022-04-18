package edu.wright.airviewer2;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class AddPageInPDF {

	public AddPageInPDF() {
		// TODO Auto-generated constructor stub
	}
	
	protected void addPagePdf(String filePath) throws Exception {
 		  
		  File file = new File(filePath);
	      PDDocument document = PDDocument.load(file);
	       
	      //Listing the number of existing pages
	      int noOfPages= document.getNumberOfPages();
	      //System.out.print(noOfPages);
	       
	      //Removing the pages
	      PDPage my_page = new PDPage();
	      
	     // my_page.getC getContents();
	      
	      document.addPage(my_page);
	      
	      //document.pag
	      
	      System.out.println("page added");

	      //Saving the document
	      document.save(filePath+"-1addPage.pdf");

	      //Closing the document
	      document.close();  
	}

}
