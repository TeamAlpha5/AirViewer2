package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.google.java.contract.Ensures;

/**
 * @author Deepika Gadiraju
 *
 */
/**
 * The class PPTConversion is used for converting pdf to ppt with a constructor which takes a pdf file path.
 * It contains pptConversion() method which is used for the conversion of pdf to ppt format.
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class PPTConversion {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public PPTConversion(String filePath) {
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
* This method is used for the conversion of pdf to ppt file format
* @pre(filepath.length()>0)
* @post(result==true)
* @throws Exception
* @throws NullPointerException
*/	
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean pptConversion() throws Exception, NullPointerException
    {
		Document doc = new Document(filePath);
		// Save resultant DOC file
		doc.save(filePath+"-dd.Pptx", SaveFormat.Pptx);
		return true;		
	}	
}
