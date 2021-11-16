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
			value = value.replace("[Tenant]", TenantStorage.getCurrentTenant());
			value = value.replace("[CORE_SERVER_URL]", TenantStorage.getCurrentTenantUrlServer());

		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
		return value;
	}
}
