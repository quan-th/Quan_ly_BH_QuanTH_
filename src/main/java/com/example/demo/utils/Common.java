/**
 * Copyright(C) Nov 24, 2016 Luvina,Common.java,Nov 24, 2016,LA-AM
 */
package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

/**
 * @author LA-AM Lá»›p gá»“m cÃ¡c phÆ°Æ¡ng thá»©c Common
 */
public class Common {
	/**
	 * checkLogin
	 *
	 * @param session
	 *            HttpSession
	 * @return ADM001
	 */
	public static String checkLogin(HttpSession session) {
		String template = "";
		if (session.getAttribute("loginId") == null) {
			template = Constant.MH001;
		}
		return template;

	}
	/**
	 * Convert Giới tính từ số -> chữ
	 * 
	 * @param number
	 *            01->Nam,02->Nữ
	 * @return giới tính
	 */
	public static String convertGender(String number) {
		return (Constant.MALE.equals(number) ? ValueProperties.getValue(Constant.STR_MALE)
				: ValueProperties.getValue(Constant.STR_FEMALE));
	}
	/**
	 * mÃ£ hÃ³a báº±ng MD5
	 *
	 * @param str
	 *            chuá»—i cáº§n mÃ£ hÃ³a
	 * @return chuá»—i Ä‘Æ°á»£c mÃ£ hÃ³a
	 */
	public static String convertToMD5(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Encryt Fail");
		}
		return sb.toString();
	}

	/**
	 * Escape wildcard trong mysql
	 * 
	 * @param chuá»—i
	 *            truyá»�n vÃ o
	 * @return chuá»—i tráº£ vá»�.
	 */
	public static String escapeWildCard(String str) {
		str = str.replace("_", "\\_");
		str = str.replace("%", "\\%");
		return str;
	}

	/**
	 * Convert ngÃ y tá»« yyyy-mm-dd thÃ nh dd/mm/yyyy
	 * 
	 * @param date
	 *            ngÃ y cáº§n convert
	 * @return ngÃ y sau khi convert
	 */
	public static String convertDate(String date) {
		String[] arrDate = date.split("-");
		String retDate = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
		return retDate;
	}

	/**
	 * Kiểm tra chuỗi có null
	 * 
	 * @param string
	 *            chuỗi cần kiểm tra
	 * @return true nếu null,false nếu không
	 */
	public static boolean isNull(String string) {
		return string == null;
	}

	/**
	 * Kiểm tra ngày hợp lệ
	 *
	 * @param date
	 *            return true nếu hợp lệ, false nếu không hợp lệ
	 */
	public static boolean checkValidDate(String date) {

		boolean flag = true;

		int year = 0;
		int month = 0;
		int day = 0;
		try {
			String[] arrDate = date.split("/");
			year = Integer.parseInt(arrDate[2]);
			month = Integer.parseInt(arrDate[1]);
			day = Integer.parseInt(arrDate[0]);
		} catch (NumberFormatException e) {
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 31) {
				flag = false;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 30) {
				flag = false;
			}
			break;
		case 2:
			if ((year % 4 == 0) || (year % 100 == 0 && year % 400 == 0)) {
				if (day > 29) {
					flag = false;
				}
			} else {
				if (day > 28) {
					flag = false;
				}
			}
			break;
		default:
			break;
		}
		return flag;
	}

	/**
	 * validate order
	 * 
	 * @param order
	 *            order can validate
	 * @return order neu hop le, ASC neu khong hop le
	 */
	public static String validOrder(String order) {
		if (Constant.SORT_ASC.equals(order) || Constant.SORT_DESC.equals(order)) {
			return order;
		} else {
			return Constant.SORT_ASC;
		}
	}

	/**
	 * convert String thành date
	 * 
	 * @param strDate
	 *            chuỗi cần convert
	 * @return date
	 */
	public static String convertStringToDate(String strDate) {
		String[] rs = strDate.split("/");
		return rs[2] + "-" + rs[1] + "-" + rs[0];
	}

	/**
	 * Conver utf-8 to Ascii
	 * 
	 * @param s
	 *            chuỗi cần convert
	 * @return chuỗi được convert
	 */
	public static String decompose(String s) {
		return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("đ", "d");
	}

	/**
	 * Chuẩn hoá chuỗi Kí tự đầu của word viết hoa Các word cách nhau 1 space
	 * Chỉ nhận kí tự latin
	 * 
	 * @param s
	 *            chuỗi truy�?n vào
	 * @return chuỗi được chuẩn hoá
	 */
	public static String normarlizeString(String s) {
		StringBuilder result = new StringBuilder();
		s = decompose(s);
		String[] words = s.split(" ");
		for (int i = 0; i < words.length; i++) {
			StringBuilder word = new StringBuilder(
					words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()));
			if (i < words.length - 1) {
				word.append(" ");
			}
			result.append(word);
		}
		return result.toString();
	}

	/**
	 * Chuẩn hoá mục ch�?n công ty.
	 * 
	 * @param choseCompany
	 *            chuỗi cần chuẩn hoá
	 * @return return choseCompany nếu hợp lệ, return Constant.ALREADY_HAVE nếu
	 *         không hợp lệ
	 */
	public static String normalizeChoseCompany(String choseCompany) {
		if (Constant.ALREADY_HAVE.equals(choseCompany) || Constant.ADD_NEW_COMPANY.equals(choseCompany)) {
			return choseCompany;
		} else {
			return Constant.ALREADY_HAVE;
		}
	}

	/**
	 * escapeWildcard
	 *
	 * @param str
	 *            String
	 * @return String escapeWildcard
	 */
	public static String escapeWildcard(String str) {
		String tem = str;
		tem = tem.replace("\\", "\\\\");
		tem = tem.replace("%", "\\%");
		tem = tem.replace("_", "\\_");
		return tem;
	}
}
