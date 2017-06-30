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
 *
 */
public interface TblUserDaoCustom {
	/**
	 * lấy danh sách ngư�?i dùng theo đi�?u kiện
	 * 
	 * @param info
	 *            đi�?u kiện tìm kiếm
	 * @param currentPage
	 *            trang hiện tại
	 * @param maxResult
	 *            số record/trang
	 * @return danh sách ngư�?i dùng
	 */
	public List<DisplayUser> getListUsers(SearchingInfo info, int currentPage, int maxResult);

	/**
	 * Lấy tổng số user
	 * 
	 * @param info
	 *            đi�?u kiệm tìm kiếm
	 * @return số user
	 */
	public long getNumberOfUsers(SearchingInfo info);

	/**
	 * Lấy thông tin chi tiết User
	 * 
	 * @param id
	 *            userId
	 * @return thông tin chi tiết User
	 */
	public DetailUser getDetailUser(int id);

	/**
	 * Kiểm tra user có tồn tại không
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
