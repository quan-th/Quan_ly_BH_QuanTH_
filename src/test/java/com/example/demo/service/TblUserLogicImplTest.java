package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.DisplayUser;
import com.example.demo.entities.SearchingInfo;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.impl.TblUserLogicImpl;
import com.example.demo.utils.Common;

@RunWith(MockitoJUnitRunner.class)
public class TblUserLogicImplTest {
	@InjectMocks
	private TblUserLogicImpl tblUserLogic;
	@Mock
	private TblUserDao tblUserDao;
	private SearchingInfo searchingInfo = new SearchingInfo();

	@Before
	public void setUp() {
		List<TblUser> tblUsers = new ArrayList<>();
		tblUsers.add(new TblUser());

		List<DisplayUser> displayUsers = new ArrayList<>();
		displayUsers.add(new DisplayUser(1, "Tran Viet Hung", "01", "1989-12-20", "0123456789", "2016-12-25",
				"2017-12-25", "Ha Noi"));
		displayUsers.add(new DisplayUser(20, "Tran Đuc Cong", "01", "1995-01-11", "4567891235", "2017-01-17",
				"2017-01-31", "Ha Noi"));
		when(tblUserDao.findByUserNameAndUserPassword("admin", "admin")).thenReturn(tblUsers);

		when(tblUserDao.getNumberOfUsers(searchingInfo)).thenReturn((long) 24);
		when(tblUserDao.getListUsers(searchingInfo, 1, 2)).thenReturn(displayUsers);
	}

	@Test
	public void LoginByUsernameAndPasswordTest() {
		List<TblUser> tblUsers = tblUserLogic.LoginByUsernameAndPassword("admin", "admin");
		assertEquals(1, tblUsers.size());
	}

	@Test
	public void getNumberOfUsersTest() {
		SearchingInfo info = searchingInfo;
		long totalRecords = tblUserLogic.getNumberOfUsers(info);
		assertEquals(24, totalRecords);
	}

	@Test
	public void getListUsersTest() {
		searchingInfo.setCompanyId("1");
		int currentPage = 1;
		int maxResult = 2;
		ArrayList<DisplayUser> displayUsersActual = tblUserLogic.getListUsers(searchingInfo, currentPage, maxResult);
		ArrayList<DisplayUser> displayUsers = new ArrayList<>();

		DisplayUser displayUser = new DisplayUser();
		displayUser.setId(1);
		displayUser.setUsername("Tran Viet Hung");
		displayUser.setGender("Nam");
		displayUser.setBirthdate("20/12/1989");
		displayUser.setInsuranceNumber("0123456789");
		displayUser.setStartDate("25/12/2016");
		displayUser.setEndDate("25/12/2017");
		displayUser.setPlaceOfRegister("Ha Noi");

		displayUsers.add(displayUser);

		DisplayUser displayUser2 = new DisplayUser();
		displayUser2.setId(20);
		displayUser2.setUsername("Tran Đuc Cong");
		displayUser2.setGender("Nam");
		displayUser2.setBirthdate("11/01/1995");
		displayUser2.setInsuranceNumber("4567891235");
		displayUser2.setStartDate("17/01/2017");
		displayUser2.setEndDate("31/01/2017");
		displayUser2.setPlaceOfRegister("Ha Noi");

		displayUsers.add(displayUser2);
		assertEquals(true, Common.myComparison(displayUsers, displayUsersActual));
	}
}
