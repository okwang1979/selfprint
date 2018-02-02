package cn.selfprint.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class JavaPdfHelloWorld {
	
	public static String PATH = "D:"+ File.separator;
	
	
	public void creatHellPdf(String fileName,List<String> content){
		  Document document = new Document();
		  
		  
	        try {
	        	File file = new File(PATH+fileName);
	        	if(!file.exists()){
	        		file.createNewFile();
	        	} 
	        	
	        	
	        	
	        	
	        	   //方法一：使用Windows系统字体(TrueType)    
	            BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);    
	                
	            //方法二：使用iTextAsian.jar中的字体    
	            //BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);    
	                
	            //方法三：使用资源字体(ClassPath)    
	            ////BaseFont baseFont = BaseFont.createFont("/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);    
	        	
	        	
	            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	            document.open();
	            Font font = new Font(baseFont);    
	            for(String line:content){
	            	  document.add(new Paragraph(line,font));
	            }
	          
	            document.close();
	            writer.close();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
			 
				e.printStackTrace();
			}
	}

}
