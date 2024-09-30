package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.QLKhachHang;


/**
 *
 * Họ tên sinh viên: THÀNH XUÂN PHÚC
 */
public class FrmQLKhachHang extends JFrame {

    private JTable tblKhachHang;  
    private JButton btDocFile, btGhiFile;

    private DefaultTableModel model;
    private JTextField txtMax, txtMin, txtTB;
  
    private JCheckBox chkSapXep;

    private static final String FILE_NHAP = "input.txt";
    private static final String FILE_XUAT = "output.txt";

    private QLKhachHang qlkh = new QLKhachHang();

    public FrmQLKhachHang(String title) {
        super(title);
        createGUI();
        pack();
        //setSize(900, 500);
        processEvent();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên chủ hộ","Số nhân khẩu", "Chỉ số cũ (m3)", "Chỉ số mới(m3)","Tiêu thụ(m3)","Vượt định mức", "Tiền trả(đồng)"};
        model = new DefaultTableModel(null, columnNames);
        tblKhachHang = new JTable(model);
        //canh lề cho cột trong JTable
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        
        tblKhachHang.getColumnModel().getColumn(6).setCellRenderer(centerRender);
        tblKhachHang.getColumnModel().getColumn(7).setCellRenderer(rightRender);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblKhachHang);      
        //tạo các điều khiển nhập liệu  và các nút lệnh
        JPanel p1 = new JPanel();

        p1.add(btDocFile = new JButton("Nhập dữ liệu khách hàng"));
        p1.add(btGhiFile = new JButton("Xuất hóa đơn thanh toán"));

        JPanel p2 = new JPanel();
        p2.add(new JLabel("Mức tiêu thụ thấp nhất:"));
        p2.add(txtMin = new JTextField(10));
        
        p2.add(new JLabel("Mức tiêu thụ cao nhất:"));
        p2.add(txtMax = new JTextField(10));
        
        p2.add(new JLabel("Mức tiêu thụ trung bình:"));
        p2.add(txtTB = new JTextField(10));
        
        p2.add(chkSapXep = new JCheckBox("Sắp xếp"));

        //add các thành phần vào cửa sổ
        add(p1, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

    } 

    private void processEvent() {
        btDocFile.addActionListener((e) -> {
            qlkh.DocKhachHang(FILE_NHAP);
            loadDataToJTable();
            txtMin.setText(String.valueOf(qlkh.getTieuThuThapNhat()));
                txtMax.setText(String.valueOf(qlkh.getTieuThuCaoNhat()));
                txtTB.setText(String.valueOf(qlkh.getTieuThuTrungBinh()));
        });
        btGhiFile.addActionListener((e) -> {
            qlkh.GhiHoaDon(FILE_XUAT);
            JOptionPane.showMessageDialog(null,"Xuất hoá đơn thành công ");
            
            
        });
        chkSapXep.addItemListener((e) -> {

            if (chkSapXep.isSelected()) {
                qlkh.sapXepTheoMucTieuThu();
                loadDataToJTable();
            }

        });
    }
    private void loadDataToJTable() {
        model.setRowCount(0);
        for (KhachHang kh : qlkh.getDsKhachHang()) {
            model.addRow(new Object[]{kh.getMaso(), kh.getHoten(), kh.getSonhankhau(),kh.getChisocu(),kh.getChisomoi(), kh.getTieuThu(),kh.getVuotDinhMuc(), kh.tinhTienTra()});
        }
    }

}
