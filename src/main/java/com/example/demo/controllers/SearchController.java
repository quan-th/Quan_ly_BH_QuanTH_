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
 * SearchController
 */
@Controller
public class SearchController {
	@Autowired
	private TblUserLogic tblUserLogic;
	@Autowired
	private TblCompanyLogic tblCompanyLogic;
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
			
			session.setAttribute(sessionId, searchingInfo);
		} else {
			searchingInfo = new SearchingInfo();
			List<Company> companies = tblCompanyLogic.getAllCompany();
			searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
		}
		totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
		currentPage = Common.formedCurrentPage(searchingInfo.getCurrentPage(), maxResult, totalRecords);
		searchingInfo.setCurrentPage(currentPage+"");
		ArrayList<Integer> pages = Common.paging(currentPage, maxResult, totalRecords);
		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo, currentPage,
				maxResult);
		model.addAttribute("searchingInfo", searchingInfo);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", Common.getTotalOfPages(maxResult, totalRecords));
		return Constant.MH002;
	}
	@RequestMapping(value = "/AllUsers.do", method = RequestMethod.GET)
	private String listUsers(ModelMap model, HttpServletRequest request) {
		SearchingInfo searchingInfo = new SearchingInfo();
		List<Company> companies = tblCompanyLogic.getAllCompany();
		searchingInfo.setCompanyId(companies.get(0).getCompanyID() + "");
		int currentPage = 1;
		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
		int totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
		ArrayList<Integer> pages = Common.paging(currentPage, maxResult, totalRecords);
		ArrayList<DisplayUser> allUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo, currentPage,
				maxResult);
		model.addAttribute("searchingInfo", searchingInfo);
		model.addAttribute("allUsers", allUsers);

		model.addAttribute("pages", pages);

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalPages", Common.getTotalOfPages(maxResult, totalRecords));

		return Constant.MH002;
	}

	/**
	 * load default details
	 * @param model model
	 */
	@ModelAttribute("companies")
	private List<Company> loadDefaultValues(ModelMap model) {
		return tblCompanyLogic.getAllCompany();
	}

	/**
	 * Export CSV
	 * @param model model
	 * @param searchingInfo searchingInfo
	 * @return File jsp
	 */
	@RequestMapping(value = "/Search.do/CSV", method = RequestMethod.POST)
	private String exportCSV(ModelMap model, @ModelAttribute SearchingInfo searchingInfo) {
		int currentPage = 1;
		int totalRecords = 0;
		int maxResult = Integer.parseInt(ValueProperties.getValue(Constant.MAX_RESULT));
		try {
			totalRecords = (int) tblUserLogic.getNumberOfUsers(searchingInfo);
			currentPage = Integer.parseInt(searchingInfo.getCurrentPage());
			currentPage = currentPage < 1 ? 1 : currentPage;
			ArrayList<Integer> pages = Common.paging(currentPage, maxResult, totalRecords);
			model.addAttribute("totalPages", Common.getTotalOfPages(maxResult, totalRecords));

			ArrayList<DisplayUser> displayUsers = (ArrayList<DisplayUser>) tblUserLogic.getListUsers(searchingInfo,
					currentPage, maxResult);
			model.addAttribute("allUsers", displayUsers);
			model.addAttribute("searchingInfo", searchingInfo);
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", currentPage);
			String jsonCompany = tblCompanyLogic.getJsonCompanyById(Integer.parseInt(searchingInfo.getCompanyId()));
			if (tblUserLogic.exportUser(searchingInfo, jsonCompany)) {
				return Constant.MH002;
			} else {
				return Constant.ERROR;
			}
		} catch (Exception e) {
			return Constant.ERROR;
		}
	}
}
