/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics.impl;

import java.util.ArrayList;
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
	public ArrayList<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult) {
		ArrayList<DisplayUser> displayUsers = (ArrayList<DisplayUser>) tblUserDao.getListUsers(info,currentPage,maxResult);
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
}

