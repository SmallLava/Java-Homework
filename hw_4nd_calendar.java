import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class hw_4nd_calendar {
    public static boolean checkLeapYear(int year) {
        if(year % 400 == 0) {
            return true;
        } else if(year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }

    public static List<List<Integer>> separateDate(int[] monthDate) {
        List<List<Integer>> chunks = new ArrayList<>();

            for (int i = 0; i < monthDate.length; i += 7) {
            int end = Math.min(monthDate.length, i + 7);
            List<Integer> chunk = new ArrayList<>();
            for (int j = i; j < end; j++) chunk.add(monthDate[j]);
            chunks.add(chunk);
        }
        return chunks;
    }
    public static void main(String[] args) {
        Set<Integer> evenMonth = new HashSet<>(Set.of(2, 4, 6, 9, 11));
        Scanner scn = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(scn.nextLine(), formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.printf("S  M  T  W  T  F  S\n");
        int month = date.getMonthValue();
        int maxDate;
        if(evenMonth.contains(month)) {
            if(month == 2) {
                maxDate = checkLeapYear(date.getYear()) ? 29 : 28;
            } else {
                maxDate = 30;
            }
        } else {
            maxDate = 31;
        }
        int count = 1;
        int shiftDate = date.getDayOfWeek().getValue() - (date.getDayOfMonth() % 7) + 1;
        int[] calendar = new int[maxDate + shiftDate];
        for (int i = shiftDate; i < maxDate + shiftDate; i++) {
            calendar[i] = count;
            count++;
        }
        List<List<Integer>> output = separateDate(calendar);
        for (int index = 0; index < output.size(); index++) {
            for (int i = 0; i < output.get(index).size(); i++) {
                if(output.get(index).get(i) != 0) {
                    System.out.printf("%-2d ", output.get(index).get(i));
                } else {
                    System.out.printf("   ");
                }
            }
            System.out.printf("\n");
        }
    }
}