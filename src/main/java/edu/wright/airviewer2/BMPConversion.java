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

/**
 * @author Akhil sabbella
 *
 */
/**
 * The class DocConversion is used for converting pdf to bmp format with a constructor which takes a pdf file path.
 * It contains docConversion() method which is used for the conversion of pdf to bmp format
 */

@Invariant("filePath!= null && filePath.length() > 0")
public class BMPConversion{	
	String filePath;
	
	/*
	 * This is a constructor where it takes path of the pdf as a parameter
	 * 
	 * @param filePath
	 * 
	 * 
	 */
	
	@Requires("filePath != null && filePath.length() > 0")
	public BMPConversion(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 *main method
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	
	public static void main(String[] args)
	        throws IOException, DocumentException
	
	/**
	 * This method is used for the conversion of pdf to bmp format
	 * @pre(filepath.length()>0)
	 * @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
		
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
