package com.Insurance.Claims.Insurance.Contracts;

import java.util.ArrayList;
import java.util.List;

import com.Insurance.Claims.Insurance.Models.Claim;
import com.Insurance.Claims.Insurance.Models.ClaimApplication;
import com.Insurance.Claims.Insurance.Models.ClaimBills;
import com.Insurance.Claims.Insurance.Models.ClaimHistory;
import com.Insurance.Claims.Insurance.Models.CoveredDiseases;
import com.Insurance.Claims.Insurance.Models.PolicyMembers;
import com.Insurance.Claims.Insurance.Models.ReUpload;
import com.Insurance.Claims.Insurance.Models.Uploads;

public interface IService {

	ArrayList<Claim> getAllClaims();

	ArrayList<Claim> getFilteredClaims(String status);

	Claim getClaimById(int clamId);

	ArrayList<Claim> viewAllClaims();

	Claim viewClaimById(int clamId);

	int editClaimById(int clamId, String clamRemarks, String clamStatus);

	void addClaim(int i, double requestAmount);

	Claim getClaimByid(int clamIplcId);

	void addClaimBills(ClaimBills bill);

	ArrayList<ClaimBills> viewClaimDocsById(int clamId);

	ClaimBills viewdocBills(int billIndex);

	void addClaimApplication(ClaimApplication application);

	List<ClaimApplication> getAllApplicantions();

	Claim getCliamByPolicy(int id);

	void updateClaimBill(ClaimBills bill);

	List<CoveredDiseases> getAllDiseasesCovered(int id);

	void editClaimById(int clamId, String clamRemarks, String clamStatus, String clamProcessedAmount);

	void updateClaimDate(int clamId);

	List<PolicyMembers> getPlcyMembers();

	List<ClaimHistory> getHistory(int cid);

	List<String> getFamilyByPolicy(int id);

	void addRequiredUpload(ReUpload requiredUpload);

	List<ReUpload> getAllReUploads(int id);

	void addUploads(Uploads up);

	List<Uploads> getAllUploads(int claimId);

}
