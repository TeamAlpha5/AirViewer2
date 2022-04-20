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
/**This class has attribute file path which consists of location of pdf which needs to be protected
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
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	
	@SuppressWarnings("unused")
	/**
 * This method pdfpass(string pwd) uses password given by user to protect  the PDF.
 * 
 * This function is using Apache PDF box library to achieve this purpose.
 * 
 * Reference link for this functionality: https://pdfbox.apache.org/docs/1.8.10/javadocs/index.html?org/apache/pdfbox/pdmodel/PDDocument.html
 * 
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
