package edu.wright.airviewer2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;



/**
 * @author DeepikaGadiraju
 *
 */
public class TextannotationTest {
	// Here object will be initialized
	static TextAnnotationMaker box = null;

	/**
	 * @throws Exception
	 */
	@BeforeAll
	public static void setUp() throws Exception {
		// Here pdf file path will be given to test the file
		box = new TextAnnotationMaker();
	}

	/**
	 * @throws Exception
	 */
	@AfterAll
	public static void tearDown() throws Exception {
		// Here object will be initialized to null
		box = null;
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
                                File f=new File("c:/Documents/test.pdf");
                                PDDocument docob=PDDocument.load(f);
				box.make(docob,{"1","150","50","150","150","Hello"});
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}