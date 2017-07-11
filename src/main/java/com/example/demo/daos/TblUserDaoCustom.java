/**
 * 
 */
package com.example.demo.daos;

import java.util.List;

import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;

/**
 * @author tranhongquan
 * TblUserDaoCustom
 */
public interface TblUserDaoCustom {
	/**
	 * get List user by searching Info
	 * @param info searching Info
	 * @param currentPage current Page
	 * @return list User
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info, int currentPage);

	/**
	 * get totalUser
	 * @param info searching Info
	 * @return number of users
	 */
	public long getNumberOfUsers(SearchingInfo info);
}
