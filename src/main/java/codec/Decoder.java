package codec;

import java.util.HashMap;

public class Decoder {
    private TableManager tables;

    public Decoder(){
        this.tables = new TableManager();

    }

    public String decode(String input, int Ment, int Mdes){

        HashMap<String ,Integer > entTable = tables.bitToNumberTable(Ment);
        HashMap<String ,Integer > desTable = tables.bitToNumberTable(Mdes);


        int bitsS = (int) Math.round(Math.log(Mdes) / Math.log(2));
        int bitsE = (int) Math.round(Math.log(Ment) / Math.log(2));
        int totalLen = input.length();

        int borderBit = Mdes;
        String output = input.substring(borderBit-Mdes,Mdes);
        int L;
        int D;
        String slideBits;
        String entBits;
        boolean hasNext = borderBit+bitsS+bitsE < input.length();

        while(hasNext){

            //Read bits representing both windows from input
            entBits= input.substring(borderBit,borderBit+bitsE);
            slideBits= input.substring(borderBit+bitsE, borderBit+bitsE+bitsS);

            //Get each respective size
            L = entTable.get(entBits);
            D = desTable.get(slideBits);
            //add the coincidence to the output
            String nextBits = output.substring(output.length()-D, output.length()-D+L);
            output += nextBits;
            borderBit += bitsS+bitsE;

            //Check if we have space for an (L,D) on the input or if it's the end
            if(borderBit+bitsS+bitsE <= totalLen){
                //Move the border of our input reading
            }else{
                hasNext = false;
            }
        }
        output += input.substring(borderBit);
        return output;
    }

}