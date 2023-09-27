package com.Insurance.Claims.Insurance.Daos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.Insurance.Claims.Insurance.Contracts.ClaimsDao;
import com.Insurance.Claims.Insurance.Models.Claim;
import com.Insurance.Claims.Insurance.Models.ClaimApplication;
import com.Insurance.Claims.Insurance.Models.ClaimBills;
import com.Insurance.Claims.Insurance.Models.CoveredDiseases;
import com.Insurance.Claims.Insurance.RowMappers.ApplicationRowMapper;
import com.Insurance.Claims.Insurance.RowMappers.ClaimBillsMap;
import com.Insurance.Claims.Insurance.RowMappers.ClaimsMapper;
import com.Insurance.Claims.Insurance.RowMappers.DiseasesRowMapper;
import com.Insurance.Claims.Insurance.RowMappers.RowMap;

@Component
public class ClaimsDaoImpl implements ClaimsDao {

	JdbcTemplate jdbcTemplate;

	private String SQL_GET_CLAIMS = "select * from  _Claims";
	private String SQL_GET_FILTERED_CLAIMS = "select * from  _Claims where clam_status=?";
	private String SQL_GET_CLAIM_BY_ID = "select * from  _Claims where clam_id=?";
	private String SQL_INSERT_CLAIM = "insert into _Claims(clam_source,clam_type,clam_date,clam_amount_requested,clam_iplc_id) values(?,?,?,?,?)";
	private String SQL_INSERT_CLAIMBill = "insert into ClaimBills(clam_id,clbl_document_title,clbl_document_path) values(?,?,?)";

	@Autowired
	public ClaimsDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public ArrayList<Claim> getAllClaims() {
		System.out.println("bhav");
		return (ArrayList<Claim>) jdbcTemplate.query(SQL_GET_CLAIMS, new ClaimsMapper());
	}

	@Override
	public ArrayList<Claim> getFilteredClaims(String status) {
		// TODO Auto-generated method stub
		return (ArrayList<Claim>) jdbcTemplate.query(SQL_GET_FILTERED_CLAIMS, new Object[] { status },
				new ClaimsMapper());
	}

	@Override
	public Claim getClaimById(int clamId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_GET_CLAIM_BY_ID, new Object[] { clamId }, new ClaimsMapper());
	}

	@Override
	public void addClaim(ClaimBills claim) {
		String query = "insert into Claims(patient_id,patient_name, date_of_birth, gender, contact_number, address, joined_date, disease, diagnosis, treatment, room_charges, medicine_bill, document_path) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] values = {};
		jdbcTemplate.update(query, values);
	}

	@Override
	public Claim getClaimByid(int clamIplcId) {
		// TODO Auto-generated method stub

		return jdbcTemplate.queryForObject("select distinct clam_id from _Claims where clam_iplc_id=" + clamIplcId,
				new RowMap());
	}

	@Override
	public void setDocs(String f, String filePath, int cid) {
		System.out.println("brooo");
		jdbcTemplate.update(SQL_INSERT_CLAIMBill, cid, f, filePath);
	}

	@Override
	public ArrayList<ClaimBills> getDocs(int clamId) {
		// TODO Auto-generated method stub
		System.out.println("docs");
		return (ArrayList<ClaimBills>) jdbcTemplate.query("select * from ClaimBills where clam_id=" + clamId,
				new ClaimBillsMap());
	}

	private String SQL_VIEW_CLAIMS = "select * from  _Claims where clam_status is null";
	private String SQL_VIEW_CLAIM_BY_ID = "select * from  _Claims where clam_id=?";
	private String SQL_EDIT_CLAIM_BY_ID = "update _claims set clam_remarks=?, clam_status=? where clam_id=?";

	@Override
	public ArrayList<Claim> viewAllClaims() {
		// TODO Auto-generated method stub
		System.out.println("hii");
		return (ArrayList<Claim>) jdbcTemplate.query(SQL_VIEW_CLAIMS, new ClaimsMapper());
	}

	@Override
	public Claim viewClaimById(int clamId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_VIEW_CLAIM_BY_ID, new Object[] { clamId }, new ClaimsMapper());
	}

	@Override
	public int editClaimById(int clamId, String clamRemarks, String clamStatus) {
		return jdbcTemplate.update(SQL_EDIT_CLAIM_BY_ID, clamRemarks, clamStatus, clamId);

	}

	@Override
	public void setClaim(int i,double requestAmount) {
		
		String currentDate = LocalDate.now() + "";
		Date sqlDate = Date.valueOf(currentDate);
		
			jdbcTemplate.update(SQL_INSERT_CLAIM, "HSPT", "DRCT", sqlDate,requestAmount, i);
	}

	@Override
	public ClaimBills getDocBills(int billIndex) {
		// TODO Auto-generated method stub
		System.out.println("nooooo");
		return (ClaimBills) jdbcTemplate.queryForObject("select * from ClaimBills where clbl_billindex=" + billIndex,
				new ClaimBillsMap());
	}

	@Override
	public void addClaimApplication(ClaimApplication application) {
		System.out.println(application.getMemberIndex() + 1);
		String query = "insert into insurance_claim(policy_id,member_index,relation,joined_date,patient_name,date_of_birth,gender,contact_number,address,disease,diagnosis,treatment,claimAmount) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] values = { application.getClamIplcId(), application.getMemberIndex(), application.getRelation(),
				application.getJoinedDate(), application.getPatientName(), application.getDateOfBirth(),
				application.getGender(), application.getContactNumber(), application.getAddress(),
				application.getDisease(), application.getDiagnosis(), application.getTreatment(),
				application.getClaimAmountRequested() };
		jdbcTemplate.update(query, values);
	}

	@Override
	public List<ClaimApplication> getAllApplications() {

		return jdbcTemplate.query("select * from insurance_claim", new ApplicationRowMapper());
	}

	@Override
	public Claim getClaimByPolicy(int id) {

		return jdbcTemplate.queryForObject("select * from _Claims where clam_iplc_id=?", new Object[] { id },
				new ClaimsMapper());
	}

	@Override
	public void updateClaimBill(ClaimBills bill) {
		String currentDate = LocalDate.now() + "";
		Date sqlDate = Date.valueOf(currentDate);
		System.out.println(sqlDate + "   ppp");
		String query = "update ClaimBills set clbl_processed_amount=?,clbl_processed_date=?,clbl_remarks=?,clbl_status=? where clbl_billindex=?";
		Object[] values = { bill.getProcessedAmount(), sqlDate, bill.getRemarks(), bill.getStatus(),
				bill.getBillIndex() };
		jdbcTemplate.update(query, values);

	}

	@Override
	public List<CoveredDiseases> getAllCoveredDiseases(int id) {

		return jdbcTemplate.query(
				"SELECT d.* FROM insurancepolicies1 ip JOIN insurancepackagecovereddiseases npc ON ip.iplc_insp_id = npc.insp_id JOIN diseases d ON npc.disc_id = d.disc_id WHERE ip.iplc_id = ?",
				new Object[] { id }, new DiseasesRowMapper());

	}

	@Override
	public void updateClaimBill(int clamId, String clamRemarks, String clamStatus, String clamProcessedAmount) {
		jdbcTemplate.update("update _Claims set clam_remarks=?, clam_status=?,clam_processed_amount=? where clam_id=?",clamRemarks,clamStatus,Double.parseDouble(clamProcessedAmount),clamId);
		
	}

	@Override
	public void updateDate(int clamId) {
		String currentDate = LocalDate.now() + "";
		Date sqlDate = Date.valueOf(currentDate);
		Object[] values= {sqlDate,clamId};
		jdbcTemplate.update("update _Claims set clam_processed_date=? where clam_id=?",values );
	}

}
