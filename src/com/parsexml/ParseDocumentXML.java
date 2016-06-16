package com.parsexml;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseDocumentXML {
	/**
	 * get word margin by parse document.xml content
	 * @param content	document.xml content
	 * @return		ArrayList of word margin include topmargin bottommargin leftmargin rightmargin
	 * @throws Exception
	 */
	public static ArrayList getWordMargin(String content) throws Exception{
		ArrayList wordMargin = new ArrayList();
		//int[] wordMargin = new int[4];
		System.out.println(content);
		//create SAXReader object
		SAXReader reader = new SAXReader();
		//read xml  content or file , convert it to Document
		Document document = reader.read(new ByteArrayInputStream(content.getBytes("UTF-8")));
		//get root element object
		Element root = document.getRootElement();
		//loop through and find the element of margin
		Element body = root.element("body");
		Element sectPr = body.element("sectPr");
		
		Element pgSz = sectPr.element("pgSz");
		Element pgMar = sectPr.element("pgMar");
		
		Attribute width = pgSz.attribute("w");
		Attribute height = pgSz.attribute("h");
		Attribute topMargin = pgMar.attribute("top");
		Attribute bottomMargin = pgMar.attribute("bottom");
		Attribute leftMargin = pgMar.attribute("left");
		Attribute rightMargin = pgMar.attribute("right");
		/*
		wordMargin[0] = Integer.parseInt(topMargin.getValue());
		wordMargin[1] = Integer.parseInt(bottomMargin.getValue());
		wordMargin[2] = Integer.parseInt(leftMargin.getValue());
		wordMargin[3] = Integer.parseInt(rightMargin.getValue());*/
		wordMargin.add(width);
		wordMargin.add(height);
		wordMargin.add(topMargin);
		wordMargin.add(bottomMargin);
		wordMargin.add(leftMargin);
		wordMargin.add(rightMargin);
		//System.out.println(wordMargin.toString());
		return wordMargin;
	} 
	
	
}
