/**
 * Copyright(C) 2016  Luvina
 * TblCompanyDaoImpl.java,Dec 19, 2016,HP
 */
package com.example.demo.daos.impls;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.daos.TblCompanyDaoCustom;
import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         TblCompanyDaoImpl
 */

@Transactional
public class TblCompanyDaoImpl implements TblCompanyDaoCustom {
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
