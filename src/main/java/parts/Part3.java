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

    public void lz77_text(){ //

        Reader txtReader = new Reader();
        //C:\Users\adriv\IdeaProjects\LZ77_codec\quijote_short.txt
        //C:\Users\adriv\IdeaProjects\LZ77_codec\hamlet_short.txt
        StringBuffer binTxt = txtReader.cargarTxt("C:\\Users\\adriv\\IdeaProjects\\LZ77_codec\\quijote_short.txt");

        System.out.println("input: " + binTxt);
        codec3(binTxt.toString()); //call old lz77 code using the current encoded string as input
    }

    public void codec3(String text){
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        Reader txtReader = new Reader();

        String input = text;
        int inputLen = text.length();
        int Mdest = 256*2*2*2; //sliding window
        int Ment = 32; //input window

        System.out.println("ent window: " + Ment);
        System.out.println("sliding window: " + Mdest);
        System.out.println("original lenght:" + text.length());

        long nano_startTime = System.nanoTime();
        String bitIns = inserter.bitInsertion(Mdest,input);
        String encodedTxt = encoder.String_encoder(bitIns, Ment, Mdest);
        long nano_endTime = System.nanoTime();

        System.out.println("Time taken in nano seconds: " + (nano_endTime - nano_startTime));
        System.out.println("inserted lenght:" + bitIns.length());
        System.out.println("encoded lenght:" + encodedTxt.length());


        String decoded = decoder.decode(encodedTxt, Ment, Mdest);
        StringBuffer bitDel = inserter.bitDeletion(Mdest,decoded);

        StringBuffer decTxt = txtReader.ASCIIbin2string(bitDel);
        System.out.println("Decoded text: " + decTxt);
    }



}
