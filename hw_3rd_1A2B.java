import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class hw_3rd_1A2B {
    public static ArrayList<Integer> createRandomNumber(int count) { //用預設數字只排序確保不重複
        if(count > 10) throw new ArithmeticException();
        ArrayList<Integer> numberList = new ArrayList<>(
            Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        );
        Collections.shuffle(numberList, new Random());
        ArrayList<Integer> output = new ArrayList<>();
        for(int i : numberList.subList(0, count)) { //用遍歷固定數量的子序列方式轉換至ArrayList
            output.add(i);
        }
        return output;
    }

    public static ArrayList<Integer> digitsSeperaIntegers(int number) {
        ArrayList<Integer> digits = new ArrayList<>();
        if(number == 0) {
            digits.add(0);
            return digits;
        }
        while(number > 0) { //將輸入int除以10以拆分位數
            digits.add(number % 10);
            number /= 10;
        }
        Collections.reverse(digits);
        return digits;
    }

    public static String checkAnswer(ArrayList<Integer> answer, int input) {
        ArrayList<Integer> guess = digitsSeperaIntegers(input);
        int A = 0, B = 0;
        for(int guessIndex = 0; guessIndex < guess.size(); guessIndex++) { //用此迴圈遍歷猜測值序列
            for (int answerIndex = 0; answerIndex < answer.size(); answerIndex++) { //用此迴圈遍歷答案序列
                if(Objects.equals(answer.get(answerIndex), guess.get(guessIndex)) && answerIndex == guessIndex) {
                    A++;
                } else if (Objects.equals(answer.get(answerIndex), guess.get(guessIndex)) && answerIndex != guessIndex) {
                    B++;
                }
            }
        }
        return A+"A"+B+"B";
    }

    public static boolean checkRepeatNumber(int input) {
        ArrayList<Integer> target = digitsSeperaIntegers(input);
        HashSet<Integer> uniqueInt = new HashSet<>();
        for(int i : target) { //遍歷猜測值序列檢查重複
            if(!uniqueInt.add(i)) {
                return true;
            }
        }
        return false;
    }

    public static int digitsOfInteger(int input) {
        int digits = 1;
        if(input == 0) return 0;
        while((input / 10) >= 1) { //輸入int除以10得到位數
            digits++;
            input /= 10;
        }
        return digits;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        boolean isCorrect = false;
        try {
            System.out.println("Input number count to play: ");
            ArrayList<Integer> answer = createRandomNumber(scn.nextInt());
            int count = 1;
            while(!isCorrect && count <= 10) { //在答對前重複提問和檢查過程
                System.out.printf("Input number to guess (total %d digits):", answer.size());
                int guess = scn.nextInt();
                if(checkRepeatNumber(guess)) {
                    System.out.println("Input number can't repeat");
                } else {
                    if(digitsOfInteger(guess) == answer.size()) {
                        String output = checkAnswer(answer, guess);
                        if(output.equals(answer.size()+"A0B")) {
                            isCorrect = true;
                            System.out.println("Congratulations!" + " guessed " + count + " times");
                        } else {
                            System.out.println(output);
                        }
                    } else {
                        System.out.println("The number of digits entered does not meet the requirements");
                    }
                    count++;
                }
                
            }
            if(count > 10) {
                System.out.println("Answer is: ");
                for(int i : answer) { //失敗時遍歷答案序列以輸出
                    System.out.printf("%d", i);
                }
            }
        } catch(ArithmeticException e) {
            System.err.println("Can't create answer digits more than 10");
        } finally {
            scn.close();
        }
    }
}