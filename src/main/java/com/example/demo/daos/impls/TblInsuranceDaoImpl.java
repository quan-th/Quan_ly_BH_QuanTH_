/**
 * Copyright(C) 2016  Luvina
 * TblInsuranceDaoImpl.java,Jan 4, 2017,HP
 */
package com.example.demo.daos.impls;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.TblInsuranceDaoCustom;
import com.example.demo.entities.InsuranceInfo;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         TblInsuranceDaoImpl
 */
@Component
@Transactional
public class TblInsuranceDaoImpl implements TblInsuranceDaoCustom {
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#checkExist(java.lang.String)
	 */
	@Override
	public boolean checkExist(String number) {
		long count = 0;

//		try {
//			
//			StringBuilder command = new StringBuilder("Select count(*) from " + TblInsurance.class.getName() + " c "//
//					+ " where c.insuranceNumber= :insuranceNumber ");
//			Query query = entityManager.createQuery(command.toString());
//			query.setParameter("insuranceNumber", number);
//			count = (long) query.getSingleResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return count != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#insertOrUpdateInsurance(com.luvina.
	 * entities.InsuranceInfo)
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean insertOrUpdateInsurance(InsuranceInfo insuranceInfo, TblCompany company, TblUser user,
			TblInsurance insurance) throws Exception {
			return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#getInsuranceInfo(int)
	 */
	@Override
	public InsuranceInfo getInsuranceInfo(int userId) {
		InsuranceInfo detailUser = null;
		return detailUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#deleteInsurance(int)
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean deleteInsurance(int id) throws Exception {
			

			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#getTblInsuranceByUserId(int)
	 */
	@Override
	public TblInsurance getTblInsuranceByUserId(int id) {
		TblUser tblUser =(TblUser) entityManager
				.createQuery("Select c from " + TblUser.class.getName()
						+ " c where c.userInternalId =:userInternalId")
				.setParameter("userInternalId", id).getSingleResult();
		return tblUser.getTblInsurance();
	}

}