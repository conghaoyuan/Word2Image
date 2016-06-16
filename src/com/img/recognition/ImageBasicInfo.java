package com.img.recognition;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageBasicInfo {
	public int[] getWordMarginByImg(String imgFile) throws Exception{
		int[] wordMarginInfo = new int[6];
		int[] rgb = new int[3];
		File file = new File(imgFile);
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		int n = 0;
		int mini =width,maxi =0,minj =height,maxj =0;
		for(int i = minx;i<width;i++){
			for(int j=miny;j<height;j++){
				int pixel = bi.getRGB(i, j);
				if(pixel != -1){
					if(i<mini){
						mini = i;   //get the min pix of img ,the left margin
					}
					if(i>maxi){
						maxi = i;   //get the max pix of img, can calculate the right margin
					}
					if(j<minj){
						minj = j;	//get the min pix of img ,the top margin
					}
					if(j>maxj){
						maxj = j;	//get the max pix of img, can calculate the bottom margin
					}
					
					rgb[0] = (pixel & 0xff0000) >> 16;
					rgb[1] = (pixel & 0xff00) >> 8;
					rgb[2] = (pixel & 0xff);
					
//					System.out.println("i=" + i + ",j=" + j +"="+ pixel+ ":(" + rgb[0] + ","  
//		                        + rgb[1] + "," + rgb[2] + ")"); 
					n++;
				}
				
			}
		}
		//System.out.println(maxj);
		int leftMargin = mini;
		int rightMargin = width - maxi;
		int topMargin = minj;
		int bottomMargin = height - maxj;
		wordMarginInfo[0] = width;
		wordMarginInfo[1] = height;
		wordMarginInfo[2] = topMargin;
		wordMarginInfo[3] = bottomMargin;
		wordMarginInfo[4] = leftMargin;
		wordMarginInfo[5] = rightMargin;
//		for(int i=0;i<wordMarginInfo.length;i++){
//			System.out.println(wordMarginInfo[i]);
//		}
		return wordMarginInfo;
	}
}
