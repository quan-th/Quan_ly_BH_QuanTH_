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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.TblCompanyDaoCustom;
import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         TblCompanyDaoImpl
 */
@Component
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblCompanyDao#getAllCompany()
	 */
	@Override
	public List<Company> getAllCompany() {
		ArrayList<Company> companies = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuilder command = new StringBuilder("select new " + Company.class.getName());
			command.append("(u.companyInternalId,");
			command.append("u.companyName)");
			command.append("from " + TblCompany.class.getName());
			command.append(" u order by companyName ASC");
			Query<Company> query = session.createQuery(command.toString(), Company.class);
			companies = (ArrayList<Company>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblCompanyDao#getCompanyById(int)
	 */
	@Override
	public String getCompanyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String result = "";
		try {
			StringBuilder command = new StringBuilder("Select c from " + TblCompany.class.getName() + " c "//
					+ " where c.companyInternalId= :companyInternalId ");
			Query<TblCompany> query = session.createQuery(command.toString(), TblCompany.class);
			query.setParameter("companyInternalId", id);
			TblCompany tblCompany = (TblCompany) query.getSingleResult();
			result = "{\"" + Constant.COMPANY_NAME + "\":\"" + tblCompany.getCompanyName() + "\",\""
					+ Constant.COMPANY_ADDRESS + "\":\"" + tblCompany.getAddress() + "\",\"" + Constant.COMPANY_EMAIL
					+ "\":\"" + tblCompany.getEmail() + "\",\"" + Constant.COMPANY_PHONE + "\":\""
					+ tblCompany.getTelephone() + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblCompanyDao#getCompanyByID(int)
	 */
	@Override
	public TblCompany getTblCompanyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder command = new StringBuilder("Select c from " + TblCompany.class.getName() + " c "//
				+ " where c.companyInternalId= :UserInternalId ");
		Query<TblCompany> query = session.createQuery(command.toString(), TblCompany.class);
		query.setParameter("UserInternalId", id);
		return query.getSingleResult();
	}

}
