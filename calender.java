import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.Arrays;

public class calender {
    static String[] ArrayDayOfWeeks = { "SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT" };
    static ArrayList<String> dayOfWeeks = new ArrayList<>(Arrays.asList(ArrayDayOfWeeks));
    static LocalDate today = LocalDate.now();
    // static LocalDate today = LocalDate.now().minusMonths(2);

    public static void main(String args[]) {
        calender.defaultCalender(today);
        printMonth();
    }

    static int count = 0;

    private static void printMonth() { // count을 요일의 index로 하여 1일부터 마지막날까지 날짜을 출력해줌
        fillWithPrevMonth(today);
        fillWithNowMonth(today);
        fillWithNextMonth(today);
        System.out.println("");
    }

    private static void fillWithNowMonth(LocalDate today) {
        for (int i = 1; i <= today.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {
            System.out.print(i + "\t");
            count++;
            if (count % 7 == 0) {
                System.out.println("");
            }
        }
    }

    private static void fillWithPrevMonth(LocalDate today) {
        for (int i = today.minusMonths(1).with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY))
                .getDayOfMonth(); i <= today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth())
                        .getDayOfMonth(); i++) {
            System.out.print(i + "\t");
            count++;
        }
    }

    private static void fillWithNextMonth(LocalDate today) {
        for (int i = 1; i <= today.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY))
                .getDayOfMonth(); i++) {
            System.out.print(i + "\t");
            count++;
            if (count % 7 == 0) {
                System.out.println("");
            }
        }
    }

    private static void defaultCalender(LocalDate today) { // 달력의 년도와 달 그리고 요일을 출력함
        System.out.println(today.getMonth() + "\t" + today.getYear());
        for (String doy : dayOfWeeks) {
            System.out.print(doy + "\t");
        }
        System.out.println("");
    }
}
