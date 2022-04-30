package edu.wright.airviewer2;
import com.itextpdf.text.DocumentException;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.aspose.pdf.Document;
import com.aspose.pdf.ImageStamp;
import com.aspose.pdf.Rotation;
import com.google.java.contract.Ensures;
/**
 * @author Rahul Satla
 *
 */
/**
 * The class PdfStamp is used for adding stamp to the pdf with a constructor which takes a pdf file path.
 * It contains addingStamp() method which is used for the addition of stamp to the pdf.
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class PdfStamp {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	@Requires("filePath != null && filePath.length() > 0")
	public PdfStamp(String filePath) {
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
	 * This method is used for the addition of stamp to the pdf
	 * @pre(filepath.length()>0)
	 * @post(result==true)
	 * @throws Exception
	 * @throws NullPointerException
	 */
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean addingStamp() throws Exception, NullPointerException
    {
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 *
		 * 
		 */
		String logoPath="";
		Document pdfDocument = new Document(filePath);
		JFrame frame = new JFrame("Select stamp");  
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	    int result = fileChooser.showOpenDialog(frame);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        logoPath=selectedFile.getAbsolutePath();
	    }
	    //File file2=new File(selectedFile.getAbsolutePath());
		ImageStamp imageStamp = new ImageStamp(logoPath);
		imageStamp.setBackground(true);
		imageStamp.setXIndent(479);
		imageStamp.setYIndent(151);
		imageStamp.setHeight(100);
		imageStamp.setWidth(100);
		imageStamp.setRotate(90);
		imageStamp.setOpacity(0.5);
		// add stamp to only first page
		pdfDocument.getPages().get_Item(1).addStamp(imageStamp);
	pdfDocument.save(filePath+"-Time Stamp PDF.pdf");
		return true;
		
	}

	
}
