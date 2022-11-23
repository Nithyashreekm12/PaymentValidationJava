package com.example.demo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.example.demo.service.CprValidation;
// import com.example.demo.service.ValidateDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// String Date = "01/05/2022";
		// String CPR = "2206969901";
		// String regex = "^\\d{10}$";

		SpringApplication.run(DemoApplication.class, args);
		// ValidateDate validateDate = new ValidateDate();
		// validateDate.validateJavaDate("12/10/2022");

		// CprValidation cprValidation = new CprValidation();
		// System.out.println(cprValidation.matcher.matches());











		// System.out.println("Started Exceuting main method");

		// Pattern pattern = Pattern.compile(regex);

		// Matcher matcher = pattern.matcher(CPR);
		// System.out.println("CPR consist of 10 digits =" + matcher.matches());

		// if (matcher.matches() == true) {
		// 	System.out.println("Valid CPR number");
		// 	// System.exit(1);
		// } else {
		// 	System.out.println("Invalid CPR number");
		// }

		// LocalDate today = LocalDate.now();
		// LocalDate yesterday = today.minusDays(4);
		// LocalDate tomorrow = yesterday.plusDays(4);
		// System.out.println("Today date: " + today);
		// System.out.println("Yesterday date: " + yesterday);
		// System.out.println("Tomorrow date: " + tomorrow);




	// 	try{

    //         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    //         Date fromDate = sdf.parse("04-05-2022");
    //         Date toDate = sdf.parse("01-05-2022");

    //         System.out.println(sdf.format(fromDate));
    //         System.out.println(sdf.format(toDate));

    //         if(fromDate.compareTo(toDate)>0){
    //             System.out.println("From Date should be less than To Date");
    //         } else if(fromDate.compareTo(toDate)==0){
    //             System.out.println("From Date is equal to To Date");
    //         } 

    //     }catch(ParseException ex){
    //         ex.printStackTrace();
    //     }
     }
	}

	
