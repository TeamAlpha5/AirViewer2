package edu.wright.airviewer2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
	/**
	 * @author Akhil Sabbella
	 *
	 */
	public class TestImage {

		//Here object will be initialized
		static ImageAnnotationMaker  pObj = null;
		/**
		 * @throws Exception
		 */
		@BeforeAll
		public static void setUp() throws Exception {
			//Here pdf file path will be given to test the file
			pObj = new ImageAnnotationMaker("C:/Users/wsucatslabs/Desktop/Amazon/abc.pdf");
		}
		/**
		 * @throws Exception
		 */
		@AfterAll
		public static void tearDown() throws Exception {
			//Here object will be initialized to null
			pObj = null;
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

File f=new File("C:/Documents/test.pdf");
PDDocument docOb=PDDocument.load(f);
				pObj.make(docOb,{"1","150","50","150","150","C:/Pictures/a.jpeg"});
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
