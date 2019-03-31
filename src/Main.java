//import java.util.Scanner;

import java.util.Arrays;

public class Main {


	public static void main(String[] args) {
		String inputString = "(A AND B) OR C";

		printArr(RPNParser.mParseToStringArr(inputString));

		RPNBundle bundle = RPNParser.mParsetoBundle(inputString);

		bundle.printBundle();

		TTGenerator.genTruthTable(bundle);

	}

	public static void printArr(Object[] arr) {
		System.out.println(Arrays.toString(arr));
	}

}
