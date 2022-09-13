package cn.fish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图书借出
 */

public class BookOut {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    private JLabel jLabelBookID = null;
    private JLabel jLabelBookName = null;
    private JLabel jLabelStuID = null;
    private JLabel jLabelStuName = null;
    private JTextField jTextFieldBookID = null;
    private JTextField jTextFieldBookName = null;
    private JTextField jTextFieldStuID = null;
    private JTextField jTextFieldStuName = null;
    private JButton jButtonConfirm = null;

    public BookOut() {
        this.getJFrame().setVisible(true);
    }

    private void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(398, 337));
            jFrame.setTitle("图书借出");
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
            jLabelBookName.setBounds(new Rectangle(15, 75, 357, 47));
            jLabelBookName.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelBookName.setText("书    名: ");
            jLabelStuID = new JLabel();
            jLabelStuID.setBounds(new Rectangle(15, 119, 357, 47));
            jLabelStuID.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelStuID.setText("学    号: ");
            jLabelStuName = new JLabel();
            jLabelStuName.setBounds(new Rectangle(15, 163, 357, 47));
            jLabelStuName.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelStuName.setText("学生姓名: ");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelBookID, null);
            jContentPane.add(jLabelBookName, null);
            jContentPane.add(jLabelStuID, null);
            jContentPane.add(jLabelStuName, null);
            jContentPane.add(getJTextFieldBookID(), null);
            jContentPane.add(getJTextFieldBookName(), null);
            jContentPane.add(getJTextFieldStuID(), null);
            jContentPane.add(getJTextFieldStuName(), null);
            jContentPane.add(getJButtonConfirm(), null);
        }
        return jContentPane;
    }

    private JButton getJButtonConfirm() {
        if (jButtonConfirm == null) {
            jButtonConfirm = new JButton();
            jButtonConfirm.setBounds(new Rectangle(125, 221, 131, 50));
            jButtonConfirm.setText("确定");
            jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doOutBook();
                }
            });
        }
        return jButtonConfirm;
    }

    private void doOutBook() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldBookID.getText().equals("") &&
                            !jTextFieldBookName.getText().equals("") &&
                            !jTextFieldStuID.getText().equals("") &&
                            !jTextFieldStuName.getText().equals("")) {
                        LogBook outBook = new LogBook();
                        outBook.setActDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                        outBook.setActMode("借出");
                        outBook.setBookID(jTextFieldBookID.getText());
                        outBook.setBookName(jTextFieldBookName.getText());
                        outBook.setStuID(jTextFieldStuID.getText());
                        outBook.setStuName(jTextFieldStuName.getText());
                        BookAct bookAct = new BookAct();
                        bookAct.logRecord(outBook);
                        JOptionPane.showMessageDialog(new JFrame(), "图书: " + outBook.getBookName() + " 借出成功!");
                        closeFrame();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "请填写完整信息!");
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private JTextField getJTextFieldStuName() {
        if (jTextFieldStuName == null) {
            jTextFieldStuName = new JTextField();
            jTextFieldStuName.setBounds(new Rectangle(110, 165, 258, 41));
            jTextFieldStuName.setEditable(false);
        }
        return jTextFieldStuName;
    }

    private JTextField getJTextFieldStuID() {
        if (jTextFieldStuID == null) {
            jTextFieldStuID = new JTextField();
            jTextFieldStuID.setBounds(new Rectangle(110, 121, 258, 41));
            jTextFieldStuID.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        checkStuExist();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }
        return jTextFieldStuID;
    }

    private void checkStuExist() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldStuID.equals("")) {
                        jTextFieldStuName.setText("");
                        UsersAct newUsersAct = new UsersAct();
                        if (newUsersAct.findUserNameByID(jTextFieldStuID.getText()).equals("查无此用户，请检查用户编号!")) {
                            JOptionPane.showMessageDialog(new JFrame(), "学生ID: " + jTextFieldStuID.getText() + " 不存在！");
                            jButtonConfirm.setEnabled(false);
                            jTextFieldStuName.setEditable(false);
                        } else {
                            jTextFieldStuName.setText(newUsersAct.findUserNameByID(jTextFieldStuID.getText()));
                            jButtonConfirm.setEnabled(true);
                            jTextFieldStuName.setEditable(true);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private JTextField getJTextFieldBookName() {
        if (jTextFieldBookName == null) {
            jTextFieldBookName = new JTextField();
            jTextFieldBookName.setBounds(new Rectangle(110, 77, 258, 41));
            jTextFieldBookName.setEditable(false);
        }
        return jTextFieldBookName;
    }

    private JTextField getJTextFieldBookID() {
        if (jTextFieldBookID == null) {
            jTextFieldBookID = new JTextField();
            jTextFieldBookID.setBounds(new Rectangle(110, 33, 258, 41));
            jTextFieldBookID.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        checkBookExist();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }
        return jTextFieldBookID;
    }

    private void checkBookExist() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldBookID.equals("")) {
                        jTextFieldBookName.setText("");
                        BookAct bookAct = new BookAct();
                        if (bookAct.findBookNameByID(jTextFieldBookID.getText()).equals("查无此书籍，请输入正确的图书编号!")) {
                            JOptionPane.showMessageDialog(new JFrame(), "图书编号不存在!");
                            jButtonConfirm.setEnabled(false);
                            jTextFieldBookName.setEditable(false);
                        } else {
                            jTextFieldBookName.setText(bookAct.findBookNameByID(jTextFieldBookID.getText()));
                            jButtonConfirm.setEnabled(true);
                            jTextFieldBookName.setEditable(true);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
