package utilities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * StrUtils class performs manipulations with string
 * 
 * @author almazbekbegaliev
 *
 */
public class StrUtils {

	/**
	 * Returns digits of the given string. if there are no any digits, returns 0;
	 * 
	 * @param String
	 * @return integer
	 */
	public static int getInteger(String string) {
		int number = 0;
		String str = "";
		char ch;

		for (int i = 0; i < string.length(); i++) {
			ch = string.charAt(i);

			if (Character.isDigit(ch) || (str.isEmpty() && (ch == '-'))) {
				str += string.charAt(i);
			}
		}
		if (!str.isEmpty()) {
			number = Integer.parseInt(str);
		}
		return number;
	}

	public static ArrayList getDigits(List<String> list) {

		ArrayList numbers = new ArrayList();
		String str = "";
		char ch;

		for (int n = 0; n < list.size(); n++) {
			for (int i = 0; i < list.get(n).length(); i++) {
				ch = list.get(n).charAt(i);
				if (Character.isDigit(ch) || ((ch == '.') && (!str.contains("."))) || (str.isEmpty() && (ch == '-'))) {
					str += ch;
				}
			}
			if (!str.isEmpty()) {
				if (str.contains("."))
					numbers.add(Double.parseDouble(str));
				else
					numbers.add(Integer.parseInt(str));

				str = "";
			}
		}
		return numbers;

	}

	/**
	 * Returns ArrayList<Integer> of given List<String>
	 * 
	 * @param List<String>
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> getInteger(List<String> listStr) {

		ArrayList<Integer> numbers = new ArrayList<>();
		String str = "";
		char ch;

		for (int n = 0; n < listStr.size(); n++) {
			for (int i = 0; i < listStr.get(n).length(); i++) {

				ch = listStr.get(n).charAt(i);
				if (Character.isDigit(ch) || (str.isEmpty() && (ch == '-'))) {
					str += ch;
				}
			}
			if (!str.isEmpty()) {
				numbers.add(Integer.parseInt(str));
				str = "";
			}
		}
		return numbers;
	}

	/**
	 * Returns ArrayList<Double> of given List<String>
	 * 
	 * @param List<String>
	 * @return ArrayList<Double>
	 */
	public static ArrayList<Double> getDouble(List<String> listStr) {

		ArrayList<Double> numbers = new ArrayList<>();
		String str = "";
		char ch;

		for (int n = 0; n < listStr.size(); n++) {
			for (int i = 0; i < listStr.get(n).length(); i++) {

				ch = listStr.get(n).charAt(i);
				if (Character.isDigit(ch) || (str.isEmpty() && (ch == '-')) || ch == '.') {
					str += ch;
				}
			}
			if (!str.isEmpty()) {
				numbers.add(Double.parseDouble(str));
				str = "";
			}
		}
		return numbers;
	}

	/**
	 * Creates random string of given length, which contains UpperCase and LowerCase
	 * letters
	 * 
	 * @param length
	 * @return String
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	
	/**
	 * Generates Random String of given length
	 * First letter will be Upper Case
	 * 
	 * @param length
	 * @return String
	 */
	public static String getRandomName(int length) {
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < length; i++) {
			s.append(getRandomChar(i == 0));
		}
		return s.toString();
	}

	
	/**
	 * Returns random Character
	 * Upper Case = true, Lower Case = false
	 * 
	 * @param boolean 
	 * 					true = Upper Case, false = Lower Case
	 * @return Char
	 */
	public static String getRandomChar(boolean upperCase) {
		Random random = new Random();
		int num = random.nextInt(26);
		return upperCase ? "" + (char) ((int) 'A' + num) : "" + (char) ((int) 'a' + num);
	}

}
