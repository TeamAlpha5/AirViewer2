package edu.wright.airviewer2;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.aspose.pdf.internal.imaging.internal.Exceptions.IO.IOException;
import com.itextpdf.text.DocumentException;
public class PdfPassword {
	//final static String USER_PASSWORD = "user";
	  //final static String OWNER_PASSWORD = "owner";
	String numberOfPages;
	String filePath;
	double parsedNumberOfPages;	
	public PdfPassword(String filePath) {
		super();
		this.filePath = filePath;
	
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	
	@SuppressWarnings("unused")
	public boolean pdfpass(String pwd) throws Exception, NullPointerException
    {
		
		String USER_PASSWORD = pwd;
		 String OWNER_PASSWORD = pwd;
 
		PDDocument document = PDDocument.load(new File(filePath));
	      AccessPermission ap = new AccessPermission();
	      /** Setting access permissions */
	      // Printing not allowed
	      ap.setCanPrint(false);
	      // Copying not allowed
	      ap.setCanExtractContent(false);
	       
	      StandardProtectionPolicy standardPP = new StandardProtectionPolicy(OWNER_PASSWORD, USER_PASSWORD, ap);
	      standardPP.setEncryptionKeyLength(128);
	      document.protect(standardPP);
	      document.save(filePath);
	      document.close();
	      return true;

 
           
    }
		
		
	}