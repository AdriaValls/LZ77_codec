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

    public void codec3(String text_line){
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();

        String input = text_line;
        int inputLen = text_line.length();
        int Mdest = 8; //sliding window
        int Ment = 4; //input window

        System.out.println("ent window: " + Ment);
        System.out.println("sliding window: " + Mdest);

        String bitIns = inserter.bitInsertion(Mdest,input);
        String encodedInput = encoder.String_encoder(bitIns, Ment, Mdest);

        System.out.println("encoded input:" + encodedInput);
    }


    public void lz77_text(){ //

        Reader txtReader = new Reader();

        File file_text = new File("C:\\Users\\adriv\\IdeaProjects\\LZ77_codec\\hamlet_short.txt");
        try {
            Scanner scanner =  new Scanner(file_text);
            while (scanner.hasNextLine()){

                StringBuffer string = new StringBuffer(scanner.nextLine());
                StringBuffer encoded = txtReader.string2ASCIIbin(string);
                //StringBuffer decoded = txtReader.ASCIIbin2string(encoded);

                System.out.println("input: " + encoded);
                codec3(encoded.toString()); //call old lz77 code using the current encoded string as input
                //System.out.println(decoded);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
