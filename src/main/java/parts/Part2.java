package parts;

import charts.LineChart;
import codec.BitInserter;
import codec.Common;
import codec.Decoder;
import codec.Encoder;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Part2 {

    public Part2(){
        Common common = new Common();
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();

        int maxWindowSize = 512;
        int minWindowSize = 2;

        int inputLen = 10000;
        //Input window (Ment) and sliding window (Mdest) variables.
        int windowSize=minWindowSize;
        String input = common.randomSequence(inputLen);


        XYSeries series1 = new XYSeries("Window size");

        while (windowSize <= maxWindowSize){
            System.out.println("Sliding window Size =  "+windowSize+"\n");
            System.out.println("Entry window Size =  "+windowSize/2+"\n");

            String bitIns = inserter.bitInsertion(windowSize,input);
            String encodedInput = encoder.String_encoder(bitIns, windowSize/2, windowSize);

            //original/nou x:1
            float compressionFactor = (float) inputLen / (float) encodedInput.length();

            series1.add(windowSize, compressionFactor);
            windowSize = windowSize * minWindowSize;
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        LineChart chart = new LineChart();
        chart.initUI(dataset);
        chart.setVisible(true);
    }

    public static void graph2values(){
        Common common = new Common();
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();

        int maxWindowSize = 2048;
        int minWindowSize = 2;

        int inputLen = 10000;
        //Input window (Ment) and sliding window (Mdest) variables.
        int Ment=minWindowSize;
        int Mdest=minWindowSize;
        String input = common.randomSequence(inputLen);



        while (Mdest <= maxWindowSize){
            System.out.println("Sliding window Size =  "+Mdest+"\n");
            Ment=minWindowSize;
            while (Ment <= Mdest){
                System.out.println("Entry window Size =  "+Ment+"\n");
                //codecRun(input, Ment, Mdest);
                String bitIns = inserter.bitInsertion(Mdest,input);
                String encodedInput = encoder.String_encoder(bitIns, Ment, Mdest);

                //original/nou x:1
                float compressionFactor = inputLen /  encodedInput.length();

                Ment = Ment * minWindowSize;
            }

            Mdest = Mdest * minWindowSize;
        }
        codecRun(input, Ment, Mdest);
        encoder = new Encoder();
        String encoded = encoder.String_encoder(input, 16 , 32);

        decoder = new Decoder();
        String output = decoder.decode(encoded, 16 , 32);

        System.out.print("original = "+input+"\n");
        System.out.print("encoded = "+encoded+"\n");
        System.out.print("decoded = "+output+"\n");
        if(input.equals(output)){
            System.out.print("Correccto \n");
        }
/*
        com.zetcode.GraphTest graphTest = new com.zetcode.GraphTest();
        graphTest.showgraph();
        graphTest.setVisible(true);

 */
    }

    public static void codecRun(String input, int Ment, int Mdest){


        System.out.println("Input length: "+input.length()+"\n");
        System.out.println("Input stream: "+input+"\n");

        System.out.println("Entry window size: "+Ment+"\n");
        System.out.println("Sliding windowsize: "+Mdest+"\n");

        BitInserter inserter = new BitInserter();
        String bitIns = inserter.bitInsertion(Mdest,input);

        Encoder encoder = new Encoder();
        String encodedInput = encoder.String_encoder(bitIns, Ment, Mdest);

        Decoder decoder = new Decoder();
        String outputWithBit = decoder.decode(encodedInput, Ment, Mdest);
        System.out.println("Decoded sequence with bit insert: "+outputWithBit+"\n");

    }
}
