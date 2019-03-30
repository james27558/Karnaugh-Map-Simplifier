public class Operator extends Expression {
	String name;
	operators type;

	Operator(String val) {
		this.name = val;

		if (val == "AND") {
			this.type = operators.AND;
		} else if (val == "NOT") {
			this.type = operators.NOT;
		} else if (val == "OR") {
			this.type = operators.OR;
		}
	}


	enum operators {AND, NOT, OR}

	;
}
