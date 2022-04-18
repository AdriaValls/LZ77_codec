import java.util.HashMap;

public class Encoder {
    public Encoder(){

    }
    public String String_encoder(String input, int Ment, int Mdest){
        /*
        We first use the Table manager to get the correct format of the mataches (L, D) in bits, then we find the matches.
         */

        TableManager table1 = new TableManager();
        TableManager table2 = new TableManager();
        HashMap<Integer, String> L_formatTable = table1.numberToBitTable(Ment);
        HashMap<Integer, String> D_formatTable = table2.numberToBitTable(Mdest);

        //find matches
        int matches[];
        matches = new int[10];
        //String input = "1100100110";
        String sldWin = input.substring(0,Mdest);
        String entWin = input.substring(Mdest, Mdest + Ment);
        //System.out.println("Encoder sliding window: "+ sldWin +"\n");
        //System.out.println("Encoder ent window: "+ entWin);

        String compressed_input = "";

        /*
        we use the J to keep track of the L throughout the entire encoding process. We also use the J to update the input window (entWin)
         */
        for (int j = 0; j < input.length(); j++) {

            String temp = entWin; //equals the input window, which will decrease in size as we compare it with the Sliding window trying to find matches
            int max = temp.length(); //an index for the sliding window, it is used to make sure we compare a chunk of the sliding window that has the...
            //same size as the input window.
            boolean stop = false; //when we find our first match, it will be the biggest, so we tell it to stop searching
            int L = 1; //the width of the match is 1 by default, if we find a match, it will be updated with its new width

            while (temp.length() > 0 && stop == false) { //if we run out of input window, we stop, or if we find a match

                for (int i = 0; i < (sldWin.length() - temp.length()) + 1; i++) {
                    if (temp.equals(sldWin.substring(i, max))) {
                        int D = sldWin.length() - i;
                        L = temp.length();

                        String formatLon = L_formatTable.get(L);
                        String formatDist = D_formatTable.get(D);

                        //System.out.println(sldWin.substring(i, max) + " == " + temp + "  (" + L + ", " + D + ")" + "  " + formatLon + " " + formatDist); //+ "  "+formatLon + " " +formatDist
                        compressed_input += formatLon + formatDist;
                        //System.out.println(compressed_input);
                        stop = true; //since we found a match, we stop
                        break;
                    }
                    max++; //if we don't find any matches, we move to the right on the sliding window (within the same sliding window)
                }
                temp = temp.substring(0, temp.length() - 1); //if we don't find any matches with the current chunk of the input window, ...
                //we reduce the size of the input window, and search again.
                max = temp.length(); //we also need to update the new max index, because the size will be smaller, and so should be the chunk of the
                //sliding window we compare it to.
            }

            sldWin = sldWin.substring(L,sldWin.length()) + entWin.substring(0,L); //we update the sliding window (shift right)
            j += L; //we update our J by adding the L, which will help us jump to the next position the input window should slide.
            //entWin = entWin.substring(L,entWin.length()) + input.substring(Mdest+Ment,Mdest+Ment+L); //old code, ignore

            if(j+Mdest+Ment < input.length()){  //if the input window can slide to the right
                entWin = input.substring(j+Mdest,j+Mdest+Ment); //update accordingly
                //System.out.println(sldWin + " " + entWin);
            }
            else{ //there is no enough space left (the next will be entWin.length() < Ment)
                entWin = input.substring(j+Mdest,input.length()); //so we just give it the bits that are left
                //System.out.println(sldWin + " " + entWin);
                compressed_input += entWin; //we save the remaining bits at the end of the string
                break;
            }

            stop = false; //we set stop back to false, so we can use the while loop for the next iteration.
            j--; //since we are using J + L to keep track of our movement, for example: 0 + 3 = j = 3, the loop...
            //always increases j by +1, so to keep the real value we cancel that +1 with a -1.

        }
        compressed_input = input.substring(0,Mdest) + compressed_input; //original sliding window + the compressed input
        //System.out.println(compressed_input);
        return compressed_input;

    }
}
