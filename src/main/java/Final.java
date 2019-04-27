import java.util.ArrayList;
import java.util.Arrays;

public class Final {

    private Final() {
    }

    static Node[][] generateKMap(String inputString) {
        printArr(RPNParser.mParseToStringArr(inputString));

        RPNBundle bundle = RPNParser.mParsetoBundle(inputString);

        bundle.printBundle();

        TTResult[] truthTable = TTGenerator.genTruthTable(bundle);

        for (int i = 0; i < truthTable.length; i++) {
            System.out.println(truthTable[i]);
        }

        return KMGenerator.genKMap(truthTable);

    }

    static ArrayList<Grouping> generateGroupings(Node[][] KMap) {
        return KMGroupingGenerator.generateGroupings(KMap);
    }

    static String generateSimplifiedExpression(String inputString) {
        Node[][] KMap = generateKMap(inputString);

        System.out.println(Arrays.deepToString(KMap));

        ArrayList<Grouping> groupings = generateGroupings(KMap);

        System.out.println(Arrays.toString(groupings.toArray()));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < groupings.size(); i++) {
            System.out.println(groupings.get(i).returnCommonInputs());

            String currentInputString = groupings.get(i).returnStringRepresentationOfCommonInputs();

            if (currentInputString.length() == 0) {
                return "Expression can not be simplified";
            }

            if (currentInputString.length() > 1) {
                sb.append("(" + currentInputString + ")");
            } else {
                sb.append(currentInputString);
            }

            if (i < groupings.size() - 1) {
                sb.append(" OR ");
            }
        }

        return sb.toString();
    }

    static void printArr(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
