import java.util.ArrayList;
import java.util.Arrays;

public class RPNStack {

	ArrayList<Object> stack = new ArrayList<>();

	Object pop() {
		return stack.remove(stack.size() - 1);
	}

	void push(Object obj) {
		stack.add(obj);
	}

	void printStack() {
		System.out.println(Arrays.toString(stack.toArray()));
	}

	int getCurrentStackIndex() {
		stack.trimToSize();
		return stack.size() - 1;
	}

	boolean isEmpty() {
		return stack.isEmpty();
	}

	String[] toStringArray() {
		String[] out = new String[stack.size()];

		for (int i = 0; i < stack.size(); i++) {
			out[i] = (String) stack.get(i);
		}

		return out;
	}

}
