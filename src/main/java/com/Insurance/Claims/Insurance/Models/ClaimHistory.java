package com.Insurance.Claims.Insurance.Models;

import java.sql.Date;

public class ClaimHistory {
	private int cf_id;
	private int cf_clam_id;
	private String cf_clam_status;

	public int getCf_clam_processed_by() {
		return cf_clam_processed_by;
	}

	public void setCf_clam_processed_by(int cf_clam_processed_by) {
		this.cf_clam_processed_by = cf_clam_processed_by;
	}

	private int cf_clam_processed_by;

	public int getCf_id() {
		return cf_id;
	}

	public void setCf_id(int cf_id) {
		this.cf_id = cf_id;
	}

	public int getCf_clam_id() {
		return cf_clam_id;
	}

	public void setCf_clam_id(int cf_clam_id) {
		this.cf_clam_id = cf_clam_id;
	}

	public String getCf_clam_status() {
		return cf_clam_status;
	}

	public void setCf_clam_status(String cf_clam_status) {
		this.cf_clam_status = cf_clam_status;
	}

	public String getCf_clam_remarks() {
		return cf_clam_remarks;
	}

	public void setCf_clam_remarks(String cf_clam_remarks) {
		this.cf_clam_remarks = cf_clam_remarks;
	}

	public Date getCf_clam_date() {
		return cf_clam_date;
	}

	public void setCf_clam_date(Date cf_clam_date) {
		this.cf_clam_date = cf_clam_date;
	}

	private String cf_clam_remarks;
	private Date cf_clam_date;
}
