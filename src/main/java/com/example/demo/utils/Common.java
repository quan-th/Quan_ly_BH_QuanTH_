/**
 * Copyright(C) Nov 24, 2016 Luvina,Common.java,Nov 24, 2016,LA-AM
 */
package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * @author LA-AM lop gom cac phuong thuc common
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
	 * Convert giới tính
	 * 
	 * @param number
	 *            01->Nam,02->nữ
	 * @return giới tính
	 */
	public static String convertGender(String number) {
		return (Constant.MALE.equals(number) ? ValueProperties.getValue(Constant.STR_MALE)
				: ValueProperties.getValue(Constant.STR_FEMALE));
	}

	/**
	 * ma hoa MD5
	 *
	 * @param str
	 *            chuoi can ma hoa md5
	 * @return chuoi da duoc ma hoa md5
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
	 * @param chuoi
	 *            can escapeWildCard
	 * 
	 * @return chuoi da duoc escape
	 */
	public static String escapeWildCard(String str) {
		str = str.replace("_", "\\_");
		str = str.replace("%", "\\%");
		return str;
	}

	/**
	 * Convert ngay tu yyyy-mm-dd thanh dd/mm/yyyy
	 * 
	 * @param date
	 *            ngay can convert
	 * @return ngay sau khi convert
	 */
	public static String convertDate(String date) {
		String[] arrDate = date.split("-");
		String retDate = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
		return retDate;
	}

	/**
	 * kiem tra chuoi null
	 * 
	 * @param string
	 *            chuoi can kiem tra
	 * @return true neu null,false neu khong
	 */
	public static boolean isNull(String string) {
		return string == null;
	}

	/**
	 * Kiem tra ngay hop le
	 *
	 * @param date
	 *            return true neu ngay hop le, false neu ngay khong hop le
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
	 * convert String thanh date
	 * 
	 * @param strDate
	 *            chuoi can convert
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
	 *            chuoi can convert
	 * @return chuoi da duoc convert
	 */
	public static String decompose(String s) {
		return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("Ä‘", "d");
	}

	/**
	 * Chuan hoa chuoi String theo require
	 * 
	 * @param s
	 *            chuoi chuyen hoa
	 * @return chuoi da duoc chuyen hoa
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
	 * Chuan hoa chon cong ty.
	 * 
	 * @param choseCompany
	 *            chuoi can chuan hoa¡
	 * @return return choseCompany neu hop le‡, return Constant.ALREADY_HAVE neu
	 *         khong hop le
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

	/**
	 * So sánh 2 arraylist
	 * 
	 * @param <E>
	 * 
	 * @param companies
	 *            expected
	 * @param companiesActual
	 *            actual
	 * @return
	 */
	public static <E> boolean myComparison(ArrayList<E> companies, ArrayList<E> companiesActual) {
		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).equals(companiesActual.get(i)) == false) {
				return false;
			}
		}
		return true;
	}
}
