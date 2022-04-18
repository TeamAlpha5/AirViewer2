package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.google.java.contract.Ensures;

/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class TextConversion {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public TextConversion(String filePath) {
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
	public boolean textConversion() throws Exception, NullPointerException
    {
		
	/*
	 *Here document object is initialized with the Pdf
	 * @param filePath is the pdf location path
	 * Here conversion of PDF to Text takes place
	 * 
	 * 
	 */
		File f = new File(filePath);
		String parsedText;
		PDFParser parser = new PDFParser(( new RandomAccessFile(f, "r")));
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		/*
		 * This class will take a pdf document and strip out all of the text
		 *and ignore the formatting and such.
		 */
		PDFTextStripper pdfStripper = new PDFTextStripper();
		PDDocument pdDoc = new PDDocument(cosDoc);
		parsedText = pdfStripper.getText(pdDoc);
		/*
		 * Prints formatted representations of
		 *  objects to a text-output stream. This class implements all of the print methods
		 *   found in PrintStream. It does not contain methods for writing raw bytes, for which
		 *   program should use unencoded byte streams. 
		 * 
		 */
		PrintWriter pw = new PrintWriter(filePath+"-convertedText.txt");
		pw.print(parsedText);
		pw.close();
		return true;
		
	}
	
}
