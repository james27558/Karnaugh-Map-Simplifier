//import java.util.Scanner;

import java.util.Arrays;

public class Main {


	public static void main(String[] args) {
		String inputString = "NOT(A OR B OR (C AND D))";
		System.out.println(Arrays.toString(RPNParser.mParsetoExpArray(inputString)));
	}

}
