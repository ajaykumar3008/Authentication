package com.Insurance.Claims.Insurance.Controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.Insurance.Claims.Insurance.Contracts.IService;
import com.Insurance.Claims.Insurance.Models.Claim;
import com.Insurance.Claims.Insurance.Models.ClaimApplication;
import com.Insurance.Claims.Insurance.Models.ClaimBills;
import com.Insurance.Claims.Insurance.Models.ClaimHistory;
import com.Insurance.Claims.Insurance.Models.CoveredDiseases;
import com.Insurance.Claims.Insurance.Models.PolicyMembers;
import com.Insurance.Claims.Insurance.Models.ReUpload;
import com.Insurance.Claims.Insurance.Models.Uploads;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HospitalController {

	@Autowired
	IService insuranceService;


	// Get all books
	@GetMapping(value = "/getAllClaims")
	public String getAllClaims(Model model) {
		System.out.println("madh");
		ArrayList<Claim> li = (ArrayList<Claim>) insuranceService.getAllClaims();
		System.out.println(li.size());
		model.addAttribute("claims", li);
		return "Claims";
	}

	@PostMapping(value = "/viewClaim")
	public String getClaimById(Model model, @RequestParam("clamId") int clamId) {
		Claim cl = insuranceService.getClaimById(clamId);
		model.addAttribute("claim", cl);
		return "viewclaim";
	}

	@GetMapping(value = "/getFilteredClaims")
	public String getFilteredClaims(Model model, @RequestParam("status") String status) {
		System.out.println("madh");
		ArrayList<Claim> li = (ArrayList<Claim>) insuranceService.getFilteredClaims(status);
		System.out.println(li.size());
		model.addAttribute("claims", li);
		return "Claims";
	}

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public void downloadExcel(@RequestParam("selectedStatus") String status, HttpServletResponse response)
			throws IOException {
		ArrayList<Claim> Claims = new ArrayList<>();
		if (status == "select") {
			Claims = (ArrayList<Claim>) insuranceService.getAllClaims();
		} else {
			Claims = (ArrayList<Claim>) insuranceService.getFilteredClaims(status);
		}
		System.out.println(status + "Satish");

		// Create an Excel workbook using Apache POI
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Claims Data");
		Row headerRow = sheet.createRow(0);

		// Define column headings
		headerRow.createCell(0).setCellValue("Claim_Id");
		headerRow.createCell(1).setCellValue("ClamSource");
		headerRow.createCell(2).setCellValue("ClamType");
		headerRow.createCell(3).setCellValue("ClamDate");
		headerRow.createCell(4).setCellValue("ClamAmountRequestedt");
		headerRow.createCell(5).setCellValue("ClamIplcId");
		headerRow.createCell(6).setCellValue("ClamProcessedAmount");
		headerRow.createCell(7).setCellValue("ClamProcessedDate");
		headerRow.createCell(8).setCellValue("ClamProcessedBy");
		headerRow.createCell(9).setCellValue("ClamRemarks");
		headerRow.createCell(10).setCellValue("ClamStatus");

		int rowIdx = 1;
		for (Claim claim : Claims) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(claim.getClamId());
			row.createCell(1).setCellValue(claim.getClamSource());
			row.createCell(2).setCellValue(claim.getClamType());
			row.createCell(3).setCellValue(claim.getClamDate());
			row.createCell(4).setCellValue(claim.getClamAmountRequested());
			row.createCell(5).setCellValue(claim.getClamIplcId());
			row.createCell(6).setCellValue(claim.getClamProcessedAmount());
			row.createCell(7).setCellValue(claim.getClamProcessedDate());
			row.createCell(8).setCellValue(claim.getClamProcessedBy());
			row.createCell(9).setCellValue(claim.getClamRemarks());
			row.createCell(10).setCellValue(claim.getClamStatus());

		}

		// Set the response headers for Excel download
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=claims.xlsx");

		// Write the Excel data to the response output stream
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
	}

	@GetMapping(value = "/get")
	public String getAlClaims(Model model) {
		return "SETCLAIMS";
	}

	@GetMapping(value = "/allrecords")
	public String getHistory(@RequestParam("claimid") int cid, Model model) {
		List<ClaimHistory> m = insuranceService.getHistory(cid);

		model.addAttribute("claimhistory", m);
		return "history";

	}

	@GetMapping(value = "/getp")
	public String getp(@RequestParam("clamIplcId") int pid, Model model) {
		List<PolicyMembers> m = insuranceService.getPlcyMembers();
		List<PolicyMembers> mm = new ArrayList<>();
		for (PolicyMembers p : m) {
			if (p.getInsurancePolicyId() == pid) {
				mm.add(p);
			}
		}
		model.addAttribute("plym", mm);
		return "SETCLAIMS";
	}

	@RequestMapping(value = "/claimbills", method = RequestMethod.POST)
	public String claimData(@RequestParam("file[]") MultipartFile[] files,@RequestParam("documentTitle") String[] documentNames,@RequestParam("claimAmount") String[] claimAmount ,Claim claim, ClaimApplication application,
			Model model) {

		insuranceService.addClaimApplication(application);
		insuranceService.addClaim(claim.getClamIplcId(), application.getClaimAmountRequested());
		Claim clm_id = insuranceService.getClaimByid(claim.getClamIplcId());
		int cid = clm_id.getClamId();
		String uploadDir = "src/main/resources/static/file";

		try {
			// Create the target directory if it doesn't exist
			Files.createDirectories(Paths.get(uploadDir));
			int i=0;
			for (MultipartFile file : files) {
				// Get the original file name
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				// Create the target file path within the directory
				Path targetLocation = Paths.get(uploadDir).resolve(fileName);

				// Copy the file to the target location
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

				String fullPath = targetLocation.toAbsolutePath().toString();
				ClaimBills bill=new ClaimBills();
				bill.setDocumentPath(fullPath);
				bill.setDocumentTitle(documentNames[i]);
				bill.setClaimAmount(Double.parseDouble(claimAmount[i]));
				bill.setClaimId(cid);
				insuranceService.addClaimBills(bill);

			}

			// After successfully storing all files, you can redirect to a success page or return a response accordingly
			return "SETCLAIMS";
		} catch (IOException ex) {
			ex.printStackTrace();

		}

		return "SETCLAIMS";
	}

	// Insurance----------------------------------------------------------------------------
	@PostMapping(value = "/viewdocs")
	public String viewDocs(Model model, @RequestParam("clamId") int clamId) {
		ArrayList<ClaimBills> cl = insuranceService.viewClaimDocsById(clamId);
		System.out.println(cl.get(0).getProcessedDate() + "brooo");
		model.addAttribute("claim", cl);
		return "ViewClaimDocuments";
	}

	@GetMapping(value = "/viewClaims")
	public String viewAllClaims(Model model) {
		ArrayList<Claim> li = (ArrayList<Claim>) insuranceService.viewAllClaims();
		System.out.println(li.size());
		model.addAttribute("claims", li);
		return "InsuClaims";
	}

	@PostMapping(value = "/viewInsuClaim")
	public String viewClaimById(Model model, @RequestParam("clamId") int clamId) {
		Claim cl = insuranceService.viewClaimById(clamId);
		model.addAttribute("claim", cl);
		return "viewInsuclaim";
	}

	@GetMapping(value = "/docbills")
	public String viewdocBills(@RequestParam("clbl_billindex") int billIndex, Model model) {
		ClaimBills li = insuranceService.viewdocBills(billIndex);
		System.out.println(li.getProcessedDate() + "date");
		model.addAttribute("claim", li);
		return "viewSingleDocs";
	}

	@GetMapping(value = "/allclaimbills")
	public String viewClaimBills(@RequestParam("claimid") int id, Model model) {
		model.addAttribute("claimBills", insuranceService.viewClaimDocsById(id));

		return "claimbills";
	}

	@GetMapping(value = "/applications")
	public String getApplications(Model model) {

		model.addAttribute("claimApplications", insuranceService.getAllApplicantions());
		return "applications";
	}

	@GetMapping(value = "/getClaim")
	public String getClaim(@RequestParam("policy") int id, Model model) {
		Claim claim = insuranceService.getCliamByPolicy(id);
		insuranceService.updateClaimDate(claim.getClamId());
		model.addAttribute("claim", insuranceService.getCliamByPolicy(id));
		return "viewclaim";
	}

	@GetMapping(value = "/getDiseases")
	public String getCoveredDiseases(@RequestParam("policy") int id, Model model) {

		List<CoveredDiseases> ls = insuranceService.getAllDiseasesCovered(id);
		model.addAttribute("diseases", ls);

		return "diseasescovered";
	}

	@PostMapping(value = "/processClaim")
	public String editClaimById(Model model, @RequestParam("clamId") int clamId,
			@RequestParam("clamRemarks") String clamRemarks,
			@RequestParam("clamProcessedAmount") String clamProcessedAmount,
			@RequestParam("clamStatus") String clamStatus) {
		insuranceService.editClaimById(clamId, clamRemarks, clamStatus, clamProcessedAmount);
		Claim claim = insuranceService.viewClaimById(clamId);
		model.addAttribute("claim", claim);
		return "viewclaim";
	}

	@PostMapping(value = "/updateClaimBill")
	public String updateClaimBill(Model model, ClaimBills bill) {

		insuranceService.updateClaimBill(bill);
		model.addAttribute("claim", insuranceService.viewdocBills(bill.getBillIndex()));
		return "viewSingleDocs";
	}

	@GetMapping(value = "/getFamilyMembers")
	public ResponseEntity<List<String>> getFamily(@RequestParam("policy") int id, Model model) {
		List<String> members = insuranceService.getFamilyByPolicy(id);
		return ResponseEntity.ok(members);
	}

	@GetMapping(value = "/moredocuments")
	public String moreDocuments(@RequestParam("claimid") int id, Model model) {

		model.addAttribute("claimid", id);
		return "moredocuments";
	}

	@GetMapping(value = "/addRequiredUpload")
	public String reuploadDocuments(@RequestParam("claimid") int claimId, @RequestParam("name") String[] fileNames,
			@RequestParam("type") String[] fileTypes, @RequestParam("remarks") String[] fileRemarks, Model model) {

		for (int i = 0; i < fileNames.length; i++) {
			ReUpload upload = new ReUpload();
			upload.setClaimId(claimId);
			upload.setName(fileNames[i]);
			upload.setType(fileTypes[i]);
			upload.setDescription(fileRemarks[i]);
			insuranceService.addRequiredUpload(upload);
		}
		model.addAttribute("claimid", claimId);
		return "moredocuments";
	}

	@GetMapping(value = "/getrequired")
	public String getRequiredUploads(@RequestParam("claimid") int id, Model model) {

		model.addAttribute("reupload", insuranceService.getAllReUploads(id));
		model.addAttribute("claimid", id);
		return "update";
	}

	@PostMapping(value = "/adduploads")
	public String addUploads(@RequestParam("claimid") String id, MultipartHttpServletRequest request,Model model) {

		int claimId = Integer.parseInt(id);
		int index = 1;

		List<ReUpload> list = insuranceService.getAllReUploads(claimId);
		List<Uploads> list2 = insuranceService.getAllUploads(claimId);
		
		if(list2.size()>0) {
		
			index=list2.get(list2.size()).getReUploadId()+1;
		}
		for (ReUpload upload : list) {
			if (upload.getClaimId() == claimId) {
				String name = upload.getName();
				System.out.println(claimId);
				MultipartFile file = request.getFile(name);
				if (file != null && !file.isEmpty()) {
					System.out.println(name);
					if (upload.getType().equals("file")) {
						System.out.println("file");

						String uploadDir = "src/main/resources/static/file";
						try {
							Files.createDirectories(Paths.get(uploadDir));

							String fileName = StringUtils.cleanPath(file.getOriginalFilename());
							Path targetLocation = Paths.get(uploadDir).resolve(fileName);
							Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
							String fullPath = targetLocation.toAbsolutePath().toString();

							Uploads up = new Uploads();
							up.setUploadId(index);
							up.setReUploadId(upload.getUploadId());
							up.setClaimId(claimId);
							up.setData(fullPath);
							up.setType("file");

							insuranceService.addUploads(up);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					} else {

						Uploads up = new Uploads();

						up.setUploadId(index);
						up.setReUploadId(upload.getUploadId());
						up.setClaimId(claimId);
						up.setData(file.getOriginalFilename());
						up.setType("text");

						insuranceService.addUploads(up);
					}
				}
			}
		}

		model.addAttribute("claimid", claimId);
		return "update";
	}

}
