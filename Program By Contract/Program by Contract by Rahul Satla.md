## Program By Contract:

Programming by contract also defines criteria for correctness for a software module: If the class invariant AND precondition are true before a supplier is called by a client, then the invariant AND the postcondition will be true after the service has been completed.


## 1) JPEG.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class JPEG {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 *  
 */	/*
	@pre ("filePath != null && filePath.length() > 0")
	*/
	public JPEG(String filePath) {
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
	/*
	@pre("filePath != null && filePath.length() > 0")
	@post("result == true")
	*/
	public boolean jpeg() throws Exception, NullPointerException
    {
		
/*
 * Here document is initialized and with functions conversion of pdf to JPEG takes place
 */
    }
    }
```	

**Invariant:**    
- In this code, we can see the statement " @Invariant("filePath!= null && filePath.length() > 0") ". Here the file path should not be null and the file path length should be greater than zero. This condition must be true

**Pre-condition:**
- We can see in this code that the condition file path should not be null. Before the class constructor method may be executed, this condition must be met.
- We can see in this code that the file path length must be bigger than zero. This requirement must be met before the class constructor method and the jpeg() function can be executed. 

**Post-condition:**
- We can notice the condition "result==true" in this code. At the end of the jpeg() method's execution, this must equal "true."

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


## 2) PNG.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class PNG {	
	String filePath;		
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath 
 */

	//@pre ("filePath != null && filePath.length() > 0")
	public PNG(String filePath) {
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	// @pre ("filePath != null && filePath.length() > 0")
	//@post ("result == true")
	public boolean png() throws Exception, NullPointerException
    {
		
/*
 * Here document is initialized and with functions conversion of pdf to PNG takes place
 * 
 * 
 * 
 * 
 */
    }
	
}
```

**Invariant:**    
- The statement " @Invariant("filePath!= null && filePath.length() > 0") " can be found in this code. The file path should not be null in this case, and its length should be greater than zero. This condition has to be met.

**Pre-condition:**
- In this code we can see the condition file path should not be null. This condition must be true before the class constructor method can execute
- In this code we can see the condition file path length should be greater than zero. This condition must be true before the execution of class constructor method and png() method 

**Post-condition:**
- In this code we can see the condition "result==true". This must be equals to "true" at the end of the execution of png() method.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 3) HtmlConversion.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class HtmlConversion {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	//@pre ("filePath != null && filePath.length() > 0")
	public HtmlConversion(String filePath) {
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	//@pre ("filePath != null && filePath.length() > 0")
	//@post ("result == true")
	@SuppressWarnings("unused")
	public boolean htmlConversion() throws Exception, NullPointerException
    {
		
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * Here conversion of PDF to HTML takes place
		 * 
		 */
	}
	
}
```

**Invariant:**    
- In this code, we can see the statement " @Invariant("filePath!= null && filePath.length() > 0") ". Here the file path should not be null and the file path length should be greater than zero. This condition must be true

**Pre-condition:**
- We can see in this code that the condition file path should not be null. Before the class constructor method may be executed, this condition must be met.
- We can see in this code that the file path length must be bigger than zero. This requirement must be met before the class constructor method and the htmlConversion() function can be executed.

**Post-condition:**
- We can notice the condition "result==true" in this code. At the end of the htmlConversion() method's execution, this must equal "true."



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


## 4) DocConversion.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class DocConversion {	
	String filePath;
	String savingFilePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	//@pre ("filePath != null && filePath.length() > 0")
	public DocConversion(String filePath) {
		super();
		this.filePath = filePath;
		savingFilePath=filePath.replace("pdf", "doc");
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	//@pre("filePath != null && filePath.length() > 0")
	//@post("result == true")
	@SuppressWarnings("unused")
	public boolean docConversion() throws Exception, NullPointerException
    {
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * SavingFilePath is the path where the document is saved
		 * 
		 */
	}	
}
```

**Invariant:**    
- The statement " @Invariant("filePath!= null && filePath.length() > 0") " can be found in this code. The file path should not be null in this case, and its length should be greater than zero. This condition has to be met.

**Pre-condition:**
- In this code we can see the condition file path should not be null. This condition must be true before the class constructor method can execute
- In this code we can see the condition file path length should be greater than zero. This condition must be true before the execution of class constructor method and docConversion() method 

**Post-condition:**
- In this code we can see the condition "result==true". This must be equals to "true" at the end of the execution of docConversion() method.


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


## 5) TextConversion.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class TextConversion {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	// @pre ("filePath != null && filePath.length() > 0")
	public TextConversion(String filePath) {
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	//@pre ("filePath != null && filePath.length() > 0")
	//@post ("result == true")
	@SuppressWarnings("unused")
	public boolean textConversion() throws Exception, NullPointerException
    	{
		
	/*
	 *Here document object is initialized with the Pdf
	 * @param filePath is the pdf location path
	 * Here conversion of PDF to Text takes place
	 * 
	 * 
	 */
		
	}
	
}
```

**Invariant:**    
- In this code, we can see the statement " @Invariant("filePath!= null && filePath.length() > 0") ". Here the file path should not be null and the file path length should be greater than zero. This condition must be true

**Pre-condition:**
- We can see in this code that the condition file path should not be null. Before the class constructor method may be executed, this condition must be met.
- We can see in this code that the file path length must be bigger than zero. This requirement must be met before the class constructor method and the textConversion() function can be executed.

**Post-condition:**
- We can notice the condition "result==true" in this code. At the end of the textConversion() method's execution, this must equal "true."



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


## 6) Watermarkaddition.java

```
/**
 * @author Rahul Satla
 *
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class Watermarkaddition {	
	String filePath;
	
/*
 * This is a constructor where it takes path of the pdf as a parameter
 * @param filePath
 * 
 * 
 */
	// @pre ("filePath != null && filePath.length() > 0")
	public Watermarkaddition(String filePath) {
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	//@pre ("filePath != null && filePath.length() > 0")
	//@post ("result == true")
	@SuppressWarnings("unused")
	public boolean watermarkAdd() throws Exception, NullPointerException
	{
		
		/*
		 *Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 * Here addition of watermark to the PDF takes place
		 * 
		 * 
		 */
		
	}
}
```
**Invariant:**    
- The statement " @Invariant("filePath!= null && filePath.length() > 0") " can be found in this code. The file path should not be null in this case, and its length should be greater than zero. This condition has to be met.

**Pre-condition:**
- In this code we can see the condition file path should not be null. This condition must be true before the class constructor method can execute
- In this code we can see the condition file path length should be greater than zero. This condition must be true before the execution of class constructor method and watermarkAdd() method 

**Post-condition:**
- In this code we can see the condition "result==true". This must be equals to "true" at the end of the execution of watermarkAdd() method.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 7) PdfStamp.java

```
/**
 * @author Rahul Satla
 *
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
	//@pre ("filePath != null && filePath.length() > 0")
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
	 * @throws Exception
	 * @throws NullPointerException
	 */
	// @pre ("filePath != null && filePath.length() > 0")
	// @post ("result == true")
	@SuppressWarnings("unused")
	public boolean addingStamp() throws Exception, NullPointerException
    {
		/*
		 * Here document object is initialized with the Pdf
		 * @param filePath is the pdf location path
		 *
		 * 
		 */
	

	
}
}
```

**Invariant:**    
- In this code, we can see the statement " @Invariant("filePath!= null && filePath.length() > 0") ". Here the file path should not be null and the file path length should be greater than zero. This condition must be true

**Pre-condition:**
- We can see in this code that the condition file path should not be null. Before the class constructor method may be executed, this condition must be met.
- We can see in this code that the file path length must be bigger than zero. This requirement must be met before the class constructor method and the addingStamp() function can be executed.

**Post-condition:**
- We can notice the condition "result==true" in this code. At the end of the addingStamp() method's execution, this must equal "true."

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




