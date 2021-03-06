package edu.wright.airviewer2;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import com.google.java.contract.Ensures;

/**
 * @author Rahul Satla
 *
 */
/**
 * The class JPEG is used for converting pdf to JPEG picture format with a constructor which takes a pdf file path.
 * It contains jpeg() method which is used for the conversion of pdf to jpeg picture format
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class JPEG {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public JPEG(String filePath) {
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
	 * This method is used for the conversion of pdf to jpeg file format
	 * @pre(filepath.length()>0)
	 * @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	public boolean jpeg() throws Exception, NullPointerException
    {
		
/*
 * Here document is initialized and with functions conversion of pdf to JPEG takes place
 * 
 * 
 * 
 * 
 */
		PDDocument document = null;
		//Loading an existing document 
	     File file = new File(filePath); 
	     
		document = PDDocument.load(file);
    PDFRenderer pdfRenderer = new PDFRenderer(document);
    for (int page = 0; page < document.getNumberOfPages(); ++page)
    {
        BufferedImage bim=null;
		try
		{
			bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		} catch (IOException e)
		{
			// catch block
			e.printStackTrace();
		}
  
        ImageIOUtil.writeImage(
                bim, String.format(filePath+"-Image-%d.%s", page + 1, "JPEG"), 300);
    }
		return true;
    }
	
}
