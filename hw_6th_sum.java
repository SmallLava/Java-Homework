import java.util.Scanner;

public class hw_6th_sum {
    public static void main(String[] args) throws Exception {
        try(Scanner scn = new Scanner(System.in)) {
            Boolean isEnd = false;
            float sum = 0;
            while(!isEnd) {
                String input = scn.nextLine();
                try {
                    if(input.equals("q")) throw new Exception("Break with q");
                    float number = Float.parseFloat(input);
                    sum += number;
                } catch(NumberFormatException e) {
                    System.out.println("Input number to sum or type \"q\" to quit");
                } catch(Exception e) {
                    isEnd = true;
                }
            }
            System.out.println(sum);
        }
    }
}