package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.google.java.contract.Ensures;


/**
 * @author Ravali Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class PdfOptimization {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
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
	 * @throws Exception
	 * @throws NullPointerException
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
