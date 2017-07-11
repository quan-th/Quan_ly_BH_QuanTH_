/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceDao.java,Jan 4, 2017,HP
 */
package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TblInsurance;

/**
 * @author HP
 * TblInsuranceDao
 */
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer>{
	/**
	 * find TblInsurance by insuranceNumber
	 * @param insuranceNumber insuranceNumber
	 * @return TblInsurance tblInsurance
	 */
	public TblInsurance findByInsuranceNumber(String insuranceNumber);
	/**
	 * save TblIsurance
	 */
	public TblInsurance save(TblInsurance tblCompany);
	/**
	 * Delete tblInsurance
	 */
	public void delete(TblInsurance tblCompany);
}
