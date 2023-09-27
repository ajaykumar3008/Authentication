package com.Insurance.Claims.Insurance.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Insurance.Claims.Insurance.Contracts.ClaimsDao;
import com.Insurance.Claims.Insurance.Contracts.IService;
import com.Insurance.Claims.Insurance.Models.Claim;
import com.Insurance.Claims.Insurance.Models.ClaimApplication;
import com.Insurance.Claims.Insurance.Models.ClaimBills;
import com.Insurance.Claims.Insurance.Models.CoveredDiseases;

@Service
public class ClaimService implements IService {
	@Autowired
	ClaimsDao cdao;

	@Override
	public ArrayList<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		System.out.println("hem");
		return (ArrayList<Claim>) cdao.getAllClaims();
	}

	@Override
	public ArrayList<Claim> getFilteredClaims(String status) {
		// TODO Auto-generated method stub
		return (ArrayList<Claim>) cdao.getFilteredClaims(status);
	}

	@Override
	public Claim getClaimById(int clamId) {
		// TODO Auto-generated method stub
		return cdao.getClaimById(clamId);
	}

	@Override
	public void addClaim(int i,double requestAmount) {
		cdao.setClaim(i,requestAmount);

	}

	@Override
	public Claim getClaimByid(int clamIplcId) {
		// TODO Auto-generated method stub
		return cdao.getClaimByid(clamIplcId);
	}

	@Override
	public void addClaimBills(String f, String filePath, int cid) {
		// TODO Auto-generated method stub
		cdao.setDocs(f, filePath, cid);
	}

	@Override
	public ArrayList<ClaimBills> viewClaimDocsById(int clamId) {
		// TODO Auto-generated method stub
		return cdao.getDocs(clamId);
	}

	// Insurance------------

	@Override
	public ArrayList<Claim> viewAllClaims() {
		// TODO Auto-generated method stub
		return (ArrayList<Claim>) cdao.viewAllClaims();
	}

	@Override
	public Claim viewClaimById(int clamId) {
		// TODO Auto-generated method stub
		return cdao.viewClaimById(clamId);
	}

	@Override
	public int editClaimById(int clamId, String clamRemarks, String clamStatus) {
		// TODO Auto-generated method stub
		return cdao.editClaimById(clamId, clamRemarks, clamStatus);
	}

	@Override
	public ClaimBills viewdocBills(int billIndex) {
		// TODO Auto-generated method stub
		return cdao.getDocBills(billIndex);
	}

	@Override
	public void addClaimApplication(ClaimApplication application) {
		cdao.addClaimApplication(application);

	}

	@Override
	public List<ClaimApplication> getAllApplicantions() {

		return cdao.getAllApplications();
	}

	@Override
	public Claim getCliamByPolicy(int id) {

		return cdao.getClaimByPolicy(id);
	}

	@Override
	public void updateClaimBill(ClaimBills bill) {

		cdao.updateClaimBill(bill);

	}

	@Override
	public List<CoveredDiseases> getAllDiseasesCovered(int id) {

		return cdao.getAllCoveredDiseases(id);
	}

	@Override
	public void editClaimById(int clamId, String clamRemarks, String clamStatus, String clamProcessedAmount) {
		
		cdao.updateClaimBill(clamId,clamRemarks,clamStatus,clamProcessedAmount);
	}

	@Override
	public void updateClaimDate(int clamId) {
		cdao.updateDate(clamId);
		
	}

}
