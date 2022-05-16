package parts;

import codec.BitInserter;
import codec.Decoder;
import codec.Encoder;
import textReader.Reader;

import java.util.ArrayList;
import java.util.List;

public class Part5 {

    public Part5(){ //lz77-text
        loadBinText();
    }

    public void loadBinText(){ //
        Reader txtReader = new Reader();
        //C:\Users\adriv\IdeaProjects\LZ77_codec\quijote_short.txt
        //C:\Users\adriv\IdeaProjects\LZ77_codec\hamlet_short.txt
        StringBuffer binTxt = txtReader.cargarTxt("C:\\Users\\adriv\\IdeaProjects\\LZ77_codec\\hamlet_short.txt");

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

        List<Integer> outlen = new ArrayList<>();
        List<Integer> MentValues = new ArrayList<>();
        List<Integer> MdestValues = new ArrayList<>();
        List<Long> timeValues = new ArrayList<>();

        int maxWindowSize = 4096;
        int minWindowSize = 4;
        int Ment=minWindowSize;
        int Mdest=minWindowSize;
        long nano_startTime;
        long nano_endTime;


        while (Mdest <= maxWindowSize){
            System.out.println("Sliding window Size =  "+Mdest+"\n");
            Ment=minWindowSize;
            while (Ment < Mdest){
                System.out.println("Entry window Size =  "+Ment+"\n");

                //codecRun(input, Ment, Mdest);
                nano_startTime = System.nanoTime();
                String bitIns = inserter.bitInsertion(Mdest,input);
                String encodedTxt = encoder.String_encoder(bitIns, Ment, Mdest);
                nano_endTime = System.nanoTime();

                outlen.add(encodedTxt.length());
                MentValues.add(Ment);
                MdestValues.add(Mdest);
                timeValues.add((nano_endTime-nano_startTime));

                Ment = Ment * 2;
            }

            Mdest = Mdest * 2;
        }
        System.out.print(inputLen);
        System.out.print(outlen);
        System.out.print(MentValues);
        System.out.print(MdestValues);
        System.out.print(timeValues);
    }
    /*
    *



        System.out.print(MentValues);
        System.out.print(MdestValues);
        System.out.print(compressionValues);
        System.out.print(timeValues);
        *
        *
        *
        * */



}
