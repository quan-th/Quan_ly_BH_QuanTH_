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
	 * Được gọi đến khi chọn tìm kiếm
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
		String sessionId = "";
		String action = request.getParameter("userAction");
		HttpSession session = request.getSession();
		int currentPage = 1;
		int totalRecords = 0;
		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
		// back
		if (action.equals(Constant.ACTION_BACK_MH002)) {
			sessionId = request.getParameter("sessionId");
			searchingInfo = (session.getAttribute(sessionId) != null) ? (SearchingInfo) session.getAttribute(sessionId)
					: new SearchingInfo();
			if (searchingInfo.getCompanyId().equals("0")) {
				List<Company> companies = tblCompanyLogic.getAllCompany();
				searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
			}
			searchingInfo.setOrderByName(Common.validOrder(searchingInfo.getOrderByName()));
		} else if (action.equals(Constant.ACTION_SEARCH_MH002)) {

			sessionId = searchingInfo.getIdNumber();

			if (sessionId.equals("0")) {
				sessionId = new Date().getTime() + "";
				searchingInfo.setIdNumber(sessionId);
			} else {

				session.removeAttribute(sessionId);
			}
			searchingInfo.setOrderByName(Common.validOrder(searchingInfo.getOrderByName()));
			try {
				currentPage = Integer.parseInt(searchingInfo.getCurrentPage());
			} catch (NumberFormatException e) {
				searchingInfo.setCurrentPage("1");
			}
			session.setAttribute(sessionId, searchingInfo);
		} else {
			searchingInfo = new SearchingInfo();
			List<Company> companies = tblCompanyLogic.getAllCompany();
			searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
		}
		totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
		currentPage = formedCurrentPage(currentPage, maxResult, totalRecords);
		ArrayList<Integer> pages = paging(currentPage, maxResult, totalRecords);
		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo, currentPage,
				maxResult);
		model.addAttribute("searchingInfo", searchingInfo);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", getTotalOfPages(maxResult, totalRecords));
		return Constant.MH002;
	}

	/**
	 * được gọi đến sau khi login thành công
	 *
	 * @param model
	 *            model
	 * @return màn hình 002
	 */
	@RequestMapping(value = "/AllUsers.do", method = RequestMethod.GET)
	private String listUsers(ModelMap model, HttpServletRequest request) {
		SearchingInfo searchingInfo = new SearchingInfo();
		List<Company> companies = tblCompanyLogic.getAllCompany();
		searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
		int currentPage = 1;
		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
		int totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
		ArrayList<Integer> pages = paging(currentPage, maxResult, totalRecords);
		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo, currentPage,
				maxResult);
		model.addAttribute("searchingInfo", searchingInfo);
		model.addAttribute("allUsers", allUsers);

		model.addAttribute("pages", pages);

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalPages", getTotalOfPages(maxResult, totalRecords));

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
	 * Chuẩn hóa currentPage
	 * 
	 * @param currentPage
	 *            currentPage
	 * @param recordsOfPage
	 *            Số records/ trang
	 * @param totalRecords
	 *            tổng số records
	 * @return
	 */
	private int formedCurrentPage(int currentPage, int recordsOfPage, int totalRecords) {
		int numberOfPages = getTotalOfPages(recordsOfPage, totalRecords);
		if (currentPage < 1) {
			return 1;
		} else if (currentPage > numberOfPages) {
			return numberOfPages;
		} else {
			return currentPage;
		}
	}

	/**
	 * Lấy danh sách trang paging
	 * 
	 * @param currentPage
	 *            trang bắt đầu
	 * @param recordsOfPage
	 *            record trên mỗi trang
	 * @param totalRecords
	 *            tổng số records
	 * @return danh sách trang paging
	 */
	private ArrayList<Integer> paging(int currentPage, int recordsOfPage, int totalRecords) {
		int numberOfPages = getTotalOfPages(recordsOfPage, totalRecords);
		int pageRange = Integer.parseInt(ValueProperties.getValue("PAGE_RANGE"));
		int numberOfPageToAdd = pageRange - 1;
		int startPage = 0;
		ArrayList<Integer> pages = new ArrayList<Integer>();
		if (currentPage - pageRange / 2 + numberOfPageToAdd < numberOfPages) {
			startPage = currentPage - pageRange / 2 > 0 ? currentPage - pageRange / 2 : 1;
		} else {
			startPage = numberOfPages - numberOfPageToAdd;
		}

		pages.add(startPage);
		for (int i = 1; startPage + i <= startPage + numberOfPageToAdd && startPage + i <= numberOfPages; i++) {
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
	 * @return tổng trang
	 */
	private int getTotalOfPages(int recordsOfPage, int totalRecords) {
		return (totalRecords % recordsOfPage == 0) ? totalRecords / recordsOfPage : totalRecords / recordsOfPage + 1;
	}

	@RequestMapping(value = "/Search.do/CSV", method = RequestMethod.POST)
	private String exportCSV(ModelMap model, @ModelAttribute SearchingInfo searchingInfo) {
		return Constant.MH001;
	}
}
