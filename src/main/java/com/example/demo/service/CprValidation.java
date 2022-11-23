package com.example.demo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CprValidation {

    //String Date = "01/05/2022";
		String CPR = "2205969901";
		String regex = "^\\d{10}$";

		Pattern pattern = Pattern.compile(regex);

		public Matcher matcher = pattern.matcher(CPR);
        {

		if (matcher.matches() == true) {
			System.out.println("Valid CPR number");
			// System.exit(1);
		} else {
			System.out.println("Invalid CPR number");
		}
    }
}

