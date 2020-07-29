package com.finance.loan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.loan.model.LoanInfo;
import com.finance.loan.repository.LoanRepository;
import com.finance.loan.service.LoanService;
import com.finance.loan.vo.LoanInfoRequestVO;
//import com.finance.loan.vo.LoanSearchRequestVO;
import com.finance.loan.vo.LoanupdateRequestVO;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
    LoanRepository loanRepository;

	@Override
	public List<LoanInfo> getloans() {
		return loanRepository.findAll();
	}

	@Override
	public LoanInfo generateLoan(LoanInfoRequestVO loan) throws ParseException {
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setFirstName(loan.getFirstName());
		loanInfo.setLastName(loan.getLastName());
		loanInfo.setLoanAmount(loan.getLoanAmount());
		Date lDate =new SimpleDateFormat("dd-MM-yyyy").parse(loan.getOriginationDate());  
		loanInfo.setLoanDate(lDate);
		loanInfo.setLoanStatus(loan.getStatus());
		loanInfo.setLoanTerm(loan.getLoanTerm());
		return loanRepository.save(loanInfo);
	}

	@Override
	public LoanInfo modifyLoan(LoanupdateRequestVO loanDetails) {
		Optional<LoanInfo> loanEntity = loanRepository.findById(Long.parseLong(loanDetails.getLoanNumber()));
		LoanInfo loanUpdateInfo = loanEntity.get();
		loanUpdateInfo.setLoanAmount(loanDetails.getLoanAmount());
		loanUpdateInfo.setLoanStatus(loanDetails.getStatus());
		loanUpdateInfo.setLoanTerm(loanDetails.getLoanTerm());
		return loanRepository.save(loanUpdateInfo);
	}

	@Override
	public LoanInfo findLoanInfoByLoanNumber(String loanNumber) {
		LoanInfo loanInfo=loanRepository.findByLoanNumber(Long.parseLong(loanNumber));
		return loanInfo;
	}

	@Override
	public LoanInfo findLoanInfoByUserName(String userName) {
		LoanInfo loanInfo=loanRepository.findByUserName(userName);
		return loanInfo;
	}

}
