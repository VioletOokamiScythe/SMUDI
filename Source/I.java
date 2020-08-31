package Source;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import org.jsoup.*;
import org.jsoup.nodes.*;

public class I extends JFrame {
    String uri = "https://lily.sunmoon.ac.kr/Page/Story/Notice.aspx";

    Document d;

    JPanel P = new JPanel();
    JLabel L = new JLabel();

    JPanel M=new JPanel();



    I() {
        try {
            d = Jsoup.connect(uri).get();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
    }
}