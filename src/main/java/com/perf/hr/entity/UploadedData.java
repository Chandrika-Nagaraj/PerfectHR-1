package com.perf.hr.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class UploadedData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String flag;
	private String candidatename;
	private String skill;
	private String resumesource;
	private String referralname;
	private String currentlocation;
	private String yoe;
	private String currentctc;
	private String expectedctc;
	private String contactnumber	;
	private String emailid;
	private String recentemployer;
	private String noticeperiod;
	private String graduatedyear;
	private String interviewdate;
	private String interviewstatus;
	private String technicalinterviewer;
	private String coerecruiterremarks;
	private String interviewfeedbackstatus;
	private String client;
	private String candidatedob;
	private String careergap;

}
