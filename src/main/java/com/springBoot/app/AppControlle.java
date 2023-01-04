package com.springBoot.app;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppControlle {
	@Autowired
	Convert convert;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private Pattern romanpattern = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

	@GetMapping("v1/app/{inputvalue}")
	public String converts(@PathVariable String inputvalue) {
		String numeral = null;
		if (isNumeric(inputvalue)) {
			int input = Integer.parseInt(inputvalue);
			numeral = convert.convertDegitToNumeral(input);
		} else if (isRomanNumeral(inputvalue)) {
			numeral = convert.convertNumeralToDigit(inputvalue.toUpperCase());
		} else
			return "Not a number or numeral";
		return numeral;

	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}

	public boolean isRomanNumeral(String strNum) {
		if (strNum == null) {
			return false;
		}
		String st = strNum.toUpperCase();
		return romanpattern.matcher(st).matches();
	}

}
