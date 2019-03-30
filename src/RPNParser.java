import java.util.ArrayList;

/**
 * <h1> Parses a String to a Expression Array for later use </h1>
 **/

public final class RPNParser {
	/**
	 * ASCII value for an open bracket
	 */
	static final int openBracketASCII = 40;

	/**
	 * ASCII value for a closed bracket
	 */
	static final int closeBracketASCII = 41;


	private RPNParser() {
	} // Don't let anyone instantiate this class.

	/**
	 * <strong> Returns the formated String[] to be used by other functions in the RPNParser class. </strong>
	 *
	 * @param inp The raw string input.
	 * @return
	 */
	static RPNBundle mParsetoBundle(String inp) {
		String[] parsedStringArray = mParseToStringArr(inp);
		Expression[] out = parseStringArrayToExpressionArray(parsedStringArray);
		Input[] inputs = extractDistinctInputs(out);

		return new RPNBundle(out, inputs);
	}

	static String[] mParseToStringArr(String inp) {
		String[] split = RPNParser.formatString(inp);

		return RPNParser.mInfixToRPNStringArray(split);
	}

	static private Expression[] parseStringArrayToExpressionArray(String[] inp) {
		Expression[] out = new Expression[inp.length];
		ArrayList<Input> inputs = new ArrayList<>();

		for (int i = 0; i < inp.length; i++) {
			String current = inp[i];

			// Input
			if (current.length() == 1) {
				Input existingInp = searchArrayListForInputWithName(inputs, current);

				if (existingInp != null) {
					out[i] = existingInp;

				} else {
					Input newInput = new Input(current);
					inputs.add(newInput);

					out[i] = newInput;
				}
			}

			// Operator
			if (current.length() > 1) {
				out[i] = new Operator(current);
			}
		}

		return out;
	}

	static Input[] extractDistinctInputs(Expression[] expArr) {
		ArrayList<Input> inputs = new ArrayList<>();

		for (int i = 0; i < expArr.length; i++) {
			Expression current = expArr[i];
			if (current instanceof Input) {
				if (!inputs.contains(current)) {
					inputs.add((Input) current);
				}
			}
		}

		return inputs.toArray(new Input[inputs.size()]);
	}

	static private String[] formatString(String inp) {
		ArrayList<String> ArrayListOut = new ArrayList<>();
		// Add padding around the brackets
		String spacedInp = inp.replaceAll("(\\()", " ( ").replaceAll("(\\))", " ) ");

		// Split By Whitespace
		String[] splitInp = spacedInp.split("\\s");

		ArrayListOut.add("("); // Opening Padding Bracket

		for (int i = 0; i < splitInp.length; i++) {
			if (splitInp[i].length() != 0) {
				ArrayListOut.add(splitInp[i].trim());
			}
		}

		ArrayListOut.add(")"); // Closing Padding Bracket

		Object[] temp = ArrayListOut.toArray();
		String[] out = new String[temp.length];

		for (int j = 0; j < temp.length; j++) {
			out[j] = (String) temp[j];
		}

		return out;
	}

	static private String[] mInfixToRPNStringArray(String[] inp) {
		RPNStack operatorRPNStack = new RPNStack();
		RPNStack properRPNStack = new RPNStack();

		ArrayList<Integer> openBracketIndexes = new ArrayList<>();

		for (int i = 0; i < inp.length; i++) {
			int firstCharASCII = getASCIIValue(inp[i].charAt(0));

			// An Input (A, B, C ...)
			if (inp[i].length() == 1 && firstCharASCII != openBracketASCII && firstCharASCII != closeBracketASCII) {
				properRPNStack.push(inp[i]);
			}

			// An Operator (AND, OR, NOT)
			if (inp[i].length() > 1) {
				operatorRPNStack.push(inp[i]);
			}

			// Open Bracket
			if (inp[i].length() == 1 && firstCharASCII == openBracketASCII) {
				operatorRPNStack.push(inp[i]);
				openBracketIndexes.add(operatorRPNStack.getCurrentStackIndex());
			}

			if (inp[i].length() == 1 && firstCharASCII == closeBracketASCII) {
				int lastOpenBracketIndex = openBracketIndexes.get(openBracketIndexes.size() - 1);

				while (operatorRPNStack.getCurrentStackIndex() > lastOpenBracketIndex) {
					String popped = (String) operatorRPNStack.pop();
					properRPNStack.push(popped);
				}

				operatorRPNStack.pop(); // Pop the open bracket
				openBracketIndexes.remove(openBracketIndexes.size() - 1);
			}
		}

		return properRPNStack.toStringArray();
	}

	static private Input searchArrayListForInputWithName(ArrayList<Input> arr, String name) {
		for (int i = 0; i < arr.size(); i++) {
			if (getASCIIValue(arr.get(i).name.charAt(0)) == getASCIIValue(name.charAt(0))) {
				return arr.get(i);
			}
		}

		return null;
	}

	static private int getASCIIValue(char inp) {
		return (int) inp;
	}
}
