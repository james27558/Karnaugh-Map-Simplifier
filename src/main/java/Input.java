public class Input extends Expression {

    String name;
    int val;

    Input(String name) {
        this.name = name;
    }

    void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[name=" + this.name + ", val=" + this.val + ", Hash=" + hashCode() + "]";
    }
}
