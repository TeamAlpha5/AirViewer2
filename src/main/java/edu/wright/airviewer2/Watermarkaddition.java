package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.VerticalAlignment;
import com.aspose.pdf.WatermarkArtifact;
import com.aspose.pdf.facades.EncodingType;
import com.aspose.pdf.facades.FontStyle;
import com.aspose.pdf.facades.FormattedText;
import com.google.java.contract.Ensures;

/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class Watermarkaddition {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public Watermarkaddition(String filePath) {
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
	public boolean watermarkAdd() throws Exception, NullPointerException
	{
		
		/*
		 *Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * Here addition of watermark to the PDF takes place
		 * 
		 * 
		 */
		Document doc = new Document(filePath);

		// Create a formatted text
		FormattedText formattedText = new FormattedText("Confidential Document", java.awt.Color.RED, FontStyle.Courier, EncodingType.Identity_h, true, 40.0F);
		// Create watermark artifact and set its properties
		WatermarkArtifact artifact = new WatermarkArtifact();        
		artifact.setText(formattedText);        
		artifact.setArtifactHorizontalAlignment (HorizontalAlignment.Center);
		artifact.setArtifactVerticalAlignment (VerticalAlignment.Center);
		artifact.setRotation (25);
		artifact.setOpacity (0.5);
		artifact.setBackground (false);
		// Add watermark to the first page of PDF
		doc.getPages().get_Item(1).getArtifacts().add(artifact);
		// Save watermarked PDF document
		doc.save(filePath+"- watermark.pdf");
		return true;
		
		
		
		
	}
	
}
