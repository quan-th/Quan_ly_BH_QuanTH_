/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceDao.java,Jan 4, 2017,HP
 */
package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;

/**
 * @author HP
 *
 *         TblInsuranceDao
 */
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer>, TblInsuranceDaoCustom {
	/**
	 * Tìm kiếm theo số thẻ bảo hiểm
	 * @param insuranceNumber
	 * @return TblInsurance thẻ bảo hiểm cần tìm
	 */
	public TblInsurance findByInsuranceNumber(String insuranceNumber);
	public TblInsurance save(TblInsurance tblCompany);
}
