package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import com.google.java.contract.Ensures;

/**
 * @author Rahul Satla
 *
 */
/**
 * The class HtmlConversion is used for converting pdf to html format with a constructor which takes a pdf file path.
 * It contains htmlConversion() method which is used for the conversion of pdf to html format
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class HtmlConversion {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public HtmlConversion(String filePath) {
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
	 * This method is used for the conversion of pdf to html format
	 * @pre(filepath.length()>0)
	 * @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean htmlConversion() throws Exception, NullPointerException
    {
		
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * Here conversion of PDF to HTML takes place
		 * 
		 */
		PDDocument pdf = PDDocument.load(new File(filePath));
	    Writer output = new PrintWriter(filePath+"-converted.html", "utf-8");
	    new PDFDomTree().writeText(pdf, output);
	    
	    output.close();
		return true;
	}
	
}
