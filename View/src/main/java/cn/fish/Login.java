package cn.fish;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Login {
    private JFrame jFrame = null;
    private JPanel jContentPane = null;
    private JLabel jLabelLoginUserName = null;
    private JLabel jLabelLoginPassword = null;
    private JTextField jTextFieldLoginUserName = null;
    private JPasswordField jPasswordFieldLoginPassword = null;

    public JButton jButtonLogin = null;
    public JButton jButtonReg = null;

    private Alert alert = null;

    public Login() {
        this.getJFrame().setVisible(true);
        this.getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void closeFrame() {
        this.jFrame.dispose();
    }

    private JFrame getJFrame() {
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(new Dimension(400, 300));
            jFrame.setTitle("登录");
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
            jLabelLoginUserName = new JLabel();
            jLabelLoginUserName.setBounds(new Rectangle(18, 53, 335, 38));
            jLabelLoginUserName.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabelLoginUserName.setText("  账  号: ");
            jLabelLoginPassword = new JLabel();
            jLabelLoginPassword.setBounds(new Rectangle(18, 107, 335, 38));
            jLabelLoginPassword.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabelLoginPassword.setText("  密  码: ");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelLoginUserName, null);
            jContentPane.add(jLabelLoginPassword, null);
            jContentPane.add(getJTextFieldLoginUserName(), null);
            jContentPane.add(getJPasswordFieldLoginPassword(), null);
            jContentPane.add(getJButtonLogin(), null);
            jContentPane.add(getJButtonReg(), null);
        }
        return jContentPane;
    }

    private JButton getJButtonReg() {
        if (jButtonReg == null) {
            jButtonReg = new JButton();
            jButtonReg.setBounds(new Rectangle(295, 214, 82, 28));
            jButtonReg.setText("注册管理员");
            jButtonReg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    closeFrame();
                    new Register();
                }
            });
        }
        return jButtonReg;
    }

    private JButton getJButtonLogin() {
        if (jButtonLogin == null) {
            jButtonLogin = new JButton();
            jButtonLogin.setBounds(new Rectangle(143, 162, 120, 41));
            jButtonLogin.setText("登录");
            jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // 当点了登录按钮时，调用登录方法
                    doLogin();
                }
            });
        }
        return jButtonLogin;
    }

    private JTextField getJTextFieldLoginUserName() {
        if (jTextFieldLoginUserName == null) {
            jTextFieldLoginUserName = new JTextField();
            jTextFieldLoginUserName.setBounds(new Rectangle(84, 56, 266, 33));
            jTextFieldLoginUserName.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // 当在用户名框中按回车时，使密码框获取焦点
                        jPasswordFieldLoginPassword.requestFocusInWindow();
                    }
                }
            });
        }
        return jTextFieldLoginUserName;
    }

    private JPasswordField getJPasswordFieldLoginPassword() {
        if (jPasswordFieldLoginPassword == null) {
            jPasswordFieldLoginPassword = new JPasswordField();
            jPasswordFieldLoginPassword.setBounds(new Rectangle(84, 111, 266, 33));
            jPasswordFieldLoginPassword.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent e) {
                    // 当在密码框中按回车时，调用登陆方法
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        doLogin();
                    }
                }
            });
        }
        return jPasswordFieldLoginPassword;
    }

    private void doLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (jTextFieldLoginUserName.getText() != null &&
                        !jTextFieldLoginUserName.getText().equals("") &&
                        String.valueOf(jPasswordFieldLoginPassword.getPassword()) != null &&
                        !String.valueOf(jPasswordFieldLoginPassword.getPassword()).equals("")) {
                        UserAdmin loginUser = new UserAdmin();
                        loginUser.setUserName(jTextFieldLoginUserName.getText());
                        loginUser.setUserPWD(String.valueOf(jPasswordFieldLoginPassword.getPassword()));
                        loginUser.setUserORG("UICC");
                        loginUser.setAdmin(false);
                        UsersAct usersact = new UsersAct();
                        if (usersact.adminLogin(loginUser)) {
                            closeFrame();
                            new LibraryMainUI();
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "登陆失败");
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "用户名或密码为空");
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

}