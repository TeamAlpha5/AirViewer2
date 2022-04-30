## Contract for programming:
## 1. Class BoxAnnotationMaker 
```
/**
*@invariant("contents!= null && contents.length()> 0")
*/
public class BoxAnnotationMaker {
 int pageNumber = parseInt(arguments.get(0));
            float lowerLeftX = parseFloat(arguments.get(1));
            float lowerLeftY = parseFloat(arguments.get(2));
            float width = parseFloat(arguments.get(3));
            float height = parseFloat(arguments.get(4));
            String contents = "";
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 16; // Or whatever font size you want.
            float textWidth = font.getStringWidth(contents) * fontSize / 1000.0f;
            float textHeight = 32;
/**
*@pre ("contents != null && contents.length() > 0")
*/
    public static List<PDAnnotation> make(PDDocument document,
            ArrayList<String> arguments) {
        
    }
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:**
- The condition “contents!= null” and “contents.length > 0” must be true of a class whenever it is accessible to the client.

**@pre-condition:** 
1.	The condition “contents != null” and “contents.length > 0” must be true before the constructor method can execute.
2.	The condition "contents.length >= 1 " must be true before adding the Box annotation to the pdf.

**@post-condition:**
- The condition "result == true" must be true, after adding Box annotation to the pdf.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 2. Class EllipseAnnotationMaker 

```
/**
*@invariant("contents!= null && contents.length()> 0")
*/
public class EclipseAnnotationMaker {
int pageNumber = parseInt(arguments.get(0));
            float lowerLeftX = parseFloat(arguments.get(1));
            float lowerLeftY = parseFloat(arguments.get(2));
            float width = parseFloat(arguments.get(3));
            float height = parseFloat(arguments.get(4));
            String contents = arguments.get(5);

            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            final float fontSize = 16.0f; // Or whatever font size you want.
            final float lineSpacing = 4.0f;
            float textWidth = font.getStringWidth(contents) * fontSize / 1000.0f;
            final float textHeight = fontSize + lineSpacing;
/**
*@pre ("contents != null && contents.length() > 0")
*/
    public static List<PDAnnotation> make(PDDocument document,
            ArrayList<String> arguments) {
        
    }
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:**
- The condition “contents!= null” and “contents.length > 0” must be true of a class whenever it is accessible to the client.

**@pre-condition:** 
1.	The condition “contents != null” and “contents.length > 0” must be true before the constructor method can execute.
2.	The condition "contents.length >= 1 " must be true before adding an Eclipse annotation to the pdf.

**@post-condition:**
- The condition "result == true" must be true, after adding Eclipse annotation to the pdf.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3. Class TextAnnotationMaker 
```
/**
*@invariant("contents!= null && contents.length()> 0")
*/
public class TextAnnotationMaker {
int pageNumber = parseInt(arguments.get(0));
            float lowerLeftX = parseFloat(arguments.get(1));
            float lowerLeftY = parseFloat(arguments.get(2));
            float width = parseFloat(arguments.get(3));
            float height = parseFloat(arguments.get(4));
            String contents = arguments.get(5);
            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            final float fontSize = 16.0f; // Or whatever font size you want.
            final float lineSpacing = 4.0f;
            width = max(width, font.getStringWidth(contents) * fontSize / 1000.0f);
            final float textHeight = fontSize + lineSpacing;
/**
*@pre ("contents != null && contents.length() > 0")
*/
    public static List<PDAnnotation> make(PDDocument document,
            ArrayList<String> arguments) {
        
    }
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:**
- The condition “contents!= null” and “contents.length > 0” must be true of a class whenever it is accessible to the client.

**@pre-condition:** 
1.	The condition “contents != null” and “contents.length > 0” must be true before the constructor method can execute.
2.	The condition "contents.length >= 1 " must be true before adding the Text annotation to the pdf.

**@post-condition:** 
- The condition "result == true" must be true, after adding the Text annotation to the pdf.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 4. Class PPTConversion 
```
/**
*@Invariant("filePath!= null && filePath.length() > 0")
*/
public class PPTConversion {	
	String filePath;

/**
*@pre("filePath != null && filePath.length() > 0")
*/
	public PPTConversion(String filePath) {
		this.filePath = filePath;
	}
    
/**
*@pre("filePath != null && filePath.length() > 0")
*/
   public boolean pptConversion() throws Exception, NullPointerException
    {
		
	}

}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

**@ Invariant:**
- The condition “filePath!= null” and “filePath.length > 0” must be true of a class whenever it is accessible to the client.

**@pre-condition:** 
1.	The condition “filePath != null” and “filePath.length > 0” must be true before the constructor method can execute.
2.	The condition "filePath.length >= 1 " must be true before converting pdf to ppt.

**@post-condition:**
- The condition "result == true" must be true, after converting it to ppt.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

