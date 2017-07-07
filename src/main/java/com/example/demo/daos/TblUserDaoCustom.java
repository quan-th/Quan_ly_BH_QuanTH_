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
	 * Lấy danh sách người dùng theo thông tin tìm kiếm
	 * 
	 * @param info
	 *            thông tin tìm kiếm
	 * @param currentPage
	 *            trang hiện tjai
	 * @param maxResult
	 *            số record/trang
	 * @return danh sách User
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult);

	/**
	 * Lấy tổng số user
	 * 
	 * @param info
	 *            thông tin tìm kiếm
	 * @return số user
	 */
	public long getNumberOfUsers(SearchingInfo info);

	/**
	 * Lấy thông tin chi tiết User
	 * 
	 * @param id
	 *            userId
	 * @return thông tin chi tiết  User
	 */
	public DetailUser getDetailUser(int id);

	/**
	 * Kiểm tra tồn tại user
	 * 
	 * @param id
	 *            userid
	 * @return true nếu có false nếu không
	 */
	public boolean checkUserExist(int id);
	/**
	 * lay user theo Id
	 * @param id userId
	 * @return TblUser
	 */
	public TblUser getUserById(int id);

}
