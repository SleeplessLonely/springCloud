package com.example.pdf.parse.doc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFMarkedContentExtractor;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Matrix;

public class demo {
	private static Log log = LogFactory.getLog(demo.class);

	public static void main(String[] args) {
		File file = new File("I:\\test\\Java设计模式（刘伟）.pdf");
		String parsedText = readPdf(file);
		File descFile = new File("I:\\test\\test\\desc.doc");
		writeDoc(parsedText, descFile);
		System.out.println("执行完成");
	}

	private static void writeDoc(String outputStream, File descFile) {
		try (FileOutputStream fos = new FileOutputStream(descFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {
			if (!descFile.exists()) {
				if(descFile.createNewFile()) {
					log.info("创建文件已创建");
				}
			}
			bos.write(outputStream.getBytes());
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String readPdf(File sourceFile) {
		String message = null;
		try (	
				InputStream inputStream = new FileInputStream(sourceFile);
				RandomAccessBufferedFileInputStream rabInput = new RandomAccessBufferedFileInputStream(inputStream);
			) {
			PDFParser parser = new PDFParser(rabInput);
			parser.parse();
			COSDocument cosDoc = parser.getDocument();
			PDDocument pdDocument = new PDDocument(cosDoc);
//			PDPageTree pages = pdDocument.getPages();
//			PDFMarkedContentExtractor pdfMarkedContentExtractor = null;
//			for (PDPage pdPage : pages) {
//				pdfMarkedContentExtractor = new PDFMarkedContentExtractor();
//				pdfMarkedContentExtractor.processPage(pdPage);
//				Matrix textLineMatrix = pdfMarkedContentExtractor.getTextLineMatrix();
//			}
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			pdfTextStripper.setStartPage(1);
			pdfTextStripper.setEndPage(20);
			pdfTextStripper.setPageEnd("\f\r");
			message = pdfTextStripper.getText(pdDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}
