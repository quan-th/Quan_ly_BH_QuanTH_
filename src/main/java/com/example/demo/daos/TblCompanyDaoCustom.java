package com.example.demo.daos;

import java.util.List;

import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;

public interface TblCompanyDaoCustom {
	/**
	 * Lấy danh sách company
	 * 
	 * @return mảng company
	 */
	public List<Company> getAllCompany();

	/**
	 * Lấy thông tin Company theo id
	 * 
	 * @param id
	 *            id công ty
	 * @return chuỗi json thông tin công ty
	 */
	public String getCompanyById(int id);

	/**
	 * Lấy thông tin Company theo id
	 * 
	 * @param id
	 *            id nguoi dung
	 * @return Tblcompany theo id
	 */
	public TblCompany getTblCompanyById(int id);
}
