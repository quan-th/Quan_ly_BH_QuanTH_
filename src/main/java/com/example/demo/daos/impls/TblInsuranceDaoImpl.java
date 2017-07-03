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
		
//			if (Constant.ADD_NEW_COMPANY.equals(insuranceInfo.getChoseCompany())) {
//				entityManager.persist(company);
//			}
//			entityManager.persist(insurance);
//			entityManager.persist(user);
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
//		try {
//			
//			StringBuilder command = new StringBuilder();
//			command.append("select new " + InsuranceInfo.class.getName());
//			command.append("(u.userFullName,");
//			command.append("u.userSexDivision,");
//			command.append("u.birthday,");
//			command.append("u.tblInsurance.insuranceNumber,");
//			command.append("u.tblInsurance.insuranceStartDate,");
//			command.append("u.tblInsurance.insuranceEndDate,");
//			command.append("u.tblInsurance.placeOfRegister,");
//			command.append("u.tblCompany.companyInternalId,");
//			command.append("u.userName,");
//			command.append("u.userPassword)");
//			command.append("from " + TblUser.class.getName());
//			command.append(" u inner join u.tblInsurance inner join u.tblCompany where u.userInternalId=:userId");
//			Query query = entityManager.createQuery(command.toString(), InsuranceInfo.class);
//			query.setParameter("userId", userId);
//			detailUser = (InsuranceInfo) query.getSingleResult();
//			detailUser.setUserId(userId);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
			
//			TblUser tblUser = (TblUser) entityManager
//					.createQuery("select u from " + TblUser.class.getName() + " u where u.userInternalId=:userId")
//					.setParameter("userId", id).getSingleResult();
//			entityManager.remove(tblUser);
//			entityManager.remove(tblUser.getTblInsurance());
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblInsuranceDao#getTblInsuranceByUserId(int)
	 */
	@Override
	public TblInsurance getTblInsuranceByUserId(int id) {
		
//		int insuranceId = (int) entityManager
//				.createQuery("Select c.tblInsurance.insuranceInternalId from " + TblUser.class.getName()
//						+ " c where c.userInternalId =:userInternalId")
//				.setParameter("userInternalId", id).getSingleResult();
//		TblInsurance tblInsurance = (TblInsurance) entityManager
//				.createQuery("Select c from " + TblInsurance.class.getName()
//						+ " c where c.insuranceInternalId =:insuranceInternalId")
//				.setParameter("insuranceInternalId", insuranceId).getSingleResult();
		TblUser tblUser =(TblUser) entityManager
				.createQuery("Select c from " + TblUser.class.getName()
						+ " c where c.userInternalId =:userInternalId")
				.setParameter("userInternalId", id).getSingleResult();
//		return tblInsurance;
		return tblUser.getTblInsurance();
	}

}
