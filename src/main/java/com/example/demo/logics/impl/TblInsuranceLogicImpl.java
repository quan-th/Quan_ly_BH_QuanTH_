/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceLogicImpl.java,Jan 4, 2017,HP
 */
package com.example.demo.logics.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
 * @author HP TblInsuranceLogicImpl
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
		return tblInsuranceDao.findByInsuranceNumber(number) != null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.logics.TblInsuranceLogic#insertOrUpdateInsurance(com.luvina.
	 * entities.InsuranceInfo)
	 */
	@Transactional
	@Override
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo) {
		TblUser tblUser = createTblUserForInsertOrUpdate(insuranceInfo.getUserId());
		TblCompany tblCompany = createTblCompanyForInsertOrUpdate(insuranceInfo);
		TblInsurance tblInsurance = createTblInsuranceForInsertOrUpdate(insuranceInfo);
		tblUser = setDetailForTblUser(tblUser,insuranceInfo);
		tblUser.setTblCompany(tblCompany);
		tblUser.setTblInsurance(tblInsurance);
		if (Constant.ADD_NEW_COMPANY.equals(insuranceInfo.getChoseCompany())) {
			tblCompanyDao.save(tblCompany);
		}
		tblInsuranceDao.save(tblInsurance);
		tblUserDao.save(tblUser);
    	return true;
	}
	/**
	 * set more Details For TblUser
	 * @param tblUser tblUser
	 * @param insuranceInfo insuranceInfo
	 * @return tblUser
	 */
	private TblUser setDetailForTblUser(TblUser tblUser, InsuranceInfo insuranceInfo) {
		if (insuranceInfo.getUserId() == 0) {
			tblUser.setUserName("admin");
			tblUser.setUserPassword("admin");
		} else {
			// trường hợp thêm mới add userId và userPassword như cũ
			tblUser.setUserName(insuranceInfo.getUsername());
			tblUser.setUserPassword(insuranceInfo.getUserPassword());
		}
		tblUser.setBirthday(Common.convertStringToDate(insuranceInfo.getBirthdate()));
		tblUser.setUserFullName(Common.normarlizeString(insuranceInfo.getFullname()));
		tblUser.setUserSexDivision(insuranceInfo.getGender());
		return tblUser;
	}

	/**
	 * create TblInsurance For Insert Or Update
	 * @param insuranceInfo insuranceInfo
	 * @return
	 */
	private TblInsurance createTblInsuranceForInsertOrUpdate(InsuranceInfo insuranceInfo) {
		TblInsurance tblInsurance = null;
		if (insuranceInfo.getUserId() != 0) {
			tblInsurance = tblUserDao.findByUserInternalId(insuranceInfo.getUserId()).getTblInsurance();
		} else {
			tblInsurance = new TblInsurance();
		}
		tblInsurance.setInsuranceNumber(insuranceInfo.getInsuranceNumber());
		tblInsurance.setPlaceOfRegister(insuranceInfo.getPlaceOfRegister());
		tblInsurance.setInsuranceStartDate(Common.convertStringToDate(insuranceInfo.getStartDate()).toString());
		tblInsurance.setInsuranceEndDate(Common.convertStringToDate(insuranceInfo.getEndDate()).toString());
		return tblInsurance;
	}

	/**
	 * create TblCompany For Insert Or Update
	 * @param insuranceInfo insurentInfo
	 * @return tblCompany
	 */
	private TblCompany createTblCompanyForInsertOrUpdate(InsuranceInfo insuranceInfo) {
		TblCompany tblCompany = null;
		if (Constant.ALREADY_HAVE.equals(insuranceInfo.getChoseCompany())) {
			tblCompany = tblCompanyDao.findByCompanyInternalId(Integer.parseInt(insuranceInfo.getCompanyId()));
		} else {
			tblCompany = new TblCompany();
			tblCompany.setCompanyName(insuranceInfo.getCompanyName());
			tblCompany.setAddress(insuranceInfo.getCompanyAddress());
			tblCompany.setEmail(insuranceInfo.getCompanyEmail());
			tblCompany.setTelephone(insuranceInfo.getCompanyPhone());
		}
		return tblCompany;
	}

	/**
	 * Create TblUser for insert and update
	 * @param userId
	 * @return
	 */
	private TblUser createTblUserForInsertOrUpdate(int userId) {
		if (userId != 0) {
			return tblUserDao.findByUserInternalId(userId);
		} else {
			return new TblUser();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblInsuranceLogic#getInsuranceInfo(int)
	 */
	@Override
	public InsuranceInfo getInsuranceInfo(int userId) {
		TblUser tblUser = tblUserDao.findByUserInternalId(userId);
		InsuranceInfo insuranceInfo = new InsuranceInfo();
		insuranceInfo.setFullname(tblUser.getUserFullName());
		insuranceInfo.setGender(Common.convertGender(tblUser.getUserSexDivision()));
		insuranceInfo.setBirthdate(Common.convertDate(tblUser.getBirthday()));
		insuranceInfo.setInsuranceNumber(tblUser.getTblInsurance().getInsuranceNumber());
		insuranceInfo.setStartDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceStartDate()));
		insuranceInfo.setEndDate(Common.convertDate(tblUser.getTblInsurance().getInsuranceEndDate()));
		insuranceInfo.setPlaceOfRegister(tblUser.getTblInsurance().getPlaceOfRegister());
		insuranceInfo.setCompanyId(tblUser.getTblCompany().getCompanyInternalId() + "");
		insuranceInfo.setUsername(tblUser.getUserName());
		insuranceInfo.setUserPassword(tblUser.getUserPassword());
		insuranceInfo.setUserId(userId);
		return insuranceInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblUserLogic#deleteUser(int)
	 */
	@Transactional
	@Override
	public boolean deleteInsurance(int id) {
		TblUser tblUser = tblUserDao.findByUserInternalId(id);
		tblUserDao.delete(tblUser);
		tblInsuranceDao.delete(tblUser.getTblInsurance());
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.logics.TblInsuranceLogic#checkValidInsuranceForUpdate(
	 * com.example.demo.entities.InsuranceInfo)
	 */
	@Override
	public boolean checkValidInsuranceForUpdate(InsuranceInfo insuranceInfo) {
		TblUser tblUser = tblUserDao.findByUserInternalId(insuranceInfo.getUserId());
		if (insuranceInfo.getInsuranceNumber().equals(tblUser.getTblInsurance().getInsuranceNumber())) {
			return true;
		}
		TblInsurance tblInsurance = tblInsuranceDao.findByInsuranceNumber(insuranceInfo.getInsuranceNumber());
		if (tblInsurance == null) {
			return true;
		}
		return false;
	}
}
