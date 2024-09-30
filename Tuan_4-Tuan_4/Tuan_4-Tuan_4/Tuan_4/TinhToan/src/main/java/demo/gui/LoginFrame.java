/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ADMIN
 */
public class LoginFrame extends JFrame {

    private JLabel lbUser, lbPass;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btLogin, btReset;

    public LoginFrame(String title) {
        super(title);

        createGui();
        processEvent();

        setSize(300, 200);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createGui() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 10, 10));

        p.add(lbUser = new JLabel("User name"));
        p.add(txtUser = new JTextField(10));
        p.add(lbPass = new JLabel("Password"));
        p.add(txtPass = new JPasswordField(10));
        p.add(btLogin = new JButton("Login"));
        p.add(btReset = new JButton("Reset"));

        btLogin.setIcon(new ImageIcon(this.getClass().getResource("/images/login.png")));
        btReset.setIcon(new ImageIcon(this.getClass().getResource("/images/reset.png")));
        

        lbUser.setBounds(10, 10, 100, 40);
        txtUser.setBounds(120, 10, 150, 40);

        lbPass.setBounds(10, 60, 100, 40);
        txtPass.setBounds(120, 60, 150, 40);

        btLogin.setBounds(20, 110, 100, 40);
        btReset.setBounds(150, 110, 100, 40);

        add(p);
    }

    public static void main(String[] args) {
        LoginFrame frm = new LoginFrame("Login System");
        frm.setVisible(true);
    }

    private void processEvent() {
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Code xu ly
                String user = txtUser.getText();
                String pass = txtPass.getText();
                if (user.equals("admin") || pass.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
                }

            }
        });
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code xu ly
                txtUser.setText("");
                txtPass.setText("");
                txtUser.requestFocus();
            }
        });
    }
}
