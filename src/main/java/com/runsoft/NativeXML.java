package com.runsoft;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;

public class NativeXML extends LibraryBase {
    private String fileName = "";
    //已加载文件名
    private Document xmlDocument = null;
    //文档对象

    /**
     * 初始化对象时可直接创建文档对象
     * @param fileOrRoot 待解析文件名;或根节点名
     * @param newDocument 是否新建文档
     * @throws DocumentException
     */
    public NativeXML(String fileOrRoot,boolean newDocument) throws DocumentException {
        if (newDocument){
            xmlDocument = DocumentHelper.createDocument();
            xmlDocument.setXMLEncoding("UTF-8");
            xmlDocument.addElement(fileOrRoot);
        } else{
            if (fileOrRoot.equals("")){
                //do nothing
            }else {
                fileName = fileOrRoot;
                loadFromFile(fileOrRoot);
            }
        }
    }

    public  Document getDocument(){
        return xmlDocument;
    }

    /**
     * 从文件中读取
     * @param filename 待解析文件名
     * @return 文档对象
     * @throws DocumentException
     */
    public Document loadFromFile(String filename) throws DocumentException {
        SAXReader reader = new SAXReader();
        xmlDocument  = reader.read(filename);

        this.fileName = filename;
        return xmlDocument;
    }

    /**
     * 解析XML字符串
     * @param text 待解析字符串
     * @return 文档对象
     * @throws DocumentException
     */
    public Document loadFromText(String text) throws DocumentException {
        xmlDocument = DocumentHelper.parseText(text);
        return xmlDocument;
    }

    /**
     * 将数据写入文件
     * @param filename 待写入文件名
     * @param pretty 启用可读格式
     * @throws IOException
     */
    public void saveToFile(String filename, boolean pretty) throws IOException {
        OutputFormat xFormat;
        if (pretty) {
            xFormat = OutputFormat.createPrettyPrint();
        } else {
            xFormat = OutputFormat.createCompactFormat();
        }

        if (filename.equals("")) filename = this.fileName;
        FileWriter fileWriter = new FileWriter(filename);
        XMLWriter writer = new XMLWriter(fileWriter, xFormat);

        writer.write(xmlDocument);
        writer.close();
    }

    public String getXML(){
        return xmlDocument.asXML();
    }

    /**
     * 获取指定节点
     * @param nParent 父节点
     * @param nName 节点名称
     * @return 返回nParent下名称为nName的节点
     */
    public Element NodeByName(Element nParent, String nName){
        return nParent.element(nName);
    }

    /**
     * 获取指定节点
     * @param nParent 父节点
     * @param nName 节点名称
     * @return 返回nParent下名称为nName的节点,不存在则异常
     * @throws Exception
     */
    public Element NodeByNameR(Element nParent, String nName) throws Exception {
        Element nNode = NodeByName(nParent, nName);
        //get node

        if (nNode == null ){
            throw new Exception(String.format("node (%s.%s) isn't exists", nParent.getName(), nName));
        } else{
            return nNode;
        }
    }
}
