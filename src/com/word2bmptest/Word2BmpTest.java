package com.word2bmptest;

import java.io.IOException;

import com.pdf2bmp.Pdf2Bmp;
import com.word2pdf.Word2Pdf;

public class Word2BmpTest {
	public static void main(String[] args) throws IOException{
		String inputFile = "D:\\word3.docx";
		String outputFile = "D:\\wordtobmp\\tmp.pdf";
		String getPdfFilePath = "D:\\wordtobmp";
		Word2Pdf.word2Pdf(inputFile, outputFile);
		Pdf2Bmp.pdf2Bmp(outputFile, getPdfFilePath);
	}
}
