package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.Page;
import com.aspose.pdf.SaveFormat;
import com.aspose.pdf.TextStamp;
import com.aspose.pdf.VerticalAlignment;
import com.google.java.contract.Ensures;


/**
 * @author Ravali Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class HeaderAddition {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public HeaderAddition(String filePath) {
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
	public boolean headerAddition(String header) throws Exception, NullPointerException
    {
		 Document pdfDocument = new Document(filePath);

        // Create header
        TextStamp textStamp = new TextStamp(header);

        // Set properties of the stamp
        textStamp.setTopMargin(10);
        textStamp.setHorizontalAlignment(HorizontalAlignment.Center);
        textStamp.setVerticalAlignment(VerticalAlignment.Top);

        // Add header on all pages
        for (Page page : pdfDocument.getPages()) {
            page.addStamp(textStamp);
        }

        // Save updated document
        pdfDocument.save(filePath + "-TextinHeader_out.pdf");
		return true;
		
	}

	
}
