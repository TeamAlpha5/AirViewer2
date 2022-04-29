package edu.wright.airviewer2;

import java.io.IOException;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import com.itextpdf.text.DocumentException;

/**
 * @author Rahul Satla
 *
 */
/**
 * The class DocConversion is used for converting pdf to document format with a constructor which takes a pdf file path.
 * It contains docConversion() method which is used for the conversion of pdf to document format
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class DocConversion {
	String filePath;
	String savingFilePath;

	/*
	 * This is a constructor where it takes path of the pdf as a parameter
	 * 
	 * @param filePath
	 * 
	 * 
	 */
	@Requires("filePath != null && filePath.length() > 0")
	public DocConversion(String filePath) {
		super();
		this.filePath = filePath;
		savingFilePath = filePath.replace("pdf", "doc");
	}

	/**
	 *main method
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws IOException, DocumentException {
	}
	/**
	 * This method is used for the conversion of pdf to document format
	 * @pre(filepath.length()>0)
	 * @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean docConversion() throws Exception, NullPointerException {
		/*
		 * Here document object is initialized with the Pdf
		 * 
		 * @param filePath is the pdf location path SavingFilePath is the path where the
		 * document is saved
		 * 
		 */
		Document doc = new Document(filePath);
		// Save resultant DOC file
		doc.save(savingFilePath, SaveFormat.Doc);
		return true;

	}

}
