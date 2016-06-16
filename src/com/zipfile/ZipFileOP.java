package com.zipfile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipFileOP {
	
	public static String writeZipFile(String docxFile) throws Exception{
		String docxname = docxFile.substring(0, docxFile.length()-5);
		String zipFile = docxname+".zip";
		FileInputStream fis = new FileInputStream(docxFile);
		FileOutputStream fos = new FileOutputStream(zipFile);
		int temp;
		while((temp = fis.read())!=-1){
			fos.write(temp);
		}
		fis.close();
		fos.close();
		//System.out.println("从 "+docxFile+" 到 "+zipFile);
		return zipFile;
	}
	
	/**
	 * 读取zip中的文件
	 * @param file
	 * @throws Exception
	 */
	public static void readZipFile(String file) throws Exception{
		ZipFile zf = new ZipFile(file);
		InputStream in =new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while((ze = zin.getNextEntry()) != null ){
//			if(ze.isDirectory()){
//				
//			}else{
				System.err.println(ze.getName() + ":"+ze.getSize() + "bytes");
//				long size = ze.getSize();
//				if(size>0){
//					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
//					String line;
//					while((line = br.readLine())!=null){
//						System.out.println(line);
//					}
//					br.close();
//				}
//				System.out.println();
//			}
		}
		zin.closeEntry();
	}
	
	/**
	 * 返回document.xml文件的内容
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getDocumentFile(String file) throws Exception{
		String content = "";
		ZipFile zf = new ZipFile(file);
		InputStream in =new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while((ze = zin.getNextEntry()) != null ){
			//System.out.println(ze.getName() + ":"+ze.getSize() + "bytes");
			long size = ze.getSize();
			if(size>0 && ze.getName().equals("word/document.xml")){
				BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
				String line;
				while((line = br.readLine())!=null){
					content+=line;
				}
				br.close();
			}
		}
		zin.closeEntry();
		return content;
	}
	
}
