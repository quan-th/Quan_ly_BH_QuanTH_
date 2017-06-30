/**
 * Copyright(C) 2016  Luvina
 * TblCompanyLogicImpl.java,Dec 19, 2016,HP
 */
package com.example.demo.logics.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;
import com.example.demo.logics.TblCompanyLogic;

/**
 * @author HP
 *
 *         TblCompanyLogicImpl
 */
@Component
public class TblCompanyLogicImpl implements TblCompanyLogic {
	@Autowired 
	private TblCompanyDao tblCompanyDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.logics.TblCompanyLogic#getAllCompany()
	 */
	@Override
	public List<Company> getAllCompany() {
		// TODO Auto-generated method stub
		List<TblCompany> allCompany= tblCompanyDao.findAll();
		List<Company> retCompanys = new ArrayList<>();
		for (TblCompany tblCompany : allCompany) {
			Company company= new Company(tblCompany.getCompanyInternalId(), tblCompany.getCompanyName());
			retCompanys.add(company);
		}
		return retCompanys;
	}
	/*
	 * (non-Javadoc)
	 * @see com.luvina.logics.TblCompanyLogic#getCompanyById(int)
	 */
	@Override
	public String getCompanyById(int id) {
		// TODO Auto-generated method stub
		return (tblCompanyDao.getCompanyById(id));
	}

}
