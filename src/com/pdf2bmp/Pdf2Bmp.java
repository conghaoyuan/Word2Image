package com.pdf2bmp;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Pdf2Bmp {
	public static void pdf2Bmp(String pdfFile ,String getPdfFilePath) throws IOException{
		System.out.println(pdfFile);
		//add pdf file
		//File file = new File("C:\\Users\\Administrator\\AppData\\Local\\Temp"+pdfFile);
		File file = new File(pdfFile);
		//open with read only
		RandomAccessFile raf = new RandomAccessFile(file , "r");
		
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);
		System.out.println("page numbers:" + pdffile.getNumPages());
		//String getPdfFilePath = System.getProperty("user.dir")+"pdfPicFile";
		//String getPdfFilePath = "C:\\Users\\Administrator\\AppData\\Local\\Temp";
		try{
			for(int i = 1;i<=pdffile.getNumPages();i++){
				PDFPage page = pdffile.getPage(i);
				if(page == null){
					//System.out.println(i);
				}
				
				Rectangle rect = new Rectangle(
							0,
							0,
							(int)page.getBBox().getWidth(),
							(int)page.getBBox().getHeight()
						);
				int n = 2;   //pdf enlarge parameter  0-16
				Image img = page.getImage(
							rect.width*n, 
							rect.height*n, 
							rect, 
							null,
							true,
							true
						);
				BufferedImage tag = new BufferedImage(
							rect.width*n,
							rect.height*n,
							BufferedImage.TYPE_INT_BGR
						);
				tag.getGraphics().drawImage(img, 0, 0, rect.width*n, rect.height*n, null);
				
				FileOutputStream out = new FileOutputStream(getPdfFilePath+ "\\" + i + ".bmp");
				System.out.println("save as:"+ getPdfFilePath + "\\" + i + ".bmp");
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
				
				//1f --0.01 image quality
				JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
				param.setQuality(1f, true);
				encoder.setJPEGEncodeParam(param);
	            encoder.encode(tag); // jpeg encoding
	            out.close();
	            raf.close();
			}
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Error====="+e.getMessage());
		}	
	}
}
