public class Operator extends Expression {
    String name;
    operators type;

    Operator(String val) {
        this.name = val;

        if (this.name.equals("AND")) {
            this.type = operators.AND;

        } else if (this.name.equals("OR")) {
            this.type = operators.OR;

        } else if (this.name.equals("NOT")) {
            this.type = operators.NOT;
        }

    }


    public enum operators {AND, NOT, OR}

    ;
}
