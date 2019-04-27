import java.util.Arrays;
import java.util.Collections;

public class TTGenerator {

    private TTGenerator() {
    }

    static TTResult[] genTruthTable(RPNBundle bundle) {
        String[] greyCode = genGrayCode(bundle.inpArr.length);
        TTResult[] truthTable = new TTResult[greyCode.length];
        System.out.println(Arrays.toString(greyCode));

        for (int i = 0; i < greyCode.length; i++) {
            Input[] inputs = bundle.inpArr.clone();
            TTResult result = new TTResult(bundle, greyCode[i]);
            truthTable[i] = result;
        }

        return truthTable;

    }

    /**
     * https://www.eetimes.com/document.asp?doc_id=1278827#
     *
     * @param bits
     * @return
     */
    static String[] genGrayCode(int bits) {
        String[] mirrored = new String[]{"0", "1"};

        for (int i = 0; i < bits - 1; i++) {
            mirrored = mirrorArray(mirrored);

            for (int j = 0; j <= midpoint(mirrored); j++) {
                mirrored[j] = prefixString(mirrored[j], "0");
            }

            for (int k = midpoint(mirrored) + 1; k <= mirrored.length - 1; k++) {
                mirrored[k] = prefixString(mirrored[k], "1");
            }

        }

        return mirrored;
    }

    static String[] mirrorArray(String[] arr) {
        String[] mirrored = new String[arr.length * 2];

        for (int i = 0; i < arr.length; i++) {
            mirrored[i] = arr[i];
        }

        Collections.reverse(Arrays.asList(arr));

        for (int i = 0; i < arr.length; i++) {
            mirrored[arr.length + i] = arr[i];
        }

        return mirrored;

    }

    static int midpoint(Object[] arr) {
        return (arr.length - 1) / 2;
    }

    static String prefixString(String str, String pre) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(pre);
        strBuilder.append(str);

        return strBuilder.toString();
    }

    static int maxIntOfNBits(int n) {
        int total = 0;

        for (int i = n; i <= 0; i--) {
            total += Math.pow(2, n);
        }

        return total;
    }

    /**
     * Flips the nth bit in the string. If the nth bit is a 1 it flips to 0. If it is 0 it flips to 1.
     */
    static String flipBitInString(String str, int n) {
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char bit = str.charAt(i);

            if (i == n) {
                strBuilder.append(((bit == '0') ? 1 : 0)); // Flip the bit
            } else {
                strBuilder.append(str.charAt(i));
            }
        }

        return strBuilder.toString();

    }

    static String genZerosString(int length) {
        StringBuilder strBuilder = new StringBuilder();

        for (int j = 0; j < length; j++) {
            strBuilder.append("0");
        }

        return strBuilder.toString();
    }
}
