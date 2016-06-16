package com.test;

import java.util.ArrayList;

import org.dom4j.Attribute;

import com.img.recognition.ImageBasicInfo;
import com.parsexml.ParseDocumentXML;
import com.pdf2bmp.Pdf2Bmp;
import com.word2pdf.Word2Pdf;
import com.zipfile.ZipFileOP;

public class Test {
	public static void main(String[] args) throws Exception{
		String inputFile = "D:\\word3.docx";
		String outputFile = "D:\\wordtobmp\\tmp.pdf";
		String getPdfFilePath = "D:\\wordtobmp";
		Word2Pdf.word2Pdf(inputFile, outputFile);
		Pdf2Bmp.pdf2Bmp(outputFile, getPdfFilePath);
		try{
			//get the margin info of document.xml file
			String zipFile = ZipFileOP.writeZipFile(inputFile);
			String content = ZipFileOP.getDocumentFile(zipFile);
			ArrayList wordMargin = ParseDocumentXML.getWordMargin(content);
			Attribute width = (Attribute) wordMargin.get(0);
			Attribute height = (Attribute) wordMargin.get(1);
			Attribute topMargin = (Attribute) wordMargin.get(2);
			Attribute bottomMargin = (Attribute) wordMargin.get(3);
			Attribute leftMargin = (Attribute) wordMargin.get(4);
			Attribute rightMargin = (Attribute) wordMargin.get(5);
			int width_xml = Integer.parseInt(width.getValue());
			int height_xml = Integer.parseInt(height.getValue());
			int top_xml = Integer.parseInt(topMargin.getValue());
			int bottom_xml = Integer.parseInt(bottomMargin.getValue());
			int left_xml = Integer.parseInt(leftMargin.getValue());
			int right_xml = Integer.parseInt(rightMargin.getValue());
			//convert pix to the cm
			float width_xml_cm = 210;
			float height_xml_cm = 297;
			float top_xml_cm = width_xml_cm/width_xml*top_xml;
			float bottom_xml_cm = width_xml_cm/width_xml*bottom_xml;
			float left_xml_cm = width_xml_cm/width_xml*left_xml;
			float right_xml_cm = width_xml_cm/width_xml*right_xml;
			System.out.println("document.xml取出的页边距像素值为：");
			System.out.println("页面宽度： "+width_xml+" 页面高度： "+height_xml);
			System.out.println("上边距： "+top_xml+" 下边距： "+bottom_xml);
			System.out.println("左边距： "+left_xml+" 右边距： "+right_xml);
			System.out.println("像素值换算成cm为：");
			System.out.println("页面宽度： "+width_xml_cm+" 页面高度： "+height_xml_cm);
			System.out.println("上边距： "+top_xml_cm+" 下边距： "+bottom_xml_cm);
			System.out.println("左边距： "+left_xml_cm+" 右边距： "+right_xml_cm);
			System.out.println();
			//get the margin info of img
			String imgFile = "D:\\wordtobmp\\1.bmp";
			ImageBasicInfo imgBasicInfo = new ImageBasicInfo();
			int[] wordMarginInfo = imgBasicInfo.getWordMarginByImg(imgFile);
			int width_img = wordMarginInfo[0];
			int height_img = wordMarginInfo[1];
			int top_img = wordMarginInfo[2];
			int bottom_img = wordMarginInfo[3];
			int left_img = wordMarginInfo[4];
			int right_img = wordMarginInfo[5];
			float width_img_cm = 210;
			float height_img_cm = 297;
			float top_img_cm = width_img_cm/width_img*top_img;
			float bottom_img_cm = width_img_cm/width_img*bottom_img;
			float left_img_cm = width_img_cm/width_img*left_img;
			float right_img_cm = width_img_cm/width_img*right_img;
			System.out.println("转化后的图片取出的页边距像素值为：");
			System.out.println("页面宽度： "+width_img+" 页面高度： "+height_img);
			System.out.println("上边距： "+top_img+" 下边距： "+bottom_img);
			System.out.println("左边距： "+left_img+" 右边距： "+right_img);
			System.out.println("像素值换算成cm为：");
			System.out.println("页面宽度： "+width_img_cm+" 页面高度： "+height_img_cm);
			System.out.println("上边距： "+top_img_cm+" 下边距： "+bottom_img_cm);
			System.out.println("左边距： "+left_img_cm+" 右边距： "+right_img_cm);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
