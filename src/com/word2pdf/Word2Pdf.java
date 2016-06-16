package com.word2pdf;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Word2Pdf {
	private static final int wdFormatPDF = 17;
	public static boolean word2Pdf(String inputFile,String pdfFile){
		ComThread.InitSTA();  
		  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        Dispatch doc = null;  
        try {  
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false)); 
            app.setProperty("AutomationSecurity", new Variant(3)); 
            Dispatch docs = app.getProperty("Documents").toDispatch();
            System.out.println("input file: " + inputFile);  
            doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();  
            System.out.println("convert [" + inputFile + "] >>> [" + pdfFile + "]");  
            Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);
            long end = System.currentTimeMillis();  
  
            System.out.println("using time:" + (end - start) + "ms.");  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:" + e.getMessage());  
        } finally {  
            Dispatch.call(doc, "Close", false);  
            System.out.println("close file");  
            if (app != null)  
                app.invoke("Quit", new Variant[] {});  
        }
        ComThread.Release();  
        ComThread.quitMainSTA();  
        return false;  
	}
}
