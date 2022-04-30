package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.google.java.contract.Ensures;


/**This class PdfOptimization has attribute file path which consists of location of pdf which needs to be optimized
*
@invariant ("filepath != null && numberOfPages.length() > 0")
**author Ravali Satla
*/
@Invariant("filePath!= null && filePath.length() > 0")
public class PdfOptimization {	
	String filePath;
	
/**
 * This constructor PdfOptimization takes the "filePath" as a parameter.
 * @pre ("filepath != null && filepath > 0")
 * @param filepath
 *
 */
	@Requires("filePath != null && filePath.length() > 0")
	public PdfOptimization(String filePath) {
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
 * This method pdfOptimization() is used to optimize the PDF.
 * 
 * This function is using Apache PDF box library to achieve this purpose.
 * 
 * Reference link for this functionality: https://pdfbox.apache.org/docs/1.8.10/javadocs/index.html?org/apache/pdfbox/pdmodel/PDDocument.html
 * The method uses optimize method which is used to optimize pdf.
 * @pre ("filePath != null")
 * @post ("result == true")
 * @return
 * @throws IOException
 */
    @Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean pdfOptimization() throws Exception, NullPointerException
    {
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * SavingFilePath is the path where the document is saved
		 * 
		 */
		Document doc = new Document(filePath);
		doc.optimize();
		doc.save(filePath+"-Optimized_output.pdf");
		return true;
		
	}

	
}
