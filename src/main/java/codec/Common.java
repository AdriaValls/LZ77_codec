package codec;

public class Common {

    public Common(){

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
