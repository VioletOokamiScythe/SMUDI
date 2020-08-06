package Source;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

import org.jsoup.*;

public class SMUDI extends JFrame {

    ImageIcon originON = new ImageIcon("./Resources/ON.png");
    ImageIcon originOFF = new ImageIcon("./Resources/OFF.png");

    Image originONImage = originON.getImage();
    Image originOFFImage = originOFF.getImage();

    Image changedONImage = originONImage.getScaledInstance(112, 168, Image.SCALE_SMOOTH);
    Image changedOFFImage = originOFFImage.getScaledInstance(112, 168, Image.SCALE_SMOOTH);

    ImageIcon ON = new ImageIcon(changedONImage);
    ImageIcon OFF = new ImageIcon(changedOFFImage);

    JButton MainB = new JButton(OFF);

    JButton LINKLMS = new JButton("LMS(E강의동)");
    JButton LINKLILY = new JButton("선문포탈");
    JButton LINKSWS = new JButton("학사포탈");
    JButton LINKFOLIO = new JButton("선문 포트폴리오");

    JPanel MainP = new JPanel(null);
    JPanel LINKEDSITE = new JPanel(new GridLayout(2,2));

    int stat = 0;

    SMUDI() {

        setContentPane(MainP);

        MainP.add(MainB);

        MainB.setBounds(0, 0, 112, 168);
        MainB.setBorderPainted(false);
        MainB.setContentAreaFilled(false);
        MainB.setFocusPainted(false);
        MainB.setOpaque(false);

        LINKEDSITE.add(LINKLMS);
        LINKEDSITE.add(LINKLILY);
        LINKEDSITE.add(LINKSWS);
        LINKEDSITE.add(LINKFOLIO);

        setUndecorated(true);
        setSize(new Dimension(112, 168));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));


        class Listener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == MainB) {
                    switch (stat) {
                        case 0:
                            stat = 1;
                            setSize(new Dimension(1120, 560));
                            MainB.setIcon(ON);
                            MainB.setBounds((getSize().width - 112) / 2, (getSize().height - 168) / 2, 112, 168);
                            for (int i = 0; i < 128; i++) {
                                setBackground(new Color(0, 0, 0, i));
                            }
                            LINKEDSITE.setBounds(0, 0, 1120 / 2, 560 / 3);
                            MainP.add(LINKEDSITE);
                            break;

                        default:
                            stat = 0;
                            setBackground(new Color(0, 0, 0, 0));
                            MainB.setIcon(OFF);
                            MainB.setBounds(0, 0, 112, 168);
                            MainP.remove(LINKEDSITE);
                            setSize(new Dimension(112, 168));

                            break;
                    }
                }

                if (e.getSource() == LINKLMS) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://lms.sunmoon.ac.kr/ilos/main/main_form.acl"));
                    } catch (Exception e0) {
                        //TODO: handle exception
                    }
                }
                if (e.getSource() == LINKLILY) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://lily.sunmoon.ac.kr/MainDefault.aspx"));
                    } catch (Exception e0) {
                        //TODO: handle exception
                    }
                }
                if (e.getSource() == LINKSWS) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://sws.sunmoon.ac.kr/login.aspx"));
                    } catch (Exception e0) {
                        //TODO: handle exception
                    }
                }
                if (e.getSource() == LINKFOLIO) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://folio.sunmoon.ac.kr/hmpg/main/Main.do"));
                    } catch (Exception e0) {
                        //TODO: handle exception
                    }
                }
            }

        }

        Listener L = new Listener();

        MainB.addActionListener(L);
        LINKLMS.addActionListener(L);
        LINKLILY.addActionListener(L);
        LINKSWS.addActionListener(L);
        LINKFOLIO.addActionListener(L);
    }

    public static void main(String[] args) {
        new SMUDI();
    }
}
