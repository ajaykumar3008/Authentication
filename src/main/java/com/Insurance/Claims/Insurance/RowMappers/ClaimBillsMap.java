package com.Insurance.Claims.Insurance.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.Insurance.Claims.Insurance.Models.ClaimBills;

public class ClaimBillsMap implements RowMapper<ClaimBills> {

	@Override
	public ClaimBills mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClaimBills claimBill = new ClaimBills();

		claimBill.setClaimId(rs.getInt(1));
		claimBill.setBillIndex(rs.getInt(2));
		claimBill.setDocumentTitle(rs.getString(3));
		claimBill.setDocumentPath(rs.getString(4));
		claimBill.setClaimAmount(rs.getDouble(5));
		claimBill.setProcessedAmount(rs.getDouble(6));
		java.sql.Date processedDateSql = rs.getDate(7);
		if (processedDateSql != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String processedDate = dateFormat.format(processedDateSql);
			claimBill.setProcessedDate(processedDate);;
		}
		claimBill.setProcessedBy(rs.getInt(8));
		claimBill.setRemarks(rs.getString(9));
		claimBill.setStatus(rs.getString(10));

		return claimBill;
	}
}
