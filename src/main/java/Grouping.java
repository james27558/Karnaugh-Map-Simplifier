import java.util.HashMap;

public class Grouping {

    Node[][] nodeList;
    int i;
    int j;
    int width;
    int height;
    int size;
    HashMap<String, Integer> commonInputs;

    Grouping(Node[][] nodeList, int j, int i, int width, int height) {
        this.nodeList = nodeList;
        this.i = i;
        this.j = j;
        this.width = width;
        this.height = height;
        this.size = (width + 1) * (height + 1);
    }

    void setGroupingAsVisited() {
        int boardWidth = nodeList[0].length;
        int boardHeight = nodeList.length;

        for (int k = 0; k <= height; k++) {
            for (int l = 0; l <= width; l++) {
                if (nodeList[(j + k) % boardHeight][(i + l) % boardWidth].val == 1) {
                    nodeList[(j + k) % boardHeight][(i + l) % boardWidth].visited = true;
                }
            }
        }
    }

    boolean isGroupFullyVisited() {
        int boardWidth = nodeList[0].length;
        int boardHeight = nodeList.length;

        for (int k = 0; k <= height; k++) {
            for (int l = 0; l <= width; l++) {
                if (nodeList[(j + k) % boardHeight][(i + l) % boardWidth].visited == false) {
                    return false;
                }
            }
        }

        return true;
    }

    void calculateCommonInputs() {
        HashMap<String, Integer> runningInputs = nodeList[this.j][this.i].getAllInputs();
        System.out.println("S " + runningInputs);

        int boardWidth = nodeList[0].length;
        int boardHeight = nodeList.length;

        for (int n = 0; n <= this.width; n++) {
            for (int p = 0; p <= this.height; p++) {
                Node currentNode = nodeList[(this.j + p) % boardHeight][(this.i + n) % boardWidth];
                HashMap<String, Integer> currentNodeInputs = currentNode.getAllInputs();

                System.out.println("C " + currentNodeInputs);

                for (String key : currentNodeInputs.keySet()) {
                    if (runningInputs.containsKey(key)) {
                        if (!runningInputs.get(key).equals(currentNodeInputs.get(key))) {
                            runningInputs.remove(key);
                        }
                    }
                }

                System.out.println("R " + runningInputs);

            }
        }

        this.commonInputs = runningInputs;
        System.out.println("DONE");

    }

    HashMap<String, Integer> returnCommonInputs() {
        if (commonInputs == null) {
            calculateCommonInputs();
        }

        return commonInputs;
    }

    String returnStringRepresentationOfCommonInputs() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < commonInputs.size(); i++) {
            String key = (String) commonInputs.keySet().toArray()[i];
            String strToAppend = (commonInputs.get(key) == 1) ? key : "NOT(" + key + ")";

            if (i != commonInputs.size() - 1) {
                sb.append(strToAppend);
                sb.append(" AND ");

            } else {
                sb.append(strToAppend);
            }
        }

        return sb.toString();
    }

    public String toString() {
        return "[G " + j + ", " + i + ", " + width + ", " + height + "]";
    }
}
