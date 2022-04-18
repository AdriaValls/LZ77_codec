public class LZ77Main {

    public static void main(String[] args) {
        System.out.println("LZ77 Codec");
        part1();
        //part2(); //para ver la grafica
    }

    public static void part1(){
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

    /*
    public static void part2(){
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();

        int maxWindowSize = 2048;
        int minWindowSize = 2;

        int inputLen = 10000;
        //Input window (Ment) and sliding window (Mdest) variables.
        int windowSize=minWindowSize;
        String input = randomSequence(inputLen);

        XYSeries series1 = new XYSeries("Tamaño de ventana");

        while (windowSize <= maxWindowSize){
            System.out.println("Sliding window Size =  "+windowSize+"\n");
            System.out.println("Entry window Size =  "+windowSize+"\n");

            String bitIns = inserter.bitInsertion(windowSize,input);
            String encodedInput = encoder.String_encoder(bitIns, windowSize, windowSize);

            //original/nou x:1
            float compressionFactor = (float) inputLen / (float) encodedInput.length();
            //System.out.println(encodedInput.length());
            //System.out.println(compressionFactor);

            //CompList.add(compressionFactor);
            //System.out.println(windowSize);
            //windowList.add(windowSize);
            series1.add(windowSize, compressionFactor);


            windowSize = windowSize * minWindowSize;
        }
        com.zetcode.GraphTest graphTest = new com.zetcode.GraphTest();
        //graphTest.showgraph(CompList,windowList);
        graphTest.showgraph(series1);
        graphTest.setVisible(true);
    }
*/

    public static void graph2values(){
        BitInserter inserter = new BitInserter();
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();

        int maxWindowSize = 2048;
        int minWindowSize = 2;

        int inputLen = 10000;
        //Input window (Ment) and sliding window (Mdest) variables.
        int Ment=minWindowSize;
        int Mdest=minWindowSize;
        String input = randomSequence(inputLen);



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

    public static boolean isPowerOfTwo(int n){
        if (n < 1){ //We don't want a size of 1
            return false;
        }
        return (int)(Math.ceil((Math.log(n) / Math.log(2)))) == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    public static String randomSequence(int size){
        String input = "";
        for (int i = 0; i <= size; i++ ){
            String temp = String.valueOf(Math.round(Math.random())); //randomly generate 0 or 1 to append it to the input string
            input += temp;
        }
        return input;
    }
}
