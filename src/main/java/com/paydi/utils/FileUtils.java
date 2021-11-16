package com.paydi.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.constant.CommonConstant;

import io.sentry.Sentry;

public class FileUtils {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static String saveUploadedFileBatch(String path_parent, List<MultipartFile> files, long batchCode)
			throws IOException {
		String file_name = "";
		try {
			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue;
				}
				File directory1 = new File(path_parent);
				if (!directory1.exists()) {
					directory1.mkdir();
				}
				byte[] bytes = file.getBytes();
				Path path = Paths.get(path_parent + System.getProperty("file.separator") + batchCode + "_!@#$_"
						+ file.getOriginalFilename());

				Files.write(path, bytes);
				file_name = path.toAbsolutePath().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.captureException(e);
		}

		return file_name;
	}

	// save file
	public static String saveUploadedFiles(String path_parent, List<MultipartFile> files) throws IOException {
		String file_name = "";
		try {
			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue; // next pls
				}
				File directory1 = new File(path_parent);
				if (!directory1.exists()) {
					directory1.mkdir();
				}
				byte[] bytes = file.getBytes();
				Path path = Paths.get(path_parent + System.getProperty("file.separator") + file.getOriginalFilename());

				Files.write(path, bytes);
				file_name = path.toAbsolutePath().toString();
			}
		} catch (Exception e) {

			Sentry.captureException(e);
		}

		return file_name;
	}

	public static List<String> saveUploadedMulltiFilesBatch(String path_parent, List<MultipartFile> files)
			throws IOException {
		String file_name = "";
		List<String> filesUloaded = null;
		try {
			filesUloaded = new ArrayList<String>();
			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue; // next pls
				}
				File directory1 = new File(path_parent);
				if (!directory1.exists()) {
					directory1.mkdir();
				}
				byte[] bytes = file.getBytes();
				Path path = Paths.get(path_parent + System.getProperty("file.separator") + file.getOriginalFilename());

				Files.write(path, bytes);
				file_name = path.toAbsolutePath().toString();
				filesUloaded.add(file_name);
			}
		} catch (Exception e) {

			Sentry.captureException(e);
		}

		return filesUloaded;
	}

	public static String getProperty(String key) throws Exception {

		String value = "";

		try (InputStream input = new FileInputStream(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + CommonConstant.FILE_CONFIG_NAME)) {
			Properties prop = new Properties();

			if (input.available() <= 0) {
				return "";
			}
			prop.load(input);
			value = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return value;
	}

	public static String getPropertyTenant(String propertiesKey) throws Exception {

		String value = "";

		try (InputStream input = new FileInputStream(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + CommonConstant.FILE_CONFIG_NAME)) {
			Properties prop = new Properties();

			if (input.available() <= 0 && TenantStorage.getCurrentTenant() != null) {
				return "";
			}
			prop.load(input);
			StringBuffer ternantDb = new StringBuffer();
			ternantDb.append(propertiesKey);
			ternantDb.append(TenantStorage.getCurrentTenant());
			value = prop.getProperty(ternantDb.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return value;
	}

	public static String getPropertyUrlCore(String key) throws Exception {

		String value = "";

		try (InputStream input = new FileInputStream(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + CommonConstant.FILE_CONFIG_NAME)) {
			Properties prop = new Properties();

			if (input.available() <= 0 && TenantStorage.getCurrentTenant() != null) {
				return "";
			}
			prop.load(input);
			value = prop.getProperty(key);
			value = value.replace("[Tenant]",  TenantStorage.getCurrentTenant() );
			value = value.replace("[CORE_SERVER_URL]", TenantStorage.getCurrentTenantUrlServer());

			
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return value;
	}
	
	public static String getPropertyUrlCoreIntegrate(String key) throws Exception {

		String value = "";

		try (InputStream input = new FileInputStream(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + CommonConstant.FILE_CONFIG_NAME)) {
			Properties prop = new Properties();

			if (input.available() <= 0 && TenantStorage.getCurrentTenant() != null) {
				return "";
			}
			prop.load(input);
			value = prop.getProperty(key);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return value;
	}

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
//			
			result = e.getMessage();
			Sentry.captureException(e);
		} catch (DocumentException e) {
//			
			result = e.getMessage();
			Sentry.captureException(e);
		} catch (IOException e) {
//			
			result = e.getMessage();
			Sentry.captureException(e);
		} finally {
			return result;
		}
	}

	public static String file2String(String filename) throws IOException {
		FileInputStream fis;
		StringBuilder sb = new StringBuilder();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + filename);
			byte[] buffer = new byte[10];
			while (fis.read(buffer) != -1) {
				sb.append(new String(buffer));
				buffer = new byte[10];
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Sentry.captureException(e);
		}

		return sb.toString();
	}

	public static Boolean createFilePDF(String filename, String html) throws IOException {
		boolean lq_create_pdf = false;
		try {
//			    OutputStream file = new FileOutputStream(new File("C:\\Users\\HP\\Desktop\\uat\\PDL\\HTMLtoPDF.pdf"));
			OutputStream file = new FileOutputStream(new File(filename));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			InputStream is = new ByteArrayInputStream(html.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			file.close();
			lq_create_pdf = true;
		} catch (Exception e) {
			Sentry.captureException(e);
		}
		return lq_create_pdf;
	}

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		String mineType = servletContext.getMimeType(fileName);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

	public static Resource getUserFileResource() throws IOException {
		// todo replace tempFile with a real file
		Path tempFile = Files.createTempFile("upload-test-file", ".txt");
		Files.write(tempFile, "some test content...\nline1\nline2".getBytes());
		logger.info("uploading: " + tempFile);
		File file = tempFile.toFile();
		// to upload in-memory bytes use ByteArrayResource instead
		return new FileSystemResource(file);
	}

	public static void comverStrinBase642IMG(String base64String) {
//        String base64String = "data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB5C...";
		String[] strings = base64String.split(",");
		String extension;
		switch (strings[0]) {
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
		case "data:image/png;base64":
			extension = "png";
			break;
		default:// should write cases for more images types
			extension = "jpg";
			break;
		}
		// convert base64 string to binary data
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String path = "D:\\temp\\test_image\\test_image." + extension;
		File file = new File(path);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
			File file1 = new File(path);
		} catch (IOException e) {
			Sentry.captureException(e);
		}
	}

	public static Resource getUserFileResourceIng(String base64String) throws IOException {

		String[] strings = base64String.split(",");
		String extension;
		switch (strings[0]) {
		case "data:image/jpeg;base64":
			extension = ".jpeg";
			break;
		case "data:image/png;base64":
			extension = ".png";
			break;
		default:
			extension = ".jpg";
			break;
		}

		Path tempFile = Files.createTempFile("upload-test-file", extension);
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		Files.write(tempFile, data);
		File file = tempFile.toFile();
		return new FileSystemResource(file);
	}

	public static void base64String_JPEC(String base64String) {
//	        String base64String = "data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB5C...";
		String[] strings = base64String.split(",");
		String extension;
		switch (strings[0]) {// check image's extension
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
		case "data:image/png;base64":
			extension = "png";
			break;
		default:// should write cases for more images types
			extension = "jpg";
			break;
		}
		// convert base64 string to binary data
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String path = "D:\\test_image." + extension;
		File file = new File(path);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
		} catch (IOException e) {

			Sentry.captureException(e);
		}
	}

}
