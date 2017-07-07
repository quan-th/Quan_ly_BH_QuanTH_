/**
 * Copyright(C) 2016  Luvina
 * TblUserDaoImpl.java,Dec 14, 2016,HP
 */
package com.example.demo.daos.impls;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.example.demo.daos.TblUserDaoCustom;
import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;

/**
 * @author HP
 * TblUserDaoImpl
 */
@Component
@Transactional
public class TblUserDaoImpl implements TblUserDaoCustom {
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.luvina.daos.TblUserDao#getListUser(com.luvina.entities.SearchingInfo)
	 */
	@Override
	public List<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult) {
		ArrayList<DisplayUser> displayUsers = new ArrayList<DisplayUser>();
		ArrayList<String> conditions = new ArrayList<String>();

		if (info != null) {
			if (info.getUsername().equals("") == false && Common.isNull(info.getUsername()) == false) {
				conditions.add(Constant.DAO_USER_NAME);
			}
			if (info.getInsuranceId().equals("") == false && Common.isNull(info.getInsuranceId()) == false) {
				conditions.add(Constant.DAO_INSURANCE_NUMBER);
			}
			if (info.getPlaceOfRegister().equals("") == false && Common.isNull(info.getPlaceOfRegister()) == false) {
				conditions.add(Constant.DAO_PLACE_OF_REGISTER);
			}
			if (info.getCompanyId().equals("0") == false) {
				conditions.add(Constant.DAO_COMPANY_ID);
			}
		}
		try {

			StringBuilder command = new StringBuilder("select new " + DisplayUser.class.getName());
			command.append("(u.userInternalId,");
			command.append(" u.userFullName,");
			command.append(" u.userSexDivision,");
			command.append(" u.birthday,");
			command.append(" u.tblInsurance.insuranceNumber,");
			command.append(" u.tblInsurance.insuranceStartDate,");
			command.append(" u.tblInsurance.insuranceEndDate,");
			command.append(" u.tblInsurance.placeOfRegister)");
			command.append(" from ");
			command.append(TblUser.class.getName());
			command.append(" u inner join u.tblInsurance");
			int countConditions = 1;
			if (conditions.size() != 0) {
				command.append(" Where ");
				for (String condition : conditions) {
					if (condition.equals(Constant.DAO_USER_NAME)) {
						command.append("u.userFullName like ? ");
					}
					if (condition.equals(Constant.DAO_INSURANCE_NUMBER)) {
						command.append("u.tblInsurance.insuranceNumber = ? ");
					}
					if (condition.equals(Constant.DAO_PLACE_OF_REGISTER)) {
						command.append("u.tblInsurance.placeOfRegister like ? ");
					}
					if (condition.equals(Constant.DAO_COMPANY_ID)) {
						command.append("u.tblCompany.companyInternalId = ? ");
					}
					if (countConditions < conditions.size()) {
						command.append(" And ");
					}
					countConditions++;
				}
			}
			command.append(" order by u.userFullName " + Common.validOrder(info.getOrderByName()));
			Query query = entityManager.createQuery(command.toString(), DisplayUser.class);
			if (currentPage != 0 && maxResult != 0) {
				query.setFirstResult(getStartPosition(currentPage, maxResult));
				query.setMaxResults(maxResult);
			}
			countConditions = 0;
			if (conditions.size() != 0) {

				for (String condition : conditions) {
					if (condition.equals(Constant.DAO_USER_NAME)) {
						query.setParameter(countConditions, "%" + Common.escapeWildcard(info.getUsername()) + "%");
					}
					if (condition.equals(Constant.DAO_INSURANCE_NUMBER)) {
						query.setParameter(countConditions, info.getInsuranceId());
					}
					if (condition.equals(Constant.DAO_PLACE_OF_REGISTER)) {
						query.setParameter(countConditions,
								"%" + Common.escapeWildcard(info.getPlaceOfRegister()) + "%");
					}
					if (condition.equals(Constant.DAO_COMPANY_ID)) {
						query.setParameter(countConditions, Integer.parseInt(info.getCompanyId()));
					}
					countConditions++;
				}
			}
			displayUsers = (ArrayList<DisplayUser>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return displayUsers;
	}

	/**
	 * Lấy vị trí record đầu tiên
	 * 
	 * @param currentPage
	 *            trang hiện tại
	 * @param maxResult
	 *            kết quả / trang
	 * @return
	 */
	private int getStartPosition(int currentPage, int maxResult) {
		return (currentPage - 1) * maxResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblUserDao#getNumberOfUsers(com.luvina.entities.
	 * SearchingInfo)
	 */
	@Override
	public long getNumberOfUsers(SearchingInfo info) {

		ArrayList<String> conditions = new ArrayList<String>();
		long count = 0;
		if (info != null) {
			if (info.getUsername().equals("") == false && Common.isNull(info.getUsername()) == false) {
				conditions.add(Constant.DAO_USER_NAME);
			}
			if (info.getInsuranceId().equals("") == false && Common.isNull(info.getInsuranceId()) == false) {
				conditions.add(Constant.DAO_INSURANCE_NUMBER);
			}
			if (info.getPlaceOfRegister().equals("") == false && Common.isNull(info.getPlaceOfRegister()) == false) {
				conditions.add(Constant.DAO_PLACE_OF_REGISTER);
			}
			if (info.getCompanyId().equals("0") == false) {
				conditions.add(Constant.DAO_COMPANY_ID);
			}
		}
		try {

			StringBuilder command = new StringBuilder(
					"select count(*) from " + TblUser.class.getName() + " u inner join u.tblInsurance ");
			int countConditions = 1;
			if (conditions.size() != 0) {
				command.append(" Where ");
				for (String condition : conditions) {
					if (condition.equals(Constant.DAO_USER_NAME)) {
						command.append("u.userFullName like ? ");
					}
					if (condition.equals(Constant.DAO_INSURANCE_NUMBER)) {
						command.append("u.tblInsurance.insuranceNumber = ? ");
					}
					if (condition.equals(Constant.DAO_PLACE_OF_REGISTER)) {
						command.append("u.tblInsurance.placeOfRegister like ? ");
					}
					if (condition.equals(Constant.DAO_COMPANY_ID)) {
						command.append("u.tblCompany.companyInternalId = ? ");
					}
					if (countConditions < conditions.size()) {
						command.append(" And ");
					}
					countConditions++;
				}
			}

			countConditions = 0;
			Query query = entityManager.createQuery(command.toString());
			if (conditions.size() != 0) {

				for (String condition : conditions) {
					if (condition.equals(Constant.DAO_USER_NAME)) {
						query.setParameter(countConditions, "%" + Common.escapeWildcard(info.getUsername()) + "%");
					}
					if (condition.equals(Constant.DAO_INSURANCE_NUMBER)) {
						query.setParameter(countConditions, info.getInsuranceId());
					}
					if (condition.equals(Constant.DAO_PLACE_OF_REGISTER)) {
						query.setParameter(countConditions,
								"%" + Common.escapeWildcard(info.getPlaceOfRegister()) + "%");
					}
					if (condition.equals(Constant.DAO_COMPANY_ID)) {
						query.setParameter(countConditions, Integer.parseInt(info.getCompanyId()));
					}
					countConditions++;
				}
			}
			count = (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblUserDao#getDetailUser(int)
	 */
	@Override
	public DetailUser getDetailUser(int id) {
		DetailUser detailUser = new DetailUser();

		try {

			StringBuilder command = new StringBuilder();
			command.append(" select new ");
			command.append(DetailUser.class.getName());
			command.append("(u.userInternalId,");
			command.append(" u.userFullName,");
			command.append(" u.userSexDivision,");
			command.append(" u.birthday,");
			command.append(" u.tblInsurance.insuranceNumber,");
			command.append(" u.tblInsurance.insuranceStartDate,");
			command.append(" u.tblInsurance.insuranceEndDate,");
			command.append(" u.tblInsurance.placeOfRegister,");
			command.append(" u.tblCompany.companyName)");
			command.append(" from ");
			command.append(TblUser.class.getName());
			command.append(" u inner join u.tblInsurance inner join u.tblCompany where");
			command.append(" u.userInternalId=:userId");
			Query query = entityManager.createQuery(command.toString(), DetailUser.class);
			query.setParameter("userId", id);
			detailUser = (DetailUser) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblUserDao#checkUserExist(int)
	 */
	@Override
	public boolean checkUserExist(int id) {

		long countUser = 0;
		try {

			String command = "select count(*) from " + TblUser.class.getName() + " u where u.userInternalId=:userId";
			Query query = entityManager.createQuery(command.toString());
			query.setParameter("userId", id);
			countUser = (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countUser != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luvina.daos.TblUserDao#getUserById(int)
	 */
	@Override
	public TblUser getUserById(int id) {

		TblUser tblUser = (TblUser) entityManager
				.createQuery("Select u from " + TblUser.class.getName() + " u where u.userInternalId =:userInternalId")
				.setParameter("userInternalId", id).getSingleResult();
		return tblUser;
	}

}
