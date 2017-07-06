package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.daos.TblInsuranceDao;
import com.example.demo.daos.TblUserDao;
import com.example.demo.entities.DetailUser;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;
import com.example.demo.logics.impl.TblInsuranceLogicImpl;
import com.example.demo.logics.impl.TblUserLogicImpl;

@RunWith(MockitoJUnitRunner.class)
public class TblInsuranceLogicImplTest {
	@InjectMocks
	private TblInsuranceLogicImpl sut;
	@InjectMocks
	private TblUserLogicImpl tblUserLogicImpl;
	@Mock
	private TblInsuranceDao tblInsuranceDao;
	@Mock
	private TblCompanyDao tblCompanyDao;
	@Mock
	private TblUserDao tblUserDao;

	/**
	 * Test exist Insurance
	 */
	@Test
	public void checkExistTest() {
		// setup
		TblInsurance value = new TblInsurance();
		when(tblInsuranceDao.findByInsuranceNumber(anyString())).thenReturn(value);
		// process
		TblInsurance value1 = tblInsuranceDao.findByInsuranceNumber(anyString());
		// assert
		assertTrue(value1 != null);
	}

	/**
	 * Test insert Insurance Success [In] tblInsurance: default
	 * tblCompanyDao:default tblUser:fefault [Out] insuranceActual:tblInsurance
	 * companyActual:tblCompanyDao userActual:tblUserDao
	 */
	@Test
	public void insertOrUpdateInsuranceTest() {
		// setup
		TblInsurance tblInsurance = new TblInsurance();
		when(tblInsuranceDao.save(tblInsurance)).thenReturn(tblInsurance);
		TblCompany tblCompany = new TblCompany();
		when(tblCompanyDao.save(tblCompany)).thenReturn(tblCompany);
		TblUser tblUser = new TblUser();
		when(tblUserDao.save(tblUser)).thenReturn(tblUser);
		// process
		TblInsurance insuranceActual = tblInsuranceDao.save(tblInsurance);
		TblCompany companyActual = tblCompanyDao.save(tblCompany);
		TblUser userActual = tblUserDao.save(tblUser);
		// assert
		assertTrue(
				insuranceActual.equals(tblInsurance) && companyActual.equals(tblCompany) && userActual.equals(tblUser));
	}

	/**
	 * Test save Insurance throws DataIntegrityViolationException [In]
	 * tblInsurance: default tblCompanyDao:default tblUser:fefault [Out]
	 * insuranceActual:tblInsurance companyActual:tblCompanyDao
	 * userActual:tblUserDao
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void insertOrUpdateInsuranceThrowRTE() {
		// setup
		TblInsurance insurance = new TblInsurance();
		when(tblInsuranceDao.save(insurance)).thenThrow(DataIntegrityViolationException.class);
		TblCompany company = new TblCompany();
		when(tblCompanyDao.save(company)).thenReturn(company);
		TblUser tblUser = new TblUser();
		when(tblUserDao.save(tblUser)).thenReturn(tblUser);
		// process
		TblCompany companyActual = tblCompanyDao.save(company);
		TblInsurance insuranceActual = tblInsuranceDao.save(insurance);
		TblUser userActual = tblUserDao.save(tblUser);

	}

	@Test
	public void getDetailUserTest() {
		// setup
		TblUser tblUser = new TblUser();
		tblUser.setUserFullName("<h1>Tran Hong Quan</h1>");
		tblUser.setUserSexDivision("01");
		tblUser.setBirthday("1995-06-17");
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance.setInsuranceNumber("0123456789");
		tblInsurance.setInsuranceStartDate("2017-06-17");
		tblInsurance.setInsuranceEndDate("2018-06-17");
		tblInsurance.setPlaceOfRegister("<p>Ha noi</p>");
		tblUser.setTblInsurance(tblInsurance);
		TblCompany tblCompany = new TblCompany();
		tblCompany.setCompanyName("<td>Ha Dong</td>");
		tblUser.setTblCompany(tblCompany);
		when(tblUserDao.findByUserInternalId(anyObject())).thenReturn(tblUser);
		// process
		DetailUser detailUserExpect = new DetailUser();
		detailUserExpect.setUsername("<h1>Tran Hong Quan</h1>");
		detailUserExpect.setGender("Nam");
		detailUserExpect.setBirthdate("17/06/1995");
		detailUserExpect.setInsuranceNumber("0123456789");
		detailUserExpect.setStartDate("17/06/2017");
		detailUserExpect.setEndDate("17/06/2018");
		detailUserExpect.setPlaceOfRegister("<p>Ha noi</p>");
		detailUserExpect.setCompany("<td>Ha Dong</td>");
		DetailUser detailUserActual = tblUserLogicImpl.getDetailUser(1);
		// assert
		assertThat(detailUserActual,is(detailUserExpect));

	}

}
