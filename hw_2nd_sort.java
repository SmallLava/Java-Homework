// 111316030 陳祐宇
// 直接輸入n個整數 以空格分開
// 輸入直到非整數救回輸出結果 若同行輸入則只輸出非整數輸入前的結果
import java.util.Arrays; //Arrays用以簡化擴充陣列
import java.util.Scanner;

public class hw_2nd_sort {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] target = new int[0];
        while (scn.hasNextInt()) { //只要還有下一個輸入就擴充陣列並填入
            target = Arrays.copyOf(target, target.length + 1);
            target[target.length - 1] = scn.nextInt();
        }
        int[] sorted = new int[target.length];
        int index = 0;
        while(index < sorted.length) { //依序對輸出陣列填值(index為目前改變目標)
            int max = Integer.MIN_VALUE;
            for(int h:target) { //遍歷輸入陣列找最大值
                if(h > max)
                max = h;
            }
            int i = 0;
            while (i < target.length) { //根據找到的最大值尋找該值之引索並標記為已取出(MIN_VALUE)
                if(max == target[i]) {
                    target[i] = Integer.MIN_VALUE;
                    sorted[index] = max;
                    break;
                }
                i++;
            }
            index++;
        }
        for(int i:sorted) { //遍歷已排序陣列並輸出
            System.out.printf("%d ", i);
        }
    }
}