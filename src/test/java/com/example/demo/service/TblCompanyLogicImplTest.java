package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.daos.TblCompanyDao;
import com.example.demo.entities.Company;
import com.example.demo.entities.TblCompany;
import com.example.demo.logics.impl.TblCompanyLogicImpl;
import com.example.demo.logics.impl.TblUserLogicImpl;
@RunWith(MockitoJUnitRunner.class)
public class TblCompanyLogicImplTest {
	@InjectMocks
	private TblUserLogicImpl tblUserLogic;
	@InjectMocks
	private TblCompanyLogicImpl tblCompanyLogic;
	@Mock
	private TblCompanyDao tblCompanyDao;
	@Before
	public void setUp() {
		ArrayList<TblCompany> companies = new ArrayList<>();
		companies.add( new TblCompany(1, "Luvina","Ha Noi","Luvina@luvina.com","0123456789"));
		companies.add( new TblCompany(2, "FPT","Ha Noi","FPT@FPT.com","0123987654"));
		companies.add( new TblCompany(3, "ITC","Ha nam Dinh","ITC@ITC.com","0123777444"));
		companies.add( new TblCompany(4, "TinhVan","Ha Dong","TV@TV.com","0123888555"));
		companies.add( new TblCompany(5, "CityCom","Quán Thánh","CCom@Ccom..com","0123456789"));
		when(tblCompanyDao.findAll()).thenReturn(companies);
	}
	
	@Test
	public void getAllCompanyTest() {
		ArrayList<Company> companiesActual = (ArrayList<Company>) tblCompanyLogic.getAllCompany();
		ArrayList<Company> companies = new ArrayList<>();
		companies.add( new Company(1, "Luvina"));
		companies.add( new Company(2, "FPT"));
		companies.add( new Company(3, "ITC"));
		companies.add( new Company(4, "TinhVan"));
		companies.add( new Company(5, "CityCom"));
		assertThat(companiesActual, is(companies));
	}

	
	

}
