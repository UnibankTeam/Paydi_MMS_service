package com.paydi.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import io.sentry.Sentry;

@Component
public class PdfGenaratorUtil {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PdfGenaratorUtil.class);
	private Sentry sentry;
	@Autowired
	private TemplateEngine templateEngine;

	public static String createPFDFromHTML(String html, String pathStorePDF) {

		String result = "SUCCESS";

		try {
			OutputStream file;
			file = new FileOutputStream(new File(pathStorePDF));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			file.close();
		} catch (FileNotFoundException e) {
			Sentry.captureException(e);
			result = e.getMessage();
		} catch (DocumentException e) {
			Sentry.captureException(e);
			result = e.getMessage();
		} catch (IOException e) {
			Sentry.captureException(e);
			result = e.getMessage();
		} finally {
			return result;
		}
	}

	public String createPdf(String templateName, Map map, String path, String trn_ref_no) throws Exception {
		SimpleDateFormat format_date_vn = new SimpleDateFormat("yyyyMMdd");
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry pair = (Map.Entry) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		File directory1 = new File(path + format_date_vn.format(new Date()));
		if (!directory1.exists()) {
			directory1.mkdir();
		}

		File directory2 = new File(
				path + format_date_vn.format(new Date()) + System.getProperty("file.separator") + trn_ref_no);
		if (!directory2.exists()) {
			directory2.mkdir();
		}

		String processedHtml = templateEngine.process(templateName, ctx);

		FileOutputStream os = null;
		String fileName = format_date_vn.format(new Date()) + System.getProperty("file.separator") + trn_ref_no
				+ System.getProperty("file.separator") + "voucher.pdf";
		try {

			String pathStorePDF = path + System.getProperty("file.separator") + fileName;
			OutputStream file;
			file = new FileOutputStream(new File(pathStorePDF));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			InputStream is = new ByteArrayInputStream(processedHtml.getBytes(Charset.forName("UTF-8")));
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			file.close();
			logger.info("PDF created successfully" + pathStorePDF);
			return pathStorePDF;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					Sentry.captureException(e); }
			}
		}
	}
	
	public String createPdfManual(String templateName, Map map, String path, String trn_ref_no,Date createdDate) throws Exception {
		SimpleDateFormat format_date_vn = new SimpleDateFormat("yyyyMMdd");
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry pair = (Map.Entry) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		File directory1 = new File(path + format_date_vn.format(createdDate));
		if (!directory1.exists()) {
			directory1.mkdir();
		}

		File directory2 = new File(
				path + format_date_vn.format(createdDate) + System.getProperty("file.separator") + trn_ref_no);
		if (!directory2.exists()) {
			directory2.mkdir();
		}

		String processedHtml = templateEngine.process(templateName, ctx);

		FileOutputStream os = null;
		String fileName = format_date_vn.format(createdDate) + System.getProperty("file.separator") + trn_ref_no
				+ System.getProperty("file.separator") + "voucher.pdf";
		try {

			String pathStorePDF = path + System.getProperty("file.separator") + fileName;
			OutputStream file;
			file = new FileOutputStream(new File(pathStorePDF));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			InputStream is = new ByteArrayInputStream(processedHtml.getBytes(Charset.forName("UTF-8")));
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			file.close();
			//system.out.println("PDF created successfully" + pathStorePDF);
			return pathStorePDF;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					/* ignore */ }
			}
		}
	}

	// save file
	public void saveUploadedFiles(MultipartFile files, String path_parent, String trn_ref_no) throws IOException {
		SimpleDateFormat format_date_vn = new SimpleDateFormat("yyyyMMdd");

		if (files.isEmpty()) {
			return;
		}
		File directory1 = new File(path_parent + format_date_vn.format(new Date()));
		if (!directory1.exists()) {
			directory1.mkdir();
		}

		File directory2 = new File(
				path_parent + format_date_vn.format(new Date()) + System.getProperty("file.separator") + trn_ref_no);
		if (!directory2.exists()) {
			directory2.mkdir();
		}
		byte[] bytes = files.getBytes();
		Path path = Paths.get(format_date_vn.format(new Date()) + System.getProperty("file.separator") + trn_ref_no
				+ System.getProperty("file.separator") + "bill.pdf");
		Files.write(path, bytes);
	}

}