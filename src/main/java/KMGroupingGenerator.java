import java.util.ArrayList;
import java.util.Arrays;

public class KMGroupingGenerator {
    private KMGroupingGenerator() {
    }

    static ArrayList<Grouping> generateGroupings(Node[][] nodeList) {
        ArrayList<Grouping> acceptedGroupings = new ArrayList<>();
        int boardWidth = nodeList[0].length;
        int boardHeight = nodeList.length;

        for (int j = 0; j < nodeList.length; j++) {
            for (int i = 0; i < nodeList[0].length; i++) {
                Node startingNode = nodeList[j][i];

                if (startingNode.val == 1) {
                    boolean horizontalWrap = false;
                    boolean verticalWrap = false;

                    int maxWidth = 0;
                    int maxHeight = 0;

                    startingNode.setAsStartingNode();

                    // Preliminary Search
                    while (nodeList[j][(i + maxWidth + 1) % boardWidth].val == 1 && nodeList[j][(i + maxWidth + 1) % boardWidth].startingNode == false) {
                        if (i + maxWidth + 1 > boardWidth - 1) {
                            horizontalWrap = true;
                        }

                        maxWidth++;
                    }

                    while (nodeList[(j + maxHeight + 1) % boardHeight][i].val == 1 && nodeList[(j + maxHeight + 1) % boardHeight][i].startingNode == false) {
                        if (j + maxHeight + 1 > boardHeight - 1) {
                            verticalWrap = true;
                        }

                        maxHeight++;
                    }

                    ArrayList<Grouping> widthFirstGroupings = new ArrayList<>();

                    for (int width = maxWidth; width >= 0; width--) {
                        for (int height = maxHeight; height >= 0; height--) {
                            if (isGroupValid(nodeList, j, i, width, height)) {
                                widthFirstGroupings.add(new Grouping(nodeList, j, i, width, height));
                            }
                        }
                    }

                    System.out.println("Possible " + Arrays.toString(widthFirstGroupings.toArray()));


                    for (Grouping group : widthFirstGroupings) {
                        if (group.isGroupFullyVisited() == false) {
                            group.setGroupingAsVisited();
                            acceptedGroupings.add(group);

                            System.out.println("Group added " + group.toString());
                        }
                    }

                    System.out.println(j + " " + i + ", " + maxWidth + " " + maxHeight + " horizWrap " + horizontalWrap + " vertWrap " + verticalWrap);

                    startingNode.startingNode = false;

                }

            }
        }


//        System.out.println(Arrays.toString(acceptedGroupings.toArray()));

        ArrayList<Grouping> fullyAcceptedGroupings = new ArrayList<>();
        ArrayList<Integer> excludedIndicies = new ArrayList<>();

        for (int i = 0; i < acceptedGroupings.size(); i++) {
            resetAllNodes(nodeList);
            int j;

            for (j = 0; j < acceptedGroupings.size(); j++) {
                if (i != j && !excludedIndicies.contains(j)) {
                    // Apply Grouping
                    acceptedGroupings.get(j).setGroupingAsVisited();
                }
            }

            if (!acceptedGroupings.get(i).isGroupFullyVisited()) {
                fullyAcceptedGroupings.add(acceptedGroupings.get(i));
            } else {
                excludedIndicies.add(i);
            }

        }

//        System.out.println(Arrays.toString(fullyAcceptedGroupings.toArray()));

        for (int i = 0; i < fullyAcceptedGroupings.size(); i++) {
            fullyAcceptedGroupings.get(i).calculateCommonInputs();
        }

        return fullyAcceptedGroupings;

    }

    static void resetAllNodes(Node[][] nodeList) {
        for (int i = 0; i < nodeList.length; i++) {
            for (int j = 0; j < nodeList[0].length; j++) {
                nodeList[i][j].visited = false;
            }
        }
    }

    static boolean isGroupValid(Node[][] nodeList, int j, int i, int width, int height) {
        int boardWidth = nodeList[0].length;
        int boardHeight = nodeList.length;

        for (int k = 0; k <= height; k++) {
            for (int l = 0; l <= width; l++) {
                if (nodeList[(j + k) % boardHeight][(i + l) % boardWidth].val == 0) {
                    return false;
                }
            }
        }

        if (isDoubleWholeNumber(Math.log((width + 1) * (height + 1)) / Math.log(2))) {
            return true;
        }

        return false;
    }

    static boolean isDoubleWholeNumber(double num) {
        if (num - Math.floor(num) == 0) {
            return true;
        }

        return false;
    }

//    static void orderGroupingsDescending(ArrayList<Grouping> groupingsList) {
//        boolean swap = true;
//
//        while (swap) {
//            swap = false;
//
//            for (int i = 0; i < groupingsList.size(); i++) {
//                if (i < groupingsList.size() - 1) {
//                    if (groupingsList.get(i).size < groupingsList.get(i + 1).size) {
//                        Grouping swappedElem = groupingsList.get(i);
//                        groupingsList.set(i, groupingsList.get(i + 1));
//                        groupingsList.set(i + 1, swappedElem);
//                        swap = true;
//                    }
//                }
//            }
//
//        }
//
//    }
}
