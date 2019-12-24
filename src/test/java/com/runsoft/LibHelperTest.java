package com.runsoft; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* LibHelper Tester. 
* 
* @author <Authors name> 
* @since <pre>12/23/2019</pre> 
* @version 1.0 
*/ 
public class LibHelperTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: ToHexStr(byte[] nData) 
* 
*/ 
@Test
public void testToHexStr() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: ToBytes(String nData, String charsetname) 
* 
*/ 
@Test
public void testToBytes() throws Exception { 
//TODO: Test goes here...
	String hex = LibraryHelper.ToHexString("你好,我是中文");
	System.out.println("ToHexStr: " + hex);
	System.out.println("HexToStr: " + LibraryHelper.RestoreHex(hex));

} 


} 
