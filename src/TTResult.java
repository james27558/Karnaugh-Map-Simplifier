import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TTResult {

	int bitLength;
	Input[] inpArrCopy;
	HashMap inputs = new HashMap();

	TTResult(RPNBundle bundle, String greyCode) {
		this.bitLength = bundle.inpArr.length;

		List<Expression> expList = new ArrayList<Expression>();

		for (int i = 0; i < bundle.expArr.length; i++) {
			expList.add(bundle.expArr[i]);
		}

		this.inpArrCopy = bundle.inpArr.clone();

		for (int i = 0; i < bundle.inpArr.length; i++) {
			String name = inpArrCopy[i].name;
			int value = toBinaryInt(greyCode.charAt(i)); // Used in Displaying The K Map

			inputs.put(name, value);

			inpArrCopy[i].setVal(value);

			System.out.println(name + " " + value);
		}

		while (expList.size() != 1) {
			for (int j = 0; j < expList.size(); j++) {

				if (expList.get(j) instanceof Operator) {
					Operator currentOp = (Operator) expList.get(j);
					System.out.println("Oper" + currentOp.name);

					if (currentOp.type == Operator.operators.AND) {
						Input prevInput = (Input) expList.get(j - 1);
						Input prevPrevInput = (Input) expList.get(j - 2);

						boolean res = toBoolean(prevInput.val) && toBoolean(prevPrevInput.val);

						Input newInp = new Input("Z");
						newInp.setVal(toBinaryInt(res));
						expList.remove(j - 2);
						expList.add(j - 2, newInp);

						expList.remove(j - 1);
						expList.add(j - 1, null);

						expList.remove(j);
						expList.add(j, null);

						while (expList.remove(null)) {
						}
						System.out.println(expList);


						break;


					} else if (currentOp.type == Operator.operators.OR) {
						Input prevInput = (Input) expList.get(j - 1);
						Input prevPrevInput = (Input) expList.get(j - 2);

						boolean res = toBoolean(prevInput.val) || toBoolean(prevPrevInput.val);

						Input newInp = new Input("Z");
						newInp.setVal(toBinaryInt(res));
						expList.remove(j - 2);
						expList.add(j - 2, newInp);

						expList.remove(j - 1);
						expList.add(j - 1, null);

						expList.remove(j);
						expList.add(j, null);

						while (expList.remove(null)) {
						}

						System.out.println(expList);

						break;

					} else if (currentOp.type == Operator.operators.NOT) {
						Input prevInput = (Input) expList.get(j - 1);

						boolean res = !toBoolean(prevInput.val);

						Input newInp = new Input("Z");
						newInp.setVal(toBinaryInt(res));
						expList.remove(j - 1);
						expList.add(j - 1, newInp);

						expList.remove(j);
						expList.add(j, null);

						while (expList.remove(null)) {
						}

						System.out.println(expList);

						break;

					}

				}

			}


		}

		System.out.println("DONE");

	}

	int toBinaryInt(char bit) {
		if (bit == '1') {
			return 1;
		} else {
			return 0;
		}
	}

	boolean toBoolean(int bit) {
		if (bit == 1) {
			return true;
		} else {
			return false;
		}
	}

	int toBinaryInt(boolean bit) {
		if (bit) {
			return 1;
		} else {
			return 0;
		}
	}


}
