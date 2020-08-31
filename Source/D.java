package Source;

import java.util.*;
import java.text.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import org.jsoup.*;
import org.jsoup.nodes.*;


public class D extends JFrame {

    public static void main(String[] args) {
        new D();
    }

    String uri="http://lms.sunmoon.ac.kr/ilos/main/main_form.acl";

    Document d;

    JTextArea Diary = new JTextArea();

    JPanel MainPanel = new JPanel(new BorderLayout());
    JScrollPane MainEastPanel = new JScrollPane(Diary);
    JPanel NorthPanel = new JPanel(new BorderLayout());
    JPanel NorthSubPanel = new JPanel(new FlowLayout());

    JPanel daily_JPanel = new JPanel(new GridLayout(7, 7));

    JLabel C_Time = new JLabel();

    JButton Time_Sync = new JButton("¿?");

    JButton PrevYear = new JButton("<<");
    JButton PrevMonth = new JButton("<");
    JButton CurrentTime = new JButton("=");
    JButton NextMonth = new JButton(">");
    JButton NextYear = new JButton(">>");

    JButton B=new JButton();

    JLabel[] W_O_D = new JLabel[7];
    String[] W_O_DList = { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

    JButton[] MonthDayButton = new JButton[42];
    int ButtonCursor = 0;

    Calendar paper = Calendar.getInstance();

    int D_O_W;

    D() {

        try {
            d = Jsoup.connect(uri).get();
            Diary.append(d.title());
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        
        C_Time.setText(paper.getTime() + "");
        C_Time.setHorizontalAlignment(JLabel.CENTER);
        C_Time.setFont(new Font("Serif", Font.PLAIN, 28));

        Time_Sync.setPreferredSize(new Dimension(56, 56));

        NorthPanel.add(C_Time, BorderLayout.CENTER);
        NorthPanel.add(Time_Sync, BorderLayout.WEST);
        NorthPanel.add(NorthSubPanel, BorderLayout.SOUTH);

        MainEastPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 5,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2));

        CurrnetTime C_T = new CurrnetTime();
        C_T.start();
        

        NorthSubPanel.add(PrevYear);
        NorthSubPanel.add(PrevMonth);
        NorthSubPanel.add(CurrentTime);
        NorthSubPanel.add(NextMonth);
        NorthSubPanel.add(NextYear);

        MainPanel.add(NorthPanel, BorderLayout.NORTH);
        MainPanel.add(daily_JPanel, BorderLayout.CENTER);
        MainPanel.add(MainEastPanel, BorderLayout.EAST);

        // 요일 문구 블록
        for (int i = 0; i < 7; i++) {
            W_O_D[i] = new JLabel("" + W_O_DList[i]);
            W_O_D[i].setHorizontalAlignment(JLabel.CENTER);
            daily_JPanel.add(W_O_D[i]);
        }

        elicit_D_O_W(1);

        // 저번달 버튼 추가 블록
        for (ButtonCursor = 0; ButtonCursor < D_O_W - 1; ButtonCursor++) {
            MonthDayButton[ButtonCursor] = new JButton();
            MonthDayButton[ButtonCursor].setEnabled(false);
            daily_JPanel.add(MonthDayButton[ButtonCursor]);
        }

        // 이번달 버튼 추가 블록
        for (int i = D_O_W, j = 1; i < D_O_W + 31; i++, j++) {
            MonthDayButton[i] = new JButton(Integer.toString(j));
            daily_JPanel.add(MonthDayButton[i]);
            ButtonCursor++;
        }

        // 다음달 버튼 추가 블록
        for (int j = 1; j < 6; j++) {
            MonthDayButton[ButtonCursor] = new JButton(Integer.toString(j));
            daily_JPanel.add(MonthDayButton[ButtonCursor]);
            ButtonCursor++;
        }

        setContentPane(MainPanel);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        setVisible(true);
        setTitle("SMUDI_Calendar");

    }

    // 요일 구하는 함수
    int elicit_D_O_W(int TargetDate) {
        String year = Integer.toString(paper.get(Calendar.YEAR));
        String month = String.format("%02d", paper.get(Calendar.MONTH) + 1);
        String YYYYMM01 = year + month + String.format("%02d", TargetDate);
        DateFormat DF = new SimpleDateFormat("yyyyMMdd");
        Date date;
        try {
            date = DF.parse(YYYYMM01);
            paper.setTime(date);
            D_O_W = paper.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return D_O_W;
    }
   

    // 현재 시간 새로고침
    class CurrnetTime extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while (true) {
                paper = Calendar.getInstance();
                C_Time.setText(paper.getTime() + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
