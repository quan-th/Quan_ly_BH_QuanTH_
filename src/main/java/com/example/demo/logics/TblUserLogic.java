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
	 * lấy danh sách ngư�?i dùng theo đi�?u kiện
	 * @param info đi�?u kiện tìm kiếm
	 * @param currentPage trang hiện tại
	 * @param maxResult số record/trang
	 * @return danh sách ngư�?i dùng
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info,int currentPage,int maxResult);
	/**
	 * lấy danh sách ngư�?i dùng theo đi�?u kiện để export file csv
	 * @param info đi�?u kiện tìm kiếm
	 * @param currentPage trang hiện tại
	 * @param maxResult số record/trang
	 * @return danh sách ngư�?i dùng
	 */
//	public List<DisplayUser> getListUserForExport(SearchingInfo info,int currentPage,int maxResult);
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
	 * @param info đi�?u kiệm tìm kiếm
	 * @return số user
	 */
	public long getNumberOfUsers(SearchingInfo info);
	/**
	 * Ghi thong tin user theo yeu cau tim kiem
	 * @param searchingInfo thong tin tim kiem
	 * @param JsonCompany thong tin cong ty
	 * @return true neu thanh cong false neu khong thanh cong
	 */
//	public boolean exportUser(SearchingInfo searchingInfo,String JsonCompany);
}
