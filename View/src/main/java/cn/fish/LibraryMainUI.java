package cn.fish;

import javax.swing.*;
import java.awt.*;

public class LibraryMainUI {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    // 第一排
    private JButton jButtonBookOut = null;
    private JButton jButtonBookIn = null;
    private JButton jButtonNewBook = null;
    private JButton jButtonNewStu = null;
    private JButton jButtonNotInBook = null;

    public LibraryMainUI() {
        this.getJFrame().setVisible(true);
    }

    public void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(400, 300));
            jFrame.setTitle("图书馆应用");
            jFrame.setResizable(false);
            jFrame.setContentPane(getJContentPane());
            jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
        }
        return jFrame;
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            // first row
            jContentPane.add(getJButtonBookOut(), null);
            jContentPane.add(getJButtonBookIn(), null);
            jContentPane.add(getJButtonNewBook(), null);
            // second row
            jContentPane.add(getJButtonNewStu(), null);
            jContentPane.add(getJButtonNotInBook(), null);
        }
        return jContentPane;
    }

    private JButton getJButtonNotInBook() {
        if (jButtonNotInBook == null) {
            jButtonNotInBook = new JButton();
            jButtonNotInBook.setBounds(new Rectangle(150, 125, 100, 50));
            jButtonNotInBook.setText("未还图书");
            jButtonNotInBook.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new BookNotBack();
                }
            });
        }
        return jButtonNotInBook;
    }

    private JButton getJButtonNewStu() {
        if (jButtonNewStu == null) {
            jButtonNewStu = new JButton();
            jButtonNewStu.setBounds(new Rectangle(25, 125, 100, 50));
            jButtonNewStu.setText("学生管理");
            jButtonNewStu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new StuRegister();
                }
            });
        }
        return jButtonNewStu;
    }

    private JButton getJButtonNewBook() {
        if (jButtonNewBook == null) {
            jButtonNewBook = new JButton();
            jButtonNewBook.setBounds(new Rectangle(275, 25, 100, 50));
            jButtonNewBook.setText("新书录入");
            jButtonNewBook.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new NewBookIn();
                }
            });
        }
        return jButtonNewBook;
    }

    private JButton getJButtonBookIn() {
        if (jButtonBookIn == null) {
            jButtonBookIn = new JButton();
            jButtonBookIn.setBounds(new Rectangle(150, 25, 100, 50));
            jButtonBookIn.setText("归还");
            jButtonBookIn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new BookIn();
                }
            });
        }
        return jButtonBookIn;
    }

    private JButton getJButtonBookOut() {
        if (jButtonBookOut == null) {
            jButtonBookOut = new JButton();
            jButtonBookOut.setBounds(new Rectangle(25, 25, 100, 50));
            jButtonBookOut.setText("借出");
            jButtonBookOut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new BookOut();
                }
            });
        }
        return jButtonBookOut;
    }
}
