package com.perf.hr.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.perf.hr.Service.UploadService;
import com.perf.hr.entity.FileInfo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
 


@Controller
@RequestMapping("/User")
public class FileController {
	
	@Autowired
	UploadService uploadService;
	
	@Value("spring.datasource.url")
	String url;

	
	
	@RequestMapping("/upload")
	public String getFileUpload(Model model) {
		//model.addAttribute("user", new FileInfo());
		
		System.out.println("Saved");
		return "upload";
		
	}
	
	@PostMapping("/save")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String success=null;
		 String name = "test.xlsx";
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(name)));
	                stream.write(bytes);
	                stream.close();
	                success="You successfully uploaded " + name + " into " + name + "-uploaded !";
	                System.out.println(file.getName());
	                uploadService.getData();
	                
	                saveData();
	                filterData();
	                
	                
	            } catch (Exception e) {
	            	success="You failed to upload " + name + " => " + e.getMessage();

	            }
	        } else {
	        	success= "You failed to upload " + name + " because the file was empty.";
	        }
	        System.out.println("Reached End of the Method");
	        System.out.println(success);
	        return "success";
	    }
	
	public void filterData() {
		
		  String jdbcURL = "jdbc:mysql://localhost:3306/sales?useSSL=false";
		 try {
			 String sql="insert into finalcandidates select * from candidates where  candidates.flag='y' and emailid not in (select emailid from finalcandidates);";
					 Connection  connection = DriverManager.getConnection(jdbcURL, "root", "root");
			CallableStatement cs = connection.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
	}
	
	public void saveData() {
		  String excelFilePath = "test.xlsx";
		  String jdbcURL = "jdbc:mysql://localhost:3306/sales?useSSL=false";
	        int batchSize = 20;
	 
	        Connection connection = null;
	 
	        try {
	            long start = System.currentTimeMillis();
	             
	            FileInputStream inputStream = new FileInputStream(excelFilePath);

	            Workbook  workbook = new XSSFWorkbook(inputStream);
	 
	            Sheet firstSheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = firstSheet.iterator();
	 
	            connection = DriverManager.getConnection(jdbcURL, "root", "root");
	            connection.setAutoCommit(false);
	  
	            String sql = "INSERT INTO `sales`.`candidates`\r\n"
	            		+ "(`flag`,\r\n"
	            		+ "`candidatename`,\r\n"
	            		+ "`skill`,\r\n"
	            		+ "`resumesource`,\r\n"
	            		+ "`referralname`,\r\n"
	            		+ "`currentlocation`,\r\n"
	            		+ "`yoe`,\r\n"
	            		+ "`currentctc`,\r\n"
	            		+ "`expectedctc`,\r\n"
	            		+ "`contactnumber`,\r\n"
	            		+ "`emailid`,\r\n"
	            		+ "`recentemployer`,\r\n"
	            		+ "`noticeperiod`,\r\n"
	            		+ "`graduatedyear`,\r\n"
	            		+ "`interviewdate`,\r\n"
	            		+ "`interviewstatus`,\r\n"
	            		+ "`technicalinterviewer`,\r\n"
	            		+ "`coe`,\r\n"
	            		+ "`interviewfeedbackstatus`,\r\n"
	            		+ "`client`,\r\n"
	            		+ "`candidatedob`,\r\n"
	            		+ "`careergap`,\r\n"
	            		+ "`recruiterremarks`)\r\n"
	            		+ "VALUES\r\n"
	            		+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	            PreparedStatement statement = connection.prepareStatement(sql);    
	             
	            int count = 0;
	             
	            rowIterator.next(); // skip the header row
	             
	            while (rowIterator.hasNext()) {
	                Row nextRow = rowIterator.next();
	                Iterator<Cell> cellIterator = nextRow.cellIterator();

	                while (cellIterator.hasNext()) {
	                    Cell nextCell = cellIterator.next();
	 
	                    int columnIndex = nextCell.getColumnIndex();
	            System.out.println(columnIndex);
	                    switch (columnIndex) {
	                    case 0:
	                        String flag = nextCell.getStringCellValue();
	                        statement.setString(1, flag);
	                        break;
	                    case 1:
	                      String name = nextCell.getStringCellValue();
	                        statement.setString(2, name);
	                        break;
	                    case 2:
	                        String skill= nextCell.getStringCellValue();
	                        statement.setString(3, skill);
	                        break;
	                    case 3:
	                        String resumesource= nextCell.getStringCellValue();
	                        statement.setString(4, resumesource);
	                        break;
	                    case 4:
	                        String referralname= nextCell.getStringCellValue();
	                        statement.setString(5, referralname);
	                        break;
	                    case 5:
	                        String yoe= nextCell.getStringCellValue();
	                        statement.setString(6, yoe);
	                    case 6:
	                        String currentctc= nextCell.getStringCellValue();
	                        statement.setString(7, currentctc);
	                        break;
	                    case 7:
	                        String expectedctc= nextCell.getStringCellValue();
	                        statement.setString(8, expectedctc);
	                        break;
	                    case 8:
	                        String contactnumber= nextCell.getStringCellValue();
	                        statement.setString(9, contactnumber);
	                        break;//@@@@@@@@@@@@@@@
	                    case 9:
	                        Double emailid=  nextCell.getNumericCellValue();
	                        System.out.println(emailid);
	                        statement.setDouble(10, emailid);
	                        break;
	                    case 10:
	                        String recentemployer= nextCell.getStringCellValue();
	                        statement.setString(11, recentemployer);
	                        break;
	                    case 11:
	                        String noticeperiod= nextCell.getStringCellValue();
	                        statement.setString(12, noticeperiod);
	                        break;
	                    case 12:
	                        String graduatedyear= nextCell.getStringCellValue();
	                        statement.setString(13, graduatedyear);
	                        break;
	                    case 13:
	                        String interviewdate= nextCell.getStringCellValue();
	                        statement.setString(14, interviewdate);
	                        break;
	                    case 14:
	                        String interviewstatus= nextCell.getStringCellValue();
	                        statement.setString(15, interviewstatus);
	                        break;
	                    case 15:
	                        String technicalinterviewer= nextCell.getStringCellValue();
	                        statement.setString(16, technicalinterviewer);
	                        break;
	                    case 16:
	                        String coe= nextCell.getStringCellValue();
	                        statement.setString(17, coe);
	                        break;
	                    case 17:
	                        String recruiterremarks= nextCell.getStringCellValue();
	                        statement.setString(18, recruiterremarks);
	                        break;
	                    case 18:
	                        String interviewfeedbackstatus= nextCell.getStringCellValue();
	                        statement.setString(19, interviewfeedbackstatus);
	                        break;
	                    case 19:
	                        String client= nextCell.getStringCellValue();
	                        statement.setString(20, client);
	                        break;
	                    case 20:
	                        String candidatedob= nextCell.getStringCellValue();
	                        statement.setString(21, candidatedob);
	                        break;
	                    case 21:
	                        String careergap= nextCell.getStringCellValue();
	                        statement.setString(22, careergap);
	                        break;
						
						  case 22: 
							  String Skill= nextCell.getStringCellValue(); 
							  statement.setString(23, Skill); 
							  break;
						
	                        
	                    }
	 
	                }
	                 
	                statement.addBatch();
	                 
	                if (++count % batchSize == 0) {
	                    statement.executeBatch();
	                }              
	 
	            }
	 
	            workbook.close();
	             
	            // execute the remaining queries
	            statement.executeBatch();
	  
	            connection.commit();
	            connection.close();
	             
	            long end = System.currentTimeMillis();
	            System.out.printf("Import done in %d ms\n", (end - start));
	             
	        } catch (IOException ex1) {
	            System.out.println("Error reading file");
	            ex1.printStackTrace();
	        } catch (SQLException ex2) {
	            System.out.println("Database error");
	            ex2.printStackTrace();
	        }
	}
	
	
}
