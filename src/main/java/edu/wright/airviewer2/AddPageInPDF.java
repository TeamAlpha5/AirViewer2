package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
/*
 * @author erraramdixit
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class AddPageInPDF {
        /*
	 * The below method adds an extra empty page to the pdf so that user can edit and add what ever he needs
	 * File created date 04/12/2022 no changes made after this date
	 * if any changes made to it please update the date and document the reason for the upgrade 
	 */
	public AddPageInPDF() {
		// TODO Auto-generated constructor stub
	}
	
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	protected boolean addPagePdf(String filePath) throws Exception {
 		  
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
		return true;
	}

}
