/**
 * Copyright(C) 2016  Luvina
 * SearchController.java,Dec 19, 2016,HP
 */
package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Company;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.logics.TblCompanyLogic;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;
import com.example.demo.utils.ValueProperties;

/**
 * @author HP
 *
 *         SearchController
 */
@Controller
public class SearchController {
	@Autowired
	private TblUserLogic tblUserLogic;
	@Autowired
	private TblCompanyLogic tblCompanyLogic;

	/**
	 * được g�?i đến khi ngư�?i dùng ch�?n tìm kiếm
	 * 
	 * @param model
	 *            model
	 * @param searchingInfo
	 *            thông tin tìm kiếm
	 * @return màn hình 002
	 */
	@RequestMapping(value = "/Search.do", method = RequestMethod.GET)
	private String searchUsers(ModelMap model, @ModelAttribute SearchingInfo searchingInfo,
			HttpServletRequest request) {
//		String sessionId = "";
//		String action = request.getParameter("userAction");
//		HttpSession session = request.getSession();
//		int currentPage = 1;
//		int totalRecords = 0;
//		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
//		// back
//		if (Constant.ACTION_BACK_MH002.equals(action)) {
//			sessionId = request.getParameter("sessionId");
//			searchingInfo = (session.getAttribute(sessionId) != null) ? (SearchingInfo) session.getAttribute(sessionId)
//					: new SearchingInfo();
//			if ("0".equals(searchingInfo.getCompanyId())) {
//				List<Company> companies = tblCompanyLogic.getAllCompany();
//				searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
//			}
//			searchingInfo.setOrderByName(Common.validOrder(searchingInfo.getOrderByName()));
//		} else if (Constant.ACTION_SEARCH_MH002.equals(action)) {
//			// search
//			sessionId = searchingInfo.getIdNumber();
//			// lần đầu tìm kiếm
//			if (sessionId.equals("0")) {
//				sessionId = new Date().getTime() + "";
//				searchingInfo.setIdNumber(sessionId);
//			} else {
//				// các lần tìm kiếm sau
//				session.removeAttribute(sessionId);
//			}
//			searchingInfo.setOrderByName(Common.validOrder(searchingInfo.getOrderByName()));
//			session.setAttribute(sessionId, searchingInfo);
//		} else {
//			searchingInfo = new SearchingInfo();
//			List<Company> companies = tblCompanyLogic.getAllCompany();
//			searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
//		}
//		totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
//		try {
//			currentPage = Integer.parseInt(searchingInfo.getCurrentPage());
//			currentPage = currentPage < 1 ? 1 : currentPage;
//			ArrayList<Integer> pages = paging(currentPage, maxResult, totalRecords);
//			ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo,
//					currentPage, maxResult);
//			model.addAttribute("searchingInfo", searchingInfo);
//			model.addAttribute("allUsers", allUsers);
//			model.addAttribute("pages", pages);
//			model.addAttribute("currentPage", currentPage);
//			model.addAttribute("totalPages", getTotalOfPages(maxResult, totalRecords));
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
		return Constant.MH002;
	}

	/**
	 * được g�?i đến sau khi login thành công
	 *
	 * @param model
	 *            model
	 * @return màn hình 002
	 */
	@RequestMapping(value = "/AllUsers.do", method = RequestMethod.GET)
	private String listUsers(ModelMap model, HttpServletRequest request) {
		SearchingInfo searchingInfo = new SearchingInfo();
//		List<Company> companies = tblCompanyLogic.getAllCompany();
//		searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
//		int currentPage = 1;
//		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
//		int totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
//		ArrayList<Integer> pages = paging(currentPage, maxResult, totalRecords);
//		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo, currentPage,
//				maxResult);
		model.addAttribute("searchingInfo", searchingInfo);
//		model.addAttribute("allUsers", allUsers);
		model.addAttribute("allUsers", new ArrayList<DisplayUser>());
//		model.addAttribute("pages", pages);
		model.addAttribute("pages", new ArrayList<Integer>());
//		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentPage", 1);
//		model.addAttribute("totalPages", getTotalOfPages(maxResult, totalRecords));
		model.addAttribute("totalPages", getTotalOfPages(1, 1));
		return Constant.MH002;
	}

	/**
	 * load thông tin mặc định
	 * 
	 * @param model
	 *            model
	 */
	@ModelAttribute("companies")
	private List<Company> loadDefaultValues(ModelMap model) {
		return tblCompanyLogic.getAllCompany();
	}

	/**
	 * Lấy danh sách trang được paging
	 * 
	 * @param currentPage
	 *            trang bắt đầu
	 * @param recordsOfPage
	 *            record trên mỗi trang
	 * @param totalRecords
	 *            tổng số records
	 * @return danh sách trang dùng để paging
	 */
	private ArrayList<Integer> paging(int currentPage, int recordsOfPage, int totalRecords) {
		int numberOfPages = getTotalOfPages(recordsOfPage, totalRecords);
		ArrayList<Integer> pages = new ArrayList<Integer>();
		int startPage = currentPage - 2 > 0 ? currentPage - 2 : 1;
		pages.add(startPage);
		for (int i = 1; startPage + i <= startPage + 4 && startPage + i <= numberOfPages; i++) {
			pages.add(startPage + i);
		}
		return pages;
	}

	/**
	 * Lấy tổng số trang
	 * 
	 * @param startPage
	 *            trang bắt đầu
	 * @param recordsOfPage
	 *            record trên mỗi trang
	 * @return tổng số trang
	 */
	private int getTotalOfPages(int recordsOfPage, int totalRecords) {
		return (totalRecords % recordsOfPage == 0) ? totalRecords / recordsOfPage : totalRecords / recordsOfPage + 1;
	}

	@RequestMapping(value = "/Search.do/CSV", method = RequestMethod.POST)
	private String exportCSV(ModelMap model, @ModelAttribute SearchingInfo searchingInfo) {
//		int currentPage = 1;
//		int totalRecords = 0;
//
//		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
//		try {
//			totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
//			currentPage = Integer.parseInt(searchingInfo.getCurrentPage());
//			currentPage = currentPage < 1 ? 1 : currentPage;
//			ArrayList<Integer> pages = paging(currentPage, maxResult, totalRecords);
//			model.addAttribute("totalPages", getTotalOfPages(maxResult, totalRecords));
//
//			ArrayList<DisplayUser> displayUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo,
//					currentPage, maxResult);
//			model.addAttribute("allUsers", displayUsers);
//			model.addAttribute("searchingInfo", searchingInfo);
//			model.addAttribute("pages", pages);
//			model.addAttribute("currentPage", currentPage);
//			String jsonCompany = tblCompanyLogic.getCompanyById(Integer.parseInt(searchingInfo.getCompanyId()));
//			if (tblUserLogic.exportUser(searchingInfo, jsonCompany)) {
//				return Constant.MH002;
//			} else {
//				return Constant.ERROR;
//			}
//		} catch (Exception e) {
//			return Constant.ERROR;
//		}
		return Constant.MH001;
	}
}
