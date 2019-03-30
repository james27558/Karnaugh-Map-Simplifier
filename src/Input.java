public class Input extends Expression {

	String name;

	Input(String val) {
		this.name = val;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[name=" + name + ", Hash=" + hashCode() + "]";
	}
}
