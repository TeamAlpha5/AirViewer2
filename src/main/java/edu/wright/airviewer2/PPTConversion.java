package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.google.java.contract.Ensures;

@Invariant("filePath!= null && filePath.length() > 0")
public class PPTConversion {	
	String filePath;
	
	@Requires("filePath != null && filePath.length() > 0")
	public PPTConversion(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
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
