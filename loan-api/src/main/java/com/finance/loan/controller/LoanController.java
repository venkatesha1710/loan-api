package com.finance.loan.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.loan.model.LoanInfo;
import com.finance.loan.service.LoanService;
import com.finance.loan.vo.LoanInfoRequestVO;
//import com.finance.loan.vo.LoanSearchRequestVO;
import com.finance.loan.vo.LoanupdateRequestVO;
import com.finance.loan.vo.MessageResponseVO;

/**
 * 
 */
@RestController
@RequestMapping("/api")
public class LoanController {
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

	@Autowired
	LoanService loanservice;
    
    

    @GetMapping("/loanslist")
    @CrossOrigin(origins = "${URL}")
    public List<LoanInfo> getAllLoans() {
        return loanservice.getloans();
    }
    
    @GetMapping("/getLoanInfo/{loanNumber}")
    @CrossOrigin(origins = "${URL}")
	public ResponseEntity<LoanInfo> getLoanInfoByLoanNumber(@PathVariable String loanNumber) {
		logger.info("Entered getLoanInfoByLoanNumber method {}", loanNumber);
		LoanInfo loanInfo= loanservice.findLoanInfoByLoanNumber(loanNumber);
		logger.info("Loan Infomation returned {}", loanInfo.getLoanNumber());
		return new ResponseEntity<>(loanInfo, HttpStatus.OK);
	}
    
    @GetMapping("/getLoanInfo/{userName}")
    @CrossOrigin(origins = "${URL}")
	public ResponseEntity<LoanInfo> getLoanInfoByUserName(@PathVariable String userName) {
		logger.info("Entered getLoanInfoByUserName method {}", userName);
		LoanInfo loanInfo= loanservice.findLoanInfoByUserName(userName);
		logger.info("Loan Infomation returned {}", loanInfo.getUserName());
		return new ResponseEntity<>(loanInfo, HttpStatus.OK);
	}

    @PostMapping("/addloan")
    @CrossOrigin(origins = "${URL}")
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanInfoRequestVO loan) throws ParseException {
        loanservice.generateLoan(loan);
        return ResponseEntity.ok(new MessageResponseVO("Loan created successfully!"));
    }

    @PostMapping("/updateloan")
    @CrossOrigin(origins = "${URL}")
    public ResponseEntity<?> updateLoan(@Valid @RequestBody LoanupdateRequestVO loanDetails) {
        loanservice.modifyLoan(loanDetails);
        return ResponseEntity.ok(new MessageResponseVO("Loan updated successfully!"));
    }

}
