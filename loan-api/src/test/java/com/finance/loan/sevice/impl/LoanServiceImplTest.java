package com.finance.loan.sevice.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.finance.loan.model.LoanInfo;
import com.finance.loan.repository.LoanRepository;
import com.finance.loan.service.LoanService;
import com.finance.loan.service.impl.LoanServiceImpl;
import com.finance.loan.vo.LoanInfoRequestVO;


@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {
	@Mock
	LoanRepository loanRepo;
	
	@InjectMocks
	LoanService loanService = new LoanServiceImpl();
	
	LoanInfoRequestVO loan;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		loan = new LoanInfoRequestVO();
		loan.setFirstName("test");
		loan.setLastName("user");
		loan.setLoanAmount("20000");
		loan.setLoanTerm("1");
		loan.setOriginationDate("20-01-2020");
		loan.setStatus("approved");
	}
	@Test
	public void addLoan() throws Exception{
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setFirstName(loan.getFirstName());
		loanInfo.setLastName(loan.getLastName());
		loanInfo.setLoanAmount(loan.getLoanAmount());
		Date lDate =new SimpleDateFormat("dd-MM-yyyy").parse(loan.getOriginationDate());
		loanInfo.setLoanDate(lDate);
		loanInfo.setLoanStatus(loan.getStatus());
		loanInfo.setLoanTerm(loan.getLoanTerm());
		
		Mockito.when(loanRepo.save(Mockito.any(LoanInfo.class))).thenReturn(loanInfo);
		LoanInfo l = loanService.generateLoan(loan);
		
		Assertions.assertNotNull(l);

	}
}
