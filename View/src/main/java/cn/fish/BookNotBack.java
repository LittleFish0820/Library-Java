package cn.fish;

import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookNotBack {
    private JFrame jFrame = null;
    private JScrollPane jContentPane = null;
    private JTextArea textArea = null;

    public BookNotBack() {
        this.getJFrame().setVisible(true);
        getNotBackBookInfo();
    }

    private void getNotBackBookInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BookAct act = new BookAct();
                ArrayList<Document> list = act.getNotBackBook();
                textArea.append("借书日期       图书编号        书名      学生学号        姓名\n");
                for (int i = 0; i < list.size(); i++) {
                    String one = list.get(i).getString("actDate") + "   " +
                                 list.get(i).getString("bookID") + "   " +
                                 list.get(i).getString("bookName") + "   " +
                                 list.get(i).getString("stuID") + "   " +
                                 list.get(i).getString("stuName") + "\n";
                    textArea.append(one);
                }
            }
        }).start();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(400, 300));
            jFrame.setTitle("未归还图书");
            jFrame.setResizable(false);
            jFrame.setContentPane(getJContentPane());
            jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    //System.exit(0);
                }
            });
        }
        return jFrame;
    }

    private JScrollPane getJContentPane() {
        if (jContentPane == null) {
            textArea = new JTextArea(400, 300);
            jContentPane = new JScrollPane(textArea);
        }
        return jContentPane;
    }
}