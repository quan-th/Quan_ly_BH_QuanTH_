/**
 * Copyright(C) Nov 24, 2016 Luvina,Common.java,Nov 24, 2016,LA-AM
 */
package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.servlet.http.HttpSession;

/**
 * @author LA-AM 
 * lop gom cac phuong thuc common
 */
public class Common {
	/**
	 * checkLogin
	 * @param session HttpSession
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
	 * Convert gender
	 * @param number 01->Nam,02->nữ
	 * @return gender 
	 */
	public static String convertGender(String number) {
		return (Constant.MALE.equals(number) ? ValueProperties.getValue(Constant.STR_MALE)
				: ValueProperties.getValue(Constant.STR_FEMALE));
	}

	/**
	 * encoding MD5
	 * @param str origin String
	 * @return changed string 
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
	 * Convert date form yyyy-mm-dd -> dd/mm/yyyy
	 * @param date origin string
	 * @return changed string
	 */
	public static String convertDate(String date) {
		String[] arrDate = date.split("-");
		String retDate = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
		return retDate;
	}

	/**
	 * check null
	 * @param string origin string
	 * @return true if null,false if != null
	 */
	public static boolean isNull(String string) {
		return string == null;
	}

	/**
	 * check valid date
	 * @param date origin date
	 * return true if valid , false if not valid
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
	 * @param order origin order
	 * @return order if valid, ASC if not valid
	 */
	public static String validOrder(String order) {
		if (Constant.SORT_ASC.equals(order) || Constant.SORT_DESC.equals(order)) {
			return order;
		} else {
			return Constant.SORT_ASC;
		}
	}

	/**
	 * convert date form 
	 * @param strDate origin string
	 * @return changed string 
	 */
	public static String convertStringToDate(String strDate) {
		String[] rs = strDate.split("/");
		return rs[2] + "-" + rs[1] + "-" + rs[0];
	}

	/**
	 * Conver utf-8 to Ascii
	 * @param s origin string
	 * @return changed string 
	 */
	public static String decompose(String s) {
		return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("Ä‘", "d");
	}

	/**
	 * normarlize String
	 * @param s origin string
	 * @return changed string 
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
	 * normalize ChoseCompany.
	 * @param choseCompany origin ChoseCompany¡
	 * @return choseCompany if valid, return Constant.ALREADY_HAVE if not valid
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
	 * @param str String
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
	 * formed currentPage
	 * @param currentPage currentPage
	 * @param recordsOfPage records/ page
	 * @param totalRecords total records
	 * @return current page
	 */
	public static int formedCurrentPage(String strCurrentPage, int recordsOfPage, int totalRecords) {
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(strCurrentPage);
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		int numberOfPages = getTotalOfPages(recordsOfPage, totalRecords);
		if (currentPage < 1) {
			return 1;
		} else if (currentPage > numberOfPages) {
			return numberOfPages;
		} else {
			return currentPage;
		}
	}

	/**
	 * get list paging
	 * @param currentPage current page
	 * @param recordsOfPage records/page
	 * @param totalRecords total records
	 * @return list paging
	 */
	public static ArrayList<Integer> paging(int currentPage, int recordsOfPage, int totalRecords) {
		int numberOfPages = getTotalOfPages(recordsOfPage, totalRecords);
		int pageRange = Integer.parseInt(ValueProperties.getValue("PAGE_RANGE"));
		int numberOfPageToAdd = pageRange - 1;
		int startPage = 0;
		ArrayList<Integer> pages = new ArrayList<Integer>();
		if (currentPage != 0) {
			if (1 <= currentPage && currentPage <= 1 + numberOfPageToAdd) {
				startPage = 1;
			} else if (numberOfPages - numberOfPageToAdd <= currentPage && currentPage <= numberOfPages) {
				startPage = numberOfPages - numberOfPageToAdd;
			} else {
				startPage = currentPage - pageRange / 2 > 0 ? currentPage - pageRange / 2 : 1;
			}
			pages.add(startPage);
			for (int i = 1; startPage + i <= startPage + numberOfPageToAdd && startPage + i <= numberOfPages; i++) {
				pages.add(startPage + i);
			}
		}
		return pages;
	}

	/**
	 * total page
	 * @param totalRecords total records
	 * @param recordsOfPage records/page
	 * @return total page
	 */
	public static int getTotalOfPages(int recordsOfPage, int totalRecords) {
		return (totalRecords % recordsOfPage == 0) ? totalRecords / recordsOfPage : totalRecords / recordsOfPage + 1;
	}
	/**
	 * compare date
	 * @param startDate start Date
	 * @param endDate end Date
	 * @return true if endDate > start date, false if startDate< endDate
	 */
	public static boolean compareStartDateAndEndDate(String startDate, String endDate) {
		try {
			String[] arrEndDate = endDate.split("/");
			String[] arrStartDate = startDate.split("/");
			Calendar calEndDate = new GregorianCalendar(Integer.parseInt(arrEndDate[2]),
					Integer.parseInt(arrEndDate[1]), Integer.parseInt(arrEndDate[0]));
			Calendar calStartDate = new GregorianCalendar(Integer.parseInt(arrStartDate[2]),
					Integer.parseInt(arrStartDate[1]), Integer.parseInt(arrStartDate[0]));
			int compareDate = calEndDate.compareTo(calStartDate);
			if (compareDate <= 0) {
				return false;
			} 
		} catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
		
	}
}
