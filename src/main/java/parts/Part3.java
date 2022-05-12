package parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import codec.BitInserter;
import codec.Decoder;
import codec.Encoder;
import textReader.Reader;

public class Part3 {

    public Part3(){ //lz77-text
        lz77_text();
    }

    public void codec3(String text){
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        Reader txtReader = new Reader();

        String input = text;
        int inputLen = text.length();
        int Mdest = 124; //sliding window
        int Ment = 8; //input window

        System.out.println("ent window: " + Ment);
        System.out.println("sliding window: " + Mdest);
        System.out.println("original lenght:" + text.length());

        String bitIns = inserter.bitInsertion(Mdest,input);

        System.out.println("inserted lenght:" + bitIns.length());

        String encodedTxt = encoder.String_encoder(bitIns, Ment, Mdest);

        System.out.println("encoded lenght:" + encodedTxt.length());


        String decoded = decoder.decode(encodedTxt, Ment, Mdest);
        StringBuffer bitDel = inserter.bitDeletion(Mdest,decoded);

        StringBuffer decTxt = txtReader.ASCIIbin2string(bitDel);
        System.out.println("Decoded text: " + decTxt);
    }


    public void lz77_text(){ //

        Reader txtReader = new Reader();

        StringBuffer binTxt = txtReader.cargarTxt("C:\\Users\\adriv\\IdeaProjects\\LZ77_codec\\hamlet_short.txt");

        System.out.println("input: " + binTxt);
        codec3(binTxt.toString()); //call old lz77 code using the current encoded string as input
        //System.out.println(decoded);
        //StringBuffer decTxt = txtReader.ASCIIbin2string(binTxt);
        //System.out.println("Decoded text: " + decTxt);

    }
}
