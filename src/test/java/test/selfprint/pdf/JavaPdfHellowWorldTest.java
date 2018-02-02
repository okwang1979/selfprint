package test.selfprint.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.selfprint.pdf.JavaPdfHelloWorld;

public class JavaPdfHellowWorldTest {

	@Test
	public void creatHellPdf(){
	
		List<String> content = new ArrayList<String>();
		String oneLine = "Hellow World" ;
		content.add(oneLine);
		String twoLine = "I love you2ÄãºÃ!";
		content.add(twoLine);
		String fileName= "test.pdf";
		JavaPdfHelloWorld pdf = new JavaPdfHelloWorld();
		pdf.creatHellPdf("test.pdf",content);
		
	 
		
		
		
	}
}
