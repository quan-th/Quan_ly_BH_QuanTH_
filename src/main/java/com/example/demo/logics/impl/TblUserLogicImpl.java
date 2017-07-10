/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public ArrayList<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult) {
		ArrayList<DisplayUser> displayUsers = (ArrayList<DisplayUser>) tblUserDao.getListUsers(info, currentPage,
				maxResult);
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
		try {
			detailUser.setId(id);
			detailUser.setUsername(StringEscapeUtils.escapeHtml4(tblUser.getUserFullName()));
			detailUser.setGender(Common.convertGender(tblUser.getUserSexDivision()));
			detailUser.setBirthdate(Common.convertDate(tblUser.getBirthday()));
			detailUser.setInsuranceNumber(tblUser.getTblInsurance().getInsuranceNumber());
			detailUser.setStartDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceStartDate()));
			detailUser.setEndDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceEndDate()));
			detailUser
					.setPlaceOfRegister(StringEscapeUtils.escapeHtml4(tblUser.getTblInsurance().getPlaceOfRegister()));
			detailUser.setCompany(StringEscapeUtils.escapeHtml4(tblUser.getTblCompany().getCompanyName()));
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
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
	public boolean exportUser(SearchingInfo searchingInfo, String jsonCompany) {
		BufferedWriter bw = null;
		String COMMA_DELIMITER = ",";
		String LINE_SEPARATOR = "\n";
		try {
			JSONObject obj = new JSONObject(jsonCompany);
			String companyName = obj.getString(Constant.COMPANY_NAME);
			File exportFile = createExportFile(companyName);
			String header = createColumnOfExportFile();
			FileOutputStream fos = createFileOutputStreamWithBom(exportFile);
			OutputStreamWriter osw = createOutputStreamWriter(fos);
			bw = createBufferWriter(osw);
			bw = writeHeaderOfExportFile(bw, jsonCompany, header, LINE_SEPARATOR, COMMA_DELIMITER);
			bw = writeContentFile(bw, searchingInfo, LINE_SEPARATOR, COMMA_DELIMITER);
			bw.flush();
			return true;
		} catch (IOException ee) {
			ee.printStackTrace();
			return false;
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create BufferWriter
	 * 
	 * @param osw OutputStreamWriter
	 * @return bufferWriter
	 */
	private BufferedWriter createBufferWriter(OutputStreamWriter osw) {
		return new BufferedWriter(osw);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.logics.TblUserLogic#getListUserForExport(com.luvina.entities.
	 * SearchingInfo, int, int)
	 */
	@Override
	public List<DisplayUser> getListUserForExport(SearchingInfo info, int currentPage, int maxResult) {
		List<DisplayUser> displayUsers = tblUserDao.getListUsers(info, currentPage, maxResult);
		if (displayUsers.size() != 0) {
			for (DisplayUser displayUser : displayUsers) {
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
		header.append(ValueProperties.getValue("DOB") + ",");
		header.append(ValueProperties.getValue("START_DATE") + ",");
		header.append(ValueProperties.getValue("END_DATE") + ",");
		header.append(ValueProperties.getValue("PLACE_OF_REGISTER"));
		return header.toString();
	}

	/**
	 * Create export File
	 * @param companyName the company's Name
	 * @return file to export
	 */
	private File createExportFile(String companyName) {
		File exportFile = new File("D:\\CSV\\" + companyName + ".csv");
		exportFile.setWritable(true);
		return exportFile;
	}

	/**
	 * Write header of Export File
	 * @param bw bufferWriter
	 * @param jsonCompany detail of the COmpany
	 * @param header columns
	 * @param lineSeparator '\n'
	 * @param commaDelimiter ','
	 * @return bw
	 * @throws IOException
	 */
	private BufferedWriter writeHeaderOfExportFile(BufferedWriter bw, String jsonCompany, String header,
			String lineSeparator, String commaDelimiter) throws IOException {
		JSONObject obj = new JSONObject(jsonCompany);
		String companyName = obj.getString(Constant.COMPANY_NAME);
		String companyAddress = obj.getString(Constant.COMPANY_ADDRESS);
		String companyPhone = obj.getString(Constant.COMPANY_PHONE);
		String companyEmail = obj.getString(Constant.COMPANY_EMAIL);
		bw.append(ValueProperties.getValue("LIST_INSURANCE"));
		// 2 New Line after the header
		bw.append(lineSeparator);
		bw.append(lineSeparator);
		// Adding CompanyName
		bw.append(ValueProperties.getValue("COMPANY_NAME")+commaDelimiter+companyName+lineSeparator);
		// Adding CompanyAddress
		bw.append(ValueProperties.getValue("ADDRESS")+commaDelimiter+companyAddress+lineSeparator);
		// Adding CompanyEmail
		bw.append(ValueProperties.getValue("EMAIL")+commaDelimiter+companyEmail+lineSeparator);
		// Adding companyPhone
		bw.append(ValueProperties.getValue("PHONE_NUMBER")+commaDelimiter+companyPhone+lineSeparator);
		// Adding 2 new line
		bw.append(lineSeparator);
		bw.append(lineSeparator);
		// Adding the header of table
		bw.append(header);
		// New Line after the header
		bw.append(lineSeparator);
		return bw;
	}

	/**
	 * Create File Output Stream
	 * 
	 * @param exportFile
	 * @return
	 * @throws IOException
	 */
	private FileOutputStream createFileOutputStreamWithBom(File exportFile) throws IOException {
		byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		FileOutputStream fos = new FileOutputStream(exportFile, false);
		fos.write(bom);
		return fos;
	}

	/**
	 * 
	 * @param fileOutputStream
	 * @return OutputStreamWriter
	 * @throws UnsupportedEncodingException
	 */
	private OutputStreamWriter createOutputStreamWriter(FileOutputStream fileOutputStream)
			throws UnsupportedEncodingException {
		return new OutputStreamWriter(fileOutputStream, "UTF-8");
	}

	/**
	 * Write content
	 * 
	 * @param bw bufferedWriter
	 * @param searchingInfo info search
	 * @param lineSeparator '\n'
	 * @param commaDelimiter ','
	 * @return bw
	 * @throws IOException
	 */
	private BufferedWriter writeContentFile(BufferedWriter bw, SearchingInfo searchingInfo, String lineSeparator,
			String commaDelimiter) throws IOException {
		int currentPagetoRecord = 1;
		int maxResultToRecord = 5;
		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) getListUserForExport(searchingInfo,
				currentPagetoRecord, maxResultToRecord);
		while (allUsers.size() <= maxResultToRecord) {
			Iterator<DisplayUser> it = allUsers.iterator();
			while (it.hasNext()) {
				DisplayUser displayUser = (DisplayUser) it.next();
				bw.append(displayUser.getUsername());
				bw.append(commaDelimiter);
				bw.append(displayUser.getGender());
				bw.append(commaDelimiter);
				bw.append(displayUser.getBirthdate());
				bw.append(commaDelimiter);
				bw.append(displayUser.getInsuranceNumber());
				bw.append(commaDelimiter);
				bw.append(displayUser.getStartDate());
				bw.append(commaDelimiter);
				bw.append(displayUser.getEndDate());
				bw.append(commaDelimiter);
				bw.append(displayUser.getPlaceOfRegister());
				bw.append(lineSeparator);
			}
			if (allUsers.size() == maxResultToRecord) {
				currentPagetoRecord++;
				allUsers = (ArrayList<DisplayUser>) getListUserForExport(searchingInfo, currentPagetoRecord,
						maxResultToRecord);
			} else if (allUsers.size() < maxResultToRecord) {
				break;
			}
		}
		return bw;
	}
}
