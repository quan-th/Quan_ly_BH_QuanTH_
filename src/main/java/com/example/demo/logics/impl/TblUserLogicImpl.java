/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics.impl;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Common;

/**
 * @author HP
 *
 *         TblUserLogic
 */
@Component
public class TblUserLogicImpl implements TblUserLogic {
	@Autowired
	private TblUserDao tblUserDao;

	// @Autowired
	// private TblCompanyLogic tblCompanyLogic;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#LoginByUsernameAndPassword(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<TblUser> LoginByUsernameAndPassword(String Username, String Password) {
		return tblUserDao.findByUserNameAndUserPassword(Username, Password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#getListUser(com.luvina.entities.
	 * SearchingInfo)
	 */
	@Override
	public List<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult) {
		List<DisplayUser> displayUsers = tblUserDao.getListUsers(info, currentPage, maxResult);
		if (displayUsers.size() != 0) {
			for (DisplayUser displayUser : displayUsers) {
				displayUser.setUsername(StringEscapeUtils.escapeHtml4(displayUser.getUsername()));
				displayUser.setGender(Common.convertGender(displayUser.getGender()));
				displayUser.setBirthdate(Common.convertDate(displayUser.getBirthdate()));
				displayUser.setInsuranceNumber(displayUser.getInsuranceNumber());
				displayUser.setStartDate(Common.convertDate(displayUser.getStartDate()));
				displayUser.setEndDate(Common.convertDate(displayUser.getEndDate()));
				displayUser.setPlaceOfRegister(StringEscapeUtils.escapeHtml4(displayUser.getPlaceOfRegister()));
			}
		}
		return displayUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#getDetailUser(int)
	 */
	@Override
	public DetailUser getDetailUser(int id) {
		TblUser tblUser = tblUserDao.findByUserInternalId(id);
		DetailUser detailUser = new DetailUser();
		detailUser.setUsername(StringEscapeUtils.escapeHtml4(tblUser.getUserFullName()));
		detailUser.setGender(Common.convertGender(tblUser.getUserSexDivision()));
		detailUser.setBirthdate(Common.convertDate(tblUser.getBirthday()));
		detailUser.setInsuranceNumber(tblUser.getTblInsurance().getInsuranceNumber());
		detailUser.setStartDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceStartDate()));
		detailUser.setEndDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceEndDate()));
		detailUser.setPlaceOfRegister(StringEscapeUtils.escapeHtml4(tblUser.getTblInsurance().getPlaceOfRegister()));
		detailUser.setCompany(StringEscapeUtils.escapeHtml4(tblUser.getTblCompany().getCompanyName()));
		return detailUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#checkUserExist(int)
	 */
	@Override
	public boolean checkUserExist(int id) {
		return tblUserDao.findByUserInternalId(id) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#getNumberOfUsers(com.luvina.entities.
	 * SearchingInfo)
	 */
	@Override
	public long getNumberOfUsers(SearchingInfo info) {
		return tblUserDao.getNumberOfUsers(info);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#exportUser(com.luvina.entities.
	 * SearchingInfo, java.lang.String)
	 */
	// @Override
	// public boolean exportUser(SearchingInfo searchingInfo, String
	// JsonCompany) {
	// BufferedWriter bw = null;
	// OutputStreamWriter osw = null;
	// int currentPagetoRecord = 1;
	// int maxResultToRecord = 5;
	// ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>)
	// getListUserForExport(searchingInfo,
	// currentPagetoRecord, maxResultToRecord);
	//
	// try {
	// JSONObject obj = new JSONObject(JsonCompany);
	// String companyName = obj.getString(Constant.COMPANY_NAME);
	// String companyAddress = obj.getString(Constant.COMPANY_ADDRESS);
	// String companyPhone = obj.getString(Constant.COMPANY_PHONE);
	// String companyEmail = obj.getString(Constant.COMPANY_EMAIL);
	//
	// File f = new File("D:\\" + companyName + ".csv");
	//
	// String COMMA_DELIMITER = ",";
	// String LINE_SEPARATOR = "\n";
	// byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
	//
	// // File header
	// String HEADER = ValueProperties.getValue("USER_FULL_NAME") + "," +
	// ValueProperties.getValue("GENDER") + ","
	// + ValueProperties.getValue("DOB") + "," +
	// ValueProperties.getValue("INSURANCE_NUMBER") + ","
	// + ValueProperties.getValue("START_DATE") + "," +
	// ValueProperties.getValue("END_DATE") + ","
	// + ValueProperties.getValue("PLACE_OF_REGISTER");
	//
	// FileOutputStream fos = new FileOutputStream(f, false);
	// fos.write(bom);
	// osw = new OutputStreamWriter(fos, "UTF-8");
	// bw = new BufferedWriter(osw);
	//
	// // Adding the header of file
	// bw.append(ValueProperties.getValue("LIST_INSURANCE"));
	// // 2 New Line after the header
	// bw.append(LINE_SEPARATOR);
	// bw.append(LINE_SEPARATOR);
	// // Adding CompanyName
	// bw.append(ValueProperties.getValue("COMPANY_NAME"));
	// bw.append(COMMA_DELIMITER);
	// bw.append(companyName);
	// bw.append(LINE_SEPARATOR);
	// // Adding CompanyAddress
	// bw.append(ValueProperties.getValue("ADDRESS"));
	// bw.append(COMMA_DELIMITER);
	// bw.append(companyAddress);
	// bw.append(LINE_SEPARATOR);
	// // Adding CompanyEmail
	// bw.append(ValueProperties.getValue("EMAIL"));
	// bw.append(COMMA_DELIMITER);
	// bw.append(companyEmail);
	// bw.append(LINE_SEPARATOR);
	// // Adding companyPhone
	// bw.append(ValueProperties.getValue("PHONE_NUMBER"));
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + companyPhone);
	// bw.append(LINE_SEPARATOR);
	// // Adding 2 new line
	// bw.append(LINE_SEPARATOR);
	// // Adding the header of table
	// bw.append(HEADER);
	// // New Line after the header
	// bw.append(LINE_SEPARATOR);
	// // Iterate the empList
	// while (allUsers.size() <= maxResultToRecord) {
	// Iterator<DisplayUser> it = allUsers.iterator();
	// while (it.hasNext()) {
	// DisplayUser displayUser = (DisplayUser) it.next();
	// bw.append(displayUser.getUsername());
	// bw.append(COMMA_DELIMITER);
	// bw.append(displayUser.getGender());
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + displayUser.getBirthdate());
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + displayUser.getInsuranceNumber());
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + displayUser.getStartDate());
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + displayUser.getEndDate());
	// bw.append(COMMA_DELIMITER);
	// bw.append("'" + displayUser.getPlaceOfRegister());
	// bw.append(LINE_SEPARATOR);
	// }
	// if (allUsers.size() == maxResultToRecord) {
	// currentPagetoRecord++;
	// allUsers = (ArrayList<DisplayUser>) getListUserForExport(searchingInfo,
	// currentPagetoRecord,
	// maxResultToRecord);
	// } else if (allUsers.size() < maxResultToRecord) {
	// break;
	// }
	// }
	// bw.flush();
	// return true;
	// } catch (Exception ee) {
	// ee.printStackTrace();
	// return false;
	// } finally {
	// try {
	// bw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.logics.TblUserLogic#getListUserForExport(com.luvina.entities.
	 * SearchingInfo, int, int)
	 */
	// @Override
	// public List<DisplayUser> getListUserForExport(SearchingInfo info, int
	// currentPage, int maxResult) {
	// List<DisplayUser> displayUsers
	// =tblUserDao.getListUsers(info,currentPage,maxResult);
	// if (displayUsers.size() != 0) {
	// for (DisplayUser displayUser : displayUsers) {
	// displayUser.setGender(Common.convertGender(displayUser.getGender()));
	// displayUser.setBirthdate(Common.convertDate(displayUser.getBirthdate()));
	// displayUser.setInsuranceNumber(displayUser.getInsuranceNumber());
	// displayUser.setStartDate(Common.convertDate(displayUser.getStartDate()));
	// displayUser.setEndDate(Common.convertDate(displayUser.getEndDate()));
	// displayUser.setPlaceOfRegister(StringEscapeUtils.escapeHtml4(displayUser.getPlaceOfRegister()));
	// }
	// }
	// return displayUsers;
	// }

}
