package com.paydi.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import org.slf4j.LoggerFactory;

import io.sentry.Sentry;

public class StringUtilsCommon {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StringUtilsCommon.class);
	private Sentry sentry;
	public boolean stringDecode(String originalInput, String encodeInput) {
		String encodedString;
		try {

			encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes("UTF-8"));

			logger.info("encodedString:" + encodedString);
			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			String decodedString = new String(decodedBytes);

			logger.info("decodedString:" + decodedString);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Sentry.captureException(e);
		}
		return false;
	}

	public static String OTP(int len) {

		logger.info("Generating OTP using random() :" + len);

		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[len];
		for (int i = 0; i < len; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}

		return String.valueOf(otp);
	}

	public static char[] geek_Password(int len) {
		logger.info("Generating password using random() : ");
		//system.out.print("Your new password is : ");

		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";

		String values = Capital_chars + Small_chars + numbers + symbols;
		Random rndm_method = new Random();
		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return password;
	}

	static String[] ChuSo = { " không ", " một ", " hai ", " ba ", " bốn ", " năm ", " sáu ", " bảy ", " tám ",
			" chín " };
	static String[] Tien = { "", " nghìn", " triệu", " tỷ", " nghìn ", " triệu " };

	// 1. Hàm đọc số có ba chữ số;
	public static String DocSo3ChuSo(int baso) {

		int tram, chuc, donvi = 0;
		String KetQua = "";

		tram = Integer.parseInt(String.valueOf(baso / 100));
		chuc = Integer.parseInt(String.valueOf((baso % 100) / 10));
		donvi = baso % 10;

		if (tram == 0 && chuc == 0 && donvi == 0)
			return "";
		if (tram != 0) {
			KetQua += ChuSo[tram] + " trăm ";
			if ((chuc == 0) && (donvi != 0))
				KetQua += " linh ";
		}
		if ((chuc != 0) && (chuc != 1)) {
			KetQua += ChuSo[chuc] + " mươi";
			if ((chuc == 0) && (donvi != 0))
				KetQua = KetQua + " linh ";
		}
		if (chuc == 1)
			KetQua += " mười ";
		switch (donvi) {
		case 1:
			if ((chuc != 0) && (chuc != 1)) {
				KetQua += " mốt ";
			} else {
				KetQua += ChuSo[donvi];
			}
			break;
		case 5:
			if (chuc == 0) {
				KetQua += ChuSo[donvi];
			} else {
				KetQua += " lăm ";
			}
			break;
		default:
			if (donvi != 0) {
				KetQua += ChuSo[donvi];
			}
			break;
		}
		return KetQua;
	}

	public static String DocSo3ChuSoSau(int baso) {
		int tram, chuc, donvi = 0;
		String KetQua = "";

		tram = Integer.parseInt(String.valueOf(baso / 100));
		chuc = Integer.parseInt(String.valueOf((baso % 100) / 10));
		donvi = baso % 10;
		if (tram == 0 && chuc == 0 && donvi == 0)
			return "";
		if (tram != 0) {
			KetQua += ChuSo[tram] + " trăm ";
			if ((chuc == 0) && (donvi != 0))
				KetQua += " linh ";
		} else {
			KetQua += "không" + " trăm ";
			if ((chuc == 0) && (donvi != 0))
				KetQua += " linh ";
		}

		if ((chuc != 0) && (chuc != 1)) {
			KetQua += ChuSo[chuc] + " mươi";
			if ((chuc == 0) && (donvi != 0))
				KetQua = KetQua + " linh ";
		}
		if (chuc == 1)
			KetQua += " mười ";
		switch (donvi) {
		case 1:
			if ((chuc != 0) && (chuc != 1)) {
				KetQua += " mốt ";
			} else {
				KetQua += ChuSo[donvi];
			}
			break;
		case 5:
			if (chuc == 0) {
				KetQua += ChuSo[donvi];
			} else {
				KetQua += " lăm ";
			}
			break;
		default:
			if (donvi != 0) {
				KetQua += ChuSo[donvi];
			}
			break;
		}
		return KetQua;
	}

	// 2. Hàm đọc số thành chữ (Sử dụng hàm đọc số có ba chữ số)
	public static  String DocTienBangChu(String SoTien) {

		int lan, i = 0;
		String KetQua = "", tmp = "";
		BigInteger numBig = new BigInteger(SoTien);
		Long so = new Long("0");

		String[] ViTri = new String[6];
		if (numBig.longValue() < 0)
			return "Số tiền âm !";
		if (numBig.longValue() == 0)
			return "Không đồng !";
		if (numBig.longValue() > 0) {

			so = numBig.longValue();
		} else {
			so = -numBig.longValue();
		}
		if (so > Long.parseLong(String.valueOf("8999999999999999"))) {
			// SoTien = 0;
			return "Số quá lớn!";
		}
		BigInteger num1 = new BigInteger("1000000000000000");
		BigInteger num_so = new BigInteger(String.valueOf(so));
		ViTri[5] = String.valueOf((int) Math.floor(num_so.divide(num1).intValue()));
		if (ViTri[5] == null || ViTri[5].equals("0.0"))
			ViTri[5] = "0";
		so = (so - Long.parseLong(ViTri[5]) * num1.longValue());
		BigInteger num2 = new BigInteger("1000000000000");
		ViTri[4] = String.valueOf((int) Math.floor(num_so.divide(num2).intValue()));
		if (ViTri[4] == null || ViTri[4].equals("0.0"))
			ViTri[4] = "0";
		so = (so - Long.parseLong(ViTri[4]) * Long.parseLong("1000000000000"));
		ViTri[3] = String.valueOf((int) Math.floor(so / 1000000000));
		if (ViTri[3] == null || ViTri[3].equals("0.0"))
			ViTri[3] = "0";
		so = so - Long.parseLong(ViTri[3]) * 1000000000;
		ViTri[2] = String.valueOf((so / 1000000));
		if (ViTri[2] == null || ViTri[2].equals("0.0"))
			ViTri[2] = "0";
		ViTri[1] = String.valueOf((so % 1000000) / 1000);
		if (ViTri[1] == null || ViTri[1].equals("0.0"))
			ViTri[1] = "0";
		ViTri[0] = String.valueOf((so % 1000));
		if (ViTri[0] == null || ViTri[0].equals("0.0"))
			ViTri[0] = "0";
		if (Integer.parseInt(ViTri[5]) > 0) {
			lan = 5;
		} else if (Integer.parseInt(ViTri[4]) > 0) {
			lan = 4;
		} else if (Integer.parseInt(ViTri[3]) > 0) {
			lan = 3;
		} else if (Integer.parseInt(ViTri[2]) > 0) {
			lan = 2;
		} else if (Integer.parseInt(ViTri[1]) > 0) {
			lan = 1;
		} else {
			lan = 0;
		}
		int k = lan;
		for (i = lan; i >= 0; i--) {
			if (i < k) {
				tmp = DocSo3ChuSoSau(Integer.parseInt(ViTri[i]));
				KetQua += tmp;
				if (Integer.parseInt(ViTri[i]) > 0) 
					KetQua += Tien[i];				
			} else {
				tmp = DocSo3ChuSo(Integer.parseInt(ViTri[i]));
				KetQua += tmp;
				if (Integer.parseInt(ViTri[i]) > 0)
					KetQua += Tien[i];				
			}
		}
		if (KetQua.substring(KetQua.length() - 1) == ",") {
			KetQua = KetQua.substring(0, KetQua.length() - 1);
		}
		KetQua = KetQua.substring(1, 2).toUpperCase() + KetQua.substring(2);
		return KetQua + " đồng";
	}

	public String convertToWordPercent(String x) {
		String result = "";
		if (x == null || x.length() == 0) {
			return "";
		}

		String[] array_word_percent = x.split(" ");
		for (int i = 0; i < array_word_percent.length - 1; i++) {
			result += array_word_percent[i] + " ";
		}
		return result;
	}
}

