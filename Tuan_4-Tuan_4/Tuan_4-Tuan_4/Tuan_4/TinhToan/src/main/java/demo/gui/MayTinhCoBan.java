package demo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MayTinhCoBan extends JFrame {

    private JTextField txtDisplay;
    private JButton btReset;
    private JButton[] bt = new JButton[16];
    private String[] str = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
    private double sodautien = 0;
    private String operator = "";
    private boolean sobatdau = true;

    public MayTinhCoBan() {
        setTitle("Máy tính");
        taoGiaoDien(); // Gọi hàm tạo giao diện
        pack(); // Tự động điều chỉnh kích thước
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi nhấn nút x
    }

    public static void main(String[] args) {
        MayTinhCoBan frm = new MayTinhCoBan();
        frm.setVisible(true); // Hiển thị giao diện
    }

    private void taoGiaoDien() {
        JPanel p1 = new JPanel();
        txtDisplay = new JTextField(); // Khởi tạo JTextField
        p1.add(txtDisplay); 
        txtDisplay.setPreferredSize(new Dimension(150, 30)); // Kích thước cho JTextField
        btReset = new JButton("C"); // Khởi tạo nút Reset
        p1.add(btReset);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 4, 5, 5)); // Thiết lập lưới 4x4 cho các nút

        // Tạo các nút và thêm vào bảng
        for (int i = 0; i < bt.length; i++) {
            bt[i] = new JButton(str[i]);
            p2.add(bt[i]);
        }

        // Thêm sự kiện cho các nút số và dấu chấm
        for (int i = 0; i < bt.length; i++) {
            if ("0123456789.".contains(str[i])) {
                bt[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (sobatdau) {
                            txtDisplay.setText(e.getActionCommand());
                            sobatdau = false;
                        } else {
                            txtDisplay.setText(txtDisplay.getText() + e.getActionCommand());
                        }
                    }
                });
            }
        }

        // Thêm sự kiện cho các nút phép toán
        bt[3].addActionListener(e -> setOperator("+"));  // nút '+'
        bt[7].addActionListener(e -> setOperator("-"));  // nút '-'
        bt[11].addActionListener(e -> setOperator("*")); // nút '*'
        bt[15].addActionListener(e -> setOperator("/")); // nút '/'

        // Thêm sự kiện cho nút '='
        bt[14].addActionListener(new ActionListener() { // nút '='
            @Override
            public void actionPerformed(ActionEvent e) {
                double sothuhai = Double.parseDouble(txtDisplay.getText());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = sodautien + sothuhai;
                        break;
                    case "-":
                        result = sodautien - sothuhai;
                        break;
                    case "*":
                        result = sodautien * sothuhai;
                        break;
                    case "/":
                        if (sothuhai != 0) {
                            result = sodautien/ sothuhai;
                        } else {
                            txtDisplay.setText("Error: Div by 0");
                            return;
                        }
                        break;
                    default:
                        return;
                }

                txtDisplay.setText(String.valueOf(result));
                sobatdau = true;
            }
        });

        // Xử lý sự kiện cho nút reset
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDisplay.setText("");
                sodautien = 0;
                operator = "";
                sobatdau = true;
            }
        });

        setLayout(new BorderLayout());
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
    }

    // Phương thức để lưu toán hạng đầu tiên và phép toán
    private void setOperator(String op) {
        sodautien = Double.parseDouble(txtDisplay.getText());
        operator = op;
        sobatdau = true; // Để bắt đầu nhập số mới cho toán hạng thứ hai
    }
}
