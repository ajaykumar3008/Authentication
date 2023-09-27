package com.Insurance.Claims.Insurance.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Insurance.Claims.Insurance.Models.Claim;

public class RowMap implements RowMapper<Claim> {
	public Claim mapRow(ResultSet rs, int rowNum) throws SQLException {
		Claim clam = new Claim();

		clam.setClamId(rs.getInt("clam_id"));
		return clam;
	}
}
