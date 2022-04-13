package edu.wright.airviewer2;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import com.itextpdf.text.DocumentException;
public class SplitPdf {
	
	String numberOfPages;
	String filePath;
	double parsedNumberOfPages;	
	public SplitPdf(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	
	public void splitpdf() throws Exception, NullPointerException
    {
		
		File file1=new File(filePath);
					       PDDocument document=null;
						try {
							document = PDDocument.load(file1);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
  
       // Splitter Class
        Splitter splitting = new Splitter();
  
        // Splitting the pages into multiple PDFs
        List<PDDocument> Page=null;
		try {
			Page = splitting.split(document);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  
        // Using a iterator to Traverse all pages
        Iterator<PDDocument> iteration = Page.listIterator();
  
        // Saving each page as an individual document
        int j = 1;
        while (iteration.hasNext()) {
            PDDocument pd = iteration.next();
            try {
				pd.save("C:/Users/wsucatslabs/Desktop/Amazon/Resume-"
				        + j++ + ".pdf");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        System.out.println("Splitted Pdf Successfully.");
            }
    }
