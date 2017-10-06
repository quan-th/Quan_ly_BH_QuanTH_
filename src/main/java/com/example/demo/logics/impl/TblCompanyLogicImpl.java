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
import com.example.demo.utils.Constant;

/**
 * @author HP
 * TblCompanyLogicImpl
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
		
		List<TblCompany> allCompany= tblCompanyDao.findAll();
		System.out.println();
		List<Company> retCompanys = new ArrayList<>();
		allCompany.forEach(tblCompany->{
			Company company= new Company(tblCompany.getCompanyInternalId(), tblCompany.getCompanyName());
			retCompanys.add(company);
		});
		return retCompanys;
	}
	/*
	 * (non-Javadoc)
	 * @see com.luvina.logics.TblCompanyLogic#getCompanyById(int)
	 */
	@Override
	public String getJsonCompanyById(int id) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println();
		TblCompany tblCompany = tblCompanyDao.findByCompanyInternalId(id);
		String result = "{\"" + Constant.COMPANY_NAME + "\":\"" + tblCompany.getCompanyName() + "\",\""
				+ Constant.COMPANY_ADDRESS + "\":\"" + tblCompany.getAddress() + "\",\"" + Constant.COMPANY_EMAIL
				+ "\":\"" + tblCompany.getEmail() + "\",\"" + Constant.COMPANY_PHONE + "\":\""
				+ tblCompany.getTelephone() + "\"}";
		return result;
		
	}

}
