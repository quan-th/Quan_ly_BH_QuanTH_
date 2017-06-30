/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics;

import java.util.List;

import com.example.demo.entities.TblUser;

/**
 * @author HP
 *
 * TblUserLogic
 */
public interface TblUserLogic {
	/**
	 * Kiem tra login co thanh cong khong
	 * @param Username username
	 * @param Password password
	 * @return thanh cong->true, khong thanh cong->fail
	 */
	public List<TblUser> LoginByUsernameAndPassword(String Username,String Password);
}
