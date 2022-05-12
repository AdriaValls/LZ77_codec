import codec.BitInserter;
import codec.Decoder;
import codec.Encoder;
import codec.TableManager;
import org.junit.Test;

import java.util.HashMap;

public class LZ77Test {

    @Test
    public void apartado1() {
        int Ment  = 4;
        int Mdes  = 8;
        BitInserter inserter = new BitInserter();
        String input = "1101000100100101101100011010100101";

        String insBit = inserter.bitInsertion(Mdes, input);

        Encoder encoder = new Encoder();
        String encoded = encoder.String_encoder(insBit, Ment , Mdes);

        Decoder decoder = new Decoder();
        String decoded = decoder.decode(encoded, Ment , Mdes);
        String output = inserter.bitInsertion(Mdes, decoded);

        System.out.print("original = "+input+"\n");
        System.out.print("encoded = "+encoded+"\n");
        System.out.print("decoded = "+output+"\n");

        //Comprovamos que el original sea el mismo que el decodificado
        assert  output.equals(input);

    }


    @Test
    public void insertBitTest() {
        int Mdes  = 4;
        System.out.print("Window size = "+ Mdes+"\n");
        String input = "11010000010010110110001010100101";
        String sol = "1101000100100101101100011010100101";
        BitInserter inserter = new BitInserter();
        String output = inserter.bitInsertion(Mdes, input);
        System.out.print("in = "+input+"\n");
        System.out.print("en = "+output+"\n");
        System.out.print(input.length()+"\n");
        System.out.print(output.length()+"\n");
    }
    @Test
    public void deleteBitTest() {
        int Mdes  = 4;
        String input = "1101000100100101101100011010100101";
        System.out.print("in: "+input+"\n");
        BitInserter inserter = new BitInserter();
        StringBuffer output = inserter.bitDeletion(Mdes, input);
        System.out.print("ou: "+ output+"\n");

        System.out.print(input.length()+"\n");
        System.out.print(output.length()+"\n");
    }



    //Otros tests  del código
    @Test
    public void NumToBitTest() {
        TableManager tables = new TableManager();

        HashMap<Integer, String> numBits = tables.numberToBitTable(8);
        HashMap<String ,Integer > bitsnum = tables.bitToNumberTable(8);

    }
    @Test
    public void encodingTest() {
        TableManager tables = new TableManager();
        int entrySize = 4;
        int slideSize = 8;
        String input = "110100010010110110001010100101";

        Decoder decoder = new Decoder();

        String output = decoder.decode(input, entrySize, slideSize);
        System.out.print(output);
    }

    @Test
    public void decodingTest() {
        TableManager tables = new TableManager();
        int entrySize = 4;
        int slideSize = 8;
        String input = "110100010010110110001010100101";

        Decoder decoder = new Decoder();

        String output = decoder.decode(input, entrySize, slideSize);
        System.out.print(output);
    }



}