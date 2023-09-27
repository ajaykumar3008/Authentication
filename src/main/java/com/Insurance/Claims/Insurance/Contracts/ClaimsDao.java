package com.Insurance.Claims.Insurance.Contracts;

import java.util.ArrayList;
import java.util.List;

import com.Insurance.Claims.Insurance.Models.Claim;
import com.Insurance.Claims.Insurance.Models.ClaimApplication;
import com.Insurance.Claims.Insurance.Models.ClaimBills;
import com.Insurance.Claims.Insurance.Models.CoveredDiseases;

public interface ClaimsDao {

	ArrayList<Claim> getAllClaims();

	ArrayList<Claim> getFilteredClaims(String status);

	Claim getClaimById(int clamId);

	ArrayList<Claim> viewAllClaims();

	Claim viewClaimById(int clamId);

	int editClaimById(int clamId, String clamRemarks, String clamStatus);

	void addClaim(ClaimBills claim);

	void setClaim(int i, double requestAmount);

	Claim getClaimByid(int clamIplcId);

	void setDocs(String f, String filePath, int cid);

	ArrayList<ClaimBills> getDocs(int clamId);

	ClaimBills getDocBills(int billIndex);

	void addClaimApplication(ClaimApplication application);

	List<ClaimApplication> getAllApplications();

	Claim getClaimByPolicy(int id);

	void updateClaimBill(ClaimBills bill);

	List<CoveredDiseases> getAllCoveredDiseases(int id);

	void updateClaimBill(int clamId, String clamRemarks, String clamStatus, String clamProcessedAmount);

	void updateDate(int clamId);

}
