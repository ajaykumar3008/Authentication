package com.Insurance.Claims.Insurance.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.Insurance.Claims.Insurance.Models.ClaimApplication;

public class ApplicationRowMapper implements RowMapper<ClaimApplication> {

	@Override
	public ClaimApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClaimApplication claimApplication = new ClaimApplication();

		claimApplication.setClamIplcId(rs.getInt("policy_id"));
		claimApplication.setMemberIndex(rs.getInt("member_index") + "");
		claimApplication.setRelation(rs.getString("relation"));
		claimApplication.setClaimAmountRequested(rs.getDouble("claimAmount"));

		// Convert joinedDate from java.sql.Date to String in "yyyy-MM-dd" format
		java.sql.Date joinedDateSql = rs.getDate("joined_date");
		if (joinedDateSql != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String joinedDateString = dateFormat.format(joinedDateSql);
			claimApplication.setJoinedDate(joinedDateString);
		}

		claimApplication.setPatientName(rs.getString("patient_name"));

		// Convert dateOfBirth from java.sql.Date to String in "yyyy-MM-dd" format
		java.sql.Date dateOfBirthSql = rs.getDate("date_of_birth");
		if (dateOfBirthSql != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateOfBirthString = dateFormat.format(dateOfBirthSql);
			claimApplication.setDateOfBirth(dateOfBirthString);
		}

		claimApplication.setGender(rs.getString("gender"));
		claimApplication.setContactNumber(rs.getString("contact_number"));
		claimApplication.setAddress(rs.getString("address"));
		claimApplication.setDisease(rs.getString("disease"));
		claimApplication.setDiagnosis(rs.getString("diagnosis"));
		claimApplication.setTreatment(rs.getString("treatment"));
		return claimApplication;
	}

}
