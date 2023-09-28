package com.Insurance.Claims.Insurance.RowMappers;

import com.Insurance.Claims.Insurance.Models.Uploads;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UploadsRowMapper implements RowMapper<Uploads> {

    @Override
    public Uploads mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Uploads uploads = new Uploads();
        uploads.setUploadId(resultSet.getInt("uploadId"));
        uploads.setReUploadId(resultSet.getInt("reUploadId"));
        uploads.setClaimId(resultSet.getInt("claimId"));
        uploads.setData(resultSet.getString("data"));
        uploads.setType(resultSet.getString("type"));
        return uploads;
    }
}
