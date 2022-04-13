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

public class PNG {
	
	String numberOfPages;
	String filePath;
	double parsedNumberOfPages;	
	public PNG(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	public void png() throws Exception, NullPointerException
    {
		
		PDDocument document = null;

	     File file = new File(filePath); 
	     
		document = PDDocument.load(file);
    PDFRenderer pdfRenderer = new PDFRenderer(document);
    for (int page = 0; page < document.getNumberOfPages(); ++page) {
        BufferedImage bim=null;;
		try {
			bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
  
        ImageIOUtil.writeImage(
                bim, String.format("C:/Users/wsucatslabs/Desktop/Amazon/Image-%d.%s", page + 1, "png"), 300);
    }
    }
	
}