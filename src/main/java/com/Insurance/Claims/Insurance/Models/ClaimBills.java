package com.Insurance.Claims.Insurance.Models;

import java.math.BigDecimal;
import java.sql.Date;

public class ClaimBills {

	private int claimId;
	private int billIndex;
	private String documentTitle;
	private String documentPath;
	private BigDecimal claimAmount;
	private BigDecimal processedAmount;
	private Date processedDate;
	private int processedBy;
	private String remarks;
	private String status;

	// Constructors, getters, setters, and other methods

	// Constructor
	public ClaimBills() {
		// Default constructor
	}

	// Getters and setters

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getBillIndex() {
		return billIndex;
	}

	public void setBillIndex(int billIndex) {
		this.billIndex = billIndex;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	public BigDecimal getProcessedAmount() {
		return processedAmount;
	}

	public void setProcessedAmount(BigDecimal processedAmount) {
		this.processedAmount = processedAmount;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(String processedDate) {
		this.processedDate = Date.valueOf(processedDate);
	}

	public int getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(int processedBy) {
		this.processedBy = processedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Other methods (if needed)

	// toString() method (if needed)
}
