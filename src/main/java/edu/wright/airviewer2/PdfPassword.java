package edu.wright.airviewer2;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import com.google.java.contract.Ensures;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.aspose.pdf.internal.imaging.internal.Exceptions.IO.IOException;
import com.itextpdf.text.DocumentException;
/**This class PdfPassword takes file path as an attribute  which consists of location of pdf which needs to be protected
*
@invariant ("filepath != null && numberOfPages.length() > 0")
**author Ravali Satla
*/
public class PdfPassword {
	String filePath;
	
/**
 * This constructor PdfPassword takes the "filePath" as a parameter.
 * @pre ("filepath != null && filepath > 0")
 * @param filepath
 *
 */
	public PdfPassword(String filePath) {
		super();
		this.filePath = filePath;
	
	}
/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
		/**
 * This method pdfpass(string pwd) uses password given by user to protect  the PDF.
 * 
 * This function is using Apache PDF box library to achieve this purpose.
 * 
 * Reference link for this functionality: https://pdfbox.apache.org/docs/1.8.10/javadocs/index.html?org/apache/pdfbox/pdmodel/PDDocument.html
 * The method uses AccessPermission class which is used to set access permission to the file.
 * This method uses StandardProtectionPolicy class to add Password based Protection for pdf.
 * @pre ("pwd.length>0")
 * @post ("result == true")
 * @return
 * @throws IOException
 */

@Requires("pwd.length>0")
@Ensures("result == true")
	public boolean pdfpass(String pwd) throws Exception, NullPointerException
    {
		
		String USER_PASSWORD = pwd;
		 String OWNER_PASSWORD = pwd;
 
		PDDocument document = PDDocument.load(new File(filePath));
	      AccessPermission ap = new AccessPermission();
	      /** Setting access permissions */
	      //Printing not allowed
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
