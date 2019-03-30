import java.util.Arrays;

public class RPNBundle {

	Expression[] expArr;
	Input[] inpArr;

	RPNBundle(Expression[] expArr, Input[] inpArr) {
		this.expArr = expArr;
		this.inpArr = inpArr;
	}

	void printBundle() {
		System.out.println(Arrays.toString(this.expArr));
		System.out.println(Arrays.toString(this.inpArr));
	}

}
