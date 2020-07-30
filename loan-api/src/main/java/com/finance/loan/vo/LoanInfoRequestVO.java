package com.finance.loan.vo;

public class LoanInfoRequestVO {
	private String loanTerm;
	private String loanAmount;
	private String firstName;
	private String lastName;
	private String userName;
	private String Status;
	private String originationDate;
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
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the loanStatus
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * @param loanStatus the loanStatus to set
	 */
	public void setStatus(String Status) {
		this.Status = Status;
	}
	/**
	 * @return the loanDate
	 */
	public String getOriginationDate() {
		return originationDate;
	}
	/**
	 * @param loanDate the loanDate to set
	 */
	public void setOriginationDate(String originationDate) {
		this.originationDate = originationDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}



}
