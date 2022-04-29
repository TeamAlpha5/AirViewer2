package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.aspose.pdf.devices.BmpDevice;
import com.aspose.pdf.devices.Resolution;
import com.google.java.contract.Ensures;

@Invariant("filePath!= null && filePath.length() > 0")
public class BMPConversion{	
	String filePath;
	
	@Requires("filePath != null && filePath.length() > 0")
	public BMPConversion(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean bmpConversion() throws Exception, NullPointerException
    {
		Document pdfDocument = new Document(filePath);
// Loop through all the pages of PDF file
for (int pageCount = 1; pageCount <= pdfDocument.getPages().size(); pageCount++) {
	// Create stream object to save the output image
	java.io.OutputStream imageStream = new java.io.FileOutputStream(filePath+"-Converted_Image" + pageCount + ".bmp");

	// Create Resolution object
	Resolution resolution = new Resolution(300);
	// Create BmpDevice object with particular resolution
	BmpDevice bmpDevice = new BmpDevice(resolution);
	// Convert a particular page and save the image to stream
	bmpDevice.process(pdfDocument.getPages().get_Item(pageCount), imageStream);

	// Close the stream
	imageStream.close();
}
		return true;	
		
	}

	
}
