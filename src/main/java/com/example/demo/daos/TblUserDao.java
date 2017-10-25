/**
 * Copyright(C) 2016  Luvina
 * TblUserDao.java,Dec 14, 2016,HP
 */
package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TblUser;


/**
 * @author HP
 * TblUserDao
 */
@Repository
public interface TblUserDao extends JpaRepository<TblUser,Integer>,TblUserDaoCustom{
	/**
	 * find List<TblUser> by UserNameAndUserPassword
	 * @param Username username
	 * @param Password password
	 * @return List<TblUser>
	 */
	public List<TblUser> findByUserNameAndUserPassword(String Username, String Password);
	/**
	 * find TblUser by UserInternalId
	 * @param integer UserInternalId
	 * @return TblUser
	 */
	public TblUser findByUserInternalId(Integer integer);
	/**
	 * save TblUser
	 */
	public TblUser save(TblUser tblUser);
	/**
	 * delete TblUser
	 */
	public void delete(TblUser tblUser);
}
