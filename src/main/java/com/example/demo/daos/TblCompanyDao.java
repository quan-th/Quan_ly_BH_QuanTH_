/**
 * Copyright(C) 2016  Luvina
 * TblCompanyDao.java,Dec 19, 2016,HP
 */
package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;

/**
 * @author HP
 *
 *         TblCompanyDao
 */
public interface TblCompanyDao extends JpaRepository<TblCompany, Integer>,TblCompanyDaoCustom {	
	public List<TblCompany> findAll();	
	public TblCompany findByCompanyInternalId(Integer integer);
}
