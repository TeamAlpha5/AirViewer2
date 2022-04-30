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
/**The class FooterAddition has filepath .It holds the location of pdf where the footer to the pdf should be added.
 *The footer will be added by footerAddition method
 * @author Ravali Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class FooterAddition {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public FooterAddition(String filePath) {
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
/**This method footerAddition is used to add the footer to the pdf given.
	 @pre(filepath.length()>0)
	 @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean footerAddition(String footer) throws Exception, NullPointerException
    {
		 Document pdfDocument = new Document(filePath);

        // Create header
       TextStamp textStamp = new TextStamp(footer);
        // Set properties of the stamp
        textStamp.setBottomMargin(10);
        textStamp.setHorizontalAlignment(HorizontalAlignment.Center);
        textStamp.setVerticalAlignment(VerticalAlignment.Bottom);
        // Add footer on all pages
        for (Page page : pdfDocument.getPages()) {
            page.addStamp(textStamp);
        }

        // Save updated document
        pdfDocument.save(filePath + "-Textinfooter_out.pdf");
		return true;
		
	}

	
}
