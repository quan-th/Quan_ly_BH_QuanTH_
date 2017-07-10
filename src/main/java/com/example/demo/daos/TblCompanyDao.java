/**
 * Copyright(C) 2016  Luvina
 * TblCompanyDao.java,Dec 19, 2016,HP
 */
package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TblCompany;

/**
 * @author HP
 * TblCompanyDao
 */
public interface TblCompanyDao extends JpaRepository<TblCompany, Integer>{	
	/**
	 * get all TblCompanys
	 */
	public List<TblCompany> findAll();	
	/**
	 * find TblCompany by InternalId
	 * @param integer internalId
	 * @return tblCOmpany
	 */
	public TblCompany findByCompanyInternalId(Integer integer);
	/**
	 * save TblCompany
	 */
	public TblCompany save(TblCompany tblCompany);
}
