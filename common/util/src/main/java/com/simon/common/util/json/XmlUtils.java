package com.simon.common.util.json;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * XML文件操作工具类
 * 
 * @author ZhangPing
 *
 * @create 2017年6月11日
 * 
 */
public class XmlUtils {
	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static Document loadDocByFilename(String filename) {

		SAXReader reader = new SAXReader();
		try {
			File t_file = new File(filename);
			if (t_file.exists()) {
				Document t_doc = reader.read(t_file);
				return t_doc;
			}
		} catch (Exception e) {
			;
		}
		return null;
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Document loadDocByFilename(InputStream inputStream) {

		SAXReader reader = new SAXReader();
		try {
			Document t_doc = reader.read(inputStream);
			return t_doc;
		} catch (Exception e) {
			;
		}
		return null;
	}

	/**
	 * 加载一个XML文件
	 * 
	 * @param xml
	 * @return Document
	 */
	public static Document loadDocByFile(File xml) {
		if (xml == null) {
			return null;
		}
		SAXReader reader = new SAXReader();
		try {
			Document t_doc = reader.read(xml);
			return t_doc;
		} catch (Exception e) {
			;
		}
		return null;
	}

	/**
	 * 从xml文档对象中获取所有指定标签名的元素
	 * 
	 * @param tagName
	 * @param document
	 * @return List<Element>
	 */
	@Deprecated
	public static List<Element> getNodesByTagName(String tagName,
			Document document) {
		List<Element> t_list = new ArrayList<Element>();
		if (tagName == null || document == null) {
			return t_list;
		}
		if (tagName.indexOf("@") > 0) {
			return t_list;
		}
		@SuppressWarnings("unchecked")
        List<Node> nodes = document.selectNodes(tagName);
        if (nodes == null) {
			return t_list;
		}
//		return nodes;
        return null;
	}

	/**
	 * Find node by xpath.
	 *
	 * @param xpathStr
	 * @param document
	 * @return Element
	 */
	public static List<Node> getElementByXPath(String xpathStr,
											   Document document) {
		return document.selectNodes(xpathStr);
	}

	/**
	 * 从xml文档对象中获取第一个指定标签名的元素
	 * 
	 * @param tagName
	 * @param document
	 * @return Element
	 */
	@SuppressWarnings("rawtypes")
	public static Element getFirstElementByTagName(String tagName,
			Document document) {
		if (tagName == null || document == null) {
			return null;
		}
		List t_elements = document.selectNodes("//" + tagName);
		if (t_elements != null && t_elements.size() > 0) {
			for (int i = 0; i < t_elements.size(); i++) {
				Element element = (Element) t_elements.get(i);
				if (element != null
						&& tagName.equalsIgnoreCase(element.getName())) {
					return element;
				}
			}
		}
		return null;
	}

	/**
	 * 获取指定元素的所有子元素
	 * 
	 * @param pareElement
	 * @return List<Element>
	 */
	@SuppressWarnings("rawtypes")
	public static List getChildElements(Element pareElement) {
		if (pareElement == null) {
			return null;
		}
		return pareElement.elements();
	}

	/**
	 * 获取指定元素的第一个指定标签名的子元素
	 * 
	 * @param pareElement
	 * @param tagName
	 * @return Element
	 */
	@SuppressWarnings("rawtypes")
	public static Element getFirstChildElement(Element pareElement,
			String tagName) {
		if (pareElement == null || tagName == null) {
			return null;
		}
		if (pareElement != null) {
			List t_elements = pareElement.elements();
			if (t_elements != null && t_elements.size() > 0) {
				for (int i = 0; i < t_elements.size(); i++) {
					Element element = (Element) t_elements.get(i);
					if (element != null
							&& tagName.equalsIgnoreCase(element.getName())) {
						return element;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取指定元素的所有指定标签名的子元素
	 * 
	 * @param pareElement
	 * @param tagName
	 * @return List<Element>
	 */
	@SuppressWarnings("rawtypes")
	public static List getChildElementsByTagName(Element pareElement,
			String tagName) {
		if (pareElement == null || tagName == null) {
			return null;
		}
		return pareElement.elements(tagName);
	}

	public static boolean writeXML(String file, Document m_doc) {
		org.dom4j.io.XMLWriter output = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			output = new org.dom4j.io.XMLWriter(
					new OutputStreamWriter(new FileOutputStream(file), "UTF8"),
					format);
			output.write(m_doc);
			
			return true;
		} catch (IOException t_e) {
			;
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}
}
