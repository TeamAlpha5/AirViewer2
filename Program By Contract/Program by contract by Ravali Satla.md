## Contract for Programming:


## 1.Class MergePdf 

```
/**
*@invariant ("filepath != null && filepath.length() > 0")
*/
class MergePdf  {
String filePath;
/**
*@pre ("filepath  != null && filepath .length() > 0")
*/
public MergePdf(String filePath) {
this.filePath = filePath;
}
/**
*@pre ("file2!=null&&file2.length >= 0 ")
*@post ("result == true")
*/
public boolean mergepdf() throws IOException{
      -----
      /* here we have a code that is used for merging of two pdfs into single one*/
      -----
      -----
      -----
}
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
**@ Invariant:**
- The condition “filepath != null” and “filepath.length() > 0” must be true of a class .
**@pre-condition:** 
1.The condition “file1 != null” and “file1.length() > 0” must be true to ensure there is a pdf.
2.The condition “file2 != null” and “file2.length() > 0” must be true to ensure there is a pdf .
**@post-condition:**
- The condition "result == true" must be true when mergepdf()  method finished executing.It indicates that two pdfs are merged successfully.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 2.Class SplitPdf 

```
/**
*@invariant ("noofpages != null && noofpages.length() > 0")
*/
public class SplitPdf {
String filePath;
/**
*@pre ("noofpages!= null && noofpages.length() > 0&&filepath != null && filepath.length() > 0")
*/
public SplitPdf(String filePath) {
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
*@pre ("noofpages!=null&&noofpages.length >= 0 ")
*@post ("result == true")
*/
public boolean splitpdf() throws Exception, NullPointerException
{
----------------------
/*here it contains the code for splitting the pdf into individual documents.*/
-----------------
}}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
**@ Invariant:**
- The condition “noofpages != null” and “noofpages.length() > 0,filepath != null and filepath.length() > 0” and must be true of a class .

**@pre-condition:** 
1.	The condition “noofpages != null” and “noofpages.length() > 0” must be true before the constructor method can execute.
2.	The condition "filepath.length>0 " must be true before the splitpdf() method can execute.

**@post-condition:** 
- The condition "result == true" must be true when splitpdf()  method finished executing.It indicates that the pdf is split into pages and saved into the individual ones.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3.class PdfPassword
```
/**
 * @invariant ("password!= null && password.length() > 0")
 */
public class PdfPassword {
	
	String filePath;
	@pre ("pwd.length>0")
/**
*@pre ("password!= null && password.length() > 0")
*/
	public PdfPassword(String filePath) {
		super();
		this.filePath = filePath;

	}
 /*	@pre ("password!= null && password.length() > 0")
	@post ("result == true")
	*/
	public boolean pdfpass(String password) throws Exception, NullPointerException{
	
	-----------------------
	/* contains the functionality for creating password to the pdf*/
	
	-----------------------
	
	}
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
**@ Invariant:**
- The condition “password != null” and “password.length() > 0” must be true of a class.

**@pre-condition:**
1.	The condition “password != null” and “password.length() > 0”  must be true before the constructor method can execute.
2.	The condition "filepath  != null && filepath .length() > 0" must be true
**@post-condition: **
-The condition "result == true" must be true when pdfpass(String password)  method finished executing.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 4.	class HeaderAddition
```
/**
 * @invariant ("filePath!= null && filePath.length() > 0")
 */
public class HeaderAddition {
	String filePath;
	
/**
*@pre ("filePath!= null && filePath.length() > 0")
*/
	public HeaderAddition(String filePath) {
		super();
		this.filePath = filePath;
	}
 /*	@pre ("filePath!= null && filePath.length() > 0&&header.length>0&&header!=null")
	@post ("result == true")
	*/
	public boolean headerAddition(String header) throws Exception, NullPointerException{
	
	------------------------
	/* it contains the code which is used to add header to the pdf.*/
	}
	
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
**@Invariant:**
-The condition "filePath!= null && filePath.length() > 0" must be true of a class .

**@pre-condition:** 
1.The condition "filePath!= null && filePath.length()" must be true before the constructor method can execute.
2.The condition "header != null”, “header.length() > 0” must be true before the headerAddition(String header) method can execute.

**@post-condition:**
-The condition "result == true" must be true when headerAddition(String header)  method finished executing.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 6.	class FooterAddition
```
/**
 * @invariant ("filePath!= null && filePath.length() > 0")
 */
public class FooterAddition {
	String filePath;
	
/**
*@pre ("filePath!= null && filePath.length() > 0")
*/
	public FooterAddition(String filePath) {
		super();
		this.filePath = filePath;
	}
 /*	@pre ("filePath!= null && filePath.length() > 0&&footer.length>0&&footer!=null")
	@post ("result == true")
	*/
	public boolean footerAddition(String footer) throws Exception, NullPointerException{
	
	------------------------
	/* it contains the code which is used to add footer to the pdf.*/
	}
	
}
```
----------------------------------------------------------------------------------------------------------------------------------------------------------------
**@ Invariant:**
-The condition "filePath!= null && filePath.length() > 0" must be true of a class .

**@pre-condition:** 
1.The condition "filePath!= null && filePath.length()" must be true before the constructor method can execute.
2.The condition "header != null”, “header.length() > 0” must be true before the footerAddition(String header) method can execute.

**@post-condition:** 
-The condition "result == true" must be true when footerAddition(String header)  method finished executing.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 6.	class PdfOptimzation
```
/**
 * @invariant ("filePath!= null && filePath.length() > 0")
 */
public class PdfOptimzation {
	String filePath;
	
/**
*@pre ("filePath!= null && filePath.length() > 0")
*/
	public PdfOptimzation(String filePath) {
		super();
		this.filePath = filePath;
	}
 /*	@pre ("filePath!= null && filePath.length() > 0&&footer.length>0&&footer!=null")
	@post ("result == true")
	*/
	public boolean pdfOptimization() throws Exception, NullPointerException{
	
	------------------------
	/* it contains the code which is used to optimize  the pdf.*/
	------------------------
	}
	
}
```
---------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------
**@ Invariant:**
-The condition “filepath != null” and “filepath.length() > 0” must be true of a class.
**@pre-condition:**
1.The condition "filepath  != null && filepath .length() > 0" must be true
**@post-condition:**
- The condition "result == true" must be true when   method finished executing.It indicates pdf is successfully optimized
-----------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------
