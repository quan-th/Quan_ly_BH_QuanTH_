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
	 * Lấy danh sách company
	 * @return mảng company
	 */
	public List<Company> getAllCompany();
	/**
	 * Lấy thông tin Company theo id
	 * @param id id công ty
	 * @return chuỗi json thông tin công ty
	 */
	public String getCompanyById(int id);
	
}
