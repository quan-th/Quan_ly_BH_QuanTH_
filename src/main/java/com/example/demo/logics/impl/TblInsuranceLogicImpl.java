/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceLogicImpl.java,Jan 4, 2017,HP
 */
package com.example.demo.logics.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.daos.TblInsuranceDao;
import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.InsuranceInfo;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         TblInsuranceLogicImpl
 */
@Component
public class TblInsuranceLogicImpl implements TblInsuranceLogic {
	@Autowired
	private TblInsuranceDao tblInsuranceDao;
	@Autowired
	private TblCompanyDao tblCompanyDao;
	@Autowired
	private TblUserDao tblUserDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblInsuranceLogic#checkExist(java.lang.String)
	 */
	@Override
	public boolean checkExist(String number) {
		return tblInsuranceDao.checkExist(number);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.logics.TblInsuranceLogic#insertOrUpdateInsurance(com.luvina.
	 * entities.InsuranceInfo)
	 */
	@Override
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo) {
//		TblCompany company = null;
//		TblInsurance tblInsurance = null;
//		TblUser tblUser = null;
//		try {
//			if (insuranceInfo.getUserId() != 0) {
//				tblUser = tblUserDao.getUserById(insuranceInfo.getUserId());
//			} else {
//				tblUser = new TblUser();
//			}
//
//			if (Constant.ALREADY_HAVE.equals(insuranceInfo.getChoseCompany())) {
//				company = tblCompanyDao.getTblCompanyById(Integer.parseInt(insuranceInfo.getCompanyId()));
//			} else {
//				company = new TblCompany();
//				company.setCompanyName(insuranceInfo.getCompanyName());
//				company.setAddress(insuranceInfo.getCompanyAddress());
//				company.setEmail(insuranceInfo.getCompanyEmail());
//				company.setTelephone(insuranceInfo.getCompanyPhone());
//			}
//			if (insuranceInfo.getUserId() != 0) {
//				tblInsurance = tblInsuranceDao.getTblInsuranceByUserId(insuranceInfo.getUserId());
//			} else {
//				tblInsurance = new TblInsurance();
//			}
//			tblInsurance.setInsuranceNumber(insuranceInfo.getInsuranceNumber());
//			tblInsurance.setPlaceOfRegister(insuranceInfo.getPlaceOfRegister());
//			tblInsurance.setInsuranceStartDate(Common.convertStringToDate(insuranceInfo.getStartDate()).toString());
//			tblInsurance.setInsuranceEndDate(Common.convertStringToDate(insuranceInfo.getEndDate()).toString());
//			// trư�?ng hợp thêm mới add userId và userPassword
//			if (insuranceInfo.getUserId() == 0) {
//				tblUser.setUserName("admin");
//				tblUser.setUserPassword("admin");
//			} else {
//				// trư�?ng hợp thêm mới add userId và userPassword như cũ
//				tblUser.setUserName(insuranceInfo.getUsername());
//				tblUser.setUserPassword(insuranceInfo.getUserPassword());
//			}
//			tblUser.setBirthday(Common.convertStringToDate(insuranceInfo.getBirthdate()));
//			tblUser.setUserFullName(Common.normarlizeString(insuranceInfo.getFullname()));
//			tblUser.setUserSexDivision(insuranceInfo.getGender());
//			tblUser.setTblCompany(company);
//			tblUser.setTblInsurance(tblInsurance);
//
//			return tblInsuranceDao.insertOrUpdateInsurance(insuranceInfo, company, tblUser, tblInsurance);
//		} catch (Exception e) {
//			return false;
//		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblInsuranceLogic#getInsuranceInfo(int)
	 */
	@Override
	public InsuranceInfo getInsuranceInfo(int userId) {
		InsuranceInfo insuranceInfo = tblInsuranceDao.getInsuranceInfo(userId);
		insuranceInfo.setGender(Common.convertGender(insuranceInfo.getGender()));
		insuranceInfo.setBirthdate(Common.convertDate(insuranceInfo.getBirthdate()));
		insuranceInfo.setStartDate(Common.convertDate(insuranceInfo.getStartDate()));
		insuranceInfo.setEndDate(Common.convertDate(insuranceInfo.getEndDate()));
		return insuranceInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public boolean deleteInsurance(int id) {
		try {
			return tblInsuranceDao.deleteInsurance(id);
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
}
