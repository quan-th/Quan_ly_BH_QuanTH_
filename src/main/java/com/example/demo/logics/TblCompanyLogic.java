/**
 * Copyright(C) 2016  Luvina
 * TblCompanyLogic.java,Dec 19, 2016,HP
 */
package com.example.demo.logics;

import java.util.List;

import com.example.demo.entities.Company;

/**
 * @author HP
 *
 * interface TblCompanyLogic
 */
public interface TblCompanyLogic {
	/**
	 * get All Company
	 * @return List<Company> 
	 */
	public List<Company> getAllCompany();
	/**
	 * get company details
	 * @param id company Internal ID
	 * @return json details company
	 */
	public String getJsonCompanyById(int id);
	
}
