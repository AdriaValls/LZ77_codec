import java.util.HashMap;

public class TableManager {

    public TableManager(){}

    public HashMap<Integer, String> numberToBitTable(int intSize) {
        HashMap<Integer, String> bitMap = new HashMap<Integer, String>();
        String zero = "0";
        int bitsNeeded = (int) Math.round(Math.log(intSize) / Math.log(2));

        String bits = "";
        for (int i = 0; i < bitsNeeded; i++) {
            bits += "0";
        }
        bitMap.put(intSize, bits);
        for (int i = 1; i <= intSize-1; i++) {
            String bitString = Integer.toBinaryString(i);
            if(bitString.length() < bitsNeeded){
                int extraBits =  bitsNeeded - bitString.length();
                String extraString = "";
                for (int b = 0; b < extraBits; b++) {
                    extraString += zero;
                }
                bitString = extraString+bitString;
            }
            bitMap.put(i,bitString);

        }
        return bitMap;
    }


    public HashMap<String , Integer> bitToNumberTable(int intSize) {
        HashMap<String , Integer> bitMap = new HashMap<String , Integer>();
        String zero = "0";
        int bitsNeeded = (int) Math.round(Math.log(intSize) / Math.log(2));

        String bits = "";
        for (int i = 0; i < bitsNeeded; i++) {
            bits += "0";
        }
        bitMap.put(bits, intSize);
        for (int i = 1; i <= intSize-1; i++) {
            String bitString = Integer.toBinaryString(i);
            if(bitString.length() < bitsNeeded){
                int extraBits =  bitsNeeded - bitString.length();
                String extraString = "";
                for (int b = 0; b < extraBits; b++) {
                    extraString += zero;
                }
                bitString = extraString+bitString;
            }
            bitMap.put(bitString,i);

        }
        return bitMap;
    }



}