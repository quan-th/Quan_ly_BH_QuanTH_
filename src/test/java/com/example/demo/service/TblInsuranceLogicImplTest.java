package com.example.demo.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.daos.TblInsuranceDao;
import com.example.demo.daos.TblUserDao;
import com.example.demo.data.DataFixture;
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
		// exercise
		TblInsurance value1 = tblInsuranceDao.findByInsuranceNumber(anyString());
		// verify
		assertTrue(value1 != null);
	}

	/**
	 * Test insert Insurance Success 
	 * [In] tblInsurance: default
	 * tblCompanyDao:default tblUser:fefault 
	 * [Out] insuranceActual:tblInsurance
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
		// exercise
		TblInsurance insuranceActual = tblInsuranceDao.save(tblInsurance);
		TblCompany companyActual = tblCompanyDao.save(tblCompany);
		TblUser userActual = tblUserDao.save(tblUser);
		// verify
		assertTrue(
				insuranceActual.equals(tblInsurance) && companyActual.equals(tblCompany) && userActual.equals(tblUser));
	}

	/**
	 * Test save Insurance throws DataIntegrityViolationException 
	 * [In]
	 * tblInsurance: default tblCompanyDao:default tblUser:fefault 
	 * [Out]
	 * insuranceActual:tblInsurance companyActual:tblCompanyDao
	 * userActual:tblUserDao
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void insertOrUpdateInsuranceThrowDIVE() {
		// setup
		TblInsurance insurance = new TblInsurance();
		when(tblInsuranceDao.save(insurance)).thenThrow(DataIntegrityViolationException.class);
		TblCompany company = new TblCompany();
		when(tblCompanyDao.save(company)).thenReturn(company);
		TblUser tblUser = new TblUser();
		when(tblUserDao.save(tblUser)).thenReturn(tblUser);
		// exercise
		TblCompany companyActual = tblCompanyDao.save(company);
		TblInsurance insuranceActual = tblInsuranceDao.save(insurance);
		TblUser userActual = tblUserDao.save(tblUser);

	}

	/**
	 * [In] UserId:1; 
	 * [Out] DetailUser ( Id :0, UserName :Tran HongQuan,
	 * Birthday:17/06/1995. Gender: Nam, InsuranceNumber:0123456789, StartDate
	 * :17/06/2017, Enddate: 17/06/2018, PlaceOfRegister:HaDong, Company: Ha
	 * Dong )
	 */
	@Test
	public void getDetailUserTest() {
		// setup

		TblUser tblUser = DataFixture.getTblUser();
		when(tblUserDao.findByUserInternalId(anyObject())).thenReturn(tblUser);
		// exercise

		DetailUser detailUserActual = tblUserLogicImpl.getDetailUser(0);
		DetailUser detailUserExpect = DataFixture.getDetailUser();
		// verify
		assertTrue(detailUserActual.getUsername().equals(detailUserExpect.getUsername())
				&& detailUserActual.getBirthdate().equals(detailUserExpect.getBirthdate())
				&& detailUserActual.getGender().equals(detailUserExpect.getGender())
				&& detailUserActual.getInsuranceNumber().equals(detailUserExpect.getInsuranceNumber())
				&& detailUserActual.getStartDate().equals(detailUserExpect.getStartDate())
				&& detailUserActual.getEndDate().equals(detailUserExpect.getEndDate())
				&& detailUserActual.getId() == detailUserExpect.getId()
				&& detailUserActual.getCompany().equals(detailUserExpect.getCompany())
				&& detailUserActual.getPlaceOfRegister().equals(detailUserExpect.getPlaceOfRegister()));

	}

	/**
	 * [In] UserId:1 
	 * [Out] NullpointerException
	 */
	@Test(expected = NullPointerException.class)
	public void findByUserInternalIdThrowNPE() {
		// setup
		when(tblUserDao.findByUserInternalId(anyObject())).thenThrow(new NullPointerException());
		// exercise
		DetailUser detailUserActual = tblUserLogicImpl.getDetailUser(1);
	}

	/**
	 * [In] UserId :1 
	 * [Out] actual :true
	 */
	@Test
	public void deleteInsuranceTest() {
		// setup
		TblUser tblUser = DataFixture.getTblUser();
		when(tblUserDao.findByUserInternalId(anyObject())).thenReturn(tblUser);
		doNothing().when(tblUserDao).delete(any(TblUser.class));
		doNothing().when(tblInsuranceDao).delete(any(TblInsurance.class));
		// exercise
		boolean result = sut.deleteInsurance(1);
		// verify
		assertTrue(result);

	}

	/**
	 * Test delete Insurance throw RuntimeException 
	 * [In] UserID :1
	 */
	@Test(expected = RuntimeException.class)
	public void deleteInsuranceThrowRTE() {
		// setup
		TblUser tblUser = DataFixture.getTblUser();
		when(tblUserDao.findByUserInternalId(anyObject())).thenReturn(tblUser);
		doNothing().when(tblUserDao).delete(any(TblUser.class));
		doThrow(RuntimeException.class).when(tblInsuranceDao).delete(any(TblInsurance.class));
		// exercise
		boolean result = sut.deleteInsurance(1);
	}

	/**
	 * Test findByUserInteralId return null 
	 * [In] UserID :1
	 */
	@Test(expected = NullPointerException.class)
	public void deleteInsuranceThrowNPE() {
		// setup
		when(tblUserDao.findByUserInternalId(anyObject())).thenReturn(null);
		// exercise
		boolean result = sut.deleteInsurance(1);
	}

}
