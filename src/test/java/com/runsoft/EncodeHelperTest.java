package com.runsoft; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.Base64;

import static com.runsoft.EncodeHelper.*;

/** 
* EncodeHelper Tester. 
* 
* @author <Authors name> 
* @since <pre>12/23/2019</pre> 
* @version 1.0 
*/ 
public class EncodeHelperTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: EncodeBase64(byte[] nData) 
* 
*/ 
@Test
public void testEncodeBase64NData() throws Exception { 
//TODO: Test goes here...
	String base = EncodeBase64("中文测试", "gb2312");
	System.out.println("Encodebase64: " + base);
	System.out.println("Decodebase64: " + DecodeBase64(base, "gb2312"));
} 

/** 
* 
* Method: EncodeBase64(String nStr, String charsetname) 
* 
*/ 
@Test
public void testEncodeBase64ForNStrCharsetname() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: EncodeBase64(String nStr) 
* 
*/ 
@Test
public void testEncodeBase64NStr() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: DecodeBase64(byte[] nData) 
* 
*/ 
@Test
public void testDecodeBase64NData() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: DecodeBase64(String nStr, String charsetname) 
* 
*/ 
@Test
public void testDecodeBase64ForNStrCharsetname() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: DecodeBase64(String nStr) 
* 
*/ 
@Test
public void testDecodeBase64NStr() throws Exception { 
//TODO: Test goes here... 
} 

/**
*
* Method: EncodeMD5(byte[] nData)
*
*/
@Test
public void testEncodeMD5() throws Exception {
//TODO: Test goes here...
	String md5 = EncodeMD5("MD5信息摘要", DefaultCharsetName);
	System.out.println("MD5: " + md5);
}

} 
