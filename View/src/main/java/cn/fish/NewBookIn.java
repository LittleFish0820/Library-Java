package cn.fish;

import javax.swing.*;
import java.awt.*;

public class NewBookIn {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    private JLabel jLabelBookID = null;
    private JLabel jLabelBookName = null;
    private JLabel jLabelBookSummary = null;
    private JTextField jTextFieldBookID = null;
    private JTextField jTextFieldBookName = null;
    private JTextField jTextFieldBookSummary = null;
    private JButton jButtonAdd = null;
    private JButton jButtonRemove = null;

    public NewBookIn() {
        this.getJFrame().setVisible(true);
    }

    private void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(398, 337));
            jFrame.setTitle("新书录入|图书淘汰");
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

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabelBookID = new JLabel();
            jLabelBookID.setBounds(new Rectangle(15, 31, 357, 47));
            jLabelBookID.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelBookID.setText("图书编号: ");
            jLabelBookName = new JLabel();
            jLabelBookName.setBounds(new Rectangle(15, 94, 357, 47));
            jLabelBookName.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelBookName.setText("书   名: ");
            jLabelBookSummary = new JLabel();
            jLabelBookSummary.setBounds(new Rectangle(15, 162, 357, 47));
            jLabelBookSummary.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelBookSummary.setText("图书简介: ");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelBookID,null);
            jContentPane.add(jLabelBookName,null);
            jContentPane.add(jLabelBookSummary,null);
            jContentPane.add(getJTextFieldBookID(),null);
            jContentPane.add(getJTextFieldBookName(),null);
            jContentPane.add(getJTextFieldBookSummary(),null);
            jContentPane.add(getJButtonAdd(),null);
            jContentPane.add(getJButtonRemove(),null);
        }
        return jContentPane;
    }

    private JButton getJButtonAdd() {
        if (jButtonAdd == null) {
            jButtonAdd = new JButton();
            jButtonAdd.setBounds(new Rectangle(25, 221, 131, 50));
            jButtonAdd.setText("添加");
            jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doAddBook();
                }
            });
        }
        return jButtonAdd;
    }

    private JButton getJButtonRemove() {
        if (jButtonRemove == null) {
            jButtonRemove = new JButton();
            jButtonRemove.setBounds(new Rectangle(225, 221, 131, 50));
            jButtonRemove.setText("删除");
            jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doRemoveBook();
                }
            });
        }
        return jButtonRemove;
    }

    private void doRemoveBook() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Book oldBook = new Book();
                oldBook.setBookID(jTextFieldBookID.getText());
                oldBook.setBookName(jTextFieldBookName.getText());
                oldBook.setBookSummary(jTextFieldBookSummary.getText());
                BookAct bookAct = new BookAct();
                bookAct.bookDelete(oldBook);
                JOptionPane.showMessageDialog(new JFrame(), "删除图书: " + oldBook.getBookName() + " 成功!");
                closeFrame();
            }
        }).start();
    }

    private void doAddBook() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldBookID.getText().equals("") &&
                            !jTextFieldBookName.getText().equals("") &&
                            !jTextFieldBookSummary.getText().equals("")) {
                        Book newBook = new Book();
                        newBook.setBookID(jTextFieldBookID.getText());
                        newBook.setBookName(jTextFieldBookName.getText());
                        newBook.setBookSummary(jTextFieldBookSummary.getText());
                        BookAct bookAct = new BookAct();
                        bookAct.bookAdd(newBook);
                        JOptionPane.showMessageDialog(new JFrame(), "添加图书: " + newBook.getBookName() + " 成功!");
                        closeFrame();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "请填写完整信息!");
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private JTextField getJTextFieldBookSummary() {
        if (jTextFieldBookSummary == null) {
            jTextFieldBookSummary = new JTextField();
            jTextFieldBookSummary.setBounds(new Rectangle(110, 165, 258, 41));
        }
        return jTextFieldBookSummary;
    }

    private JTextField getJTextFieldBookName() {
        if (jTextFieldBookName == null) {
            jTextFieldBookName = new JTextField();
            jTextFieldBookName.setBounds(new Rectangle(110, 97, 258, 41));
        }
        return jTextFieldBookName;
    }

    private JTextField getJTextFieldBookID() {
        if (jTextFieldBookID == null) {
            jTextFieldBookID = new JTextField();
            jTextFieldBookID.setBounds(new Rectangle(110, 34, 258, 41));
        }
        return jTextFieldBookID;
    }
}
