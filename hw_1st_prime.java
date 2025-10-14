import java.util.Scanner;

public class hw_1st_prime {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        int target = src.nextInt();
        int i = 2;
        boolean isPrime = true;
        while(i < (target / 2 + 1)) { //迴圈判斷i是否為因數 累進到target/2
            if(target % i == 0) {
                if(isPrime) {
                    System.out.printf("否 %d = ", target);
                    System.out.printf("%d", i);
                    isPrime = false;
                } else {
                    System.out.printf(" * %d", i);
                }
                target = target / i;
                i = 1;
            }
            i++;
        }
        if(isPrime) {
            System.out.printf("是 %d = ", target);
            System.out.printf("1 * %d", target);
        } else {
            System.out.printf(" * %d", target);
        }
        src.close();
    }
}