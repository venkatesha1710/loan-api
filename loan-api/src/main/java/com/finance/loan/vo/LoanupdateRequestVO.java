package com.finance.loan.vo;

public class LoanupdateRequestVO {
	private String loanNumber;
	private String status;
	private String loanTerm;
	private String loanAmount;
	/**
	 * @return the loanNumber
	 */
	public String getLoanNumber() {
		return loanNumber;
	}
	/**
	 * @param loanNumber the loanNumber to set
	 */
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	/**
	 * @return the loanStatus
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param loanStatus the loanStatus to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the loanTerm
	 */
	public String getLoanTerm() {
		return loanTerm;
	}
	/**
	 * @param loanTerm the loanTerm to set
	 */
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	/**
	 * @return the loanAmount
	 */
	public String getLoanAmount() {
		return loanAmount;
	}
	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
}
