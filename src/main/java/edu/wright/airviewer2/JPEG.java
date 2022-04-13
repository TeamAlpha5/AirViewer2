package edu.wright.airviewer2;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import com.itextpdf.text.DocumentException;
public class JPEG {	
	String numberOfPages;
	String filePath;
	double parsedNumberOfPages;	
	public JPEG(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	public void jpeg() throws Exception, NullPointerException
    {
		
		PDDocument document = null;
		//Loading an existing document 
	     File file = new File(filePath); 
	     
		document = PDDocument.load(file);
    PDFRenderer pdfRenderer = new PDFRenderer(document);
    for (int page = 0; page < document.getNumberOfPages(); ++page) {
        BufferedImage bim=null;;
		try {
			bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
        ImageIOUtil.writeImage(
                bim, String.format("C:/Users/wsucatslabs/Desktop/Amazon/Image-%d.%s", page + 1, "JPEG"), 300);
    }
    }
	
}