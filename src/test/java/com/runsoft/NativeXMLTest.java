package com.runsoft; 

import org.dom4j.Element;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* NativeXML Tester. 
* 
* @author <Authors name> 
* @since <pre>12/04/2019</pre> 
* @version 1.0 
*/ 
public class NativeXMLTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDocument() 
* 
*/ 
@Test
public void testGetDocument() throws Exception { 
//TODO: Test goes here...
    NativeXML nXML = new NativeXML("People", true);
    Element nRoot = nXML.getDocument().getRootElement();
    nRoot.addElement("dmzn").addAttribute("name", "aa");

    nXML.NodeByNameR(nRoot, "dmzn").setText("done");
    System.out.println(nXML.getXML());
} 

/** 
* 
* Method: loadFromFile(String filename) 
* 
*/ 
@Test
public void testLoadFromFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: loadFromText(String text) 
* 
*/ 
@Test
public void testLoadFromText() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveToFile(String filename, boolean pretty) 
* 
*/ 
@Test
public void testSaveToFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getXML() 
* 
*/ 
@Test
public void testGetXML() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: NodeByName(Element nParent, String nName) 
* 
*/ 
@Test
public void testNodeByName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: NodeByNameR(Element nParent, String nName) 
* 
*/ 
@Test
public void testNodeByNameR() throws Exception { 
//TODO: Test goes here... 
} 


} 
