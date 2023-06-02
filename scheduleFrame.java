import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.*;

public class scheduleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 1000;

    private JPanel northPanel;
    private JPanel centerPanel;

    LocalDate current = LocalDate.now(); // 지금 페이지내에서 보여주는 달력의 기준을 현재의 달력으로 해준다.

    public scheduleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 3));
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 7));

        JLabel now = new JLabel(getResult(current), SwingConstants.CENTER);

        JButton prev = new JButton("prev");
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current = current.minusMonths(1); // 지금 페이지내에서 보여주는 달력의 기준을 기존의 current에서 한달을 뺀 값으로 바꿔준다
                now.setText(getResult(current));
                // centerPanel에 있는 모든 요소를 지우고 다시 추가하는 방법으로 업데이트 하였다.
                centerPanel.removeAll();
                centerPanel.validate();
                centerPanel.repaint();
                printMonth(centerPanel, current);
            }
        });

        JButton next = new JButton("next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current = current.plusMonths(1); // 지금 페이지내에서 보여주는 달력의 기준을 기존의 current에서 한달을 더한 값으로 바꿔준다
                now.setText(getResult(current));
                centerPanel.removeAll();
                centerPanel.validate();
                centerPanel.repaint();
                printMonth(centerPanel, current);
            }
        });

        northPanel.add(prev);
        northPanel.add(now);
        northPanel.add(next);
        add(northPanel, BorderLayout.NORTH);

        // 여기서 부터는 아래쪽 달력

        printMonth(centerPanel, current); // 달력을 작성하는 함수에다 그릴 panel과 LocalDate타입의 current을 전달한다.
        add(centerPanel, BorderLayout.CENTER);
    }

    public void printMonth(JPanel p, LocalDate t) {
        fillWithPrevMonth(p, t);
        fillWithNowMonth(p, t);
        fillWithNextMonth(p, t);
    }

    public void fillWithPrevMonth(JPanel p, LocalDate t) {
        for (int i = t.minusMonths(1).with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)).getDayOfMonth(); i <= t
                .minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {
            makeGrayPanel(Integer.toString(i)); // 이전 달은 배경이 회색이 나오게 해주는 방법을 쓴다.
        }
    }

    public void fillWithNowMonth(JPanel p, LocalDate t) {
        for (int i = 1; i <= t.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {
            makeDefaultPanel(Integer.toString(i));
        }
    }

    public void fillWithNextMonth(JPanel p, LocalDate t) {
        for (int i = 1; i <= t.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY))
                .getDayOfMonth(); i++) {
            if (centerPanel.getComponentCount() < 42) { // centerPanel에 현재 들어간 component갯수를 비교해 가면서 6*7개만 들어가도록 해준다.
                makeGrayPanel(Integer.toString(i));
            }
        }
    }

    public String getResult(LocalDate calander) {
        int nowYear = calander.getYear();
        int nowMonth = calander.getMonthValue();
        return nowYear + "년 " + nowMonth + "월";
    }

    public void makeDefaultPanel(String s) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        panel.add(label);
        centerPanel.add(panel);
    }

    public void makeGrayPanel(String s) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        panel.add(label);
        panel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(panel);
    }
}
