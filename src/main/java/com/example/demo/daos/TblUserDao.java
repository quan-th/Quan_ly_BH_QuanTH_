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
	 * Kiem tra login co thanh cong khong
	 * @param Username username
	 * @param Password password
	 * @return thanh cong->true, khong thanh cong->fail
	 */
	public List<TblUser> findByUserNameAndUserPassword(String Username, String Password);

	public TblUser findByUserInternalId(Integer integer);
	public TblUser save(TblUser tblUser);
}
