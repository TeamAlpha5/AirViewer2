package edu.wright.airviewer2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author Rahul Satla
 *
 */
public class TestPdfStamp {
	//Here object will be initialized
	static PdfStamp pdfObj = null;
	/**
	 * @throws Exception
	 */
	@BeforeAll
	public static void setUp() throws Exception {
		//Here pdf file path will be given to test the file
		pdfObj = new PdfStamp("C:/Users/wsucatslabs/Desktop/Amazon/resume.pdf");
	}
	/**
	 * @throws Exception
	 */
	@AfterAll
	public static void tearDown() throws Exception {
		//Here object will be initialized to null
		pdfObj = null;
	}
	/**
	 * testing method
	 */
	@org.junit.jupiter.api.Test
	public void test() {
		/*
		 Here the functionality will be tested and returns the test case results
		 */
		try{
			/*
			 * here a call to the method will take place
			 */
			assertEquals(true,pdfObj.addingStamp());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}