package codec;

public class BitInserter {

    public BitInserter(){}

    public String bitInsertion(int Mdest, String sequence) {

        char nextChar; //in case we have too much of the same bit, we use the insert bit character
        char currChar = sequence.charAt(0);
        String output = "";
        int count = 0; //create a counter to keep track of consecutive-repeated bits (000000 or 111111)

        for (int i = 0; i < sequence.length(); i++) {
            nextChar = (sequence.charAt(i));
            output += "" + nextChar;
            if (nextChar == currChar) {
                count++;
                if (count == Mdest - 1) { //bit insertion
                    if (nextChar == '0') {
                        currChar = '1';
                    } else {
                        currChar = '0';
                    }
                    count = 1;
                    output += "" + currChar;
                }
            } else {
                count = 1;
                currChar = nextChar;
            }
        }
        return output;
    }


    public String bitDeletion(int Mdest, String sequence){
        int count = 0; //create a counter to keep track of consecutive-repeated bits (000000 or 111111)
        char nextChar; //in case we have too much of the same bit, we use the insert bit character
        char seqBit = sequence.charAt(0);
        String output = "";
        boolean deletenext = false;

        for (int i = 0; i < sequence.length(); i++ ) {
            nextChar = sequence.charAt(i);
            if(deletenext){
                if(nextChar != sequence.charAt(i)){
                    nextChar = sequence.charAt(i);
                    output += ""+nextChar;
                    count = 1;
                }else{
                    count = 0;
                }

                deletenext = false;
            }else{
                nextChar = sequence.charAt(i);
                if(nextChar == seqBit){
                    count += 1;
                    if(count == Mdest-1){
                        output += ""+nextChar;
                        deletenext = true;
                    }else{
                        output += ""+nextChar;
                    }
                }else{
                    seqBit = nextChar;
                    count = 1;
                    output += ""+nextChar;
                }
            }
        }
        return output;
    }
}
