/**
 * Copyright(C) 2016  Luvina
 * TblUserLogic.java,Dec 15, 2016,HP
 */
package com.example.demo.logics;

import java.util.List;

import com.example.demo.entities.DetailUser;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
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
	/**
	 * lấy danh sách người dùng theo điều kiện
	 * @param info điều kiện tìm kiếm
	 * @param currentPage trang hiện tại
	 * @param maxResult số record/trang
	 * @return danh sách người dùng
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info,int currentPage,int maxResult);
	/**
	 * Lấy thông tin chi tiết User
	 * @param id userId
	 * @return thông tin chi tiết User
	 */
	public DetailUser getDetailUser(int id);
	/**
	 * Kiểm tra user có tồn tại không
	 * @param id userid
	 * @return true nếu có false nếu không
	 */
	public boolean checkUserExist(int id);
	/**
	 * Lấy tổng số user
	 * @param info điều kiện tìm kiếm
	 * @return số user
	 */
	public long getNumberOfUsers(SearchingInfo info);
}
