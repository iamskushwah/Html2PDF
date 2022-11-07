package com.htmltopdf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HtmltopdfApplication {

	public static void main(String[] args) throws IOException {

		File html = new File("G:\\Genxcellence\\React\\demo.html");
		String xhtml = htmlToXhtml(html);
		xhtmlToPdf(xhtml,"output.pdf");
	}
	
	private static String htmlToXhtml(File html) throws IOException {
		Document document = Jsoup.parse(html,"UTF-8");
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		return document.html();
	}

	private static void xhtmlToPdf(String xhtml, String outFileName) throws IOException {
		File output = new File(outFileName);
		ITextRenderer iTextRenderer = new ITextRenderer();
		iTextRenderer.setDocumentFromString(xhtml);
		iTextRenderer.layout();
		OutputStream os = new FileOutputStream(output);
		iTextRenderer.createPDF(os);
		os.close();
	}

}
