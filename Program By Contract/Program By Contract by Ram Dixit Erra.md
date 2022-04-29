
## Contract for Programming:


--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 1. Class RotateViewerModel

``
/*
*@Invariant("filePath!= null && filePath.length() > 0")
*/
public class RotateViewerModel {
String filePath;

/**
* @param args
* @throws IOException
* @throws DocumentException
*/
public static void main(String[] args) throws IOException, PdfException {
}
/**
* @throws Exception
* @throws NullPointerException
*/

    
 /* The below method rotates the pdf in all direction(90,180,270,360) this is NOT landscape or potrait mode this functionality
 * rotates the pdf in all the required directions irrespective of rotating the monitor 
 * File created date 04/10/2022 no changes made after this date
 * if any changes made to it please update the date and document the reason for the upgrade
*@pre("filePath != null && filePath.length() > 0")
*@post("result == true")
*/
public boolean rotatefn(String filePath) throws Exception, NullPointerException {

-------------------------------------
--------------------------------
--------------------------------

}
``
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

@ Invariant: The condition “filepath != null” and “filepath.length() > 0” must be true of a class whenever it is accessible to the client.

@pre-condition: 
1.	The condition “filepath != null” and “filepath.length() > 0” must be true before the constructor method can execute.
@post-condition: The condition "result == true" must be true when rotatefn()  method finished executing.It indicates that able to rotate pdf successfully.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 2.	Class AddPageInPDF

``
@Invariant("filePath!= null && filePath.length() > 0")
public class AddPageInPDF {
        /*
	 * The below method adds an extra empty page to the pdf so that user can edit and add what ever he needs
	 * File created date 04/12/2022 no changes made after this date
	 * if any changes made to it please update the date and document the reason for the upgrade 
	 */
@Invariant("filePath!= null && filePath.length() > 0")
	public AddPageInPDF() {
		// TODO Auto-generated constructor stub
	}
/**
* @param args
* @throws IOException
* @throws DocumentException
*/
public static void main(String[] args) throws IOException, PdfException {
}
/**@pre("filePath != null && filePath.length() > 0")
   @post("result == true")
**/
	protected boolean addPagePdf(String filePath) throws Exception {
 		  
		  -------------------------------------
		  -------------------------------------
		  -------------------------------------
	}

}
``
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
@ Invariant: The condition “filepath != null” and “filepath.length() > 0” must be true of a class whenever it is accessible to the client.

@pre-condition: 
1.	The condition “filepath != null” and “filepath.length() > 0” must be true before the constructor method can execute.

@post-condition: The condition "result == true" must be true when addPagePdf(String filePath) method finished executing.It indicates that pages got added to the pdf.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3.	Class RemovePageInPDF

``
/*
*@Invariant("fileName!= null && fileName.length() > 0")
*/
public class RemovePageInPDF {

	/*
	 * The below method removes page from the pdf so that user can edit pdf
	 * File created date 04/10/2022 no changes made after this date
	 * if any changes made to it please update the date and document the reason for the upgrade
	 */
	public RemovePageInPDF() {
		// TODO Auto-generated constructor stub
	}
	/**
* @param args
* @throws IOException
* @throws DocumentException
*/
public static void main(String[] args) throws IOException, PdfException {
}
/*
*@pre("filePath != null && filePath.length() > 0")
*@post("result == true")
	
*/
	 protected boolean removePagePdf(String fileName) throws Exception {
	   	  -----------------------------------
		  -----------------------------------
		  -----------------------------------
		 
	}
	 
}
``
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
@ Invariant: The condition “filepath != null” and “filepath.length() > 0” must be true of a class whenever it is accessible to the client.

@pre-condition: 
1.	The condition “filepath != null” and “filepath.length() > 0” must be true before the constructor method can execute.

@post-condition: The condition "result == true" must be true when removePagePdf(String fileName)  method finished executing.It means that pages got removed from pdf depending on requirement

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 4.	Class EncryptPDF

``
@Invariant("fileName!= null && fileName.length() > 0")
public class EncryptPDF {

	public EncryptPDF() {
		// TODO Auto-generated constructor stub
		
	}
/**
* @param args
* @throws IOException
* @throws DocumentException
*/
public static void main(String[] args) throws IOException, PdfException {
}
	
	/**
	 * @param fileName
	 * @throws Exception
	 */
	
	/*
	 * The below method file encrypts the pdf and keeps them secure with a password
	 * File created date 04/16/2022 no changes made after this date
	 * any changes made to it please update the date and document the reason for the upgrade
	
	*@pre("fileName != null && fileName.length() > 0")
	*@post("result == true")
	*/
	public boolean encryptPdf(String fileName) throws Exception {
   	--------------------------
	--------------------------
	--------------------------
   }
}
``
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
@ Invariant: The condition “filepath != null” and “filepath.length() > 0” must be true of a class to ensure there is a pdf

@pre-condition: 
1.	The condition “filepath != null” and “filepath.length() > 0” must be true before the constructor method can execute.
@post-condition: 
1.The condition "result == true" must be true when encryptPdf(String fileName) method finished executing.It means pdf is encrypted.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
