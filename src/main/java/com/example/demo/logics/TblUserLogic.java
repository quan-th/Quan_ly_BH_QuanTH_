/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;

/**
 * @author HP
 *
 * interface TblUserLogic
 */
public interface TblUserLogic {
	/**
	 * Check Login success
	 * @param Username username
	 * @param Password password
	 * @return success->true, unsuccess->fail
	 */
	public List<TblUser> LoginByUsernameAndPassword(String Username,String Password);
	/**
	 * get list user by searching Info
	 * @param info searching Info
	 * @param currentPage current Page
	 * @param maxResult  records/Page
	 * @return list User
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info,int currentPage);
	/**
	 * get User's details
	 * @param id user internal Id
	 * @return DetailUser
	 */
	public DetailUser getDetailUser(int id) throws NullPointerException;
	/**
	 * check exist User
	 * @param id user internal Id
	 * @return true if exist, false if not exist
	 */
	public boolean checkUserExist(int id);
	/**
	 * get number of users
	 * @param info searching Info
	 * @return number of users
	 */
	public long getNumberOfUsers(SearchingInfo info);
	/**
	 * Export CSV
	 * @param searchingInfo searching infomation
	 * @param jsonCompany json Company
	 * @return success-> true, unsuccess-> false
	 * @throws IOException 
	 */
	public boolean exportUsers(OutputStream outputStream,SearchingInfo searchingInfo, String jsonCompany) throws IOException;
	/**
	 * get List UserForExport 
	 * @param info searching infomation
	 * @param currentPage current Page
	 * @param maxResult records/page
	 * @return List<DisplayUser>
	 */
	public List<DisplayUser> getListUserForExport(SearchingInfo info, int currentPage);
}
