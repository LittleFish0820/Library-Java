package cn.fish;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;

public class Register {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    private JLabel jLabelRegUserName = null;
    private JLabel jLabelRegPassword = null;
    private JLabel jLabelRegRePassword = null;
    private JTextField jTextFieldRegUserName = null;
    private JPasswordField jTextFieldRegPassword = null;
    private JPasswordField jTextFieldRegRePassword = null;
    public JButton jButtonReg = null;
    public JButton jButtonLogin = null;
    private Alert alert;

    public void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(398, 337));
            jFrame.setTitle("注册管理员");
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
            jLabelRegRePassword = new JLabel();
            jLabelRegRePassword.setBounds(new Rectangle(15, 162, 357, 47));
            jLabelRegRePassword.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegRePassword.setText("确认密码: ");
            jLabelRegPassword = new JLabel();
            jLabelRegPassword.setBounds(new Rectangle(15, 94, 357, 47));
            jLabelRegPassword.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegPassword.setText("  密  码: ");
            jLabelRegUserName = new JLabel();
            jLabelRegUserName.setBounds(new Rectangle(15, 31, 357, 47));
            jLabelRegUserName.setFont(new Font("Dialog", Font.BOLD, 18));
            jLabelRegUserName.setText("  用户名: ");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelRegUserName, null);
            jContentPane.add(jLabelRegPassword, null);
            jContentPane.add(jLabelRegRePassword, null);
            jContentPane.add(getJTextFieldRegUserName(), null);
            jContentPane.add(getJTextFieldRegPassword(), null);
            jContentPane.add(getJTextFieldRegRePassword(), null);
            jContentPane.add(getJButtonReg(), null);
            jContentPane.add(getJButtonLogin(), null);
        }
        return jContentPane;
    }

    private JButton getJButtonLogin() {
        if (jButtonLogin == null) {
            jButtonLogin = new JButton();
            jButtonLogin.setBounds(new Rectangle(289, 266, 88, 28));
            jButtonLogin.setText("去登录");
            jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    closeFrame();
                    new Login();
                }
            });
        }
        return jButtonLogin;
    }

    private JButton getJButtonReg() {
        if (jButtonReg == null) {
            jButtonReg = new JButton();
            jButtonReg.setBounds(new Rectangle(146, 221, 131, 49));
            jButtonReg.setText("注册");
            jButtonReg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    doReg();
                }
            });
        }
        return jButtonReg;
    }

    // 注册方法
    private void doReg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   if (jTextFieldRegUserName.getText() != null &&
                      !jTextFieldRegUserName.getText().equals("") &&
                       String.valueOf(jTextFieldRegPassword.getPassword()) != null &&
                      !String.valueOf(jTextFieldRegPassword.getPassword()).equals("") &&
                       String.valueOf(jTextFieldRegRePassword.getPassword()) != null &&
                      !String.valueOf(jTextFieldRegRePassword.getPassword()).equals(""))  {
                       if (!String.valueOf(jTextFieldRegPassword.getPassword()).equals(String.valueOf(jTextFieldRegRePassword.getPassword()))) {
                           JOptionPane.showMessageDialog(new JFrame(), "两次密码输入不一致！");
                       } else {
                           UserAdmin newUser = new UserAdmin();
                           newUser.setUserName(jTextFieldRegUserName.getText());
                           newUser.setUserPWD(String.valueOf(jTextFieldRegPassword.getPassword()));
                           newUser.setUserORG("UICC");
                           newUser.setAdmin(false);
                           UsersAct usersAct = new UsersAct();
                           usersAct.adminRegist(newUser);
                           JOptionPane.showMessageDialog(new JFrame(), "注册成功！");
                       }
                   } else {
                       JOptionPane.showMessageDialog(new JFrame(), "用户名或密码为空！");
                   }
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private JPasswordField getJTextFieldRegRePassword() {
        if (jTextFieldRegRePassword == null) {
            jTextFieldRegRePassword = new JPasswordField();
            jTextFieldRegRePassword.setBounds(new Rectangle(110, 165, 258, 41));
        }
        return jTextFieldRegRePassword;
    }

    private JPasswordField getJTextFieldRegPassword() {
        if (jTextFieldRegPassword == null) {
            jTextFieldRegPassword = new JPasswordField();
            jTextFieldRegPassword.setBounds(new Rectangle(110, 97, 258, 41));
        }
        return jTextFieldRegPassword;
    }

    private JTextField getJTextFieldRegUserName() {
        if (jTextFieldRegUserName == null) {
            jTextFieldRegUserName = new JTextField();
            jTextFieldRegUserName.setBounds(new Rectangle(110, 34, 258, 41));
        }
        return jTextFieldRegUserName;
    }

    public Register() {
        this.getJFrame().setVisible(true);
    }

}
