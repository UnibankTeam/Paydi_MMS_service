package com.paydi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

public class ResourceUtil {
	protected static ResourceBundle rs = null;
    protected static Locale defaultLocate = new Locale("vi", "VN");
    protected static String resourcePath = "com.namabank.resource.GlobalMessage";
    public static Boolean developing = false;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResourceUtil.class);
    public ResourceUtil() {
    }

    public static String getString(String key) {
        try {
            if (rs == null) {
                resetResourceBundle(resourcePath, defaultLocate);
            }
            return new String(rs.getString(key).getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception use) {
            logger.info("[res-warning] " + use.getMessage());
        }
        return key;
    }

    public static void resetResourceBundle(String newpath, Locale newlocate) {
        defaultLocate = newlocate;
        resourcePath = newpath;
        if (newpath != null && defaultLocate != null) {
            try {
                rs = ResourceBundle.getBundle(resourcePath, defaultLocate);
            } catch (java.lang.NullPointerException nullex) {
                logger.info("[Get Resource '" + resourcePath + "' ERROR] " + nullex.getMessage());
            }
        } else {
            rs = null;
        }
    }

    public static String readResourceTextFile(Class<?> c, String filePath, String charset) throws IOException {
        String res = null;
        InputStream instream = c.getResourceAsStream(filePath);
        if (instream == null) {
            return res;
        }
        byte[] buffer = new byte[1024 * 1024];
        int result = instream.read(buffer);
        res = new String(buffer, 0, result, charset);
        return res;
    }

    public static String readResourceTextFile(Class<?> c, String filePath) throws IOException {
        return readResourceTextFile(c, filePath, Charset.defaultCharset().name());
    }
}