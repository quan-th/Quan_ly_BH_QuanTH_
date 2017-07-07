/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceDao.java,Jan 4, 2017,HP
 */
package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;

/**
 * @author HP
 * TblInsuranceDao
 */
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer>{
	/**
	 * Tìm kiếm theo số thẻ bảo hiểm
	 * @param insuranceNumber
	 * @return TblInsurance thẻ bảo hiểm cần tìm
	 */
	public TblInsurance findByInsuranceNumber(String insuranceNumber);
	public TblInsurance save(TblInsurance tblCompany);
	public void delete(TblInsurance tblCompany);
}
