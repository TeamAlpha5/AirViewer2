package edu.wright.airviewer2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
/**
 * @author RahulSatla
 *
 */
public class TestJPEG {
	//Here object will be initialized
	static JPEG jpegObj = null;
	/**
	 * @throws Exception
	 */
	@BeforeAll
	public static void setUp() throws Exception {
		//Here pdf file path will be given to test the file
		jpegObj = new JPEG("C:/Users/wsucatslabs/Desktop/Amazon/resume.pdf");
	}
	/**
	 * @throws Exception
	 */
	@AfterAll
	public static void tearDown() throws Exception {
		//Here object will be initialized to null
		jpegObj = null;
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
			jpegObj.jpeg();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}