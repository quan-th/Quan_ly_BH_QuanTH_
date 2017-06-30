/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.TblUserLogic;

/**
 * @author HP
 *
 *         TblUserLogic
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
}
