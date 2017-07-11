/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;
import com.example.demo.utils.ValueProperties;

/**
 * @author HP TblUserLogic
 */
@Component
public class TblUserLogicImpl implements TblUserLogic {
	@Autowired
	private TblUserDao tblUserDao;
	private final String COMMA_DELIMITER = ",";
	private final String LINE_SEPARATOR = "\n";

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
	public ArrayList<DisplayUser> getListUsers(SearchingInfo info, int currentPage) {
		ArrayList<DisplayUser> displayUsers = (ArrayList<DisplayUser>) tblUserDao.getListUsers(info, currentPage);
		if (displayUsers.size() != 0) {
			displayUsers.forEach(DisplayUser::formmatDetailUserForDisplay);
		}
		return displayUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#getDetailUser(int)
	 */
	@Override
	public DetailUser getDetailUser(int id) throws NullPointerException {
		TblUser tblUser = tblUserDao.findByUserInternalId(id);
		DetailUser detailUser = new DetailUser();
		detailUser.setId(id);
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
	 * @see
	 * com.example.demo.logics.TblUserLogic#exportUser(com.example.demo.entities
	 * .SearchingInfo, java.lang.String)
	 */
	@Override
	public boolean exportUsers(OutputStream outputStream,SearchingInfo searchingInfo, String jsonCompany) throws IOException {
		ICsvBeanWriter beanWriter = null;
		OutputStream ops = createFileOutputStreamWithBom(outputStream);
		OutputStreamWriter osw = createOutputStreamWriter(ops);
		BufferedWriter bufferedWriter= new BufferedWriter(osw);
		beanWriter = createBeanWriter(bufferedWriter);
		beanWriter = writeHeaderOfExportFile(beanWriter, jsonCompany);
		beanWriter = writeListUsers(beanWriter, searchingInfo);
		return true;
	}

	/**
	 * Create BufferWriter
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @return bufferWriter
	 * @throws IOException
	 */
	private ICsvBeanWriter createBeanWriter(BufferedWriter fileWriter) throws IOException {
		return new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.logics.TblUserLogic#getListUserForExport(com.luvina.entities.
	 * SearchingInfo, int, int)
	 */
	@Override
	public List<DisplayUser> getListUserForExport(SearchingInfo info, int currentPage) {
		List<DisplayUser> displayUsers = tblUserDao.getListUsers(info, currentPage);
		if (displayUsers.size() != 0) {
			displayUsers.forEach(DisplayUser::formmatDetailUserForExport);
		}
		return displayUsers;
	}

	/**
	 * Create header of file
	 * 
	 * @return String header
	 */
	private String createColumnOfExportFile() {
		StringBuilder header = new StringBuilder();
		header.append(ValueProperties.getValue("USER_FULL_NAME") + ",");
		header.append(ValueProperties.getValue("GENDER") + ",");
		header.append(ValueProperties.getValue("DOB") + ",");
		header.append(ValueProperties.getValue("INSURANCE_NUMBER") + ",");
		header.append(ValueProperties.getValue("START_DATE") + ",");
		header.append(ValueProperties.getValue("END_DATE") + ",");
		header.append(ValueProperties.getValue("PLACE_OF_REGISTER"));
		return header.toString();
	}
	/**
	 * Write header of Export File
	 * 
	 * @param bw
	 *            bufferWriter
	 * @param jsonCompany
	 *            detail of the COmpany
	 * @return bw
	 * @throws IOException
	 */
	private ICsvBeanWriter writeHeaderOfExportFile(ICsvBeanWriter bw, String jsonCompany) throws IOException {
		JSONObject obj = new JSONObject(jsonCompany);
		String companyName = obj.getString(Constant.COMPANY_NAME);
		String companyAddress = obj.getString(Constant.COMPANY_ADDRESS);
		String companyPhone = obj.getString(Constant.COMPANY_PHONE);
		String companyEmail = obj.getString(Constant.COMPANY_EMAIL);
		String headerColumn = createColumnOfExportFile();
		
		bw.writeComment(ValueProperties.getValue("LIST_INSURANCE"));
		bw.writeComment(LINE_SEPARATOR);
		bw.writeComment(ValueProperties.getValue("COMPANY_NAME") + COMMA_DELIMITER + companyName+LINE_SEPARATOR);
		bw.writeComment(ValueProperties.getValue("ADDRESS") + COMMA_DELIMITER + companyAddress+LINE_SEPARATOR);
		bw.writeComment(ValueProperties.getValue("EMAIL") + COMMA_DELIMITER + companyEmail+LINE_SEPARATOR);
		bw.writeComment(ValueProperties.getValue("PHONE_NUMBER") + COMMA_DELIMITER + companyPhone+LINE_SEPARATOR);
		bw.writeHeader(headerColumn.split(","));
		return bw;
	}

	/**
	 * Create File Output Stream
	 * 
	 * @param exportFile
	 * @return
	 * @throws IOException
	 */
	private OutputStream createFileOutputStreamWithBom(OutputStream fos) throws IOException {
		byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };	
		fos.write(bom);
		return fos;
	}

	/**
	 * 
	 * @param fileOutputStream
	 * @return OutputStreamWriter
	 * @throws UnsupportedEncodingException
	 */
	private OutputStreamWriter createOutputStreamWriter(OutputStream fileOutputStream)
			throws UnsupportedEncodingException {
		return new OutputStreamWriter(fileOutputStream, "UTF-8");
	}

	/**
	 * Write content
	 * 
	 * @param bw
	 *            bufferedWriter
	 * @param searchingInfo
	 *            info search
	 * @return bw
	 * @throws IOException
	 */
	private ICsvBeanWriter writeListUsers(ICsvBeanWriter bw, SearchingInfo searchingInfo) throws IOException {
		int currentPagetoRecord = 1;
		int maxResultToRecord = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));

		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) getListUserForExport(searchingInfo,
				currentPagetoRecord);
		String[] header = new String[] { "username", "gender", "birthdate", "insuranceNumber", "startDate", "endDate",
				"placeOfRegister" };
		while (allUsers.size() <= maxResultToRecord) {
			for (DisplayUser displayUser : allUsers) {
				bw.write(displayUser, header);
			}
			if (allUsers.size() == maxResultToRecord) {
				currentPagetoRecord++;
				allUsers = (ArrayList<DisplayUser>) getListUserForExport(searchingInfo, currentPagetoRecord);
			} else if (allUsers.size() < maxResultToRecord) {
				break;
			}
		}
		bw.flush();
		bw.close();
		return bw;
	}
}
