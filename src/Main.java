//import java.util.Scanner;

import java.util.Arrays;

public class Main {


	public static void main(String[] args) {
		String inputString = "NOT(A OR A OR (B AND B))";

		printArr(RPNParser.mParseToStringArr(inputString));

		RPNBundle expArr = RPNParser.mParsetoBundle(inputString);

		expArr.printBundle();


	}

	public static void printArr(Object[] arr) {
		System.out.println(Arrays.toString(arr));
	}

}
