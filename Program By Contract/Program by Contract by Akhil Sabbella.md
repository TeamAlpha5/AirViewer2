# Program  by Contract:

## 1.	Class ImageAnnotationMaker

```
/**
*@invariant ("path!= null && pageNumber > 0")
*/
class ImageAnnotationMaker {
int pageNumber = parseInt(arguments.get(0));
float lowerLeftX = parseFloat(arguments.get(1));
float lowerLeftY = parseFloat(arguments.get(2));
float width = parseFloat(arguments.get(3));
float height = parseFloat(arguments.get(4));
String path = arguments. get(5)
/**
*@pre ("numberOfPages != null && numberOfPages.length() > 0")
*/
public  ImageAnnotationMaker(String path, String pagenumber){
     this. path = path;
     this. pagenumber = pagenumber;
     }
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:**
- The condition “path!= null” and “pageNumber > 0” must be true of a class whenever it is accessible to the client.

**@pre-condition:**
1.	The condition “path != null” and “pageNumber > 0” must be true before the constructor method can execute.
2.	The condition "pageNumber>= 1.0 " must be true before the adding image to the pdf.

**@post-condition:** 
- The condition "result == true" must be true, after adding an image to the pdf.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 2.Image Annotation Dialog
```
/**
*@invariant ("Imagefilepath!= null)
*/
class ImageAnnotationDialog {
int pageNumber = parseInt(arguments.get(0));
float lowerLeftX = parseFloat(arguments.get(1));
float lowerLeftY = parseFloat(arguments.get(2));
float width = parseFloat(arguments.get(3));
float height = parseFloat(arguments.get(4));
String path = arguments. get(5)
*/
public  ImageAnnotationdialog(String path, String pagenumber){
     this. Imagefilepath = Imagefilepath;
     }
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:** 
- The condition “Imagefilepath!= null” must be true of a class whenever it is accessible to the client.

**@pre-condition:**
1.	The condition “Imagefilepath != null” must be true before the constructor method can execute.

**@post-condition:** 
- The condition "result == true" must be true, after adding an image to the pdf.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3.BMP Conversion
~~~
/**
*@Invariant("filePath!= null && filePath.length() > 0")
*/
public class BMPConversion{	
	String filePath;
	
	@Requires("filePath != null && filePath.length() > 0")
	public BMPConversion(String filePath) {
		this.filePath = filePath;
	}
    
	public static void main(String[] args)
	        throws IOException, DocumentException
	    {
	    }
	@Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
	@SuppressWarnings("unused")
	public boolean bmpConversion() throws Exception, NullPointerException
    {
	 ----------------
	 ----------------
	 ----------------		
	}
	
}
~~~
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Invariant:**    
- In this code, we can see the statement " @Invariant("filePath!= null && filePath.length() > 0") ". It must be true, the file path lenght should be grater then zero and fail path should not be null.

**Pre-condition:**
- In this code we can see the condition file path should not be null. This condition must be true before the class constructor method can execute
- In this code we can see the condition file path length should be greater than zero. This condition must be true before the execution of class constructor method and bmpConversion() method

**Post-condition:**
- In this code we can see the condition "result==true". This must be equals to "true" at the end of the execution of bmpConversion() method.
