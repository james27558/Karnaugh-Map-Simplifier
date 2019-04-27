import java.util.Arrays;

public class KMGenerator {
    private KMGenerator() {
    }

    static Node[][] genKMap(TTResult[] truthTable) {
        int noOfInputs = truthTable[0].inputs.size();
        int noOfTopSideInputs = (int) Math.ceil(noOfInputs / (double) 2);
        int noOfLeftSideInputs = noOfInputs / 2; // Integer Division
        System.out.println(noOfInputs + " " + noOfTopSideInputs + " " + noOfLeftSideInputs);


        String[] leftGrayCode = TTGenerator.genGrayCode(noOfLeftSideInputs);
        String[] topGrayCode = TTGenerator.genGrayCode(noOfTopSideInputs);

        Node[][] KMap = new Node[leftGrayCode.length][topGrayCode.length];

        for (int i = 0; i < KMap.length; i++) {
            for (int j = 0; j < KMap[0].length; j++) {
                KMap[i][j] = new Node();
            }
        }

        for (int i = 0; i < topGrayCode.length; i++) {
            for (int j = 0; j < leftGrayCode.length; j++) {
                String currentTopGreyCode = topGrayCode[i];
                String currentLeftGreyCode = leftGrayCode[j];

                TTResult truthTableResult = searchTruthTable(currentTopGreyCode + currentLeftGreyCode, truthTable);

                // Add the grey code used to the Node
                for (int k = 0; k < truthTableResult.inputs.size(); k++) {
                    if (k <= noOfTopSideInputs - 1) {
                        String stringInput = (String) truthTableResult.inputs.keySet().toArray()[k];
                        KMap[j][i].addTopInput(stringInput, (int) truthTableResult.inputs.get(stringInput));
                        System.out.println(stringInput + (int) truthTableResult.inputs.get(stringInput));
                    } else {
                        String stringInput = (String) truthTableResult.inputs.keySet().toArray()[k];
                        KMap[j][i].addLeftInput(stringInput, (int) truthTableResult.inputs.get(stringInput));
                        System.out.println(stringInput + (int) truthTableResult.inputs.get(stringInput));
                    }
                }

                KMap[j][i].val = truthTableResult.result;
            }
        }

        return KMap;
    }

    static TTResult searchTruthTable(String greyCodeInputs, TTResult[] truthTable) {
        int[] greyCodeInputsArray = new int[greyCodeInputs.length()];

        for (int i = 0; i < greyCodeInputs.length(); i++) {
            if (greyCodeInputs.charAt(i) == '1') {
                greyCodeInputsArray[i] = 1;
            }
        }

        System.out.println(Arrays.toString(greyCodeInputsArray));

        for (int i = 0; i < truthTable.length; i++) {
            boolean invalid = false;
            TTResult currentTTResult = truthTable[i];

            for (int j = 0; j < greyCodeInputsArray.length; j++) {
                if (greyCodeInputsArray[j] != (int) currentTTResult.inputs.values().toArray()[j]) {
                    invalid = true;
                }
            }

            if (!invalid) {
                return currentTTResult;
            }

        }

        return null;
    }
}
