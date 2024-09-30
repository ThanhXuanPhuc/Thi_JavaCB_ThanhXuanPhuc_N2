package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import util.FileHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * Họ tên sinh viên: THÀNH XUÂN PHÚC
 */
public class QLKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public QLKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

   
    public void DocKhachHang(String filename) {
        //sinh viên viết code 
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            dsKhachHang.clear(); // Xóa danh sách cũ trước khi đọc dữ liệu mới
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length == 5) {
                    String maso = tokens[0];
                    String hoten = tokens[1];
                    int loai = Integer.parseInt(tokens[2]);
                    double chisocu = Double.parseDouble(tokens[3]);
                    double chisomoi = Double.parseDouble(tokens[4]);
                    dsKhachHang.add(new KhachHang(maso, hoten, loai, chisocu, chisomoi));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean GhiHoaDon(String filename) {
        //sinh viên viết code 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (KhachHang kh : dsKhachHang) {
                String line = kh.getMaso() + ";" +
                              kh.getHoten() + ";" +
                              kh.getTieuThu() + ";" +
                              kh.tinhTienTra();
                bw.write(line);
                bw.newLine();
            }
            return true; // Xuất thành công
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Xuất thất bại
        }
    }
   
    public void sapXepTheoMucTieuThu() {
        //sinh viên viết code        
        Collections.sort(dsKhachHang, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang kh1, KhachHang kh2) {
                return Integer.compare(kh1.getSonhankhau(), kh2.getSonhankhau());
            }
        });
    }
    
    public double getTieuThuCaoNhat()
    {
      double max=0;
      if (dsKhachHang.isEmpty()) return 0;
        max = dsKhachHang.get(0).getTieuThu();
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() > max) {
                max = kh.getTieuThu();
            }
        }
      return max;
    }
    
    public double getTieuThuThapNhat()
    {
       double min=0;
       if (dsKhachHang.isEmpty()) return 0;
         min = dsKhachHang.get(0).getTieuThu();
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() < min) {
                min = kh.getTieuThu();
            }
        }
       return min;       
    }
    
    public double getTieuThuTrungBinh()
    {
        if (dsKhachHang.isEmpty()) return 0;
        double tongTieuThu = 0;
        for (KhachHang kh : dsKhachHang) {
            tongTieuThu += kh.getTieuThu();
        }
        return tongTieuThu / dsKhachHang.size();
    }
  
}
