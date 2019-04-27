import java.util.HashMap;

public class Node {
    boolean visited;
    int val = 0;
    boolean startingNode = false;
    HashMap<String, Integer> topInputs = new HashMap<String, Integer>();
    HashMap<String, Integer> leftInputs = new HashMap<String, Integer>();

    void reset() {
        this.startingNode = false;
    }

    void setAsStartingNode() {
        this.startingNode = true;
    }

    void addTopInput(String key, int value) {
        topInputs.put(key, value);
    }

    void addLeftInput(String key, int value) {
        leftInputs.put(key, value);
    }

    HashMap<String, Integer> getAllInputs() {
        HashMap<String, Integer> out = new HashMap<>();
        out.putAll(topInputs);
        out.putAll(leftInputs);

        return out;
    }

    public String toString() {
        return "N v:" + this.val;
    }

}
