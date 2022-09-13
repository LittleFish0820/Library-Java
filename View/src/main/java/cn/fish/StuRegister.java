package cn.fish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StuRegister {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    private JLabel jLabelRegStuID = null;
    private JLabel jLabelRegStuName = null;
    private JLabel jLabelRegStuClass = null;
    private JTextField jTextFieldRegStuID = null;
    private JTextField jTextFieldRegStuName = null;
    private JTextField jTextFieldRegStuClass = null;
    private JButton jButtonAdd = null;
    private JButton jButtonRemove = null;

    public StuRegister() {
        this.getJFrame().setVisible(true);
    }

    public void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(398, 337));
            jFrame.setTitle("添加用户");
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
            jLabelRegStuID = new JLabel();
            jLabelRegStuID.setBounds(new Rectangle(15, 31, 357, 47));
            jLabelRegStuID.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegStuID.setText("学生编号: ");
            jLabelRegStuName = new JLabel();
            jLabelRegStuName.setBounds(new Rectangle(15, 94, 357, 47));
            jLabelRegStuName.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegStuName.setText("姓    名: ");
            jLabelRegStuClass = new JLabel();
            jLabelRegStuClass.setBounds(new Rectangle(15, 162, 357, 47));
            jLabelRegStuClass.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegStuClass.setText("学生班级: ");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelRegStuID,null);
            jContentPane.add(jLabelRegStuName,null);
            jContentPane.add(jLabelRegStuClass,null);
            jContentPane.add(getJTextFieldRegStuID(),null);
            jContentPane.add(getJTextFieldRegStuName(),null);
            jContentPane.add(getJTextFieldRegStuClass(),null);
            jContentPane.add(getJButtonAdd(),null);
            jContentPane.add(getJButtonRemove(),null);
        }
        return jContentPane;
    }

    private JButton getJButtonRemove() {
        if (jButtonRemove == null) {
            jButtonRemove = new JButton();
            jButtonRemove.setBounds(new Rectangle(225, 221, 131, 50));
            jButtonRemove.setText("删除");
            jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doRemoveStu();
                }
            });
        }
        return jButtonRemove;
    }

    private void doRemoveStu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserStudent oldStudent = new UserStudent();
                oldStudent.setStuID(jTextFieldRegStuID.getText());
                oldStudent.setStuName(jTextFieldRegStuName.getText());
                oldStudent.setStuClass(jTextFieldRegStuClass.getText());
                UsersAct usersAct = new UsersAct();
                usersAct.stuDelete(oldStudent);
                JOptionPane.showMessageDialog(new JFrame(), "删除学生: " + oldStudent.getStuName() + " 成功!");
                closeFrame();
            }
        }).start();
    }

    private JButton getJButtonAdd() {
        if (jButtonAdd == null) {
            jButtonAdd = new JButton();
            jButtonAdd.setBounds(new Rectangle(25, 221, 131, 50));
            jButtonAdd.setText("添加");
            jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doAddStu();
                }
            });
        }
        return jButtonAdd;
    }

    private void doAddStu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldRegStuID.getText().equals("") &&
                        !jTextFieldRegStuName.getText().equals("") &&
                        !jTextFieldRegStuClass.getText().equals("")) {
                        UserStudent newStudent = new UserStudent();
                        newStudent.setStuID(jTextFieldRegStuID.getText());
                        newStudent.setStuName(jTextFieldRegStuName.getText());
                        newStudent.setStuClass(jTextFieldRegStuClass.getText());
                        UsersAct usersAct = new UsersAct();
                        usersAct.stuAdd(newStudent);
                        JOptionPane.showMessageDialog(new JFrame(), "添加学生: " + newStudent.getStuName() + " 成功!");
                        closeFrame();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "请填写完整信息!");
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private JTextField getJTextFieldRegStuClass() {
        if (jTextFieldRegStuClass == null) {
            jTextFieldRegStuClass = new JTextField();
            jTextFieldRegStuClass.setBounds(new Rectangle(110, 165, 258, 41));
            jTextFieldRegStuClass.setEditable(false);
        }
        return jTextFieldRegStuClass;
    }

    private JTextField getJTextFieldRegStuName() {
        if (jTextFieldRegStuName == null) {
            jTextFieldRegStuName = new JTextField();
            jTextFieldRegStuName.setBounds(new Rectangle(110, 97, 258, 41));
            jTextFieldRegStuName.setEditable(false);
        }
        return jTextFieldRegStuName;
    }

    private JTextField getJTextFieldRegStuID() {
        if (jTextFieldRegStuID == null) {
            jTextFieldRegStuID = new JTextField();
            jTextFieldRegStuID.setBounds(new Rectangle(110, 34, 258, 41));
            jTextFieldRegStuID.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {      }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        checkStuExist();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {    }
            });
        }
        return jTextFieldRegStuID;
    }

    private void checkStuExist() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!jTextFieldRegStuID.equals("")) {
                        jTextFieldRegStuName.setText("");
                        jTextFieldRegStuClass.setText("");
                        UsersAct newUsersAct = new UsersAct();
                        if (newUsersAct.findUserNameByID(jTextFieldRegStuID.getText()).equals("查无此用户，请检查用户编号!")) {
                            jButtonRemove.setEnabled(false);
                            jButtonAdd.setEnabled(true);
                            jTextFieldRegStuName.setEditable(true);
                            jTextFieldRegStuClass.setEditable(true);
                        } else {
                            jTextFieldRegStuName.setText(newUsersAct.findUserNameByID(jTextFieldRegStuID.getText()));
                            jButtonAdd.setEnabled(false);
                            jButtonRemove.setEnabled(true);
                            jTextFieldRegStuName.setEditable(false);
                            jTextFieldRegStuClass.setEditable(false);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
