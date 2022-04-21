package edu.wright.airviewer2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

	/**
	 * @author Akhil Sabbella
	 *
	 */
	public class TestImageAnnotationDialog {

		//Here object will be initialized
		static ImageAnnotationDialog  pObj = null;
		Stage s=null;
		/**
		 * @throws Exception
		 */
		@BeforeAll
		public static void setUp() throws Exception {
			//Here pdf file path will be given to test the file
			pObj = new ImageAnnotationDialog(s,"Image test","3","5");
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

				pObj.doSomething();
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
