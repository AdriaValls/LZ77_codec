package parts;

import codec.Encoder;
import codec.Decoder;

public class Part1 {

    public  Part1(){
        int Ment  = 4;
        int Mdes  = 8;
        String input = "110100010010110110001010100101";

        Encoder encoder = new Encoder();
        String encoded = encoder.String_encoder(input, Ment , Mdes);

        Decoder decoder = new Decoder();
        String output = decoder.decode(encoded, Ment , Mdes);

        System.out.print("original = "+input+"\n");
        System.out.print("encoded = "+encoded+"\n");
        System.out.print("decoded = "+output+"\n");

        //Comprovamos que el original sea el mismo que el decodificado
        assert  output.equals(input);

    }
}
