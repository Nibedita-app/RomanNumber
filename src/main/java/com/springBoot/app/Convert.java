package com.springBoot.app;

import org.springframework.stereotype.Component;

@Component
public class Convert {
	public String convertDegitToNumeral(int input) {
		if (input <= 0) {
			return "Cannot be converted to Roman Numeral";
		}
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romanLetters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder roman = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			while (input >= values[i]) {
				input = input - values[i];
				roman.append(romanLetters[i]);
			}
		}
		return roman.toString();
	}

	public String convertNumeralToDigit(String input) {
		int res = 0;

		for (int i = 0; i < input.length(); i++) {
			int s1 = value(input.charAt(i));
			if (i + 1 < input.length()) {
				int s2 = value(input.charAt(i + 1));
				if (s1 >= s2) {
					res = res + s1;
				} else {
					res = res + s2 - s1;
					i++;
				}
			} else {
				res = res + s1;
			}
		}

		return Integer.toString(res);
	}

	int value(char r) {
		if (r == 'I')
			return 1;
		if (r == 'V')
			return 5;
		if (r == 'X')
			return 10;
		if (r == 'L')
			return 50;
		if (r == 'C')
			return 100;
		if (r == 'D')
			return 500;
		if (r == 'M')
			return 1000;
		return -1;
	}
}
